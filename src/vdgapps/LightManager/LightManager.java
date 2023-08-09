package vdgapps.LightManager;

import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class LightManager 
{
	private static HashMap<String, Light> lights = new HashMap<String, Light>();
	private static String activeLight = "";
	
	public static void addLight(Light l, String name)
	{
		lights.put(name, l);
	}
	
	public static void remLight(String name)
	{
		lights.remove(name);
	}
	
	public static void setActiveLight(String light)
	{
		if(lights.containsKey(light))
			activeLight = light;
	}
	
	public static void bindLight(int lightNumber, GL10 gl)
	{
		Light l = lights.get(activeLight);
		if (l !=null)
		{
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, l.specular.toFloatBuffer());
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, l.diffuse.toFloatBuffer());
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, l.ambient.toFloatBuffer());
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_EMISSION, l.emissive.toFloatBuffer());
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, l.position.toFloatBuffer());
		}
	}
}
