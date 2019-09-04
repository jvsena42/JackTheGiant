package com.jvsena42.jackthegiant.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jvsena42.jackthegiant.GameMain;

import helpers.GameInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = GameInfo.WIDTH;
		config.height = GameInfo.HEIGH;

		new LwjglApplication(new GameMain(), config);
	}
}
