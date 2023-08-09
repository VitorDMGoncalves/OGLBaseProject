package vdgapps.CameraManager;

import java.util.concurrent.locks.ReentrantLock;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.PhysicsEngine.IMovable;

import android.opengl.GLU;


public class Camera
{
	private String name;
	public Vector3D position;
	public Direction view;
	public Direction upVector;
	private ReentrantLock lock;
	
	
	public Camera(String name)
	{
		this.name = name;
		this.position = new Vector3D(0,0,0);
		this.view = new Direction(0f,0f,1f);
		this.upVector = new Direction(0f,1f,0f);
		this.lock = new ReentrantLock();
	}
	
	public Camera(String name, Vector3D position, Direction view, Direction upVector)
	{
		this.name = name;
		this.position = position;
		this.view = view;
		this.upVector = upVector;
		this.lock = new ReentrantLock();
	}
	
	
	public void lock()
	{
		this.lock.lock();
	}
	
	public void unLock()
	{
		this.lock.unlock();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void lookAt(GL10 gl)
	{
		position.lock();
		view.lock();
		upVector.lock();
		GLU.gluLookAt(gl, position.x, position.y, position.z, 
						position.x+view.getX(), position.y+view.getY(), 
						position.z+view.getZ(), upVector.getX(), upVector.getY(), upVector.getZ());
		position.unLock();
		view.unLock();
		upVector.unLock();
	}
	
}
