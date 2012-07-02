package com.gerardas.goobaDesktop;

import java.text.FieldPosition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Monster {
	public int dir; //0 aball, 1 amunt, 2 esquerra, 3 dreta
	public int adir;   //dir anterior

	public int color; // 0 vermell, 1 blau, 2 lila, 3 verd
	public Vector2 position,xocposition;

	public float afdt;

	public int z;

	public float mask;

	public boolean mort;


	public int cx;
	public int cy;

	public int nx;
	public int ny;

	public static Texture			mTexture;


	public int frame;
	public int mortframe;

	public static boolean   fiIteracions;

	public float state3dt;

	public boolean xocaran;
	public boolean xocarasol;
	public boolean xocaracandau;

	public int state;

	//0 corrent
	//1 sortint
	//2 baixant
	//3 morint
	//4 aturat

	public boolean      visible;

	public float alpha;




	public Monster(int inz){
		color=0;
		z=inz; 

		fiIteracions=false;
		dir=0;
		adir=dir;

		cx=4;  //per estalviarnos problemes, pero no te cap sentit
		cy=4;


		visible=false;
		state=0;

		state3dt=0;
		mort=false;

		mask=0;
		alpha=1;

		frame=0;
		mortframe=0;

		nx=cx;
		ny=cy;

		if (dir==0){
			ny=ny-1;
		}else if(dir==1){
			ny=ny+1;
		}else if(dir==2){
			nx=nx-1;
		}else if(dir==3){
			nx=nx+1;
		}


		position=new Vector2(HelloWorld.fieldPositions[nx][ny].posicio.x,HelloWorld.fieldPositions[nx][ny].posicio.y);

		xocposition=new Vector2(position);

		updateColor(color);
	}

	public void updateColor(int icolor){

		color=icolor;

	}

	public void spawn(int icx, int icy,int idir){
		fiIteracions=false;

		visible=true;
		xocaran=false;
		state=0;
		state3dt=0;
		cx=icx;
		cy=icy;
		nx=icx;
		ny=icy;
		dir=idir;
		adir=dir;
		mask=0;
		alpha=1;
		mort=false;
		xocarasol=false;
		xocaracandau=false;

		position.set(HelloWorld.fieldPositions[nx][ny].posicio);
		



	}



	public void update(float dt){
		fiIteracions=false;

		afdt=afdt+dt;


		if (HelloWorld.adt>=0.5){
			adir=dir;
		}

		if (state==3){   //aixo es fa perque al seguent frame del tick, els montres morts ja no puguin xocar mes
			mort=true;
		}

		if (HelloWorld.tick && !mort){//quan arriba a la casella
			adir=dir;



			//Aqui es descriu que fa cada objecte
			switch (HelloWorld.fieldPositions[nx][ny].tipus) {

			//fletxes multicolor
			case 'a':

				dir=HelloWorld.fieldPositions[nx][ny].frame;
				break;



				//fletxes vermelles
			case 'f':
				if(color==0){dir=HelloWorld.fieldPositions[nx][ny].frame;}
				break;


				//fletxes verdes
			case 'j':
				if(color==3){dir=HelloWorld.fieldPositions[nx][ny].frame;}
				break;


				//fletxes blaves
			case 'n':
				if(color==1){dir=HelloWorld.fieldPositions[nx][ny].frame;}
				break;


				//fletxes liles
			case 'r':
				if(color==2){dir=HelloWorld.fieldPositions[nx][ny].frame;}
				break;


				//teletransport
			case 'T':
				if(state!=4){
					telebuscar:
						for(int i = 0; i < HelloWorld.cols; i++) {  
							for(int j = 0; j < HelloWorld.rows; j++) {
								if(HelloWorld.fieldPositions[i][j].tipus=='T'){
									if (!HelloWorld.fieldPositions[i][j].ocupada){
										

										if(i==nx&&j==ny){


										}else{
											nx=i;
											ny=j;
											Sounds.play_teletrans();
											HelloWorld.transparenciaTele=0.7f;
											break telebuscar;
										}
									}else{
										state=4;
										HelloWorld.fieldPositions[nx][ny].ocupada=true;
									}
								}
							}
						}
				}
				break;



				//sprais
			case 'w':
				color=0;
				break;
			case 'x':
				color=1;
				break;
			case 'y':
				color=2;
				break;
			case 'z':
				color=3;
				break;








			case 'M':
				if (color==0){
					if (state==2){visible=false;}
					state=2;
					Sounds.play_down();
				}
				break;

			case 'N':
				if (color==1){
					if (state==2){visible=false;}
					state=2;
					Sounds.play_down();
				}
				break;

			case 'O':
				if (color==2){
					if (state==2){visible=false;}
					state=2;
					Sounds.play_down();
				}
				break;

			case 'P':
				if (color==3){
					if (state==2){visible=false;}
					state=2;
					Sounds.play_down();
				}
				break;


			case 'R': //clau
				if (HelloWorld.fieldPositions[nx][ny].obert==0){
					HelloWorld.fieldPositions[nx][ny].obert=1;
					Field.trepclaus++;
					Sounds.play_clau();

				}
				break;


			case 'Q':  //stop
				if (state!=4){
					state=4;
				}else{
					state=0;
				}
				break;






			default:
				break;
			}





			cx=nx;
			cy=ny;


			if (dir==0){
				ny=ny-1;
			}else if(dir==1){
				ny=ny+1;
			}else if(dir==2){
				nx=nx-1;
			}else if(dir==3){
				nx=nx+1;
			}


			if (nx <0 || nx >= HelloWorld.cols || ny <0 || ny >= HelloWorld.rows || HelloWorld.fieldPositions[nx][ny].ocupada==true){
				nx=cx;
				ny=cy;
				if (state==0){HelloWorld.fieldPositions[cx][cy].ocupada=true;state=4;HelloWorld.perdut=true;}
			}






			//Mirem  la casella seguent 
			if (nx!=cx || ny!=cy && !mort){
				switch (HelloWorld.fieldPositions[nx][ny].tipus) {   

				case 'M':
					if (color==0 && state==0){
						state=1;
						HelloWorld.fieldPositions[nx][ny].ReqObert=true;
					}
					break;

				case 'N':
					if (color==1&& state==0){
						state=1;
						HelloWorld.fieldPositions[nx][ny].ReqObert=true;
					}
					break;

				case 'O':
					if (color==2&& state==0){
						state=1;
						HelloWorld.fieldPositions[nx][ny].ReqObert=true;
					}
					break;

				case 'P':
					if (color==3&& state==0){
						state=1;
						HelloWorld.fieldPositions[nx][ny].ReqObert=true;
					}
					break;



				case 'A':  //nomes
					if(color!=0){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'B':
					if(color!=1){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'C':
					if(color!=2){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'D':
					if(color!=3){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;


				case 'E':  //no
					if(color==0){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'F':
					if(color==1){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'G':
					if(color==2){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;
				case 'H':
					if(color==3){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
					}else{
						if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					}
					break;	







				case 'w':  //sprais
					if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					break;
				case 'x':
					if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					break;
				case 'y':
					if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					break;
				case 'z':
					if(state==0){HelloWorld.fieldPositions[nx][ny].ReqObert=true;}
					break;






				case 'S': //candau
					if (HelloWorld.fieldPositions[nx][ny].obert==0 && Field.trepclaus<Field.numclaus){
						xocaran=true;xocposition.set(position);
						xocarasol=true;
						xocaracandau=true;
					}

					break;

				case 'Z': //pedra
					xocaran=true;xocposition.set(position);
					xocarasol=true;
					break;


				}
			}
		}



		//aquesta part esta copiada tambe a fora del tick perque de vegades l'update es fa just abans de que quedi ocupada aixi ens asegurem que en el seguent frame el monstre s'aturi
		if (nx <0 || nx >= HelloWorld.cols || ny <0 || ny >= HelloWorld.rows || HelloWorld.fieldPositions[nx][ny].ocupada==true){
			nx=cx;
			ny=cy;
			if (state==0){HelloWorld.fieldPositions[cx][cy].ocupada=true;state=4;}
		}




		if (state==2 || state==3 || state ==4){
			nx=cx;
			ny=cy;
		}

		if (state==0){
			if (xocaran && HelloWorld.adt>=0.5f){
				state=3;
				HelloWorld.perdut=true;
				Sounds.play_cop();
				xocposition.set(position);
			}
		}



		if (state==0 || state ==1){
			position.set(HelloWorld.fieldPositions[cx][cy].posicio);
			position.lerp(HelloWorld.fieldPositions[nx][ny].posicio, HelloWorld.adt);

		}else if(state==4){
			position.set(HelloWorld.fieldPositions[cx][cy].posicio);
			visible=true;

		}else if (state==3){

			dir=adir;
			state3dt=state3dt+dt*(HelloWorld.speed);

			position.set(HelloWorld.fieldPositions[cx][cy].posicio);
			if (xocaran){
				position.set(xocposition);
			}

			mortframe=(int)(state3dt*5);
			if (mortframe>=3){mortframe=3;}

			if (state3dt>=0.9){ visible=false;HelloWorld.fieldPositions[cx][cy].ocupada=false; }

			switch (dir) {
			case 0:
				position.y=position.y+15*(float)(Math.sqrt(state3dt))*HelloWorld.resolution;
				break;
			case 1:
				position.y=position.y-15* (float)(Math.sqrt(state3dt)) *HelloWorld.resolution;
				break;
			case 2:
				position.x=position.x+15*(float)(Math.sqrt(state3dt))*HelloWorld.resolution;
				break;
			case 3:
				position.x=position.x-15*(float)(Math.sqrt(state3dt))*HelloWorld.resolution;
				break;
			}


			//position.set(HelloWorld.fieldPositions[cx][cy].posicio);
			//position.lerp(HelloWorld.fieldPositions[nx][ny].posicio, -HelloWorld.adt/2);
		}else if (state==2){

			position.set(HelloWorld.fieldPositions[cx][cy].posicio);
		}





		//animacio

		switch (state) {
		case 0:

			alpha=1;
			if (afdt>0.1){
				if (nx==cx & ny == cy){
					frame=0;
				}else{
					frame=frame+1;
					if (frame==4){
						frame=0;
					}
					afdt=0;
				}
			}
			mask=0;
			break;



		case 1:
			mask=-((((HelloWorld.adt*2)*(HelloWorld.adt*2))*40)-(((HelloWorld.adt*2)*(HelloWorld.adt*2)*(HelloWorld.adt*2))*20))*HelloWorld.resolution;
			position.y=position.y-mask;
			break;

		case 2:
			mask=(60*HelloWorld.adt+100*HelloWorld.adt*HelloWorld.adt)*HelloWorld.resolution;
			if (mask>32*HelloWorld.resolution){
				visible=false;
				HelloWorld.hanarribat++;  //ha arribat
			}

			nx=cx;
			ny=cy;
			position.y=position.y-mask;


			break;

		case 3:


		default:
			break;
		}

	}
}
