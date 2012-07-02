package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Rei {

	public static Texture texture;
	public static Texture textureb;
	public static Texture puntexture;

	public static boolean bvisible=false;
	public static boolean visible=true;
	public static int texx=0;
	public static int texy=0;
	public static int stage=0;
	public static int pstage=0;   //previous stage
	public static int nbocas=0;    //nombre de vegades que ha obert la boca, s'han de fer 3 o 4
	public static float x=-64;
	public static float dx=0;
	public static float dt=0;
	public static String text="sdfa sdfdfs ds fdsfsdaf asdf sadf sdaadf d sadf sdf ssddf";
	public static String atext="sdfa sdfdfs ds fdsfsdaf asdf sadf sdaadf d sadf sdf ssddf";

	public static boolean sortint=false;
	public static boolean entrant=false;
	public static float anidt=0;

	public static double pundt=0;
	public static float punX,punY,punX2,punY2;   //els 2 estan per implementar, es per fer que la bola verda assenyali com arrosegar el dit
	public static Boolean punV=false;


	public static float jumpy=0;
	public static float jumpv=0;
	public static int jumptexx=0;
	
	public static final float tempsText=4.5f;

	public static void update(){

		if(HelloWorld.cPack!=HelloWorld.editPack){

		if(sortint){entrant=false;}

		if (entrant){
			texy=2;
			x=x+(Gdx.graphics.getDeltaTime()*50*HelloWorld.resolution);
			dx=dx+(Gdx.graphics.getDeltaTime()*50*HelloWorld.resolution);
			if (x>=0){
				x=0;
				entrant=false;
				texy=0;
				dx=0;
				texx=0;
			}
		}

		if (sortint){
			texy=1;
			x=x-(Gdx.graphics.getDeltaTime()*50*HelloWorld.resolution);
			dx=dx+(Gdx.graphics.getDeltaTime()*50*HelloWorld.resolution);
			if (x<=-64*HelloWorld.resolution){
				x=-64*HelloWorld.resolution;
				sortint=false;
				texy=0;
				dx=0;
				texx=0;
			}
		}

		if (dx>=8*HelloWorld.resolution){
			texx++;
			if (texx==4){
				texx=0;
			}
			dx=0;
		}

		pundt+=(double)Gdx.graphics.getDeltaTime();
		if(pundt>=1){
			pundt=pundt-1;
		}

		if(x==0){
			dt=dt+Gdx.graphics.getDeltaTime();
		}else{
			dt=0;
		}


		//animacio a x==0
		if (x==0){

			if (pstage!=stage){
				pstage=stage;
				nbocas=0;
			}else if (nbocas<=3){
				anidt=anidt+Gdx.graphics.getDeltaTime();
				if (anidt>=0.1){
					anidt=0;
					if(texx==0){
						texx=3;
					}else{
						texx=0;
						nbocas++;
					}
				}

			}else if (stage==0 && HelloWorld.cLevel<=2){
				anidt=anidt+Gdx.graphics.getDeltaTime();
				if (anidt>=0.1){
					anidt=0;
					if(texx==0 || texx==1){
						texx=2;
					}else{
						texx=0;
					}
				}

			}else{
				if(texx==2 || texx==3 ){texx=0;} //per si es queda en el frame 2 de saludar o parlar
				if (Math.random()>0.99 && texx==0){
					texx=1;
					anidt=0;
				}
				if (texx==1){
					anidt=anidt+Gdx.graphics.getDeltaTime();
					if (anidt>=0.1){
						texx=0;
						anidt=0;
					}
				}
			}
		}





		Rei.comportament();

		if(text!=atext && x==0 && bvisible){
			atext=text;
			Sounds.play_talk();
		}
		}else{
			visible=false;
		}

	}

	public static void comportament(){

		punV=false;
		text=""; 
		

		switch (HelloWorld.cPack){

		case 1:
			switch (HelloWorld.cLevel) {


			case 1:

				
				if(stage>5 && HelloWorld.drags[0].cunitats==1){
					stage=5;
				}


				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS20;  
					if(HelloWorld.drags[0].cunitats==0){  
						if (HelloWorld.fieldPositions[5][7].tipus=='b'){
							stage=6;
						}else{
							stage=7;
						}
					}

					if (dt>=5){
						dt=0;
						stage++;
					}
					break;

				case 1:
					text=Strings.RS21;
					punX=HelloWorld.fieldPositions[1][7].posicio.x;
					punY=HelloWorld.fieldPositions[1][7].posicio.y-5*HelloWorld.resolution;
					punV=true;
					if (dt>=5){
						dt=0;
						stage++;
					}
					break;

				case 2:
					text=Strings.RS22;
					punX=HelloWorld.fieldPositions[5][10].posicio.x;
					punY=HelloWorld.fieldPositions[5][10].posicio.y-5*HelloWorld.resolution;
					punV=true;
					if (dt>=5){
						dt=0;
						stage++;
					}
					break;

				case 3:
					text=Strings.RS23;

					punV=false;
					if (dt>=5){
						dt=0;
						stage++;
					}
					break;

				case 4:
					text=Strings.RS24;

					punX=HelloWorld.drags[0].x+16*HelloWorld.resolution;
					punY=HelloWorld.drags[0].y+16*HelloWorld.resolution;
					punV=true;


					if (dt>=5){
						dt=0;
						stage++;
					}
					break;

				case 5:
					text=Strings.RS25;
					punX=HelloWorld.fieldPositions[5][7].posicio.x;
					punY=HelloWorld.fieldPositions[5][7].posicio.y-5*HelloWorld.resolution;
					punV=true;
					if (!HelloWorld.colocant && HelloWorld.drags[0].cunitats ==0){
						if(HelloWorld.fieldPositions[5][7].tipus=='b'){
							stage=6;
						}else if(HelloWorld.fieldPositions[5][7].frame!=2){
							stage=7;
						}else if(HelloWorld.fieldPositions[5][7].frame==2){
							stage=8;
						}
					}
					break;

				case 6:
					text=Strings.RS26;
					if (HelloWorld.colocant){
						dt=0;
						stage=5;
					}
					break;

				case 7:
					text=Strings.RS27;

					if (HelloWorld.colocant){
						dt=0;
						stage=5;
					}else if(HelloWorld.fieldPositions[5][7].frame==2 ){
						stage=8;
					}
					break;

				case 8:
					text=Strings.RS28;
					punX=HelloWorld.botons[3].x+HelloWorld.botons[3].w/2;
					punY=HelloWorld.botons[3].y+HelloWorld.botons[3].h/2;
					punV=true;
					if(HelloWorld.fieldPositions[5][7].frame!=2 && HelloWorld.fieldPositions[5][7].tipus!='b' && !HelloWorld.colocant){
						stage=7;
						punV=false;
					}else if(HelloWorld.colocant){
						dt=0;
						stage=5;
					}
					
					if (HelloWorld.running ){
						sortint=true;
						punV=false;
						stage++;
					}
					break;
				}
				break;

			case 2:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS30;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;

			case 3:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS40;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS41;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;


			case 11:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS110;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS111;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;


				




			case 99:
				switch (stage) {
				case 0:

					break;
				}
				break;

			default:
				x=-64*HelloWorld.resolution;
				entrant=false;
				sortint=false;
				bvisible=false;
				visible=false;
				break;
			}


			break;
		case 2: //pack 2

			switch (HelloWorld.cLevel) {


			case 1:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS210;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS211;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;


			case 11:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS310;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS311;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;

			default:
				x=-64*HelloWorld.resolution;
				entrant=false;
				sortint=false;
				bvisible=false;
				visible=false;
				break;




			}






			break;





		case 3: //pack 3

			switch (HelloWorld.cLevel) {


			case 1:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS410;
					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;

				}
				break;


			case 11:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS510;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS511;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;


			default:
				x=-64*HelloWorld.resolution;
				entrant=false;
				sortint=false;
				bvisible=false;
				visible=false;
				break;



			}






			break;
			
			
			
		case 4: //pack 4 (bonus 1)

			switch (HelloWorld.cLevel) {


			case 1:
				switch (stage) {
				case 0:
					visible=true;
					bvisible=true;
					if(x!=0){entrant=true;}
					text=Strings.RS610;
					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;


				case 1:

					text=Strings.RS611;

					if (dt>=tempsText){
						dt=0;
						stage++;
					}
					break;
					
					
				case 2:

					text=Strings.RS612;

					if (dt>=tempsText){
						dt=0;
						sortint=true;
						stage++;
					}
					break;
				}
				break;


			default:
				x=-64*HelloWorld.resolution;
				entrant=false;
				sortint=false;
				bvisible=false;
				visible=false;
				break;



			}






			break;



		}
	}


}
