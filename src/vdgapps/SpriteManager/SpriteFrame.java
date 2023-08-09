package vdgapps.SpriteManager;

import vdgapps.MathUtils.Vector2D;

public class SpriteFrame 
{
	private Vector2D bottomLeftCorner;
	private Vector2D bottomRightCorner;
	private Vector2D topLeftCorner;
	private Vector2D topRightCorner;
	
	
	
	public SpriteFrame(Vector2D bottomLeftCorner, Vector2D topRightCorner) {
		
		this.bottomLeftCorner = bottomLeftCorner;
		this.topRightCorner = topRightCorner;
		this.bottomRightCorner = new Vector2D(topRightCorner.x, bottomLeftCorner.y);
		this.topLeftCorner = new Vector2D(bottomLeftCorner.x, topRightCorner.y);
	}
	
	public Vector2D getBottomLeftCorner()
	{
		return this.bottomLeftCorner;
	}
	
	public Vector2D getTopRightCorner()
	{
		return this.topRightCorner;
	}

	public Vector2D getBottomRightCorner() 
	{
		return bottomRightCorner;
	}

	public Vector2D getTopLeftCorner() 
	{
		return topLeftCorner;
	}
	
	
}
