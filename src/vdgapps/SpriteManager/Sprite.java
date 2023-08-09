package vdgapps.SpriteManager;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.OGLPrimitives.Quad;
import vdgapps.OGLUtils.Timer;
import vdgapps.TextureManager.TextureManager;
import android.content.Context;


public class Sprite 
{
	private String spriteName;
	private String textureName;
	private ArrayList<SpriteFrame> spriteFrames;
	private int frameRate;
	private Quad quad;
	private Timer timer;
	private boolean hasAlfaChannel;
	private boolean isBinded;
	private Context ctx;
	private int currentFrame;
	private long lastTimeStamp;
	
	
	public Sprite(String name, int frameRate, ArrayList<SpriteFrame> frames, String texture, boolean hasAlfaChannel)
	{
		this.spriteName = name;
		this.spriteFrames = frames;
		this.frameRate = frameRate;
		this.textureName = texture;
		this.isBinded = false;
		this.hasAlfaChannel = hasAlfaChannel;
		this.lastTimeStamp = 0;
		this.timer = new Timer();
		
	}
	
	
	public boolean isBinded()
	{
		return this.isBinded;
	}
	
	//binds the sprite to a quad
	public void bind(Quad q, Context ctx, GL10 gl)
	{
		this.isBinded = true;
		this.quad = q;
		this.ctx = ctx;
		TextureManager.loadTexture(gl, ctx, textureName);
	}
	
	private void nextFrame()
	{
		
		if(timer.getTimerElapsedMs() - lastTimeStamp >=this.frameRate)
		{
			lastTimeStamp = (long)timer.getTimerElapsedMs();
			if(currentFrame == spriteFrames.size()-1)
				currentFrame = 0;
			else
				currentFrame ++;
		}
		
	}
	
	//draws  the sprite to the binded quad
	public void draw(GL10 gl)
	{
		if(isBinded)
		{
			//timer.recordTime();
			nextFrame();
			SpriteFrame f = spriteFrames.get(currentFrame);
			quad.setTextCoords(f.getBottomLeftCorner(), f.getTopLeftCorner(), f.getTopRightCorner(), f.getBottomRightCorner());
			TextureManager.bindTexture(gl, textureName);
			quad.draw(gl);
		}
		
	}
	
}
