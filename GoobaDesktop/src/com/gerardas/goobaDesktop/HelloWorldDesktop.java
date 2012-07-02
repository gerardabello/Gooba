package com.gerardas.goobaDesktop;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class HelloWorldDesktop {
	public static void main (String[] argv) {
		
		new JoglApplication(new Main(null), "Hello World",480,800, false);
	}
}
