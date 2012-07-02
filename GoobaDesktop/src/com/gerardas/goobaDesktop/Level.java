package com.gerardas.goobaDesktop;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;



//COdi dels fitxers:
//	Field :          'xyya.'    x posx  , yy posy,  a tipus
//	inici Monstre :  'mxyyd'    m numero del monstre,  x posx  , yy posy,  d dir



public class Level {


	private static int drcount;
	private static OutputStream fileContent2;
	private static FileHandle handle;
	private static String fileContent;
	private static String pathsave = "Gooba/levels/";
	private static int ci =0;   //comptador per varis usos
	private static int cj =0;   //comptador per varis usos

	private static int nfa,nff,nfj,nfn,nfr;
	private static String gsString;


	public static void calculate_abansPacks(){
		ci=0;
		for(int i = 1; i < HelloWorld.packs+1; i++) { 
			for(int j = 1; j < HelloWorld.levels+1; j++) {

				if(HelloWorld.progres[i][j]){
					ci++;
				}
			}
			HelloWorld.abansPacksInt[i+1]=(16*i)-ci;  //quants queden per desbloquejar el nivell.
			if(ci>=(16*(i)) && !HelloWorld.demo){
				HelloWorld.abansPacks[i+1]=true;
			}

		}


		HelloWorld.abansPacks[HelloWorld.editPack]=true;
	}

	public static void calculate_abans(){

		ci=0;
		for(int i = 1; i < HelloWorld.packs+2; i++) { 
			if(HelloWorld.abansPacks[i]){  //si no es pot accedir al pack, no cal fer cap calcul
				for(int j = 1; j < HelloWorld.levels+1; j++) {
					if(j==1){
						HelloWorld.abans[i][j]=true;
					}else if(i==1 && j==2){
						if (ci==1){
							HelloWorld.abans[i][j]=true;
						}
					}else if(i==1 && j==3){
						if (ci==2){
							HelloWorld.abans[i][j]=true;
						}
					}else if(i==1 && j==4){
						if (ci==3){
							HelloWorld.abans[i][j]=true;
						}
					}else if(i==1 && j==5){
						if (ci==4){
							HelloWorld.abans[i][j]=true;
						}
					}else{
						if (ci>=j-1-(int)((j*3)/HelloWorld.levels)){
							HelloWorld.abans[i][j]=true;
						}

					}


					if(i==HelloWorld.editPack){  //TODO BORRAR, nomes es per poder editar els nivells que no s'han fet
						HelloWorld.abans[i][j]=true;
					}


					if(HelloWorld.progres[i][j]){
						ci++;
					}
				}
			}
			ci=0;
		}



	}




	public static void progress_save(){

		handle = Gdx.files.external(pathsave);	
		if (!handle.exists()){
			handle.mkdirs();
		}


		handle = Gdx.files.external(pathsave + "progres");
		fileContent2 = null;


		try{
			fileContent2 = handle.write(false);
			//assert 0==0;


			for(int i = 1; i < HelloWorld.packs+1; i++) {  
				for(int j = 1; j < HelloWorld.levels+1; j++) {

					if(HelloWorld.progres[i][j]){
						fileContent2.write('1');
					}else{
						fileContent2.write('0');
					}

				}
				fileContent2.write('\n');
			}


			fileContent2.write('*');


			fileContent2.close();
			
        } catch (FileNotFoundException e) {
        	Gdx.app.log("ERROR", "FILE NOT FOUND");
		}catch (IOException e) {
			Gdx.app.log("ERROR", "IO EXCEPTION");
			
		}

		HelloWorld.prefs.putInteger("gs", HelloWorld.goobasSaved);

		calculate_abans();
		calculate_abansPacks();
		calculate_abans();     //deixar aixi

	}

	public static void progress_read(){

		gsString = "";

		handle = Gdx.files.external(pathsave + "progres");
		ci=1;
		cj=1;
		if (handle.exists()){

			fileContent = handle.readString(); 


			try{
				for(int j = 0; j < fileContent.length(); j=j+1) {

					if(ci<=HelloWorld.packs){
						if(fileContent.charAt(j)=='1'){
							HelloWorld.progres[ci][cj]=true;
						}else if(fileContent.charAt(j)=='0'){
							HelloWorld.progres[ci][cj]=false;
						}else if(fileContent.charAt(j)=='\n'){
							ci++;
							cj=0;
						}
						cj++;


					}

				}


			}catch (Exception e) {
				// TODO: handle exception
				

			}
			HelloWorld.goobasSaved=HelloWorld.prefs.getInteger("gs");
		}


	}






