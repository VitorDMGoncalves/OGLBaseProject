package vdgapps.WorldManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;


import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.GraphicsEngine.IDrawable;
import vdgapps.ModelManager.IModel;
import vdgapps.PartitionTree.OcTree;
import vdgapps.PhysicsEngine.IMovable;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.heightMap.HeightMap;
import vdgapps.terrain.Terrain;
public class World
{
	private static int MINIMUMQUADTREESIZE = 100; //minimum vertices a quadtree must have
	private static int MINIMUMOCTREESIZE = 200; //minimum vertices a octree must have
	private OcTree<IWorldObject> worldObjectsTree;
	private Map<String, IWorldObject> worldObjectMap;
	private String name;
	private Terrain terrain;
	private BoundingBox dimension;
	
	public World(String name, BoundingBox dim, Terrain ter)
	{
		this.name = name;
		dimension = dim;
		terrain = ter;
		worldObjectsTree = new OcTree<IWorldObject>(dim, MINIMUMOCTREESIZE);
		worldObjectMap = new HashMap<String, IWorldObject>();
		addTerrainObjects2World();
	}
	
	//adds terrain drawables to world
	private void addTerrainObjects2World() 
	{
		for (HeightMap h : terrain.getHeighMaps()) 
		{
			addWorldObject((IWorldObject)h);
		}
		
	}


	public IWorldObject getWorldObject(String name) throws Exception
	{
		IWorldObject o = worldObjectMap.get(name);
		if(o!=null)
		{
			return o;
		}
		else
		{
			throw new Exception("Object not found");
		}
	}
	
	public void addWorldObject(List<IWorldObject> objs)
	{
		for (IWorldObject o : objs) 
		{
			worldObjectMap.put(o.getName(), o);
			worldObjectsTree.add(o, o.getBoundingBox());
		}
	}
	
	public void addWorldObject(IWorldObject obj)
	{
		worldObjectMap.put(obj.getName(), obj);
		worldObjectsTree.add(obj, obj.getBoundingBox());
	}
	
	
	public void removeWorldObject(String name) {
		IWorldObject o = worldObjectMap.get(name);
		
		worldObjectsTree.remove(o, o.getBoundingBox().getCenter());
		worldObjectMap.remove(name);
	}

	
	public BoundingBox getDimension()
	{
		return dimension;
	}


	public Terrain getTerrain() {
		return terrain; 
	
	}
	
	public String getName()
	{
		return name;
	}


	public OcTree<IWorldObject> getWorldObjectsTree() {
		return worldObjectsTree;
	}


	public Map<String, IWorldObject> getWorldObjectMap() {
		return worldObjectMap;
	}
	
	
			
			
			
}
