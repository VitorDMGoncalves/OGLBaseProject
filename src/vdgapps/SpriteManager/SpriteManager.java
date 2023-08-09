package vdgapps.SpriteManager;

import java.io.IOException;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.OGLPrimitives.Quad;

import android.content.Context;
import android.util.Log;


public class SpriteManager 
{
	private static HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	
	//removes a sprite from the sprite hashMap
	public static void remSprite(String name)
	{
		sprites.remove(name);
	}
	
	//loads a sprite in to the sprites HashMap
	public static void load(String name, Context ctx)
	{
		
		try 
		{
			Sprite s = SpriteLoader.loadSprite(name, ctx);
			sprites.put(name, s);
		} catch (IOException e) 
		{
			Log.d("SpriteManager Error", e.toString());
			e.printStackTrace();
		}
		
	}
	
	
	//binds a sprite stored in the sprites hashMap to a quad
	public static void bind(String name, Quad q, Context ctx, GL10 gl)
	{
		Sprite s = sprites.get(name);
		if(s !=null)
		{
			s.bind(q, ctx, gl);
		}
	}
	
	//draws a stored sprite in to its binded quad
	public static void draw(String name, GL10 gl)
	{
		Sprite s = sprites.get(name);
		if(s !=null)
		{
			s.draw(gl);
		}
	}
}
