package com.gerardas.goobaDesktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.gerardas.goobaDesktop.HelloWorld.ActionResolver;

public class Main extends Game{
	
	public static Screen Splash,GameS;
	public static boolean doneSplash=false;

	
	public Main(ActionResolver act){
		HelloWorld.actionResolver = act;
	}
	
	@Override
	public void create() {
		Splash=new SplashScreen(this);
		this.setScreen(Splash);
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub
		if (doneSplash){
			GameS.render(0);
		}else{
			Splash.render(0);
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		if (doneSplash){
			GameS.resume();
		}else{
			Splash.resume();
		}
		
	}

}
