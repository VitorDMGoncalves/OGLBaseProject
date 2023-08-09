package vdgapps.OGLUtils;

import java.nio.FloatBuffer;

public class Color4F 
{
	public float red;
	public float green;
	public float blue;
	public float alfa;
	
	
	public Color4F()
	{
		red = 1;
		green = 1;
		blue = 1;
		alfa = 1;
	}
	
	public Color4F(float red, float green, float blue, float alfa)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alfa = alfa;
	}
	
	public FloatBuffer toFloatBuffer()
	{
		FloatBuffer b = FloatBuffer.allocate(4);
		b.put(red);
		b.put(green);
		b.put(blue);
		b.put(alfa);
		
		b.rewind();
		return b;
	}
	
	
	public float[] toArray()
	{
		float[] res = new float[4];
		res[0] = red;
		res[1] = green;
		res[2] = blue;
		res[3] = alfa;
		
		return res;
	}
	
}
