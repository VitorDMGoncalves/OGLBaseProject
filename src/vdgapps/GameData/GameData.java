package vdgapps.GameData;

import vdgapps.CameraManager.Camera;
import vdgapps.CameraManager.CameraManager;
import vdgapps.CameraManager.MovableCamera;
import vdgapps.LightManager.Light;
import vdgapps.LightManager.LightManager;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.MathUtils.Vector4D;
import vdgapps.OGLUtils.Color4F;
import vdgapps.WorldManager.World;
import vdgapps.WorldManager.WorldLoader;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.config.Config;
import android.widget.Toast;

public class GameData implements IGameData
{
	public PhysicsEngineData peData;
	public GraphicsEngineData geData;
	private String worldName;
	private World world;
	
	public GameData(String worldName)
	{
		this.worldName = worldName;
	}
	
	public void load()
	{
		try 
		{
			world = WorldLoader.load(worldName, Config.context);
			Light l1 = new Light(new Color4F(1,1,1,1), new Vector4D(500,500,0,1));
			LightManager.addLight(l1, "l1");
			LightManager.setActiveLight("l1");
			
			Camera cam = new Camera("cam1",  new Vector3D(0,0,0), 
					 new Direction(0,0,1),
					 new Direction(0,1,0));
			
			CameraManager.addCamera(cam);
			CameraManager.setActiveCamera("cam1");
			
			MovableCamera mc = new MovableCamera("cam1", new Vector3D(30,20,0), new Direction(0,0,1), new Vector3D());
			mc.AttachCamera(cam);
			world.addWorldObject(mc);
			peData = new PhysicsEngineData();
			geData = new GraphicsEngineData();		
		} 
		catch (Exception e) 
		{
			Toast.makeText(Config.context, "Error loading world.", 3);
			e.printStackTrace();
		}
		
	}
	
	public World getWorld()
	{
		return world;
	}

	
}
