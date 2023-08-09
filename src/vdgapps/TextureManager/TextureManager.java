package vdgapps.TextureManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.util.Log;

public class TextureManager 
{

	private static HashMap<String, Integer> textures =  new HashMap<String, Integer>();
	private static int lastBind = -1;
	
	public static boolean loadTexture(GL10 gl ,Context ctx, String name)
	{
		Boolean loaded = false;
		InputStream is;
		int t = -1; //last texture binded
		
		try 
		{
			is = ctx.getAssets().open(name);
			t = TextureLoader.load(gl, is);
			is.close();
			loaded = true;
		} 
		catch (IOException e) 
		{
			Log.d("Error", "failed to load: " + name);
		}
		
		if(t >=0)
		{
			textures.put(name, t);
		}
		return loaded;
	}
	
	public static void bindTexture(GL10 gl, String name)
	{
		
		//if the texture is already loaded we bind it
		if(textures.containsKey(name))
		{
			int t = textures.get(name);
			//if the last binded texture is the same as the new texture, we don't need to bind it again
			if(t != lastBind)
			{
				gl.glBindTexture(GL10.GL_TEXTURE_2D, textures.get(name));
				lastBind = t;
			}
		}
	}
}
