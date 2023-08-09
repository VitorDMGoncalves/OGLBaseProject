package vdgapps.ModelManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.GraphicsEngine.OglConfig;

public class ModelManager 
{
	
	private static HashMap<String, IModel> models =  new HashMap<String, IModel>();
	
	public static void unload(String name)
	{
		ModelLoader.unloadModel(name);
	}
	
	public static void add(IModel model, String name) 
	{	
		models.put(name, model);
	}
	
	public static void draw(String name, GL10 gl)
	{
		IModel m = models.get(name);
		if(m!=null)
		{
			m.draw(gl);
		}
	}
	
	
	public static IModel get(String name) throws Exception
	{
		IModel m = models.get(name);
		if(m!=null)
		{
			return m;
		}
		else
		{
			throw new Exception("Model " + name + " does not exist");
		}
	}
	
	public static Collection<IModel> getAll()
	{
		return models.values();
	}
	
}
