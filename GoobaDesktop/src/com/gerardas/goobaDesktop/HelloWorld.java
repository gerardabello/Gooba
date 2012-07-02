package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;


//TODO fer editor que pugui em enviar propostes a mi, i les pugui afegir en una seguent actualitzacio



public class HelloWorld implements InputProcessor, Screen {

	public static final boolean demo=false;

	public static boolean negrecompra=false;

	public static boolean editorPack=false;

	public static int editPack=5; //packs+1
	public static int packs = 4;
	public static int levels = 20;
	public static boolean[][] progres;
	public static boolean[][] abans;
	public static boolean[] abansPacks;
	public static int[] abansPacksInt;
	public static float[] abansPacksTrans;

	public static String welcomeStr;
	public static int goobasSaved;


	public static int rows = 12;
	public static int cols = 7;
	public static int ndrags = 40;   //numero maxim de peces diferents que es poden tenir
	public static int cLevel = 1;
	public static int cPack = 1;

	public static int nmonstres = 84;
	public static int nbocatas = 5;

	public static int nmonstresA = 0;

	public static int panellHeight;

	public static int speed = 2;

	public static int screen = 0;

	//0 menu
	//1 jugant


	private int fumframe=0;
	private int fumcolor=0;

	public static int bototouched=999;

	public static float 		logodt =0;
	public static int 		faselogo=0;

	public static long     bench;

	public static BitmapFont font;
	public static BitmapFont fontn;

	//Declarar textures

	private Texture         grassTexture;
	private Texture         liniesTexture;
	private Texture         shadowsTexture;
	private Texture         panellTexture;
	private Texture         negreTexture;
	private Texture         triarTexture;
	private Texture         guanyatTexture;
	private Texture         logoTexture;
	private Texture         starTexture;
	private Texture         sombraStartTexture;
	private Texture         packsTitleTexture;


	public static float transparenciaTele = 0;

	private TextureRegion   currentscreen;
	private TextureRegion   nextscreen;


	private Pixmap 			linies;

	private Texture         aniTexture;
	private int             anicolor;




	public static boolean   mouseaixecat;   //per controlar si s'a aixecat el dit abans de tornar a clicar un altre boto;

	public static int     mouseX,mouseY;

	//Declarar monstres
	public static Monster[]       monstres;

	public static Boto[]       botons;

	public static Drag[]       drags;


	private int      ci,cj;   //contadors
	private float   dttriant;
	private int     currenttriant;
	private int     currenttriant2;
	private int     currenttriantx;
	private int     currenttrianty;


	private int     currentcolocx;
	private int     currentcolocy; 
	private int     currentcolocyP; 
	private int     currentcolocTexx;
	private int     currentcolocTexy;
	private boolean currentcolocV;
	private char    currentcolocTipus;
	private int    currentcolocDir;



	private int girarOx,girarOy,girarOpx,girarOpy;
	public static boolean   girant;
	private float 				girantdt;


	public static SpriteBatch     batch;
	public static Field[][] 	 fieldPositions;            
	public static int[][]        cuadrats;

	private boolean          trobat;

	public static int 			resolution;

	public static int       hanarribat;
	public static int       handarribar;

	private int 				temp;
	private int 				temp2;
	private int 				temp3;




	private float 				dt;
	public static float 		adt;


	private int dx,dy;  //distancies entre dos posicions field

	private boolean resolucioMala;

	private boolean eliminantColo;

	public static int nDrawsMenu;

	public static int posMenu;




	public static boolean    running;

	public static boolean    colocant;

	public static boolean    jugant;

	public static boolean    menuant;

	public static boolean    guanyat;
	public static boolean    perdut;
	public static boolean    segur;


	public static boolean    draging;
	public static int dragOffset2,dragOffset,LdragOffset,dragPosition,LdragPosition;
	public static double dragV,dragA;

	public static boolean transition;
	public static boolean transitioni;
	public static float transitiondt;
	public static int transitiondir;
	public static float transitionspeed;

	public static boolean    start;
	public static boolean    editant=false;
	public static boolean    tick;
	public static boolean    htick;
	public static boolean    htickd;

	public static int editTriarIndex=1;

	public static int nombreMonstresVisibles=0;

	public static Preferences prefs;

	public static ActionResolver actionResolver;

	public static boolean help;
	public static boolean editorFT;


	public interface ActionResolver {

		public void showToast(CharSequence message, int toastDuration);
		public void showAlertBox(String alertBoxTitle, String alertBoxMessage, String alertBoxButtonText);
		public void showConfirmAlert(String alertBoxTitle, String alertBoxMessage, String positiveButton, String negativeButton);
		public void openUri(String uri);
		public void confirmexit();
		public void sendEmail(String file);
	}


	//	public HelloWorld(ActionResolver act){
	//		HelloWorld.actionResolver = act;
	//	}




