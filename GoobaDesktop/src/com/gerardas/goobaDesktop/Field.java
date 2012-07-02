package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Field {
	public Vector2 posicio;
	public int x;
	public int y;

	public char tipus;
	public boolean ocupada;

	public int unitats;
	public int cunitats;

	public boolean fixa;
	public boolean gfixa;
	private float adt;


	public boolean ReqObert;
	public int obert;

	public int rate;
	public int ratec;

	public int frame;  //fa de dir, color, i frame


	public static Texture			mTexture;
	public static TextureRegion    rTexture;
	public static TextureRegion[][]    raTexture;

	public static int numclaus;
	public static int trepclaus;

	public int texx;
	public int texy;

	//TODO afegir mes fields
	//IDEES:
	//	Field que faci que durant 2 o 3 ticks, un monstre pugui atravessar parets o altres goobas
	//	estrella
	//	Forat blanc
	//	
	//	
	//	
	//	

	public Field(int i, int j,char inittipus){
		posicio= new Vector2(i*(Gdx.graphics.getWidth()/HelloWorld.cols) + (Gdx.graphics.getWidth()/(HelloWorld.cols*2)),(int) (j*((Gdx.graphics.getWidth()*1.2)/HelloWorld.rows) + ((Gdx.graphics.getWidth()*1.2)/(HelloWorld.rows*2)) + ((Gdx.graphics.getHeight()-(32*HelloWorld.resolution)-(Gdx.graphics.getWidth()*1.15 ))/1)));
		x=i;
		y=j;
		tipus=inittipus;
		ocupada = false;
		fixa=false;
		ratec=0;
		rate=0;
		gfixa=false;

		ReqObert=false;
		obert=0;

		texx=0;
		texy=1;

	}

	public void Update(float dt){


		if (HelloWorld.htick){
			if (tipus=='A' || tipus=='B' || tipus=='C' ||tipus=='D' ||tipus=='E' || tipus=='F' ||tipus=='G' || tipus=='H' || tipus=='M' || tipus=='N' || tipus=='O' || tipus=='P'   || tipus=='w'  || tipus=='x'  || tipus=='y'  || tipus=='z' || tipus=='T'){
				if (ReqObert==true){
					if(obert==0 && (tipus=='A' || tipus=='B' || tipus=='C' ||tipus=='D' ||tipus=='E' || tipus=='F' ||tipus=='G' || tipus=='H')){Sounds.play_color(true);}
					obert=1;


				}else{

					if(obert==1 && (tipus=='A' || tipus=='B' || tipus=='C' ||tipus=='D' ||tipus=='E' || tipus=='F' ||tipus=='G' || tipus=='H')){
						if(!ocupada){
							Sounds.play_color(false);
							obert=0;
						}
					}else{
						obert=0;
					}

				}
			}else if (tipus=='S'){
				if (Field.numclaus<=Field.trepclaus){
					obert=1;
					if(!Sounds.candausPlayed){
						Sounds.play_candaus();
						Sounds.candausPlayed=true;
					}
				}
			}
		}



		if (tipus=='I' || tipus=='J' ||tipus=='K' ||tipus=='L' ){




			if (HelloWorld.tick && !ocupada && cunitats>0){

				if (ratec>=rate){

					ratec=0;
					for(int j = 0; j < HelloWorld.monstres.length; j++) {
						if(HelloWorld.monstres[j].visible==false){

							cunitats--;

							HelloWorld.monstres[j].spawn(x, y, frame);


							switch (tipus) {
							case 'I':
								HelloWorld.monstres[j].updateColor(0);
								break;

							case 'J':
								HelloWorld.monstres[j].updateColor(1);
								break;

							case 'K':
								HelloWorld.monstres[j].updateColor(2);
								break;

							case 'L':
								HelloWorld.monstres[j].updateColor(3);
								break;	
							}


							break;
						}

					}                                   
				}else{
					ratec++;
				}
			}		
		}
	}

	public void SetTexture(){

		switch (tipus) {

		case 'b':
			texx=0;
			texy=0;
			frame=0;
			break;


		case 'a':
			texx=0;
			texy=1;
			break;	
		case 'f':
			texx=1;
			texy=0;
			break;
		case 'j':
			texx=2;
			texy=0;
			break;
		case 'n':
			texx=3;
			texy=0;
			break;
		case 'r':
			texx=4;
			texy=0;
			break;




		case 'A':  //nomes
			texx=1;
			texy=4;
			break;
		case 'B':
			texx=1;
			texy=6;
			break;
		case 'C':
			texx=1;
			texy=7;
			break;
		case 'D':
			texx=1;
			texy=5;
			break;


		case 'E':  //ns
			texx=3;
			texy=4;
			break;
		case 'F':
			texx=3;
			texy=6;
			break;
		case 'G':
			texx=3;
			texy=7;
			break;
		case 'H':
			texx=3;
			texy=5;
			break;



		case 'I':  //spawns
			texx=6;
			texy=0;
			break;
		case 'J':
			texx=7;
			texy=0;
			break;
		case 'K':
			texx=7;
			texy=4;
			break;
		case 'L':
			texx=6;
			texy=4;
			break;



		case 'M':  //GOALS
			texx=14;
			texy=4;
			frame=0;
			break;
		case 'N':
			texx=14;
			texy=6;
			frame=0;
			break;
		case 'O':
			texx=14;
			texy=7;
			frame=0;
			break;
		case 'P':
			texx=14;
			texy=5;
			frame=0;
			break;

		case 'w':  //sprais
			texx=12;
			texy=4;
			break;
		case 'x':
			texx=12;
			texy=6;
			break;
		case 'y':
			texx=12;
			texy=7;
			break;
		case 'z':
			texx=12;
			texy=5;
			break;


		case 'T': //teletransport
			texx=11;
			texy=0;
			frame=0;
			break;



		case 'R': //CLAU
			texx=8;
			texy=6;
			frame=0;
			break;

		case 'S': //CANDAU
			texx=8;
			texy=7;
			frame=0;
			break;

		case 'Q': //stop
			texx=10;
			texy=0;
			frame=0;
			break;

		case 'Z': //pedra
			texx=9;
			texy=0;
			frame=(int)( ((x+y)%4) );
			break;






		}		


	}
}
