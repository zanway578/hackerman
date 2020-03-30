package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HackermanGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = screenSize.width;
		config.height = screenSize.height;
		new LwjglApplication(new HackermanGame(), config);
	}
}
