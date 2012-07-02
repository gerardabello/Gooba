package com.gerardas.goobaDesktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Strings {

	public static String start;
	public static String resetlevel;
	public static String selectlevel;
	public static String level;
	public static String complete;
	public static String soundOn;
	public static String soundOff;
	public static String mainMenu;
	public static String retorna;
	public static String segur;
	public static String si;
	public static String fast;
	public static String help;
	public static String no;
	public static String editor;
	public static String helpT;


	public static String RS10;
	public static String RS11;
	public static String RS12;
	public static String RS13;
	public static String RS14;

	public static String RS20;
	public static String RS21;
	public static String RS22;
	public static String RS23;
	public static String RS24;
	public static String RS25;
	public static String RS26;
	public static String RS27;
	public static String RS28;

	public static String RS30;

	public static String RS40;
	public static String RS41;

	public static String RS110;
	public static String RS111;
	
	public static String RS210;
	public static String RS211;
	
	public static String RS310;
	public static String RS311;
	
	public static String RS410;
	
	public static String RS510;
	public static String RS511;
	
	public static String RS610;
	public static String RS611;
	public static String RS612;
	public static String resolution;
	public static String send;

	public static void setStrings(int language){
		switch (language) {
		case 0:   //english (reference)
			start="Play";
			editor="Editor";
			help="Help";
			resetlevel="Erase";
			selectlevel="Levels";
			level="Level";
			complete="Complete!";
			soundOn="Mute";
			soundOff="Unmute";
			mainMenu="Main Menu";
			retorna="Return";
			segur="Do you want to erase this level?";
			no="No";
			si="Yes";	
			
			helpT="This is a level editor. Here you can create levels and test them as easily as you play the game. You can send them and they might be included in a future update. It's important to send the levels with the solution (arrows).\n\n" +
					"When sending the level be sure to choose an email application like GMail. You can add your name in the email and it will appear at the top of your level.\n\n"+
					"Drag your finger horizontally where the arrows are to show more objects.";
			
			send="SEND";
			
			fast="FAST!";
			
			resolution = "Resolution not supported, sorry!";


//			RS10="Hello! \nI'm King Gooba!";
//			RS11="We should help all the goobas escape!";
//			RS12="They go out from this arrow";
//			RS13="And have to escape through that hole in the ground";
//			RS14="Touch the play button to watch that happen";

			
			RS20="Hello, I'm king Gooba!";
			
			RS21="You must lead Goobas to this square hole in the ground";
			RS22="They appear through this big arrow with the metal thing on it";
			RS23="Now let's put an arrow to show them where to go";
			RS24="All the arrows you need are at the bottom of your screen";
			RS25="Drag the white arrow to this position";
			RS26="You put the arrow in the wrong position, hold your finger over it again";
			RS27="Quickly drag your finger across the arrow to change its direction";
			RS28="Now you can touch the play button";

			RS30="Goobas cannot pass through rocks!";

			RS40="Each Gooba has to exit through the hole of its colour";
			RS41="I'm sure you'll guess what the colors of the arrows mean";

			RS110="Locks and keys...";
			RS111="No explanation needed";
			
			RS210="The color blocks are like light filters";
			RS211="They only let through goobas of a certain colour";
			
			RS310="First do not use the STOP";
			RS311="Then use it to see what happens";
			
			RS410="Paint is really useful in this kind of situation";

			RS510="That purple thing looks strange";
			RS511="I wonder why there's two of them";
			
			RS610="The Gooba Intelligence Agency informs:";
			RS611="From now on it looks like there wont be any new objects";
			RS612="The difficulty does not diminish, though";

			break;
		case 1:   //catala
			start="Començar";
			resetlevel="Esborrar";
			selectlevel="Nivells";
			level="Nivell";
			complete="Superat!";
			soundOn="Activar So";
			soundOff="Apagar So";
			segur="Vols esborrar aquest nivell?";

			no="No";
			si="Si";


			RS10="Hola! \nSoc el rei Gooba!";
			RS11="Hem d'ajudar als Goobas a escapar!";
			RS12="Surten per aquesta fletxa";
			RS13="I han d'escapar per aquests forats al terra";
			RS14="Apreta el boto 'Play' per veure-ho";

			RS20="Hola de nou!";
			RS21="Aquest cop no sera tant senzill";
			RS22="Hauras d'utilitzar una fletxa per guiar els Goobas a la salvacio";
			RS23="Apreta el boto '+'";
			RS24="Manten el dit a sobre la fletxa blanca";
			RS25="Arrossega la fletxa fins a aquesta posicio";
			RS26="Per tornar a moure la fletxa, manten el dit a sobre ella";
			RS27="Arrossega el dit a traves de la fletxa per canviar la seva direccio";
			RS28="Ara ja pots apretar el boto 'Play'";

			RS30="Els Goobas no poden atravessar les roques!";

			RS40="Cada Gooba ha d'escapar pel forat del seu color";
			RS41="Segur que podras esbrinar per a que s'usen les fletxes de colors";

			RS110="Claus i candaus...";
			RS111="Sembla complicat";
			break;

		}

	}
}
