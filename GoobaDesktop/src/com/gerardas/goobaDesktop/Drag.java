package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Drag {
	
	public char tipus;
	public int texx,texy;
	
	public boolean visible;
	
	public int unitats;
	public int cunitats;

	public int x,y;
	
	public Drag(char inittipus){
		tipus=inittipus;
		
		visible=false;
		unitats=0;
		

	}
	
	public void SetTexture(){
		
		switch (tipus) {
		case 'a':
			texx=0;
			texy=1;
			break;
			
		case 'b':
			texx=0;
			texy=0;
			visible=false;
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

		case 'M':  //GOALS
			texx=14;
			texy=4;

			break;
		case 'N':
			texx=14;
			texy=6;

			break;
		case 'O':
			texx=14;
			texy=7;

			break;
		case 'P':
			texx=14;
			texy=5;

			break;
			
			
		case 'T': //teletransport
			texx=11;
			texy=0;
			break;

		case 'R': //CLAU
			texx=8;
			texy=6;

			break;

		case 'S': //CANDAU
			texx=8;
			texy=7;

			break;

		case 'Q': //stop
			texx=10;
			texy=0;

			break;
			
			
		case 'Z': //pedra
			texx=9;
			texy=1;

			break;
	
			
			

		}		
		
		
	}

}
