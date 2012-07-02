package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;


public class Boto {

	public static int nbotons = 70;

	public int tipus;
	public int x,y,h,w;

	private static int ci =0;
	private static  int cj =4;
	public Texture bTexture;
	public boolean visible;
	public String text;

	public int texx,texy;

	public static Texture textureB;




	public Boto(int inittipus){

		tipus=inittipus;
		x=0;
		y=0;
		w=0;
		h=0;
		texx=0;
		texy=0;
		visible=false;
		text="";

		if (tipus==0){//objectes
			texx=14*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
			


			x=Gdx.graphics.getWidth()-w*2+10*HelloWorld.resolution;
			y=0*HelloWorld.resolution;


		}else if (tipus==3){//play
			texx=10*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()-w+2*HelloWorld.resolution;
			y=0*HelloWorld.resolution;
		}else if (tipus==2){//rewind
			texx=12*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()-w+2*HelloWorld.resolution;
			y=0*HelloWorld.resolution;


		}else if (tipus==4){  //menu
			x=0*HelloWorld.resolution;
			y=0*HelloWorld.resolution;


			texx=8*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
		}else if (tipus==5){  //start

			text=Strings.start;

			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;


			x=Gdx.graphics.getWidth()/2-w/2;
			y=Gdx.graphics.getHeight()/2-h/2;
		}else if (tipus==6){  //enrera


			texx=12*32*HelloWorld.resolution;
			texy=10*32*HelloWorld.resolution;
			w=128*HelloWorld.resolution;
			h=64*HelloWorld.resolution;


			x=Gdx.graphics.getWidth()/2-w/2;
			y=0;


			//			Botons menuui
		}else if (tipus==7){  //menu1

			text=Strings.resetlevel;


			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*HelloWorld.resolution;
		}else if (tipus==8){//menu1

			text=Strings.soundOn;


			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*2*HelloWorld.resolution;
		}else if (tipus==9){//menu1

		
			text=Strings.send;

			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*3*HelloWorld.resolution;
		}else if (tipus==10){//menu1

			text=Strings.help;

			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;


			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*4*HelloWorld.resolution;
		}else if (tipus==11){  //level select
			x=Gdx.graphics.getWidth()/2-128*HelloWorld.resolution+32*HelloWorld.resolution;
			y=Gdx.graphics.getHeight()/2-128*HelloWorld.resolution+64*HelloWorld.resolution;

			texx=8*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;

			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
		}else if (tipus==12){  //next
			x=Gdx.graphics.getWidth()/2-128*HelloWorld.resolution+32*HelloWorld.resolution+64*HelloWorld.resolution;
			y=Gdx.graphics.getHeight()/2-128*HelloWorld.resolution+64*HelloWorld.resolution;

			texx=8*32*HelloWorld.resolution;
			texy=10*32*HelloWorld.resolution;
			w=128*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
		}else if (tipus==13){//menuSegur

			text=Strings.si;

			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;



			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*3*HelloWorld.resolution;
		}else if (tipus==14){//menuSewgur

			text=Strings.no;



			texx=0*32*HelloWorld.resolution;
			texy=14*32*HelloWorld.resolution;
			w=256*HelloWorld.resolution;
			h=64*HelloWorld.resolution;

			x=Gdx.graphics.getWidth()/2-w/2;
			y=HelloWorld.posMenu-64*4*HelloWorld.resolution;
		}else if (tipus==15){//boto so


			texx=1*32*HelloWorld.resolution;
			texy=0*32*HelloWorld.resolution;
			w=32*HelloWorld.resolution;
			h=32*HelloWorld.resolution;

			x=Gdx.graphics.getWidth()-w;
			y=Gdx.graphics.getHeight()-h;
		}else if (tipus==16){//boto BUY


			texx=0*32*HelloWorld.resolution;
			texy=0*32*HelloWorld.resolution;
			w=Gdx.graphics.getWidth()-20;
			h=Gdx.graphics.getHeight()/2;

			x=Gdx.graphics.getWidth()/2-w/2;
			y=Gdx.graphics.getHeight()/2-h/2;
		}else if(tipus==17){//barra FF

			
			texx=2*32*HelloWorld.resolution;
			texy=0*32*HelloWorld.resolution;
			w=160*HelloWorld.resolution;
			h=32*HelloWorld.resolution;
			
			x=((HelloWorld.botons[2].x)/2)-w/2;
			y=16*HelloWorld.resolution;
			
		}else if(tipus==18){//boto FF

			
			texx=(13*32-3)*HelloWorld.resolution;   //el boto es una mica mes ample artificialment
			texy=(14*32)*HelloWorld.resolution;
			w=(32+6)*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
			
			x=HelloWorld.botons[17].x+(32-3)*HelloWorld.resolution;
			y=0;
			
		}else if (tipus==19){  //boto enrera triar
			x=0*HelloWorld.resolution;
			y=0*HelloWorld.resolution;


			texx=12*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;
		}else if (tipus==20){  //editor

			text=Strings.editor;

			texx=0*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=192*HelloWorld.resolution;
			h=64*HelloWorld.resolution;


			x=Gdx.graphics.getWidth()/2-w/2+32*HelloWorld.resolution;
			y=Gdx.graphics.getHeight()/2-h/2-64*HelloWorld.resolution;
		}



		if (tipus>=50){   //A partir de 50 son tot botons de la pantalla de triar nivell



			if (ci==4){ci=0;cj--;}
			if (cj<0){cj=4;}

			text=Integer.toString(tipus+1);





			texx=10*32*HelloWorld.resolution;
			texy=12*32*HelloWorld.resolution;
			w=64*HelloWorld.resolution;
			h=64*HelloWorld.resolution;

			x=((Gdx.graphics.getWidth()/8)+(Gdx.graphics.getWidth()/4)*ci)-w/2;
			y=((Gdx.graphics.getWidth()/8)+(Gdx.graphics.getHeight()/6)*cj)-h/2;


			ci++;

		}


	}

