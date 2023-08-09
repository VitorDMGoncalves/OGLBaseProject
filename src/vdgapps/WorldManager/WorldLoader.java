package vdgapps.WorldManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.GraphicsEngine.IDrawable;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.PhysicsEngine.IMovable;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.WorldObjects.WorldDrawable;
import vdgapps.WorldObjects.WorldDrawbleMovable;
import vdgapps.WorldObjects.WorldMovable;
import vdgapps.WorldObjects.WorldObjectsType;
import vdgapps.config.Config;
import vdgapps.terrain.Terrain;
import vdgapps.terrain.TerrainLoader;
public class WorldLoader
{
	public static World load(String worldName, Context ctx) throws Exception
	{
		String name = "";
		Terrain terrain = new Terrain();
		BoundingBox dimension = new BoundingBox();
		List<ObjectData> objs = new ArrayList<ObjectData>();
		
		String line;
		String[] parsedLine;
		InputStream in = ctx.getAssets().open(worldName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in), 8192);
		
		while (((line = br.readLine()) != null)) 
		{
			
			line = line.trim();
			parsedLine = line.split("\\s+");
			if (parsedLine.length > 0)
			{
				if (parsedLine[0].equals("n"))
				{
					name = parsedLine[1];
				}
				
				if (parsedLine[0].equals("t"))
				{
					terrain = TerrainLoader.load(parsedLine[1], ctx);
				}
				
				if (parsedLine[0].equals("d"))
				{
					dimension.xMinus = Float.parseFloat(parsedLine[1]);
					dimension.xPlus = Float.parseFloat(parsedLine[2]);
					dimension.zPlus = Float.parseFloat(parsedLine[3]);
					dimension.zMinus = Float.parseFloat(parsedLine[4]);
					dimension.yMinus = Float.parseFloat(parsedLine[5]);
					dimension.yPlus = Float.parseFloat(parsedLine[6]);
				}
				
				if (parsedLine[0].equals("o"))
				{
					ObjectData ol = new ObjectData();
					ol.name = parsedLine[1];
					ol.modelName = parsedLine[2];
					ol.x = Float.parseFloat(parsedLine[3]);
					ol.y = Float.parseFloat(parsedLine[4]);
					ol.z = Float.parseFloat(parsedLine[5]);
					ol.phi = Integer.parseInt(parsedLine[6]);
					ol.theta = Integer.parseInt(parsedLine[7]);
					ol.isMovable = Boolean.parseBoolean(parsedLine[8]);
					ol.isDrawable = Boolean.parseBoolean(parsedLine[9]);
					ol.isColidable = Boolean.parseBoolean(parsedLine[10]);
					objs.add(ol);
				}
			}
		}
		br.close();
		
		Log.d("World", "creating started");
		World w = new World(name, dimension, terrain);
		Log.d("World", "creating ended");
		
		Log.d("World", "adding objects started");
		for (ObjectData od : objs) 
		{
			Vector3D pos = new Vector3D(od.x, od.y, od.z);
			Direction dir = new Direction();
			
			if(od.isMovable && od.isDrawable)
			{
				IWorldObject wdm = new WorldDrawbleMovable(od.name, od.modelName, pos, dir, od.isColidable, WorldObjectsType.MOVABLE_DRAWABLE);
				w.addWorldObject(wdm);
			}

			if(od.isMovable && !od.isDrawable)
			{
				IWorldObject wm = new WorldMovable(od.name, pos, dir, new Vector3D(), WorldObjectsType.MOVABLE);
				w.addWorldObject(wm);
			}

			if(od.isDrawable && !od.isMovable)
			{
				IWorldObject wd = new WorldDrawable(od.name, od.modelName, pos, dir, WorldObjectsType.DRAWABLE);
				w.addWorldObject(wd);
			}
		}
		Log.d("World", "adding objects ended");
		return w;
	}
}



