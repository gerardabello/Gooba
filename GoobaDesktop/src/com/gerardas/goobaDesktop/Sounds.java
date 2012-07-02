package com.gerardas.goobaDesktop;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

	public static boolean mute=false;

	public static Sound botoClick;

	public static Sound walk1;
	public static float walk1V=1;
	public static float walk1dt=0;

	public static Sound[] talk;
	public static Sound[] finish;


	public static Music music1;
	public static boolean music1Played;

	public static Sound down;
	public static int downC;
	public static Sound sprai;
	public static int spraiC;
	public static Sound cop;
	public static int copC;
	public static Sound clau;
	public static int clauC;
	public static Sound candaus;
	public static boolean candausPlayed;

	public static Sound teletrans;
	public static int teletransC;
	public static Sound colorO,colorT;
	public static int colorOC,colorTC;
	
	
	public static void begin(){
		downC=0;
		spraiC=0;
		copC=0;
		clauC=0;
		teletransC=0;
		colorOC=0;
		colorTC=0;
		
	}
	
	
	public static void end(){
		if(!mute){
			
			if(downC!=0){down.play(0.5f*(float)Math.sqrt(downC));}
			if(spraiC!=0){sprai.play(0.2f*(float)Math.sqrt(spraiC));}
			if(copC!=0){cop.play(1*(float)Math.sqrt(copC));}
			if(clauC!=0){clau.play(1*(float)Math.sqrt(clauC));}
			if(teletransC!=0){teletrans.play(0.7f*(float)Math.sqrt(teletransC));}
			if(colorOC!=0){colorO.play(0.1f*(float)Math.sqrt(colorOC));}
			if(colorTC!=0){colorT.play(0.1f*(float)Math.sqrt(colorTC));}
			
		}
		
		
	}



	public static void ini_sounds(){


		botoClick=Gdx.audio.newSound(Gdx.files.internal("snd/botoClick.ogg"));

		down=Gdx.audio.newSound(Gdx.files.internal("snd/down.ogg"));
		cop=Gdx.audio.newSound(Gdx.files.internal("snd/cop.ogg"));
		clau=Gdx.audio.newSound(Gdx.files.internal("snd/clau.ogg"));
		candaus=Gdx.audio.newSound(Gdx.files.internal("snd/candaus.ogg"));
		music1=Gdx.audio.newMusic(Gdx.files.internal("snd/music1.ogg"));
		music1Played=false;
		walk1=Gdx.audio.newSound(Gdx.files.internal("snd/walk1.ogg"));
		sprai=Gdx.audio.newSound(Gdx.files.internal("snd/sprai.ogg"));
		colorO=Gdx.audio.newSound(Gdx.files.internal("snd/colorO.ogg"));
		colorT=Gdx.audio.newSound(Gdx.files.internal("snd/colorT.ogg"));
		teletrans=Gdx.audio.newSound(Gdx.files.internal("snd/teletrans.ogg"));
		

		talk= new Sound[6];
		for(int i = 0; i <6; i++) {
			talk[i]=Gdx.audio.newSound(Gdx.files.internal("snd/talk"+Integer.toString(i+1)+".ogg"));
		}

		finish= new Sound[4];
		for(int i = 0; i <4; i++) {
			finish[i]=Gdx.audio.newSound(Gdx.files.internal("snd/finish"+Integer.toString(i+1)+".ogg"));
		}

		music1.setLooping(false);

		if(!mute){
			music1.setVolume(1);
		}else{
			music1.setVolume(0);
		}



	}

	public static boolean toogleMute(){

		if(mute){
			mute=false;
			music1.setVolume(1);
		}else{
			music1.setVolume(0);
			mute=true;
		}

		return mute;
	}

	public static void play_color(boolean obert){
		if(!mute){
			if(obert){
				colorOC++;
			}else{
				colorTC++;
			}
		}

	}
	

	public static void play_clau(){

		if(!mute){
			clauC++;
		}
	}
	
	public static void play_teletrans(){

		if(!mute){
			teletransC++;
		}
	}


	public static void play_sprai(){

		if(!mute){
			spraiC++;
		}
	}

	public static void play_click(){

		if(!mute){
			botoClick.play(1);
		}
	}

	public static void play_candaus(){

		if(!mute){
			candaus.play(1);
		}
	}

	public static void play_cop(){

		if(!mute){
			copC++;
		}
	}

	public static void play_down(){

		if(!mute){
			downC++;
		}
	}



	public static void play_walk(){

		if(!mute){
			walk1.play(walk1V);
		}
	}

	public static void play_loop1(){


		
		if(!music1Played && Gdx.graphics.getFramesPerSecond()>10){
			music1.play();
			music1Played=true;
		}
		

	}


	public static void stop_loop1(){

		music1.stop();
		music1Played=false;
	}





	public static void play_talk(){
		if(!mute){
			talk[(int)((Math.random()*6))].play(0.7f);
		}
	}

	public static void play_finish(){
		if(!mute){
			finish[(int)((Math.random()*4))].play();
		}
	}


	public static void update_walking(float dt){
		if (HelloWorld.nombreMonstresVisibles>0){

			walk1V=0.1f+(float)Math.sqrt((HelloWorld.nombreMonstresVisibles/30.0));


		}else{
			walk1V=0;
		}
		walk1dt+=dt;
		if (walk1dt>=(0.3)){
			play_walk();
			walk1dt=0;
		}

	}






}
