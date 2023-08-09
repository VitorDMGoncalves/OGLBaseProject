package vdgapps.SpriteManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import vdgapps.MathUtils.Vector2D;

import android.content.Context;
import android.util.Log;

public class SpriteLoader 
{
	
	//creates the Sprite frames
	private static ArrayList<SpriteFrame> createFrames(int frameXsize, int frameYsize, int frameCount, int textureXsize, int textureYsize)
	{
		ArrayList<SpriteFrame> frames = new ArrayList<SpriteFrame>();
		int blcx = 1; //bottom left corner x
		int blcy = frameYsize; //bottom right corner y
		int trcx = frameXsize; //top left corner x
		int trcy = 1; //top right corner y
		int fc = 0;
		for(int i=1; i<= textureXsize && fc < frameCount; i+=frameXsize)
		{
			fc++;
			
			SpriteFrame f = new SpriteFrame(new Vector2D((float)blcx/(float)textureXsize, (float)blcy/(float)textureYsize), 
											new Vector2D((float)trcx/(float)textureXsize, (float)trcy/(float)textureYsize));
			
			blcx += frameXsize;
			trcx += frameXsize;
			
			frames.add(f);
		}
		return frames;
	}
	

	
	public static Sprite loadSprite(String name, Context ctx) throws IOException
	{
		
		/* sprite template 
		#Sprite test
		#n->name
		#t-> texture
		#tsx-> texture x size
		#tyx-> texture y size
		#ac-> alfa channel
		#fr-> frame rate
		#fc-> frame count
		#fsx-> frame x size
		#fst-> frame y size
		n testSprite
		t testTexture.png
		tsx 512
		tsy 128
		ac 0
		fr 200
		fc 5
		fsx 35
		fsy 60
		*/
		
		String spriteName = "";
		String textureName = "";
		ArrayList<SpriteFrame> spriteFrames;
		int frameRate = 0;
		int frameCount = 0;
		int frameXsize = 0;
		int frameYsize = 0;
		int textureXsize= 0;
		int textureYsize = 0;
		boolean hasAlfaChannel = false;
		
		
		InputStream is = ctx.getAssets().open(name); //Sprite file
		BufferedReader br = new BufferedReader(new InputStreamReader(is), 8192);
		
		String newLine;
		
		while (((newLine = br.readLine()) != null))
		{
			String[] slist =  newLine.split(" ");
			Log.d("lenght", "" + slist.length);
			//search for name
			if(slist[0].equals("n") &&slist.length == 2)
			{
				spriteName = slist[1];
			}

			//search for texture
			if(slist[0].equals("t") &&slist.length == 2)
			{
				textureName = slist[1];
			}
			
			//search for alfa channel 
			if(slist[0].equals("t") &&slist.length == 2)
			{
				hasAlfaChannel = Boolean.parseBoolean(slist[1]);
			}
			
			//search for frame rate
			if(slist[0].equals("fr") &&slist.length == 2)
			{
				frameRate = Integer.parseInt(slist[1]);
			}
			
			//search for frame count
			if(slist[0].equals("fc") &&slist.length == 2)
			{
				frameCount = Integer.parseInt(slist[1]);
			}
			
			//search for frame x size
			if(slist[0].equals("fsx") &&slist.length == 2)
			{
				frameXsize = Integer.parseInt(slist[1]);
			}
			
			//search for frame y size 
			if(slist[0].equals("fsy") &&slist.length == 2)
			{
				frameYsize = Integer.parseInt(slist[1]);
			}
			
			//search for texture x size
			if(slist[0].equals("tsx") &&slist.length == 2)
			{
				textureXsize = Integer.parseInt(slist[1]);
			}
			
			//search for texture y size
			if(slist[0].equals("tsy") &&slist.length == 2)
			{
				textureYsize = Integer.parseInt(slist[1]);
			}
		}
		
		if (br != null)
		{
			br.close();
			is.close();
		}
		
		Log.d("SpriteManager", "sprite " + spriteName + " loaded.");
		spriteFrames = createFrames(frameXsize, frameYsize, frameCount, textureXsize, textureYsize);
		Sprite s= new Sprite(spriteName, frameRate, spriteFrames, textureName, hasAlfaChannel);
		
		return s;
	}
}