	public static void delete_save(){
		handle = Gdx.files.external(pathsave + Integer.toString(HelloWorld.cLevel+20*(HelloWorld.cPack-1))+"s");
		if (handle.exists()){
			handle.delete();
		}

	}
	public static void save() {

		handle = Gdx.files.external(pathsave);	
		if (!handle.exists()){
			handle.mkdirs();
		}


		handle = Gdx.files.external(pathsave + Integer.toString(HelloWorld.cLevel+20*(HelloWorld.cPack-1))+"s");
		fileContent2 = null;


		try{
			fileContent2 = handle.write(false);


			for(int i = 0; i < HelloWorld.cols; i++) {  
				for(int j = 0; j < HelloWorld.rows; j++) {
					if(!HelloWorld.fieldPositions[i][j].fixa && HelloWorld.fieldPositions[i][j].tipus!='b'){
						fileContent2.write(HelloWorld.fieldPositions[i][j].tipus);
						fileContent2.write('-');
						fileContent2.write(Integer.toString(i).charAt(0));
						fileContent2.write(',');
						if (j>=10){
							fileContent2.write(Integer.toString(j).charAt(0));
							fileContent2.write(Integer.toString(j).charAt(1));

						}else{
							fileContent2.write('0');
							fileContent2.write(Integer.toString(j).charAt(0));
						}
						fileContent2.write('.');
						fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].frame).charAt(0));
						fileContent2.write('.');

						if (HelloWorld.fieldPositions[i][j].unitats>=10){
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(0));
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(1));

						}else{
							fileContent2.write('0');
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(0));
						}

						fileContent2.write('.');
						fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].rate).charAt(0));


						fileContent2.write('\n');
					}

				}
			}

			fileContent2.write('*');

			fileContent2.close();
        } catch (FileNotFoundException e) {
        	Gdx.app.log("ERROR", "FILE NOT FOUND");
		}catch (IOException e) {
			Gdx.app.log("ERROR", "IO EXCEPTION");
		}





	}

	public static void menu(boolean principi){
		HelloWorld.speed=2;
		for(int i = 0; i < HelloWorld.cols; i++) {   //reset de field
			for(int j = 0; j < HelloWorld.rows; j++) {
				HelloWorld.fieldPositions[i][j].tipus='b';
				HelloWorld.fieldPositions[i][j].fixa=true;
				HelloWorld.fieldPositions[i][j].gfixa=true;
				HelloWorld.fieldPositions[i][j].frame=0;
				HelloWorld.fieldPositions[i][j].ocupada=false;

			}
		}

		HelloWorld.cLevel=0;
		Level.load(0);
		HelloWorld.handarribar=30;
		HelloWorld.screen=0;
		Boto.reset();
		if(principi){
			HelloWorld.logodt =0;
			HelloWorld.faselogo=0;
		}else{
			HelloWorld.logodt =99;
			HelloWorld.faselogo=1;
		}


	}

	public static void level_choose(){
		HelloWorld.screen=2;
		Level.refresh();
		Boto.reset();
		for(int i = 50; i < HelloWorld.botons.length; i++) { 
			HelloWorld.botons[i].visible=true;
		}
		if(HelloWorld.demo && HelloWorld.cPack==2 && HelloWorld.cPack!=HelloWorld.editPack){HelloWorld.botons[16].visible=true;}

	}


	public static void refresh(){

		HelloWorld.jugant=true;
		HelloWorld.menuant=false;
		HelloWorld.guanyat=false;
		HelloWorld.running=false;
		HelloWorld.girant=false;
		HelloWorld.start=true;
		HelloWorld.mouseaixecat=false;
		HelloWorld.hanarribat=0;
		HelloWorld.adt=0;
		HelloWorld.perdut=false;
		HelloWorld.segur=false;


		Sounds.candausPlayed=false;

		drcount=0;


		Boto.reset();
		//		HelloWorld.botons[4].visible=true;
		HelloWorld.botons[3].visible=true;


		if (!HelloWorld.drags[0].visible){
			HelloWorld.botons[0].visible=false;   //si no hi han drags no cal el boto  	
		}

		for(int j = 0; j < HelloWorld.monstres.length; j++) {   //reset de monstres adt
			HelloWorld.monstres[j].afdt=0;
			HelloWorld.monstres[j].visible=false;
			HelloWorld.monstres[j].alpha=1;
			HelloWorld.fieldPositions[HelloWorld.monstres[j].nx][HelloWorld.monstres[j].ny].ocupada=false;
		}

		Field.trepclaus=0;
		for(int i = 0; i < HelloWorld.cols; i++) {  
			for(int j = 0; j < HelloWorld.rows; j++) {
				HelloWorld.fieldPositions[i][j].ratec=HelloWorld.fieldPositions[i][j].rate;
				HelloWorld.fieldPositions[i][j].obert=0;
				HelloWorld.fieldPositions[i][j].ReqObert  =false;
				HelloWorld.fieldPositions[i][j].ocupada  =false;
				HelloWorld.fieldPositions[i][j].cunitats=HelloWorld.fieldPositions[i][j].unitats;
			}
		}


		for(int i = 0; i < HelloWorld.cols; i++) {
			for(int j = 0; j < HelloWorld.rows; j++) {
				HelloWorld.fieldPositions[i][j].SetTexture();
			}
		}



	}

	public static void chk_numclaus(){
		Field.numclaus=0;
		for(int i = 0; i < HelloWorld.cols; i++) {  
			for(int j = 0; j < HelloWorld.rows; j++) {
				if(HelloWorld.fieldPositions[i][j].tipus=='R'){
					Field.numclaus++;
				}
			}
		}
	}

	public static void load (int num){
		HelloWorld.cLevel=num;

		HelloWorld.screen=1;
		HelloWorld.botons[5].visible=false;
		HelloWorld.editTriarIndex=0;

		refresh();

		HelloWorld.handarribar=0;
		Field.numclaus=0;

		for(int i = 0; i < HelloWorld.cols; i++) {   //reset de field
			for(int j = 0; j < HelloWorld.rows; j++) {
				HelloWorld.fieldPositions[i][j].tipus='b';
				HelloWorld.fieldPositions[i][j].fixa=true;
				HelloWorld.fieldPositions[i][j].gfixa=true;
				HelloWorld.fieldPositions[i][j].frame=0;
				HelloWorld.fieldPositions[i][j].ocupada=false;

			}
		}


		for(int i = 0; i < HelloWorld.drags.length; i++) { 
			HelloWorld.drags[i].visible=false;
			HelloWorld.drags[i].tipus='b';
		}








		handle = Gdx.files.internal("levels/" + Integer.toString(num+20*(HelloWorld.cPack-1)));

		if(num==0){
			handle = Gdx.files.internal("levels/" + Integer.toString(0));
		}


		if (!handle.exists()){
			handle = Gdx.files.internal("levels/editor");
			HelloWorld.editant=true;
		}else{
			HelloWorld.editant=false;
		}

		fileContent = handle.readString(); 


		for(int j = 0; j < fileContent.length()-11; j=j+14) {

			if(fileContent.charAt(j)=='-'){
				HelloWorld.drags[drcount].tipus=fileContent.charAt(j+3);

				if(HelloWorld.drags[drcount].tipus!='b'){HelloWorld.drags[drcount].visible=true;}

				HelloWorld.drags[drcount].unitats=Character.getNumericValue(fileContent.charAt(j+1))*10+Character.getNumericValue(fileContent.charAt(j+2));

				drcount++;

			}else{

				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].tipus=fileContent.charAt(j);
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].fixa=true;
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].gfixa=true;
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].frame=Character.getNumericValue(fileContent.charAt(j+7));
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].unitats=Character.getNumericValue(fileContent.charAt(j+9))*10+Character.getNumericValue(fileContent.charAt(j+10));
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].cunitats=Character.getNumericValue(fileContent.charAt(j+9))*10+Character.getNumericValue(fileContent.charAt(j+10));
				HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].rate=Character.getNumericValue(fileContent.charAt(j+12));

				if (fileContent.charAt(j)=='I' || fileContent.charAt(j)=='J' | fileContent.charAt(j)=='K' || fileContent.charAt(j)=='L'){
					HelloWorld.handarribar=HelloWorld.handarribar+Character.getNumericValue(fileContent.charAt(j+9))*10+Character.getNumericValue(fileContent.charAt(j+10));
				}else if (fileContent.charAt(j)=='R'){
					Field.numclaus++;
				}
			}

		}






		for(int j = 0; j < HelloWorld.drags.length; j++) {
			if (HelloWorld.drags[j].visible){
				HelloWorld.drags[j].SetTexture();
			}
		}

		if (!HelloWorld.drags[0].visible){
			HelloWorld.botons[0].visible=false;   //si no hi han drags no cal el boto  	
		}

		//AIxo de no posar drags, seran com nivells tutorial, el jugador no podra posar cap element, només podra pitjar play i veure que passa.
		//així, podra veure de manera senzilla el funcionament d'algun element nou. Aixi el tutorial es fa en el mateix joc.

		for(int i = 0; i < HelloWorld.drags.length; i++) {
			HelloWorld.drags[i].cunitats=HelloWorld.drags[i].unitats;
		}

		//Open Save

		if(num!=0){   //el menu segur k no te save


			handle = Gdx.files.external(pathsave + Integer.toString(HelloWorld.cLevel+20*(HelloWorld.cPack-1))+"s");

			if (handle.exists()){

				fileContent = handle.readString(); 


				try{
					for(int j = 0; j < fileContent.length()-12; j=j+14) {


						//resta un drag si troba que hi ha una fletxa del mateix tipus guardada
						for(int i = 0; i < HelloWorld.drags.length; i++) {
							if(HelloWorld.drags[i].tipus==fileContent.charAt(j)){ HelloWorld.drags[i].cunitats--;}
						}

						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].tipus=fileContent.charAt(j);
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].frame=Character.getNumericValue(fileContent.charAt(j+7));
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].fixa=false;
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].unitats=Character.getNumericValue(fileContent.charAt(j+9))*10+Character.getNumericValue(fileContent.charAt(j+10));
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].cunitats=Character.getNumericValue(fileContent.charAt(j+9))*10+Character.getNumericValue(fileContent.charAt(j+10));
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].rate=Character.getNumericValue(fileContent.charAt(j+12));
						HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].ratec=Character.getNumericValue(fileContent.charAt(j+12));

						if(fileContent.charAt(j) == 'a'|| fileContent.charAt(j) == 'f' || fileContent.charAt(j) == 'j' || fileContent.charAt(j) == 'n'|| fileContent.charAt(j) == 'r' || fileContent.charAt(j) == 'I' || fileContent.charAt(j) == 'J' || fileContent.charAt(j) == 'K' || fileContent.charAt(j) == 'L'){
							HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].gfixa=false;
						}else{
							HelloWorld.fieldPositions[ Character.getNumericValue(fileContent.charAt(j+2)) ][Character.getNumericValue(fileContent.charAt(j+4))*10+Character.getNumericValue(fileContent.charAt(j+5))].gfixa=true;
						}


					}
				}catch(Error e){
					delete_save();
				}

			}


		}

		Rei.x=-64*HelloWorld.resolution;
		Rei.stage=0;
		Rei.text="";









		refresh();   //reduntant pero important

	}




	public static void editor_send() {
		Level.save();

		HelloWorld.actionResolver.sendEmail(pathsave + Integer.toString(HelloWorld.cLevel+20*(HelloWorld.cPack-1))+"s");

	}











	public static void editor_save() {


		nfa=0;
		nff=0;
		nfj=0;
		nfn=0;
		nfr=0;


		handle = Gdx.files.external(pathsave);	
		if (!handle.exists()){
			handle.mkdirs();
		}


		handle = Gdx.files.external(pathsave + Integer.toString(HelloWorld.cLevel+20*(HelloWorld.cPack-1))+"sE");
		fileContent2 = null;


		try{
			fileContent2 = handle.write(false);


			for(int i = 0; i < HelloWorld.cols; i++) {  
				for(int j = 0; j < HelloWorld.rows; j++) {
					if(!HelloWorld.fieldPositions[i][j].fixa && HelloWorld.fieldPositions[i][j].tipus!='b' && HelloWorld.fieldPositions[i][j].tipus!='a' && HelloWorld.fieldPositions[i][j].tipus!='f' && HelloWorld.fieldPositions[i][j].tipus!='n' && HelloWorld.fieldPositions[i][j].tipus!='j' && HelloWorld.fieldPositions[i][j].tipus!='r'   ){
						fileContent2.write(HelloWorld.fieldPositions[i][j].tipus);
						fileContent2.write('-');
						fileContent2.write(Integer.toString(i).charAt(0));
						fileContent2.write(',');
						if (j>=10){
							fileContent2.write(Integer.toString(j).charAt(0));
							fileContent2.write(Integer.toString(j).charAt(1));

						}else{
							fileContent2.write('0');
							fileContent2.write(Integer.toString(j).charAt(0));
						}
						fileContent2.write('.');
						fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].frame).charAt(0));
						fileContent2.write('.');

						if(HelloWorld.fieldPositions[i][j].tipus=='I' || HelloWorld.fieldPositions[i][j].tipus=='J' ||HelloWorld.fieldPositions[i][j].tipus=='K' ||HelloWorld.fieldPositions[i][j].tipus=='L' ){
							HelloWorld.fieldPositions[i][j].unitats=9;
							HelloWorld.fieldPositions[i][j].rate=1;
						}

						if (HelloWorld.fieldPositions[i][j].unitats>=10){
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(0));
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(1));

						}else{
							fileContent2.write('0');
							fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].unitats).charAt(0));
						}

						fileContent2.write('.');
						fileContent2.write(Integer.toString(HelloWorld.fieldPositions[i][j].rate).charAt(0));


						fileContent2.write('\n');
					}else{
						switch ( HelloWorld.fieldPositions[i][j].tipus) {
						case 'a':
							nfa++;
							break;
						case 'f':
							nff++;
							break;
						case 'j':
							nfj++;
							break;
						case 'n':
							nfn++;
							break;
						case 'r':
							nfr++;
							break;


						}
					}

				}
			}





			if(nfa>0){
				fileContent2.write('-');
				if (nfa>=10){
					fileContent2.write(Integer.toString(nfa).charAt(0));
					fileContent2.write(Integer.toString(nfa).charAt(1));

				}else{
					fileContent2.write('0');
					fileContent2.write(Integer.toString(nfa).charAt(0));
				}
				fileContent2.write('a');

				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');

				fileContent2.write('\n');
			}





			if(nff>0){
				fileContent2.write('-');
				if (nfj>=10){
					fileContent2.write(Integer.toString(nff).charAt(0));
					fileContent2.write(Integer.toString(nff).charAt(1));

				}else{
					fileContent2.write('0');
					fileContent2.write(Integer.toString(nff).charAt(0));
				}
				fileContent2.write('f');

				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');

				fileContent2.write('\n');
			}




			if(nfj>0){
				fileContent2.write('-');
				if (nfj>=10){
					fileContent2.write(Integer.toString(nfj).charAt(0));
					fileContent2.write(Integer.toString(nfj).charAt(1));

				}else{
					fileContent2.write('0');
					fileContent2.write(Integer.toString(nfj).charAt(0));
				}
				fileContent2.write('j');

				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');

				fileContent2.write('\n');
			}




			if(nfn>0){
				fileContent2.write('-');
				if (nfn>=10){
					fileContent2.write(Integer.toString(nfn).charAt(0));
					fileContent2.write(Integer.toString(nfn).charAt(1));

				}else{
					fileContent2.write('0');
					fileContent2.write(Integer.toString(nfn).charAt(0));
				}
				fileContent2.write('n');

				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');

				fileContent2.write('\n');
			}



			if(nfr>0){
				fileContent2.write('-');
				if (nfr>=10){
					fileContent2.write(Integer.toString(nfr).charAt(0));
					fileContent2.write(Integer.toString(nfr).charAt(1));

				}else{
					fileContent2.write('0');
					fileContent2.write(Integer.toString(nfr).charAt(0));
				}
				fileContent2.write('r');

				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');
				fileContent2.write('.');

				fileContent2.write('\n');
			}




			fileContent2.write('*');

			fileContent2.close();
        } catch (FileNotFoundException e) {
        	Gdx.app.log("ERROR", "FILE NOT FOUND");
		}catch (IOException e) {
			Gdx.app.log("ERROR", "IO EXCEPTION");
		}





	}

}