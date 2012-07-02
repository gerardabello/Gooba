package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {

	private SpriteBatch spriteBatch;
	private Texture grass;
	private Game myGame;
	private int resolution;
	private int count=0;

	/**
	 * Constructor for the splash screen
	 * @param g Game which called this splash screen.
	 */
	public SplashScreen(Game g)
	{
		myGame = g;
		if (Gdx.graphics.getWidth()+Gdx.graphics.getHeight()<760){
			resolution=1;
		}else if (Gdx.graphics.getWidth()+Gdx.graphics.getHeight()<1200){
			resolution=1;
		}else{
			resolution=2;
		}
		spriteBatch = new SpriteBatch();
		grass = new Texture(Gdx.files.internal("img/"+Integer.toString(resolution)+"/grass.png"));

	}

	@Override
	public void render(float delta){

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();

		spriteBatch.disableBlending();

		for(int i = 0; i <= (Gdx.graphics.getWidth()/grass.getWidth()); i++) {
			for(int j = 0; j <= (Gdx.graphics.getHeight()/grass.getHeight()); j++) {
				spriteBatch.draw(grass,grass.getWidth()*i ,grass.getHeight()*j ); 
			}
		}

		spriteBatch.end();


		if(count==2){

			count=0;
			Main.doneSplash=true;
			if(Main.GameS==null){
			Main.GameS=new HelloWorld();
			myGame.setScreen(Main.GameS);
			}else{
				Main.GameS.resume();
				myGame.setScreen(Main.GameS);
			}

		}else{
			count++;
		}

	}

	@Override
	public void show(){

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}


}