	public void touched(){
		Gdx.app.log("Load", Integer.toString(tipus));
		Gdx.app.log("Load", Integer.toString(HelloWorld.cPack));
		switch (tipus) {

		//BOTONS JOC
		case 3:  //boto play

			if(HelloWorld.cPack==1 && HelloWorld.cLevel==1 && Rei.stage<8){
				//cas que no sa acabat el tutorial lvl 1
			}else{
				HelloWorld.running=true;
				Boto.reset();
				HelloWorld.botons[2].visible=true;
				HelloWorld.botons[17].visible=true;
				HelloWorld.botons[18].visible=true;

				HelloWorld.start=false;
				if(HelloWorld.editant){
					Level.chk_numclaus();
				}
			}
			break;

		case 2:  //boto pause ara esta convertit en rewind

			Level.refresh();
			break;


		case 1:  //rewind

			Level.refresh();
			break;

		case 4:  //boto menu
			Boto.reset();
			
			if(!HelloWorld.editant){
			HelloWorld.nDrawsMenu=2;
			//HelloWorld.botons[6].visible=true;
			HelloWorld.botons[7].visible=true;
			HelloWorld.botons[8].visible=true;
//			HelloWorld.botons[9].visible=true;
			//HelloWorld.botons[10].visible=true;
			HelloWorld.menuant=true;
			}else{
				HelloWorld.nDrawsMenu=4;
				//HelloWorld.botons[6].visible=true;
				HelloWorld.botons[7].visible=true;
				HelloWorld.botons[8].visible=true;
				HelloWorld.botons[9].visible=true;
				HelloWorld.botons[10].visible=true;

				HelloWorld.menuant=true;
				
			}
			break;

		case 0:  //boto objectes Depreciated

			
			break;

		case 6:  //boto enrera

			HelloWorld.jugant=false;
			HelloWorld.menuant=false;
			HelloWorld.help=false;
			HelloWorld.segur=false;
			Level.refresh();
			break;


			//BOTONS MENUUI

		case 7:  

			Boto.reset();
			HelloWorld.menuant=false;
			HelloWorld.segur=true;
			HelloWorld.nDrawsMenu=4;
			HelloWorld.botons[13].visible=true;
			HelloWorld.botons[14].visible=true;


			break;

		case 8:  
			if(Sounds.toogleMute()){
				this.text=Strings.soundOff;
			}else{
				this.text=Strings.soundOn;
			}
			update();

			break;

		case 9:  //SOUND ON/OF
						
			if(HelloWorld.editant){
				HelloWorld.negrecompra =true;
				Level.save();
				Level.editor_send();
				
			}

			break;

		case 10:  

//			Boto.reset();
//			HelloWorld.menuant=false;
//			HelloWorld.help=true;
//			HelloWorld.nDrawsMenu=4;
			HelloWorld.actionResolver.showAlertBox("Level Editor", Strings.helpT, "Ok");




			break;



			//BOTONS GuanyatUI

		case 11:  
			Level.save();
			Level.level_choose();
			Sounds.play_loop1();

			break;

		case 12:  
			if(HelloWorld.cLevel!=HelloWorld.levels){
				Level.save();
				HelloWorld.cLevel++;
				Level.load(HelloWorld.cLevel);
				HelloWorld.transitioni=true;
				HelloWorld.transitiondir=4;
			}else{
				Level.save();
				HelloWorld.cPack++;
				Level.level_choose();
				Sounds.play_loop1();
			}
			break;



			//botons si o no borrar
		case 13:
			Level.delete_save();
			Level.load(HelloWorld.cLevel);


			break;
		case 14:
			HelloWorld.jugant=false;
			HelloWorld.menuant=false;
			HelloWorld.segur=false;
			Level.refresh();


			break;


		case 15: //boto so menu principal

			if(!Sounds.toogleMute()){
				this.texx=0*32*HelloWorld.resolution;
			}else{
				this.texx=1*32*HelloWorld.resolution;
			}
			update();
			break;
			
			
			
		case 16: //boto buy
			HelloWorld.negrecompra =true;
			
			if(HelloWorld.demo && HelloWorld.cPack==2){
				HelloWorld.actionResolver.openUri(null);
			}
			break;
			
			
		case 17: //barra ff

			break;
			
		case 18: //boto ff

			break;
			
		case 19: //boto enrera triar
			
			HelloWorld.jugant=false;
			HelloWorld.menuant=false;
			Level.refresh();

			break;


			//BOTONS MENU

		case 5:  

			Level.level_choose();
			HelloWorld.cPack=1;
			HelloWorld.editorPack=false;
			HelloWorld.transitioni=true;
			HelloWorld.transitiondir=4;
			break;
			
		case 20:  

			Level.level_choose();
			HelloWorld.cPack=HelloWorld.editPack;
			HelloWorld.editorPack=true;
			HelloWorld.botons[16].visible=false;
			
			if(!HelloWorld.editorFT && Gdx.app.getType().equals(ApplicationType.Android)){
				HelloWorld.actionResolver.showAlertBox("Level Editor", Strings.helpT, "Ok");
				HelloWorld.prefs.putBoolean("EFT", true);
				HelloWorld.prefs.flush();
				HelloWorld.editorFT=true;
			}
			break;

		}


		//Botons level

		if (tipus>=50){
			if(HelloWorld.abans[HelloWorld.cPack][tipus-50+1]){
				Sounds.stop_loop1();
				HelloWorld.cLevel=tipus-50+1;
				Level.load(HelloWorld.cLevel);
				HelloWorld.transitioni=true;
				HelloWorld.transitiondir=1;
			}

		}
		Sounds.play_click();
	}


	public static void update(){

		
		if(!Sounds.mute){
			HelloWorld.botons[15].texx=0*32*HelloWorld.resolution;
			HelloWorld.botons[8].text=Strings.soundOn;

		}else{
			HelloWorld.botons[15].texx=1*32*HelloWorld.resolution;
			HelloWorld.botons[8].text=Strings.soundOff;
		}
		HelloWorld.prefs.putBoolean("mute", Sounds.mute);

		HelloWorld.prefs.flush();
		
	}



	public static void reset(){
		for(int i = 0; i < HelloWorld.botons.length; i++) { 
			HelloWorld.botons[i].visible=false;		
		}
	}
}
