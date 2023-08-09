package vdgapps.TextureManager;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.khronos.opengles.GL10;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class TextureLoader 
{
	private static int genTexture(GL10 gl, Bitmap bitmap)
	{
		int[] textures = new int[1];
		

		//Generate one texture pointer...
		gl.glGenTextures(1, textures, 0);
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		//Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_MODULATE);
		
		//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		//Clean up
		bitmap.recycle();
		return textures[0];
	}
	
	public static int load(GL10 gl, InputStream texture) throws IOException
	{
		Bitmap bitmap = null;
		bitmap = BitmapFactory.decodeStream(texture);
		texture.close();
		texture = null;
		int tId =  genTexture(gl, bitmap);
		return tId;
	}
	
	public static int load(GL10 gl, Bitmap bitmap) throws IOException
	{
		if (bitmap != null)
			return genTexture(gl, bitmap);
		else return 0;
	}
}
