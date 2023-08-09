package vdgapps.MathUtils;

public class Vector2D 
{
	public float x;
	public float y;
	
	public Vector2D()
	{
		x = 0;
		y = 0;
	}
	public Vector2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	
	}
	
	public float[] toArray()
	{
		float[] array = {x, y};
		return array;
	}
}
