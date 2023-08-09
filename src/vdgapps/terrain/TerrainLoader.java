package vdgapps.terrain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.InputStreamEntity;

import android.content.Context;
import android.util.Log;
import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;
import vdgapps.ModelManager.IModel;
import vdgapps.ModelManager.Model;
import vdgapps.ModelManager.ModelManager;
import vdgapps.config.Config;
import vdgapps.heightMap.HeightMap;
import vdgapps.heightMap.HeightMapModel;

public class TerrainLoader 
{
	public static Terrain load(String name, Context ctx) throws Exception
	{
		Log.d("Loading", "Terrain loading started");
		List<HeightMap> heightsMaps = new ArrayList<HeightMap>();
		
		//terrain name
		String terrainName = "";
		//model data
		BoundingBox dimension = new BoundingBox(); //model dimensions
		String baseTexture = ""; //model base texture
		String detailTexture = ""; //model detail texture
		List<Vector3D> heights = new ArrayList<Vector3D>(); //stores read heights
		List<Vector3D> vertices= new ArrayList<Vector3D>(); //stores read vertices
		List<Vector2D> textCoords= new ArrayList<Vector2D>(); //stores read texture coodinates
		List<Vector3D> normals= new ArrayList<Vector3D>(); //stores read normals
		//model data end
		
		boolean firstpass = true;
		String newline;
		String[] parsedLine;
		InputStream is = ctx.getAssets().open(name);
		BufferedReader br = new BufferedReader(new InputStreamReader(is), 8192);
		//parses and reads Model file
		while (((newline = br.readLine()) != null)) 
		{
			newline = newline.trim();
			if (newline.length() > 0)
			{
				parsedLine = newline.split("\\s+");
				
				if(parsedLine[0].equals("tn"))
				{
					terrainName = parsedLine[1];
				}
				
				if(parsedLine[0].equals("hn"))
				{
					if(firstpass)
					{
						firstpass = false;
						name = new String();
						baseTexture = new String();
						detailTexture = new String();
						dimension = new BoundingBox();
						heights = new ArrayList<Vector3D>();
						vertices = new ArrayList<Vector3D>();
						textCoords = new ArrayList<Vector2D>();
						normals = new ArrayList<Vector3D>();	
					}
					
					else
					{
						
						HeightMapModel m = new HeightMapModel(name, baseTexture, detailTexture, dimension, heights, vertices, normals, textCoords, Model.GENERICMODEL);
						HeightMap hmap = new HeightMap(m, m.getName());
						heightsMaps.add(hmap);
						
						name = new String();
						baseTexture = new String();
						detailTexture = new String();
						dimension = new BoundingBox();
						heights = new ArrayList<Vector3D>();
						vertices = new ArrayList<Vector3D>();
						textCoords = new ArrayList<Vector2D>();
						normals = new ArrayList<Vector3D>();	
					}
			
					name = parsedLine[1];
				}
				
				if(parsedLine[0].equals("bt"))
				{
					baseTexture = parsedLine[1];
				}
				
				if(parsedLine[0].equals("dt"))
				{
					detailTexture = parsedLine[1];
				}
				
				if(parsedLine[0].equals("d"))
				{
					dimension.xMinus = Float.parseFloat(parsedLine[1]);
					dimension.xPlus = Float.parseFloat(parsedLine[2]);
					dimension.zPlus = Float.parseFloat(parsedLine[3]);
					dimension.zMinus = Float.parseFloat(parsedLine[4]);
					dimension.yMinus = Float.parseFloat(parsedLine[5]);
					dimension.yPlus = Float.parseFloat(parsedLine[6]);
				}
				
				if(parsedLine[0].equals("h"))
				{
					Vector3D h = new Vector3D();
					h.x = Float.parseFloat(parsedLine[1]);
					h.y = Float.parseFloat(parsedLine[2]);
					h.z = Float.parseFloat(parsedLine[3]);
					heights.add(h);
				}
				
				if(parsedLine[0].equals("v"))
				{
					Vector3D v = new Vector3D();
					v.x = Float.parseFloat(parsedLine[1]);
					v.y = Float.parseFloat(parsedLine[2]);
					v.z = Float.parseFloat(parsedLine[3]);
					vertices.add(v);
				}
				
				if(parsedLine[0].equals("vn"))
				{
					Vector3D n = new Vector3D();
					n.x = Float.parseFloat(parsedLine[1]);
					n.y = Float.parseFloat(parsedLine[2]);
					n.z = Float.parseFloat(parsedLine[3]);
					normals.add(n);
				}
				
				if(parsedLine[0].equals("vt"))
				{
					Vector2D tc = new Vector2D();
					tc.x = Float.parseFloat(parsedLine[1]);
					tc.y = Float.parseFloat(parsedLine[2]);
					textCoords.add(tc);
				}
			}
		}
		
		
		//close streams
		if (br != null) 
		{
			br.close();
		}
		
		HeightMapModel m = new HeightMapModel(name, baseTexture, detailTexture, dimension, heights, vertices, normals, textCoords, Model.GENERICMODEL);
		
		HeightMap hmap = new HeightMap(m, m.getName());
		heightsMaps.add(hmap);
		Terrain t = new Terrain(terrainName, heightsMaps);
		
		for (HeightMap h : heightsMaps) 
		{
			//Log.d("dimension", h.getModel().getName() + " " + h.getModel().getDimension().toString());
			
			ModelManager.add(h.getModel(), h.getModel().getName());
		}
		Log.d("Loading", "Terrain loading ended");
		return t;
	}
}