	public HelloWorld(){

	


		prefs = Gdx.app.getPreferences("gooba-preferences");
		Sounds.mute=false; 
		goobasSaved=0;
		editorFT=false;
		try{
			Sounds.mute=prefs.getBoolean("mute");
			HelloWorld.prefs.getInteger("gs");
			editorFT=prefs.getBoolean("EFT");
		}finally{

		}
		
		prefs.putBoolean("EFT", editorFT);
		HelloWorld.prefs.flush();


		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

		//BAtch
		batch = new SpriteBatch(64);   //TODO set size

		dt=0;




		Strings.setStrings(0);
		Sounds.ini_sounds();



		resolucioMala = false;
		//Decidir mida de les textures
		if (Gdx.graphics.getWidth()+Gdx.graphics.getHeight()<760){
			resolution=1;
			resolucioMala = true;
		}else if (Gdx.graphics.getWidth()+Gdx.graphics.getHeight()<1200){
			resolution=1;
		}else{
			resolution=2;
		}


		posMenu=Gdx.graphics.getHeight()/2+((64+64*4)*resolution)/2;




		panellHeight=64*resolution;
		htickd=true;
		transition=false;

		//Carregar textures
		grassTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/grass.png"));
		panellTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/panell.png"));
		triarTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/triar.png"));
		guanyatTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/guanyat.png"));
		shadowsTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/sombres.png"));
		aniTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/animacio.png"));
		logoTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/titol.png"));
		starTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/star.png"));
		sombraStartTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/botoSS.png"));
		packsTitleTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/packs.png"));



		Rei.texture =new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/king.png"));
		Rei.textureb=new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/bocata.png"));
		Rei.puntexture=new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/high.png"));


		Boto.textureB = new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/botons.png"));

		Monster.mTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/monstres.png"));


		negreTexture = new Texture(Gdx.files.internal("img/1/negre.png"));

		font=new BitmapFont(Gdx.files.internal("font/"+Integer.toString(resolution)+"/pix.fnt"),Gdx.files.internal("font/"+Integer.toString(resolution)+"/pix.png"), false);
		fontn=new BitmapFont(Gdx.files.internal("font/"+Integer.toString(resolution)+"/und.fnt"),Gdx.files.internal("font/"+Integer.toString(resolution)+"/und.png"), false);






		//TExtures tiles
		Field.mTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/tiles.png"));
		Field.rTexture = new TextureRegion(Field.mTexture);
		Field.raTexture=Field.rTexture.split(32*resolution, 32*resolution);




		//Drags

		drags=new Drag[ndrags];
		for(int i = 0; i < drags.length; i++) { 
			drags[i]= new Drag('b');
		}


		//Progres

		progres=new boolean[packs+2][levels+1];
		for(int i = 1; i < progres.length; i++) { 
			for(int j = 1; j < progres[0].length; j++) { 
				progres[i][j]= false;
			}
		}
		goobasSaved=0;

		//abans

		abans=new boolean[packs+2][levels+1];
		for(int i = 1; i < abans.length; i++) { 
			for(int j = 1; j < abans[0].length; j++) { 
				abans[i][j]= false;
			}
		}

		//abansPacks

		abansPacks=new boolean[packs+2];
		for(int i = 1; i < abansPacks.length; i++) { 
			if(i!=1 && i!=HelloWorld.packs+1){
				abansPacks[i]=false;
			}else{
				abansPacks[i]=true;
			}
		}




		//abansPacksInt

		abansPacksInt=new int[packs+2];
		for(int i = 1; i < abansPacksInt.length; i++) { 

			abansPacksInt[i]=0;

		}






		//Botons

		botons=new Boto[Boto.nbotons];

		for(int i = 0; i < botons.length; i++) { 
			botons[i]= new Boto(i);
		}



		//Field
		fieldPositions = new Field[cols][rows];

		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				fieldPositions[i][j] = new Field(i,j,'b'  );
			}
		}



		//linies
		cuadrats = new int[cols][rows];

		cuadrats[0][0]=0;

		linies = new Pixmap (MathUtils.nextPowerOfTwo(Gdx.graphics.getWidth()),MathUtils.nextPowerOfTwo( Gdx.graphics.getHeight()), Pixmap.Format.RGBA8888);
		linies.setColor(0, 0, 0, 1);
		dx=(int)(fieldPositions[1][0].posicio.x-fieldPositions[0][0].posicio.x);
		dy=(int)(fieldPositions[0][1].posicio.y-fieldPositions[0][0].posicio.y);

		for(int i = 1; i < cols; i++) {		
			linies.fillRectangle(i*(Gdx.graphics.getWidth()/HelloWorld.cols), +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][rows-1].posicio.y+dy/2)), resolution , (int)(fieldPositions[0][rows-1].posicio.y-fieldPositions[0][0].posicio.y+dy));		
			cuadrats[0][i]=i*(Gdx.graphics.getWidth()/HelloWorld.cols)+1;
		}

		for(int i = 0; i < rows; i++) {		
			linies.fillRectangle(0, +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][i].posicio.y-dy/2)), Gdx.graphics.getWidth() , resolution);		
			cuadrats[1][i]=+8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][i].posicio.y-dy/2));
			cuadrats[1][i]=-cuadrats[1][i]+Gdx.graphics.getHeight()-1;
		}
		linies.fillRectangle(0, +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][rows-1].posicio.y+dy/2)), Gdx.graphics.getWidth() , resolution);

		liniesTexture= new Texture(linies);
		linies.dispose();








		//Crear Monstres
		monstres = new Monster[nmonstres];
		for(int j = 0; j < monstres.length; j++) {
			monstres[j] = new Monster(j);
		}

		Boto.update();

		Level.progress_read();

		Level.calculate_abansPacks();

		Level.calculate_abans();


		//abansPacksTrans

		abansPacksTrans=new float[packs+2];
		for(int i = 1; i < abansPacksTrans.length; i++) { 
			if(abansPacks[i]){
				abansPacksTrans[i]=0;
			}else{
				abansPacksTrans[i]=1;
			}
		}


		//		for (int i =61; i <= 80; i++) {
		//			Level.load(i);
		//			
		//			Level.editor_save();
		//		}
		//		
		//


		//Demo stuff
		if(demo){HelloWorld.packs=2;}
		
		
		Level.menu(true);


	}


	@Override public void render (float DeltaTimeNo) {
		if (!resolucioMala){

			if(screen==0 || screen ==2){
				if(Gdx.graphics.getFramesPerSecond()>=10){
					Sounds.play_loop1();
				}
			}

			Sounds.begin();
			if (running && Gdx.graphics.getDeltaTime()<0.1){dt=Gdx.graphics.getDeltaTime();}else{dt=0;} //les coses que depen de si el joc esta corrent o no, els hi passem el dt, a la resta, deltatime directament


			mouseX=Gdx.input.getX(0);
			mouseY=Gdx.graphics.getHeight()-Gdx.input.getY(0);


			adt=adt+dt*speed;	
			if (adt>=1){
				adt=adt-1;
				tick=true;
				htickd=true;
				//	Gdx.app.log("FPS:", Integer.toString(Gdx.graphics.getFramesPerSecond()) );

			}else if(adt>=0.5){
				if(htickd){
					htickd=false;
					htick=true;




				}else{
					htick=false;
					reset_fields();
				}
			}


			bench=System.nanoTime();
			switch (screen) {
			case 0: //Main_Menu
				update_buttons();
				update_monsters(); 
				break;

			case 2: //Level choose
				update_touch_level();
				update_drag_levels();
				update_buttons();
				break;

			case 1:  //Game
				update_touch_game();
				update_buttons();
				update_fields(); 
				update_monsters();
				if(!editant){update_score();}
				if (!menuant && !segur && !help){Rei.update();}
				Sounds.update_walking(dt*speed/2);
				break;
			}







			//		if (transitioni){
			//			currentscreen= ScreenUtils.getFrameBufferTexture(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			//			
			//		}

			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			batch.begin();  


			//Draws
			if (!transition){
				switch (screen) {
				case 0: //Main_Menu
					draw_grass();
					//					draw_tiles();
					draw_monsters();  
					draw_logo();
					draw_buttons();
					break;

				case 2: //Level choose
					draw_grass();
					draw_buttons();
					draw_number_levels();
					break;

				case 1:  //Game

					draw_grass();

					draw_score();

					draw_lines();

					draw_tiles();

					if(running){draw_monsters();}

					draw_fums();




					draw_panel();

					if (!running){draw_triarui();}

					if(perdut){draw_perdut();}

					if(colocant){draw_coloc();}

					if(segur){draw_segur();}
					if(help){draw_help();}

					if (menuant){draw_menuui();}
					if (guanyat){draw_guanyatui();}


					draw_buttons();
					draw_rei();
					break;
				}

			}

			//		draw_transition();


			if(negrecompra){   //es crida una vegada just abans de que surti la pantalla de "Browser o Market", ja que al tornar tarda una mica i aixi hi ha una manera visual de veure que encara no esta llest el joc
				for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
					for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
						batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
					}
				}
				negrecompra=false;
			}


			batch.end();  
			//Gdx.app.log("Batch", Integer.toString(batch.renderCalls) );
			Sounds.end();

			tick=false;
		}else{

			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			batch.begin();  

			draw_grass();


			for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
				for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
					batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
				}
			}



			batch.draw(triarTexture,Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+panellHeight/2);

			//		font.draw(batch, Strings.level+" "+Integer.toString(cLevel), Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution+font.getCapHeight());
			//		font.draw(batch, Strings.complete                          , Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+70*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution-26*resolution+font.getCapHeight());
			font.drawWrapped(batch, Strings.resolution, Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+triarTexture.getHeight()-135*resolution,185*resolution);



			batch.end();


		}
	}



	public void update_touch_level(){


		//Drag
		if(screen==2 && !editorPack){
			if(Gdx.input.justTouched()){
				girarOx=mouseX; //reutilitzo aquestes variables
				girarOy=mouseY;


			}else if(Gdx.input.isTouched() ){
				if(Math.sqrt((girarOx-mouseX)*(girarOx-mouseX)+(girarOy-mouseY)*(girarOy-mouseY))>15*resolution ){
					if(!draging){
						dragOffset2=dragOffset;
					}
					draging=true;
					bototouched=999;




					dragPosition=(int)(dragOffset/Gdx.graphics.getWidth());
					if(dragOffset>0){
						dragPosition=dragPosition+1;
					}else{
						dragPosition=dragPosition-1;
					}


				}
				if(draging){
					LdragOffset=dragOffset;
					dragOffset=-girarOx+mouseX+dragOffset2;


					if(Math.abs(dragOffset)>=Gdx.graphics.getWidth()){  //fa que no puguis arrastrar més del compte, dificil, pero es pot fer. El que fa es que si arrastres mes del compte simplement desactiva el draging

						if (dragOffset>(Gdx.graphics.getWidth()/2) && cPack!=1){
							cPack--;
						}else if (dragOffset<(-Gdx.graphics.getWidth()/2) && cPack!=packs){
							cPack++;
						}
						if(HelloWorld.demo && HelloWorld.cPack==2 && HelloWorld.cPack!=HelloWorld.editPack){
							HelloWorld.botons[16].visible=true;
						}else{
							HelloWorld.botons[16].visible=false;	
						}
						dragV=0;
						dragOffset=0;
						dragOffset2=0;
						dragA=0;
						dragPosition=99;
						LdragPosition=99;

						girarOx=mouseX; 
						girarOy=mouseY;

					}

					if((cPack==packs&&dragOffset<0)||(cPack==1&&dragOffset>0)){
						dragOffset=dragOffset/2;
					}
					if(dragOffset>=Gdx.graphics.getWidth()){
						dragOffset=Gdx.graphics.getWidth()-1;
					}
					dragV=((dragV*4)+((dragOffset-LdragOffset)/Gdx.graphics.getDeltaTime()))/5;

				}
			}else if (!Gdx.input.isTouched() && draging ){
				draging=false;

			}
		}



	}


	public void update_buttons(){

		if (Gdx.input.justTouched() && !colocant){
			for(int i = 0; i < botons.length; i++) { 
				if (botons[i].visible){	 
					if (mouseX>=botons[i].x+(4*resolution)*((i!=15)? 1 : 0)   &&   mouseX<=botons[i].x+botons[i].w-(4*resolution)*((i!=15)? 1 : 0)  && mouseY>=botons[i].y+(4*resolution)*((i!=15)? 1 : 0)   &&   mouseY<=botons[i].y+botons[i].h-(4*resolution)*((i!=15)? 1 : 0)){	        				        	
						if(i!=17){  //botons que no es poden tocar
							bototouched=i;
							break;
						}
					}
				}

			}
		}


		if(Gdx.input.isTouched() && bototouched==18){
			if(mouseX<=botons[17].x+(32*resolution)){
				speed=1;
				botons[18].x=botons[17].x-3*resolution;
			}else if(mouseX<=botons[17].x+(64*resolution)){
				speed=2;
				botons[18].x=botons[17].x+((32-3)*resolution);
			}else if(mouseX<=botons[17].x+(96*resolution)){
				speed=3;
				botons[18].x=botons[17].x+((64-3)*resolution);
			}else if(mouseX<=botons[17].x+(128*resolution)){
				speed=4;
				botons[18].x=botons[17].x+((96-3)*resolution);
			}else{
				speed=5;
				botons[18].x=botons[17].x+((128-3)*resolution);
			}

		}




		if (!Gdx.input.isTouched() && !colocant && bototouched!=999 && dragOffset==0){
			for(int i = 0; i < botons.length; i++) { 
				if (botons[i].visible){	 
					if (mouseX>=botons[i].x   &&   mouseX<=botons[i].x+botons[i].w  && mouseY>=botons[i].y   &&   mouseY<=botons[i].y+botons[i].h){	        				        	
						if (i==bototouched){
							botons[i].touched();
							bototouched=999;  //valor que indica que no hi ha cap boto pitjat
						}
						break;

					}
				}

			}
			bototouched=999;
		}
	}

	public void update_touch_game() {

		//nomes per assegurar-se

		if (colocant || menuant){
			jugant=false;
		}else{
			jugant=true;
		}




		//posicio que esta mes aprop del cursor


		for(int i = 1; i < cols; i++) {		
			if (cuadrats[0][i]>mouseX){
				currentcolocx=i-1;
				break;
			}
			currentcolocx=cols-1;
		}

		for(int i = rows-1; i >= 0; i--) {		
			if (cuadrats[1][i]<(mouseY)){
				currentcolocy=i;
				break;
			}
		}


		for(int i = rows-1; i >= 0; i--) {		//posicio un pel mes amunt per el pal
			if (cuadrats[1][i]<(mouseY+35*HelloWorld.resolution)){
				currentcolocyP=i;
				break;
			}
		}



		if (mouseY<fieldPositions[0][0].posicio.y-dy*1.2){
			currentcolocV=false;
		}else{
			currentcolocV=true;
		}


		if(colocant && mouseY<panellHeight && (mouseY+45*HelloWorld.resolution)<cuadrats[1][0]-dy/4){
			eliminantColo=true;
		}else{
			eliminantColo=false;
		}


		if (currentcolocyP<0){
			currentcolocyP=0;

		}else if (currentcolocyP>=rows){
			currentcolocyP=rows-1;

		}





		if (start && jugant){   //Girar una fletxa

			if (Gdx.input.isTouched(0)){

				if(currentcolocV && !girant && !fieldPositions[currentcolocx][currentcolocy].fixa){
					girant=true;
					girarOpx=mouseX;
					girarOpy=mouseY;
					girarOx=currentcolocx;
					girarOy=currentcolocy;
					girantdt=0;
				}
				if (girant){
					if (Math.sqrt((girarOpx-mouseX)*(girarOpx-mouseX)+(girarOpy-mouseY)*(girarOpy-mouseY))>25*resolution && fieldPositions[girarOx][girarOy].fixa==false && fieldPositions[girarOx][girarOy].gfixa==false ){
						girantdt=0;
						if (Math.abs(girarOpx-mouseX)>Math.abs(girarOpy-mouseY)){
							if (girarOpx-mouseX>0){
								fieldPositions[girarOx][girarOy].frame=2;
							}else{
								fieldPositions[girarOx][girarOy].frame=3;
							}

						}else{
							if (girarOpy-mouseY>0){
								fieldPositions[girarOx][girarOy].frame=0;
							}else{
								fieldPositions[girarOx][girarOy].frame=1;
							}

						}

					}
					if(girantdt>=0.16 && fieldPositions[girarOx][girarOy].fixa==false && currentcolocV){
						colocant=true;
						girant=false;
						girantdt=0;

						currentcolocTipus=fieldPositions[girarOx][girarOy].tipus;
						currentcolocTexx =fieldPositions[girarOx][girarOy].texx;
						currentcolocTexy=fieldPositions[girarOx][girarOy].texy+fieldPositions[girarOx][girarOy].frame;
						currentcolocDir=fieldPositions[girarOx][girarOy].frame;
						fieldPositions[girarOx][girarOy].tipus='b';
						fieldPositions[girarOx][girarOy].fixa=true;
						fieldPositions[girarOx][girarOy].SetTexture();

					}
					girantdt=girantdt+Gdx.graphics.getDeltaTime();
				}

			}else{

				girant=false;
				girantdt=0;
			}

		}






		if (colocant){   //estar colocant un field
			if (Gdx.input.isTouched(0)){
				//continuar
			}else{
				colocant=false;
				if (fieldPositions[currentcolocx][currentcolocyP].tipus=='b' && !eliminantColo){
					fieldPositions[currentcolocx][currentcolocyP].fixa=false;
					fieldPositions[currentcolocx][currentcolocyP].tipus=currentcolocTipus;

					fieldPositions[currentcolocx][currentcolocyP].SetTexture();
					fieldPositions[currentcolocx][currentcolocyP].frame=currentcolocDir; //despres del texture, pk el texture li dona una direccio 0;

					if(fieldPositions[currentcolocx][currentcolocyP].tipus!='a' && fieldPositions[currentcolocx][currentcolocyP].tipus!='b' && fieldPositions[currentcolocx][currentcolocyP].tipus!='f' && fieldPositions[currentcolocx][currentcolocyP].tipus!='j' && fieldPositions[currentcolocx][currentcolocyP].tipus!='n' && fieldPositions[currentcolocx][currentcolocyP].tipus!='r' && fieldPositions[currentcolocx][currentcolocyP].tipus!='I' && fieldPositions[currentcolocx][currentcolocyP].tipus!='J' && fieldPositions[currentcolocx][currentcolocyP].tipus!='K'&& fieldPositions[currentcolocx][currentcolocyP].tipus!='L'){
						fieldPositions[currentcolocx][currentcolocyP].gfixa=true;
						fieldPositions[currentcolocx][currentcolocyP].frame=0;
					}else{
						fieldPositions[currentcolocx][currentcolocyP].gfixa=false;

						//si es un spawn li dona unitats per poder provar el nivell
						if (fieldPositions[currentcolocx][currentcolocyP].tipus=='I' || fieldPositions[currentcolocx][currentcolocyP].tipus=='J' || fieldPositions[currentcolocx][currentcolocyP].tipus=='K'|| fieldPositions[currentcolocx][currentcolocyP].tipus=='L'){
							fieldPositions[currentcolocx][currentcolocyP].rate=1;
							fieldPositions[currentcolocx][currentcolocyP].ratec=1;
							fieldPositions[currentcolocx][currentcolocyP].unitats=9;
							fieldPositions[currentcolocx][currentcolocyP].cunitats=9;
						}



					}

				}else{
					//que passa quan es deixa anar en una posició ocupada? Es suma el drag i es perd el field
					for(int i = 0; i < HelloWorld.drags.length; i++) {
						if(HelloWorld.drags[i].tipus==currentcolocTipus){ HelloWorld.drags[i].cunitats++;}
					}
				}
			}
		}







		if (Gdx.input.isTouched(0) && !running){  //triar
			if (!colocant){






				if(mouseX<Gdx.graphics.getWidth()-64*resolution && Math.abs(mouseY-HelloWorld.panellHeight/2)< 32*resolution){
					currenttriant2=(mouseX)/((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5);
				}else{
					currenttriant2=39;
				}

				if (Gdx.input.justTouched()){
					currenttriant=currenttriant2+editTriarIndex*5;
				}


				if(editant){

					currenttriant-=editTriarIndex*5;   //transformacio per a aquest tros de codi
					if(!colocant && currenttriant2<39 && currenttriant<39 && Math.abs(currenttriant-((mouseX)/((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5)))>1){

						if(currenttriant-((mouseX)/((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5))<0){
							if(editTriarIndex>0){editTriarIndex--;}
							currenttriant=((mouseX)/((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5));

						}else{
							if(editTriarIndex<6){editTriarIndex++;}
							currenttriant=((mouseX)/((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5));

						}
						//currenttriant=39;
					}
					currenttriant+=editTriarIndex*5;  //desfem la transformacio
				}



				if (currenttriant<ndrags && drags[currenttriant].visible && drags[currenttriant].cunitats>0){
					if(mouseY>HelloWorld.panellHeight){

						colocant=true;
						currenttriant2=39;

						drags[currenttriant].cunitats--;
						currentcolocTexx=drags[currenttriant].texx;
						currentcolocTexy=drags[currenttriant].texy;
						currentcolocTipus=drags[currenttriant].tipus;
						currentcolocDir=0;
					}

				}





			}

		}else{
			currenttriant2=39;
		}











	}


	public void reset_fields(){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				fieldPositions[i][j].ReqObert=false;

			}
		}
	}


	public void update_fields(){
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				fieldPositions[i][j].Update(Gdx.graphics.getDeltaTime());

			}
		}
	}

	public void update_monsters(){

		for(int j = 0; j < monstres.length; j++) {
			if (monstres[j].visible){
				monstres[j].update(dt*speed/2);
			}
		}


		if (tick){
			for(int j = 0; j < monstres.length; j++) {
				for(int i = 0; i < monstres.length; i++) {
					if (monstres[i].visible && monstres[j].visible  && i!=j && !monstres[i].mort && !monstres[j].mort){
						if (  monstres[j].cx==monstres[i].cx && monstres[j].cy==monstres[i].cy ){
							monstres[j].state=3;
							monstres[i].state=3;
							HelloWorld.perdut=true;
							Sounds.play_cop();
							monstres[i].xocaran=false;
							monstres[j].xocaran=false;
						}
					}
				}
			}



			for(int j = 0; j < monstres.length; j++) {
				for(int i = 0; i < monstres.length; i++) {
					if (monstres[i].visible && monstres[j].visible  && i!=j && !monstres[i].mort && !monstres[j].mort){


						if (monstres[i].dir==0 && monstres[j].dir==1 && monstres[i].cx == monstres[j].cx && monstres[i].cy-1 == monstres[j].cy && monstres[j].state!=3 && monstres[i].state!=3){
							monstres[i].xocaran=true;
							monstres[j].xocaran=true;
							monstres[j].xocposition.set(monstres[j].position);
							monstres[i].xocposition.set(monstres[i].position);

							//crec que aquests 2 no calen.

							//						}else if (monstres[j].dir==0 && monstres[i].dir==1 && monstres[j].cx == monstres[i].cx && monstres[j].cy-1 == monstres[i].cy && monstres[j].state!=3 && monstres[i].state!=3){
							//							monstres[i].xocaran=true;
							//							monstres[j].xocaran=true;
							//							monstres[j].xocposition.set(monstres[j].position);
							//							monstres[i].xocposition.set(monstres[i].position);
							//							Gdx.app.log("XOC,b", Integer.toString(i)+"--"+Integer.toString(j));
							//						}else if (monstres[i].dir==2 && monstres[j].dir==3 && monstres[i].cx-1 == monstres[j].cx && monstres[i].cy == monstres[j].cy && monstres[j].state!=3 && monstres[i].state!=3){
							//							monstres[i].xocaran=true;
							//							monstres[j].xocaran=true;
							//							monstres[j].xocposition.set(monstres[j].position);
							//							monstres[i].xocposition.set(monstres[i].position);
							//							Gdx.app.log("XOC,c", Integer.toString(i)+"--"+Integer.toString(j));
						}else if (monstres[j].dir==2 && monstres[i].dir==3 && monstres[j].cx-1 == monstres[i].cx && monstres[j].cy == monstres[i].cy && monstres[j].state!=3 && monstres[i].state!=3){
							monstres[i].xocaran=true;
							monstres[j].xocaran=true;
							monstres[j].xocposition.set(monstres[j].position);
							monstres[i].xocposition.set(monstres[i].position);
						}
					}
				}
			}
		}


		//tornem a mirar que cap monstre vagi a xocar amb un candau que ja ha baixat

		for(int j = 0; j < monstres.length; j++) {
			if (monstres[j].xocaracandau){
				if (Field.trepclaus>=Field.numclaus){
					monstres[j].xocaran=false;
					monstres[j].xocarasol=false;
				}
				monstres[j].xocaracandau=false;
			}

		}

	}

	public void update_score(){

		if (hanarribat>=handarribar && !guanyat && cLevel!=0){
			Sounds.play_finish();
			guanyat=true;

			if(!progres[cPack][cLevel]){
				progres[cPack][cLevel]=true;
				goobasSaved+=handarribar;
			}
			Level.progress_save();
			running=false;
			Boto.reset();
			botons[11].visible=true;
			if(!(cLevel==levels && cPack ==packs)){botons[12].visible=true;}
		}
	}

	public void draw_grass(){
		batch.disableBlending();

		for(int i = 0; i <= (Gdx.graphics.getWidth()/grassTexture.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/grassTexture.getHeight()); j++) {
				batch.draw(grassTexture,grassTexture.getWidth()*i ,grassTexture.getHeight()*j ); 
			}
		}

		batch.enableBlending();
	}

	private void draw_lines() {
		if(colocant){
			batch.setColor(1, 1, 1,0.6f );
		}else{
			batch.setColor(1, 1, 1,0.3f );

		}
		batch.draw(liniesTexture,0,Gdx.graphics.getHeight()-MathUtils.nextPowerOfTwo(Gdx.graphics.getHeight()));
		batch.setColor(Color.WHITE );
		// TODO pregenerar la textura i colocarla sobre el grass, ja que dibuixarho transparent cada frame es molt costós. No es gaire important
	}

	public void draw_rei(){
		if (!menuant && !segur){
			if(Rei.visible){

				batch.draw(Rei.texture,
						Rei.x*resolution,
						62*resolution,
						64*resolution*Rei.texx,
						64*resolution*Rei.texy,
						64*resolution,
						64*resolution);


			}
			if(Rei.bvisible && Rei.x==0 ){
				batch.draw(Rei.textureb, 0, 64*resolution);
				fontn.drawWrapped(batch, Rei.text,92*resolution, (64+82)*resolution,128*resolution);
			}

			if (Rei.punV){
				batch.setColor(1, 1, 1,  (float)((1+(Math.sin(Rei.pundt*Math.PI*2)))/2));
				batch.draw(Rei.puntexture, Rei.punX-Rei.puntexture.getWidth()/2, Rei.punY-Rei.puntexture.getHeight()/2);
				batch.setColor(Color.WHITE);
			}
		}
	}


	public void draw_fums(){
		if(transparenciaTele>Gdx.graphics.getDeltaTime()){
			transparenciaTele=transparenciaTele-Gdx.graphics.getDeltaTime();
		}else{
			transparenciaTele=0;
		}


		for(int i = cols-1; i >=0; i--) {
			for(int j = rows-1; j>=0; j--) {
				if (fieldPositions[i][j].tipus=='T'){
					batch.setColor(1, 1, 1, transparenciaTele);
					batch.draw(aniTexture,
							fieldPositions[i][j].posicio.x-(Field.raTexture[0][0].getRegionWidth()/2),
							fieldPositions[i][j].posicio.y-(Field.raTexture[0][0].getRegionHeight()/2)-(4*resolution),
							(int)Field.raTexture[0][0].getRegionWidth()*0,
							(int)Field.raTexture[0][0].getRegionHeight()*(15),
							(int)Field.raTexture[0][0].getRegionWidth(),
							(int)Field.raTexture[0][0].getRegionHeight());
					batch.setColor(Color.WHITE);
				}




				if (fieldPositions[i][j].tipus=='w' || fieldPositions[i][j].tipus=='x' || fieldPositions[i][j].tipus=='y' || fieldPositions[i][j].tipus=='z' ){
					if (fieldPositions[i][j].obert==1){
						if(htick){Sounds.play_sprai();}


						fumframe=(int)((adt-0.5)*8);
						if (fumframe<0){
							fumframe=fumframe+8;
						}


						if(fieldPositions[i][j].tipus=='w'){
							fumcolor=0;
						}else if(fieldPositions[i][j].tipus=='x'){
							fumcolor=1;
						}else if(fieldPositions[i][j].tipus=='y'){
							fumcolor=2;
						}else if(fieldPositions[i][j].tipus=='z'){
							fumcolor=3;
						}

						batch.draw(aniTexture,
								fieldPositions[i][j].posicio.x-(Field.raTexture[0][0].getRegionWidth()/2),
								fieldPositions[i][j].posicio.y-(Field.raTexture[0][0].getRegionHeight()/2)-(4*resolution)+fumframe*resolution,
								(int)Field.raTexture[0][0].getRegionWidth()*fumframe,
								(int)Field.raTexture[0][0].getRegionHeight()*(8+fumcolor),
								(int)Field.raTexture[0][0].getRegionWidth(),
								(int)Field.raTexture[0][0].getRegionHeight());
					}
				}
			}
		}
	}


	public void draw_number_levels(){



		for(int i = 1; i < packs+1; i++) { 
			if(i==cPack&&abansPacks[i]&&abansPacksTrans[i]>0){
				abansPacksTrans[i]-=Gdx.graphics.getDeltaTime()*2;
			}

			if(abansPacksTrans[i]<0){
				abansPacksTrans[i]=0;
			}
		}


		if(abansPacksTrans[cPack]!=0){batch.setColor(1-abansPacksTrans[cPack]*0.1f, 1-abansPacksTrans[cPack]*0.1f, 1-abansPacksTrans[cPack]*0.1f, 1-abansPacksTrans[cPack]*0.5f);}
		batch.draw(packsTitleTexture,
				dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
				Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-((Gdx.graphics.getWidth()/8)+(Gdx.graphics.getHeight()/6)*4+32*HelloWorld.resolution))/2 -  96*resolution/2,
				0*HelloWorld.resolution,
				96*HelloWorld.resolution*(cPack-1),
				256*HelloWorld.resolution,
				96*resolution);


		if(!demo && abansPacksTrans[cPack]!=0){
			batch.setColor(1,1,1,abansPacksTrans[cPack]);
			batch.draw(packsTitleTexture,
					dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
					Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
					256*HelloWorld.resolution,
					0,
					256*HelloWorld.resolution,
					300*HelloWorld.resolution);

		}
		batch.setColor(Color.WHITE);
		if(demo&&cPack==packs){

			batch.draw(packsTitleTexture,
					dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
					Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
					256*HelloWorld.resolution,
					288*HelloWorld.resolution,
					256*HelloWorld.resolution,
					256*HelloWorld.resolution);	
		}



		//repetir per dragging
		if(dragOffset!=0){
			if(cPack!=packs){
				if(abansPacksTrans[cPack+1]!=0){batch.setColor(1-abansPacksTrans[cPack+1]*0.1f, 1-abansPacksTrans[cPack+1]*0.1f, 1-abansPacksTrans[cPack+1]*0.1f, 1-abansPacksTrans[cPack+1]*0.5f);}
				batch.draw(packsTitleTexture,
						Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
						Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-((Gdx.graphics.getWidth()/8)+(Gdx.graphics.getHeight()/6)*4+32*HelloWorld.resolution))/2 -  96*resolution/2,
						0*HelloWorld.resolution,
						96*HelloWorld.resolution*(cPack),
						256*HelloWorld.resolution,
						96*resolution);


				if(!demo && abansPacksTrans[cPack+1]!=0){
					batch.setColor(1,1,1,abansPacksTrans[cPack+1]);
					batch.draw(packsTitleTexture,
							Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
							Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
							256*HelloWorld.resolution,
							0,
							256*HelloWorld.resolution,
							300*HelloWorld.resolution);

				}
				batch.setColor(Color.WHITE);
				if(demo&&cPack+1==packs){

					batch.draw(packsTitleTexture,
							Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
							Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
							256*HelloWorld.resolution,
							288*HelloWorld.resolution,
							256*HelloWorld.resolution,
							256*HelloWorld.resolution);	
				}


			}

			if (cPack!=1){
				if(abansPacksTrans[cPack-1]!=0){batch.setColor(1-abansPacksTrans[cPack-1]*0.1f, 1-abansPacksTrans[cPack-1]*0.1f, 1-abansPacksTrans[cPack-1]*0.1f, 1-abansPacksTrans[cPack-1]*0.5f);}
				batch.draw(packsTitleTexture,
						-Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
						Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()-((Gdx.graphics.getWidth()/8)+(Gdx.graphics.getHeight()/6)*4+32*HelloWorld.resolution))/2 - 96*resolution/2,
						0*HelloWorld.resolution,
						96*HelloWorld.resolution*(cPack-2),
						256*HelloWorld.resolution,
						96*resolution);


				if(!demo && abansPacksTrans[cPack-1]!=0){
					batch.setColor(1,1,1,abansPacksTrans[cPack-1]);
					batch.draw(packsTitleTexture,
							-Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
							Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
							256*HelloWorld.resolution,
							0,
							256*HelloWorld.resolution,
							300*HelloWorld.resolution);

				}
				batch.setColor(Color.WHITE);

				if(demo&&cPack-1==packs){

					batch.draw(packsTitleTexture,
							-Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-(256*HelloWorld.resolution)/2,
							Gdx.graphics.getHeight()/2-150*HelloWorld.resolution,
							256*HelloWorld.resolution,
							288*HelloWorld.resolution,
							256*HelloWorld.resolution,
							256*HelloWorld.resolution);	
				}
			}


		}




		//text del candau

		fontn.setColor(1, 1, 1, 0.7f);	

		if(!demo && !abansPacks[cPack]){
			font.draw(batch, Integer.toString(abansPacksInt[cPack]), dragOffset+Gdx.graphics.getWidth()/2-font.getBounds(Integer.toString(abansPacksInt[cPack])).width/2, Gdx.graphics.getHeight()/2-150*HelloWorld.resolution+132*resolution);
		}

		if(dragOffset!=0){
			if(cPack!=packs){
				if(!demo && !abansPacks[cPack+1]){
					font.draw(batch, Integer.toString(abansPacksInt[cPack+1]), Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-font.getBounds(Integer.toString(abansPacksInt[cPack+1])).width/2, Gdx.graphics.getHeight()/2-150*HelloWorld.resolution+132*resolution);
				}
			}
			if (cPack!=1){
				if(!demo && !abansPacks[cPack-1]){
					font.draw(batch, Integer.toString(abansPacksInt[cPack-1]), -Gdx.graphics.getWidth()+dragOffset+Gdx.graphics.getWidth()/2-font.getBounds(Integer.toString(abansPacksInt[cPack-1])).width/2, Gdx.graphics.getHeight()/2-150*HelloWorld.resolution+132*resolution);

				}
			}
		}

		fontn.setColor(Color.WHITE);



	}




	public void update_drag_levels(){

		if(!draging && dragOffset!=0){



			dragA=(Math.tan(Math.PI*((double)dragOffset/(double)Gdx.graphics.getWidth())));


			//limitador del tan
			if(dragA>=1){
				dragA=1;
			}
			if(dragA<=-1){
				dragA=-1;
			}


			//si esta als limits
			if(cPack==1 && dragOffset>0){
				dragA=9*dragOffset/Gdx.graphics.getWidth()+1;
			}
			if(cPack==packs && dragOffset<0){
				dragA=9*dragOffset/Gdx.graphics.getWidth()-1;
			}


			dragA=dragA*6000;
			dragV=dragV-dragA*Gdx.graphics.getDeltaTime();
			dragOffset+=dragV*Gdx.graphics.getDeltaTime();



			LdragPosition=dragPosition;
			dragPosition=(int)(dragOffset/Gdx.graphics.getWidth());
			if(dragOffset>0){
				dragPosition=dragPosition+1;
			}else{
				dragPosition=dragPosition-1;
			}
			if(LdragPosition==99){LdragPosition=dragPosition;}


			if(dragPosition!=LdragPosition){

				if (dragOffset>(Gdx.graphics.getWidth()/2) && cPack!=1){
					cPack--;
				}else if (dragOffset<(-Gdx.graphics.getWidth()/2) && cPack!=packs){
					cPack++;
				}
				if(HelloWorld.demo && HelloWorld.cPack==2 && HelloWorld.cPack!=HelloWorld.editPack){
					HelloWorld.botons[16].visible=true;
				}else{
					HelloWorld.botons[16].visible=false;	
				}
				dragV=0;
				dragOffset=0;
				dragOffset2=0;
				dragA=0;
				dragPosition=99;
				LdragPosition=99;
			}


		}
		//Gdx.app.log("cpakc", Integer.toString(cPack));

	}




	public void draw_tiles(){


		for(int i = cols-1; i >=0; i--) {
			for(int j = rows-1; j>=0; j--) {
				//batch.draw(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy], fieldPositions[i][j].posicio.x -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionWidth()/2) ,fieldPositions[i][j].posicio.y -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionHeight()/2)-(4*resolution)); //l'ultim 2*resolution es d'ajust

				if (fieldPositions[i][j].tipus!='b'){
					batch.draw(Field.raTexture[fieldPositions[i][j].texx+fieldPositions[i][j].obert][fieldPositions[i][j].texy+fieldPositions[i][j].frame],
							fieldPositions[i][j].posicio.x -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionWidth()/2) ,
							fieldPositions[i][j].posicio.y -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionHeight()/2)-(4*resolution)); //l'ultim 2*resolution es d'ajust


					//herba que sobresurt (mig transparent), pack 1
					batch.draw(Field.raTexture[0][5],
							fieldPositions[i][j].posicio.x -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionWidth()/2) ,
							fieldPositions[i][j].posicio.y -(Field.raTexture[fieldPositions[i][j].texx][fieldPositions[i][j].texy].getRegionHeight()/2)-(4*resolution)); //l'ultim 2*resolution es d'ajust

				}

			}
		}


		//animacio
		for(int i = cols-1; i >=0; i--) {
			for(int j = rows-1; j>=0; j--) {
				if (fieldPositions[i][j].tipus=='I' || fieldPositions[i][j].tipus=='J' ||fieldPositions[i][j].tipus=='K' ||fieldPositions[i][j].tipus=='L' ){

					switch (fieldPositions[i][j].tipus) {
					case 'I':
						anicolor=0;

						break;

					case 'J':
						anicolor=1;

						break;


					case 'K':
						anicolor=2;

						break;

					case 'L':
						anicolor=3;

						break;

					default:
						break;
					}


					if (fieldPositions[i][j].ratec>=fieldPositions[i][j].rate && fieldPositions[i][j].cunitats>0 && !fieldPositions[i][j].ocupada ){
						batch.draw(aniTexture,
								fieldPositions[i][j].posicio.x-(Field.raTexture[0][0].getRegionWidth()/2),
								fieldPositions[i][j].posicio.y-(Field.raTexture[0][0].getRegionHeight()/2)-(4*resolution),
								(int)Field.raTexture[0][0].getRegionWidth()*(fieldPositions[i][j].frame+anicolor*4),
								(int)Field.raTexture[0][0].getRegionHeight()*(int)(adt*8),
								(int)Field.raTexture[0][0].getRegionWidth(),
								(int)Field.raTexture[0][0].getRegionHeight());

					}else{  //dibuixar nomes el primer frame
						batch.draw(aniTexture,   
								fieldPositions[i][j].posicio.x-(Field.raTexture[0][0].getRegionWidth()/2),
								fieldPositions[i][j].posicio.y-(Field.raTexture[0][0].getRegionHeight()/2)-(4*resolution),
								(int)Field.raTexture[0][0].getRegionWidth()*(fieldPositions[i][j].frame+anicolor*4),
								(int)Field.raTexture[0][0].getRegionHeight(),
								(int)Field.raTexture[0][0].getRegionWidth(),
								(int)Field.raTexture[0][0].getRegionHeight());

					}

				}

			}
		}



	}

	public void draw_monsters(){



		//Ordenar monstres per draw
		for(int i = 0; i < monstres.length; i++) {
			for(int j = 0; j < monstres.length; j++) {
				if (monstres[j].visible && monstres[i].visible){
					temp=monstres[j].z;
					temp2=monstres[i].z;

					if (temp>temp2){
						temp3=temp;
						temp=temp2;
						temp2=temp3;
					}

					if (monstres[j].cy>monstres[i].cy){
						if (monstres[j].z != temp2){trobat=true;}
						monstres[j].z = temp2;
						monstres[i].z = temp;
					} else if (monstres[j].cy<monstres[i].cy) {
						if (monstres[j].z != temp){trobat=true;}
						monstres[j].z = temp;
						monstres[i].z = temp2;

					}else if (monstres[j].cy==monstres[i].cy){

						if (monstres[j].ny>monstres[i].ny){
							if (monstres[j].z != temp2){trobat=true;}
							monstres[j].z = temp2;
							monstres[i].z = temp;
						} else if (monstres[j].ny<monstres[i].ny) {
							if (monstres[j].z != temp){trobat=true;}
							monstres[j].z = temp;
							monstres[i].z = temp2;

						}else if(monstres[j].ny==monstres[i].ny && monstres[j].state==3 && monstres[i].state==3 ) {
							if (monstres[j].z != temp){trobat=true;}

							if (monstres[j].dir==1){
								monstres[j].z = temp;
								monstres[i].z = temp2;
							}else if (monstres[j].dir==0){
								monstres[j].z = temp2;
								monstres[i].z = temp;

							}



						}
					}
				}

			}	

		}




		//Dibuixar sombres


		for(int i = monstres.length-1; i >=0; i=i-1) {
			for(int j = 0; j < monstres.length; j++) {
				if (monstres[j].z==i && monstres[j].visible){	
					if (monstres[j].state!=2){
						if(monstres[j].state==3){batch.setColor(1,1,1,1-(monstres[j].state3dt*monstres[j].state3dt));}
						batch.draw(shadowsTexture,
								monstres[j].position.x-(32*HelloWorld.resolution/2),
								monstres[j].position.y-(32*HelloWorld.resolution/2)+(3*resolution)+monstres[j].mask,
								32*HelloWorld.resolution*monstres[j].frame,
								32*HelloWorld.resolution*monstres[j].dir,
								32*HelloWorld.resolution,
								32*HelloWorld.resolution);
						batch.setColor(Color.WHITE );
					}		
				}
			}
		}





		nombreMonstresVisibles=0;
		//Dibuixar monstres
		for(int i = monstres.length-1; i >=0; i=i-1) {
			for(int j = 0; j < monstres.length; j++) {
				if (monstres[j].z==i && monstres[j].visible){
					if(monstres[j].state==0){nombreMonstresVisibles++;}

					if (monstres[j].mask>0 && monstres[j].state==2){
						batch.draw(Monster.mTexture,
								monstres[j].position.x-(32*HelloWorld.resolution/2),
								monstres[j].position.y-(32*HelloWorld.resolution/2)+(3*resolution)+monstres[j].mask,
								(int)32*HelloWorld.resolution*monstres[j].frame,
								(int)32*HelloWorld.resolution*monstres[j].dir+32*HelloWorld.resolution*(4*monstres[j].color),
								(int)32*HelloWorld.resolution,
								(int)(32*HelloWorld.resolution-(monstres[j].mask)));

					}else if(monstres[j].mask>=32*HelloWorld.resolution){

					}else if(monstres[j].state==3){
						batch.setColor(1,1,1,1-(monstres[j].state3dt*monstres[j].state3dt));
						batch.draw(Monster.mTexture,
								monstres[j].position.x-(32*HelloWorld.resolution/2),
								monstres[j].position.y-(32*HelloWorld.resolution/2)+(3*resolution),
								(32*4*resolution)+((int)32*HelloWorld.resolution*(monstres[j].mortframe)),
								(int)32*HelloWorld.resolution*monstres[j].dir+32*HelloWorld.resolution*(4*monstres[j].color),
								32*HelloWorld.resolution,
								(int)32*HelloWorld.resolution);

						batch.setColor(Color.WHITE );
					}else{
						batch.draw(Monster.mTexture,
								monstres[j].position.x-(32*HelloWorld.resolution/2),
								monstres[j].position.y-(32*HelloWorld.resolution/2)+(3*resolution),
								(int)32*HelloWorld.resolution*monstres[j].frame,
								(int)32*HelloWorld.resolution*monstres[j].dir+32*HelloWorld.resolution*(4*monstres[j].color),
								32*HelloWorld.resolution,
								(int)32*HelloWorld.resolution);

					}	
				}
			}
		}



	}

	public void draw_coloc(){

		//dibuixar fantasme

		if (fieldPositions[currentcolocx][currentcolocyP].tipus=='b'){
			batch.setColor(1, 1, 1,0.8f );
		}else{
			batch.setColor(1, 0.8f, 0.8f,0.4f );
		}

		if(!eliminantColo){

			batch.draw(Field.raTexture[currentcolocTexx][currentcolocTexy],fieldPositions[currentcolocx][currentcolocyP].posicio.x-16*resolution,fieldPositions[currentcolocx][currentcolocyP].posicio.y-20*resolution);  // es 20 en comptes de 16 pk els field estan dibuixats 4 pixels mes aball de la seva posicio oficial.
			batch.setColor(Color.WHITE);
		}

		//		batch.draw(Boto.textureB,
		//				mouseX-16*resolution,
		//				mouseY,
		//				12*32*HelloWorld.resolution,
		//				14*32*HelloWorld.resolution,
		//				32*resolution,
		//				64*resolution);
		//

	}


	public void draw_perdut(){


		//De moment he posat que el boto parpadeji
	}

	public void draw_panel(){
		//batch.disableBlending();

		//Dibuixar panell
		batch.draw(Boto.textureB, 0, 0,0,32*resolution,512*resolution,96*resolution);

		//batch.enableBlending();
	}

	public void draw_buttons(){

		for(int i = 0; i < botons.length; i++) {

			if (botons[i].visible && i!=16){   //el 16 es un boto invisible per BUY

				if(i==5 || i==20){  //sombra per el boto start
					//batch.draw(sombraStartTexture,botons[i].x,botons[i].y-(64)*resolution);
					
					if(i==5){
					batch.draw(sombraStartTexture,
							botons[i].x,
							botons[i].y-(64)*resolution,
							0*resolution,
							0*resolution,
							botons[i].w,
							botons[i].h*2);
					}else if(i==20){
						batch.draw(sombraStartTexture,
								botons[i].x,
								botons[i].y-(64)*resolution,
								0*resolution,
								128*resolution,
								botons[i].w,
								botons[i].h*2);
					}
					
					
				}

				if(i>=50){


					if(!abans[cPack][i-50+1]){batch.setColor(.9f, .9f, .9f, 0.6f);}
					if(abansPacksTrans[cPack]!=0){batch.setColor(.9f-abansPacksTrans[cPack]*0.2f, .9f-abansPacksTrans[cPack]*0.2f, .9f-abansPacksTrans[cPack]*0.2f, 0.6f-abansPacksTrans[cPack]*0.1f);}

					batch.draw(Boto.textureB,
							dragOffset+botons[i].x,
							botons[i].y,
							botons[i].texx,
							botons[i].texy,
							botons[i].w,
							botons[i].h);
					if(!progres[cPack][i-50+1] && abans[cPack][i-50+1] && cPack <= packs){
						batch.draw(Boto.textureB,
								dragOffset+botons[i].x,
								botons[i].y,
								botons[i].texx+64*2*resolution,
								botons[i].texy+64*1*resolution,
								botons[i].w,
								botons[i].h);
					}


				}else{
					if(perdut && i==2){
						batch.setColor(1,  (float)((1+(Math.sin(adt*Math.PI)))/2),1,1);
					}else{
						batch.setColor(Color.WHITE);
					}

					batch.draw(Boto.textureB,
							botons[i].x,
							botons[i].y,
							botons[i].texx,
							botons[i].texy,
							botons[i].w,
							botons[i].h);

				}
				batch.setColor(Color.WHITE);

			}
		}



		for(int i = 0; i < botons.length; i++) {     	
			if (botons[i].visible){


				if (i>=50){
					if(abans[cPack][i-50+1] && abansPacksTrans[cPack]==0){

						font.draw(batch, Integer.toString(i-50+1), dragOffset+botons[i].x+11*resolution,botons[i].y+16*resolution+font.getCapHeight());
					}
				}else{

					if (botons[i].text!=""){
						font.draw(batch, botons[i].text, botons[i].x+botons[i].w/2-font.getBounds(botons[i].text).width/2,botons[i].y+botons[i].h/2+font.getCapHeight()/2+5*resolution);

					}
				}
			}
		}


		for(int i = 0; i < botons.length; i++) {     	
			if (botons[i].visible){


				if (i>=50){

					if(progres[cPack][i-50+1] && abansPacks[cPack]){batch.draw(starTexture, dragOffset+botons[i].x+32*resolution, botons[i].y+29*resolution);}
				}
			}
		}




		//Es repeteix el drawing dels +50 si s'est� fent drag, tot amb +1 i offset de tot l'screen:
		if(dragOffset<0 && cPack!=packs){
			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){

					if(i>=50){

						if(!abans[cPack+1][i-50+1]){batch.setColor(.9f, .9f, .9f, 0.6f);}
						if(abansPacksTrans[cPack+1]!=0){batch.setColor(.9f-abansPacksTrans[cPack+1]*0.2f, .9f-abansPacksTrans[cPack+1]*0.2f, .9f-abansPacksTrans[cPack+1]*0.2f, 0.6f-abansPacksTrans[cPack+1]*0.1f);}


						batch.draw(Boto.textureB,
								Gdx.graphics.getWidth()+dragOffset+botons[i].x,
								botons[i].y,
								botons[i].texx,
								botons[i].texy,
								botons[i].w,
								botons[i].h);

						batch.setColor(Color.WHITE);

					}


				}
			}



			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){
					if (i>=50){
						if(abans[cPack+1][i-50+1]&& abansPacksTrans[cPack+1]==0){


							font.draw(batch, Integer.toString(i-50+1), Gdx.graphics.getWidth()+dragOffset+botons[i].x+11*resolution,botons[i].y+16*resolution+font.getCapHeight());
						}
					}
				}
			}


			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){

					if (i>=50){

						if(progres[cPack+1][i-50+1]&& abansPacks[cPack+1]){batch.draw(starTexture,  Gdx.graphics.getWidth()+dragOffset+botons[i].x+32*resolution, botons[i].y+29*resolution);}
					}
				}
			}
		}








		//Es repeteix el drawing dels +50 si s'est� fent drag, tot amb -1 i offset de tot l'screen:
		if(dragOffset>0 && cPack!=1){
			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){

					if(i>=50){

						if(!abans[cPack-1][i-50+1]){batch.setColor(.9f, .9f, .9f, 0.6f);}
						if(abansPacksTrans[cPack-1]!=0){batch.setColor(.9f-abansPacksTrans[cPack-1]*0.2f, .9f-abansPacksTrans[cPack-1]*0.2f, .9f-abansPacksTrans[cPack-1]*0.2f, 0.6f-abansPacksTrans[cPack-1]*0.1f);}


						batch.draw(Boto.textureB,
								-Gdx.graphics.getWidth()+dragOffset+botons[i].x,
								botons[i].y,
								botons[i].texx,
								botons[i].texy,
								botons[i].w,
								botons[i].h);

						batch.setColor(Color.WHITE);

					}


				}
			}



			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){
					if (i>=50){
						if( abans[cPack-1][i-50+1]&& abansPacksTrans[cPack-1]==0){


							font.draw(batch, Integer.toString(i-50+1), -Gdx.graphics.getWidth()+dragOffset+botons[i].x+11*resolution,botons[i].y+16*resolution+font.getCapHeight());
						}
					}
				}
			}


			for(int i = 0; i < botons.length; i++) {     	
				if (botons[i].visible){

					if (i>=50){

						if(progres[cPack-1][i-50+1]&& abansPacks[cPack-1]){batch.draw(starTexture,  -Gdx.graphics.getWidth()+dragOffset+botons[i].x+32*resolution, botons[i].y+29*resolution);}
					}
				}
			}
		}




	}




	public void draw_triarui(){




		if(eliminantColo && colocant){batch.setColor(1,0.6f,0.6f,1);}
		ci=1;

		//			batch.draw(tri2,
		//					64*HelloWorld.resolution,
		//					panellHeight/2-(64*resolution)/2,
		//					0,
		//					0,
		//					32*resolution,
		//					64*resolution);


		batch.draw(Boto.textureB,
				Gdx.graphics.getWidth()-64*resolution-31*resolution,
				panellHeight/2-(64*resolution)/2,
				15*32*HelloWorld.resolution,
				12*32*HelloWorld.resolution,
				31*resolution,
				64*resolution);
		while(ci*32*resolution<Gdx.graphics.getWidth()){
			batch.draw(Boto.textureB,
					Gdx.graphics.getWidth()-64*resolution-31*resolution-ci*31*resolution,
					panellHeight/2-(64*resolution)/2,
					14*32*HelloWorld.resolution,
					12*32*HelloWorld.resolution,
					31*resolution,
					64*resolution);
			ci++;

		}



		//		for(int i=1;i<5;i++){
		//			batch.draw(Boto.textureB,
		//					(-4*resolution+Gdx.graphics.getWidth()-(64*HelloWorld.resolution))-((-1*resolution+Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5)*i,
		//					panellHeight/2-(64*resolution)/2,
		//					((15*32)-1)*HelloWorld.resolution,
		//					12*32*HelloWorld.resolution,
		//					1*resolution,
		//					64*resolution);
		//			
		//			
		//		}




		batch.setColor(Color.WHITE);

		ci=0;

		for(int j = 0; j < 5; j++) {

			if (drags[j+editTriarIndex*5].visible){
				if(drags[j+editTriarIndex*5].cunitats==0){batch.setColor(0.5f,0.5f,0.5f,0.4f);}

				drags[j+editTriarIndex*5].x=j*((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5);
				drags[j+editTriarIndex*5].x=drags[j+editTriarIndex*5].x+(((Gdx.graphics.getWidth()-(64*HelloWorld.resolution))/5)/2)-(32*resolution/2);  //per centrar
				drags[j+editTriarIndex*5].x-=4*resolution; //per ajustar
				drags[j+editTriarIndex*5].y=HelloWorld.panellHeight/2-(22*HelloWorld.resolution)/2;
				drags[j+editTriarIndex*5].y-=0*resolution; //per ajustar



				batch.draw(Field.raTexture[drags[j+editTriarIndex*5].texx][drags[j+editTriarIndex*5].texy],drags[j+editTriarIndex*5].x ,drags[j+editTriarIndex*5].y);
				//fontn.draw(batch, Integer.toString(drags[j].cunitats), (Gdx.graphics.getWidth()/2)-73*resolution+ci*48*resolution,Gdx.graphics.getHeight()/2+panellHeight/2+96*resolution-cj*48*resolution+fontn.getCapHeight());



				batch.setColor(Color.WHITE);
			}

		}


		if(!editant){


			for(int j = 0; j < 5; j++) {


				if (drags[j+editTriarIndex*5].visible){

					if(drags[j+editTriarIndex*5].cunitats!=0){
						fontn.setColor(1,1,1,0.8f);
					}else{
						fontn.setColor(1,1,1,0.3f);
					}

					fontn.draw(batch, Integer.toString(drags[j+editTriarIndex*5].cunitats), drags[j+editTriarIndex*5].x+21*resolution ,drags[j+editTriarIndex*5].y+23*resolution);

				}

			}

			fontn.setColor(Color.WHITE);
		}
		batch.setColor(Color.WHITE);
	}


	public void draw_logo(){  //i mes

		welcomeStr= Integer.toString(goobasSaved)+" Goobas Saved";


		switch (faselogo) {
		case 0:
			if(Gdx.graphics.getFramesPerSecond()>=10){logodt=logodt+Gdx.graphics.getDeltaTime();}
			if (logodt>1){
				faselogo=1;
				logodt=0;
			}
			break;

		case 1:
			logodt=logodt+Gdx.graphics.getDeltaTime();


			batch.setColor(1, 1, 1,(float)(      1-((Math.exp((double)(2.5-logodt)))/(Math.exp(2.5)))         ) );
			batch.draw(logoTexture,
					Gdx.graphics.getWidth()/2-256*resolution/2,
					Gdx.graphics.getHeight()*3/4-64*resolution/2-15*resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);

			batch.draw(logoTexture,
					Gdx.graphics.getWidth()/2-256*resolution/2,
					Gdx.graphics.getHeight()*3/4+64*resolution/2-15*resolution,
					256*HelloWorld.resolution,
					0*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);

			batch.setColor(Color.WHITE );

			batch.draw(logoTexture,
					Gdx.graphics.getWidth()/2-256*resolution/2,
					Gdx.graphics.getHeight()*3/4+64*resolution/2+(int)(Math.exp((double)(2.5-logodt))*10*resolution),
					0*HelloWorld.resolution,
					0*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);

			batch.draw(logoTexture,
					Gdx.graphics.getWidth()/2-256*resolution/2,
					Gdx.graphics.getHeight()*3/4-64*resolution/2+(int)(Math.exp((double)(2.5-logodt))*10*resolution),
					0*HelloWorld.resolution,
					64*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);

			if (logodt>2.5){
				logodt=0;
				faselogo=2;

				botons[5].visible=true;
				botons[15].visible=true;
				if(progres[1][1] && !demo)botons[20].visible=true;


				//				HelloWorld.monstres[1].spawn(2, 4, 0);
				//				HelloWorld.monstres[1].color=1;
				//				HelloWorld.monstres[2].spawn(4, 4, 1);
				//				HelloWorld.monstres[2].color=0;
				//
				//				HelloWorld.monstres[3].spawn(4, 10, 2);
				//				HelloWorld.monstres[3].color=2;
				//
				//				HelloWorld.monstres[4].spawn(1, 4, 0);
				//				HelloWorld.monstres[4].color=3;



				HelloWorld.monstres[1].spawn(3, 9, 0);
				HelloWorld.monstres[1].color=1;
				HelloWorld.monstres[2].spawn(4, 10, 0);
				HelloWorld.monstres[2].color=0;

				HelloWorld.monstres[3].spawn(3, 10, 1);
				HelloWorld.monstres[3].color=2;

				HelloWorld.monstres[4].spawn(2, 10, 2);
				HelloWorld.monstres[4].color=3;



				HelloWorld.running=true;



			}

			break;


		case 2:



			fontn.setColor(1, 1, 1, 0.7f);
			fontn.drawWrapped(batch, welcomeStr, 5*resolution, 5*resolution+fontn.getWrappedBounds(welcomeStr, Gdx.graphics.getWidth()-10*resolution).height,Gdx.graphics.getWidth()-10*resolution);
			fontn.setColor(Color.WHITE);



			logodt=logodt+Gdx.graphics.getDeltaTime();

			batch.draw(logoTexture, Gdx.graphics.getWidth()/2-256*resolution/2, Gdx.graphics.getHeight()*3/4-64*resolution/2-15*resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);
			batch.draw(logoTexture, Gdx.graphics.getWidth()/2-256*resolution/2, Gdx.graphics.getHeight()*3/4+64*resolution/2-15*resolution,
					256*HelloWorld.resolution,
					0*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);



			batch.draw(logoTexture, Gdx.graphics.getWidth()/2-256*resolution/2, Gdx.graphics.getHeight()*3/4+64*resolution/2+(int)(Math.sin((double)(logodt*3+2f))*3*resolution)+7*resolution,
					0*HelloWorld.resolution,
					0*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);
			batch.draw(logoTexture, Gdx.graphics.getWidth()/2-256*resolution/2, Gdx.graphics.getHeight()*3/4-64*resolution/2+(int)(Math.cos((double)(logodt*4+0.5f))*3*resolution)+7*resolution,
					0*HelloWorld.resolution,
					64*HelloWorld.resolution,
					256*HelloWorld.resolution,
					64*HelloWorld.resolution);


			break;

		default:
			break;
		}


	}

	public void draw_menuui(){

		for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
				batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
			}
		}







		batch.draw(Boto.textureB,
				Gdx.graphics.getWidth()/2-(32*8*resolution)/2,
				posMenu,
				0*32*HelloWorld.resolution,
				4*32*HelloWorld.resolution,
				32*8*resolution,
				32*1*resolution);
		for(int i = 0; i < nDrawsMenu; i++) {
			batch.draw(Boto.textureB,
					Gdx.graphics.getWidth()/2-(32*8*resolution)/2,
					posMenu-(64*(i+1))*resolution,
					0*32*HelloWorld.resolution,
					5*32*HelloWorld.resolution,
					32*8*resolution,
					32*2*resolution);
		}

		batch.draw(Boto.textureB,
				Gdx.graphics.getWidth()/2-(32*8*resolution)/2,
				posMenu-(64*(nDrawsMenu)+32)*resolution,
				0*32*HelloWorld.resolution,
				7*32*HelloWorld.resolution,
				32*8*resolution,
				32*1*resolution);



	}

	public void draw_score(){
		fontn.setColor(1, 1, 1, 0.8f);
		if(!editant){
		fontn.draw(batch, hanarribat+"/"+handarribar, Gdx.graphics.getWidth()-fontn.getBounds(hanarribat+"/"+handarribar).width-2*resolution, Gdx.graphics.getHeight()-2*resolution);
		}else{
		//fontn.draw(batch, Integer.toString(hanarribat), Gdx.graphics.getWidth()-fontn.getBounds(Integer.toString(hanarribat)).width-2*resolution, Gdx.graphics.getHeight()-2*resolution);

		}
		
		fontn.draw(batch, "Level "+Integer.toString(cLevel), 2*resolution, Gdx.graphics.getHeight()-2*resolution);
	
		fontn.setColor(Color.WHITE);
	}



	public void draw_segur(){

		for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
				batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
			}
		}



		batch.draw(triarTexture,Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+panellHeight/2);

		//		font.draw(batch, Strings.level+" "+Integer.toString(cLevel), Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution+font.getCapHeight());
		//		font.draw(batch, Strings.complete                          , Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+70*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution-26*resolution+font.getCapHeight());
		font.drawWrapped(batch, Strings.segur, Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+triarTexture.getHeight()-135*resolution+font.getCapHeight(),185*resolution);

	}


	public void draw_help(){

		for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
				batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
			}
		}



		batch.draw(triarTexture,Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+panellHeight/2);

		//		font.draw(batch, Strings.level+" "+Integer.toString(cLevel), Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution+font.getCapHeight());
		//		font.draw(batch, Strings.complete                          , Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+70*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution-26*resolution+font.getCapHeight());
		fontn.drawWrapped(batch, Strings.helpT, Gdx.graphics.getWidth()/2-triarTexture.getWidth()/2+32*resolution,Gdx.graphics.getHeight()/2-triarTexture.getHeight()/2+triarTexture.getHeight()-116*resolution+font.getCapHeight(),195*resolution);

	}





	public void draw_guanyatui(){

		Rei.visible=false;
		Rei.x=-64*resolution;

		for(int i = 0; i <= (Gdx.graphics.getWidth()/negreTexture.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/negreTexture.getHeight()); j++) {
				batch.draw(negreTexture,negreTexture.getWidth()*i ,negreTexture.getHeight()*j ); 
			}
		}

		if(Rei.jumpy<=0){
			Rei.jumpv=400;
			Rei.jumpy=0;

		}

		Rei.jumpv=Rei.jumpv-900*Gdx.graphics.getDeltaTime();
		Rei.jumpy=Rei.jumpy+Rei.jumpv*Gdx.graphics.getDeltaTime();
		if(Rei.jumpv<=200){
			Rei.jumptexx=2;
		}else{
			Rei.jumptexx=0;
		}

		batch.draw(Rei.texture,
				Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+20*resolution,
				Gdx.graphics.getHeight()/2+guanyatTexture.getHeight()/2+Rei.jumpy-60*resolution,
				64*resolution*Rei.jumptexx,
				64*resolution*3,
				64*resolution,
				64*resolution);


		batch.draw(guanyatTexture,Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2);
		font.draw(batch, Strings.level+" "+Integer.toString(cLevel), Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+40*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution+font.getCapHeight());
		font.draw(batch, Strings.complete                          , Gdx.graphics.getWidth()/2-guanyatTexture.getWidth()/2+70*resolution,Gdx.graphics.getHeight()/2-guanyatTexture.getHeight()/2+guanyatTexture.getHeight()-90*resolution-26*resolution+font.getCapHeight());

	}

	//TODO  Dibuixar el fons del guanyat, el menu i el triar, amb la textura de UI (poc important)

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK || keycode == Keys.B){
			if (screen==1){
				if(running){
					botons[1].touched();
				}else{
					if(menuant){
						botons[6].touched();

						//					}else if (triant){
						//						botons[6].touched();
					}else if (help || segur){
						botons[6].touched();
						botons[4].touched();
					}else{
						Level.save();
						Level.level_choose();
						Sounds.play_loop1(); 
					}
				}
			}else if (screen==2){
				Level.menu(false);
			}else if (screen==0){
				System.exit(0);
			}
		}else if(keycode== Keys.MENU || keycode == Keys.M){
			if (screen==1){
				if(!running && !menuant && !segur && !help){
					HelloWorld.botons[4].touched();
				}else if(menuant){
					HelloWorld.botons[6].touched();
				}else if(help || segur){
					HelloWorld.botons[6].touched();
				}
			}
		}
		return false;
	}

	@Override public void resize (int width, int height) {

	}

	@Override public void pause () {
		if (screen==1){
			Level.save();
		}

		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		Sounds.music1.stop();
		Sounds.music1.dispose();


		grassTexture.dispose();
		panellTexture.dispose();
		triarTexture.dispose();
		guanyatTexture.dispose();
		shadowsTexture.dispose();
		aniTexture.dispose();
		logoTexture.dispose();
		starTexture.dispose();
		sombraStartTexture.dispose();
		packsTitleTexture.dispose();


		Rei.texture.dispose();
		Rei.textureb.dispose();
		Rei.puntexture.dispose();


		Boto.textureB.dispose();

		liniesTexture.dispose();

		Monster.mTexture.dispose();

		negreTexture.dispose();

		font.dispose();
		fontn.dispose();

		//TExtures tiles
		Field.mTexture.dispose();	

	}

	@Override public void resume () {


		grassTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/grass.png"));
		panellTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/panell.png"));
		triarTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/triar.png"));
		guanyatTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/guanyat.png"));
		shadowsTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/sombres.png"));
		aniTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/animacio.png"));
		logoTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/titol.png"));
		starTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/star.png"));
		sombraStartTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/botoSS.png"));
		packsTitleTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/packs.png"));


		Rei.texture =new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/king.png"));
		Rei.textureb=new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/bocata.png"));
		Rei.puntexture=new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/high.png"));


		Boto.textureB = new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/botons.png"));

		Monster.mTexture= new Texture(Gdx.files.internal("img/"+Integer.toString(HelloWorld.resolution)+"/monstres.png"));


		negreTexture = new Texture(Gdx.files.internal("img/1/negre.png"));

		font=new BitmapFont(Gdx.files.internal("font/"+Integer.toString(resolution)+"/pix.fnt"),Gdx.files.internal("font/"+Integer.toString(resolution)+"/pix.png"), false);
		fontn=new BitmapFont(Gdx.files.internal("font/"+Integer.toString(resolution)+"/und.fnt"),Gdx.files.internal("font/"+Integer.toString(resolution)+"/und.png"), false);



		//TExtures tiles
		Field.mTexture = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/tiles.png"));
		Field.rTexture = new TextureRegion(Field.mTexture);
		Field.raTexture=Field.rTexture.split(32*resolution, 32*resolution);





		//linies

		linies = new Pixmap (MathUtils.nextPowerOfTwo(Gdx.graphics.getWidth()),MathUtils.nextPowerOfTwo( Gdx.graphics.getHeight()), Pixmap.Format.RGBA8888);
		linies.setColor(0, 0, 0, 1);
		dx=(int)(fieldPositions[1][0].posicio.x-fieldPositions[0][0].posicio.x);
		dy=(int)(fieldPositions[0][1].posicio.y-fieldPositions[0][0].posicio.y);

		for(int i = 1; i < cols; i++) {		
			linies.fillRectangle(i*(Gdx.graphics.getWidth()/HelloWorld.cols), +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][rows-1].posicio.y+dy/2)), resolution , (int)(fieldPositions[0][rows-1].posicio.y-fieldPositions[0][0].posicio.y+dy));		
			cuadrats[0][i]=i*(Gdx.graphics.getWidth()/HelloWorld.cols)+1;
		}

		for(int i = 0; i < rows; i++) {		
			linies.fillRectangle(0, +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][i].posicio.y-dy/2)), Gdx.graphics.getWidth() , resolution);		
			cuadrats[1][i]=+8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][i].posicio.y-dy/2));
			cuadrats[1][i]=-cuadrats[1][i]+Gdx.graphics.getHeight()-1;
		}
		linies.fillRectangle(0, +8*resolution+(int)(+Gdx.graphics.getHeight()-(fieldPositions[0][rows-1].posicio.y+dy/2)), Gdx.graphics.getWidth() , resolution);


		liniesTexture= new Texture(linies);
		linies.dispose();




		Sounds.music1=Gdx.audio.newMusic(Gdx.files.internal("snd/music1.ogg"));
		if(!Sounds.mute){
			Sounds.music1.setVolume(1);
		}else{
			Sounds.music1.setVolume(0);
		}

	}

	@Override public void dispose () {
		//System.exit(0);
	}



	@Override
	public boolean keyTyped(char arg0) {

		return false;
	}



	@Override
	public boolean keyUp(int arg0) {

		return false;
	}



	@Override
	public boolean scrolled(int arg0) {

		return false;
	}



	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {

		return false;
	}



	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {

		return false;
	}



	@Override
	public boolean touchMoved(int arg0, int arg1) {

		return false;
	}



	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {

		return false;
	}



	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}


	@Override
	public void show() {
		// TODO Auto-generated method stub

	}






}