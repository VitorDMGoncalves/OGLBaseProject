package vdgapps.MathUtils;

import java.nio.FloatBuffer;

public class Vector4D 
{
	public float x;
	public float y;
	public float z;
	public float w;
	
	
	public Vector4D()
	{
		x = 0;
		y = 0;
		z = 0;
		w = 0;
	}
	
	public Vector4D(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	
	public FloatBuffer toFloatBuffer()
	{
		FloatBuffer b = FloatBuffer.allocate(4);
		b.put(x);
		b.put(y);
		b.put(z);
		b.put(w);
		
		b.rewind();
		return b;
	}
	
	public float[] toArray()
	{
		float[] res = new float[4];
		res[0] = x;
		res[1] = y;
		res[2] = z;
		res[3] = w;
		
		return res;
	}
	
	//adiccao de vector
	public void addV(Vector4D vec)
	{
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
	}
	
	
	//subtraccao de vector
	public void subV(Vector4D vec)
	{
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
	}
	
	
	//multiplicacao por escalar
	public void multS(float s)
	{
		this.x*= s;
		this.y*= s;
		this.z*= s;
	}
	
	
	//Produto interno
	public void dotProd(Vector4D vec)
	{
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
	}
	
	
	//Produto Vetorial
	public void crossProd(Vector4D vec)
	{
		x = y*vec.z - z*vec.y;
		y = z*vec.x - x*vec.z;
		z = x*vec.y - y*vec.x;
	}
	
	
	public float getLenght()
	{
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	//normaliza o vector
	public void normalize()
	
	{
		float Length = getLenght();
		x = x/Length;
		y = y/Length;
		z = z/Length;
	}
	
}
