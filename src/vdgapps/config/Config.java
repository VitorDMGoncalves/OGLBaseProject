package vdgapps.config;

import android.content.Context;

public class Config 
{
	public static Context context;
	private static final String userdir = System.getProperty("user.dir");
	public static final String worldsPath = userdir + "\\worlds\\";
	public static final String modelsPath = userdir + "\\models\\";
	public static final String texturesPath = userdir + "\\textures\\";
	public static final String heightmapsPath = userdir + "\\heightmaps\\";
}
