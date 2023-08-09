package vdgapps.MathUtils;

import java.util.concurrent.locks.ReentrantLock;

public class Vector3D 
{
	public float x;
	public float y;
	public float z;
	
	private ReentrantLock lock = new ReentrantLock();
	
	public Vector3D()
	{
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector3D(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	public void lock()
	{
		this.lock.lock();
	}
	
	public void unLock()
	{
		this.lock.unlock();
	}
	
	public float[] toArray()
	{
		float[] res = new float[3];
		res[0] = x;
		res[1] = y;
		res[2] = z;
		
		return res;
	}
	
	
	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append(x +";" + y + ";" + z);
		return b.toString();
	}
	
	//adiccao de vector
	public void addV(Vector3D vec)
	{
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
	}
	
	
	//subtraccao de vector
	public void subV(Vector3D vec)
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
	public void dotProd(Vector3D vec)
	{
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
	}
	
	
	//Produto Vetorial
	public void crossProd(Vector3D vec)
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
	public Vector3D normalize()
	{
		float Length = getLenght();
		x = x/Length;
		y = y/Length;
		z = z/Length;
		return this;
	}
	
	public Vector3D getRotation()
	{
		return new Vector3D();
	}
	
	
}

