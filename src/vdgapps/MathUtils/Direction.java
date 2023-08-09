package vdgapps.MathUtils;

import java.util.concurrent.locks.ReentrantLock;

public class Direction 
{
	public float theta;
	public float phi;
	private ReentrantLock lock = new ReentrantLock();
	
	
	public Direction()
	{
		this.phi = 0;
		this.theta = 0;
	}
	
	public Direction(float theta, float phi) 
	{
		this.theta = theta;
		this.phi = phi;
	}
	
	public Direction(Vector3D vec)
	{
		vec.normalize();
		theta = MathUtils.toDegs((float)Math.acos(vec.y));
		if(vec.x!=0 || vec.z!=0)
			phi = MathUtils.toDegs((float)Math.atan2((double)vec.z,(double)vec.x));
		else
			phi = 0;
	}
	
	public Direction(float x, float y, float z)
	{
		Vector3D vec = new Vector3D(x, y, z);
		vec.normalize();
		theta =  MathUtils.toDegs((float)Math.acos(vec.y));
		if(x!=0 || z!=0)
			phi = MathUtils.toDegs((float)Math.atan2((double)vec.z,(double)vec.x));
		else
			phi = 0;
	}
	
	
	public void lock()
	{
		this.lock.lock();
	}
	
	public void unLock()
	{
		this.lock.unlock();
	}
	
	
	//blocks the angles at the range [-360, 360] for phi and theta 
	private void checkAngles()
	{
		if(theta> 360) theta = theta%360f;
		if(theta< -360) theta = theta%360f;
		if(phi>360) phi = phi%360f;
		if(phi<-360) phi = phi%360f; 
			
	}
	
	public Vector3D toVector()
	{
		checkAngles();
		float x = (float)(Math.sin(MathUtils.toRads(theta))* Math.cos(MathUtils.toRads(phi)));
		float y = (float)(Math.cos(MathUtils.toRads(theta)));
		float z = (float)(Math.sin(MathUtils.toRads(theta)) * Math.sin(MathUtils.toRads(phi)));
		if(x<0.000001 &&  x> -0.000001) x = 0;
		if(y<0.000001 &&  y> -0.000001) y = 0;
		if(z<0.000001 &&  z> -0.000001) z = 0;
		return new Vector3D(x, y, z);
	}
	
	public float getX()
	{
		checkAngles();
		return (float)(Math.sin(MathUtils.toRads(theta))* Math.cos(MathUtils.toRads(phi))); 
	}
	
	public float getY()
	{
		checkAngles();
		return (float)(Math.cos(MathUtils.toRads(theta))); 
	}
	
	public float getZ()
	{
		checkAngles();
		return (float)(Math.sin(MathUtils.toRads(theta)) * Math.sin(MathUtils.toRads(phi))); 
	}
}