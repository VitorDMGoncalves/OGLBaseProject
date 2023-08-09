package vdgapps.CameraManager;

import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;

import android.util.Log;

public class CameraManager 
{
	private static HashMap<String, Camera> cameras = new HashMap<String, Camera>();
	private static Camera nowCamera = new Camera("default", new Vector3D(0,0,0), 
												new Direction(0,0,1), new Direction(0,1,0));
	
	
	//adds a camera to the cameras hashmap
	public static void addCamera(Camera cam)
	{
		cameras.put(cam.getName(), cam);
	}
	
	
	//removes a camera from the cameras hashmap
	public static void remCamera(String name)
	{
		cameras.remove(name);
	}
	
	
	public static Camera getCamera(String name) throws Exception
	{
		Camera c = cameras.get(nowCamera);
		if(c==null)
			throw new Exception("No camera with the name" + name);
		return c;
	}
	
	public static Camera getActiveCamera()
	{
		return nowCamera;
	}
	
	
	public static void setActiveCamera(String name)
	{
		Camera c = cameras.get(name);
		if(c!=null)
		{
			c.lock();
			nowCamera = c;
			c.unLock();
		}
	}
	
	public static void bindCamera(GL10 gl)
	{
		nowCamera.lock();
		nowCamera.lookAt(gl);
		nowCamera.unLock();
	}
	
}
