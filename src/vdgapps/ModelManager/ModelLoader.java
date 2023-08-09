package vdgapps.ModelManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;
import vdgapps.OGLUtils.VertexArray;

import android.content.Context;
import android.util.Log;

public class ModelLoader
{
	
	private static IModel load(String name, Context ctx) throws IOException
	{
		//model dimensions
		float toppoint = 0;      // y+
		float bottompoint = 0;   // y-
		float leftpoint = 0;     // x-
		float rightpoint = 0;    // x+
		float farpoint = 0;      // z-
		float nearpoint = 0;     // z+
		
		//base texture file
		String baseTexture = "";
		
		//vertices arrays
		ArrayList<Vector3D> vertices= new ArrayList<Vector3D>(); //stores read vertices
		ArrayList<Vector3D> indexedVerts = new ArrayList<Vector3D>(); //stores the indexed vertices
		
		//texture coordinates arrays
		ArrayList<Vector2D> textCoords= new ArrayList<Vector2D>(); //stores read texture coodinates
		ArrayList<Vector2D> indexedTextCoords= new ArrayList<Vector2D>(); //stores read texture coodinates
		
		//normals arrays
		ArrayList<Vector3D> normals= new ArrayList<Vector3D>(); //stores read normals
		ArrayList<Vector3D> indexedNormals= new ArrayList<Vector3D>(); //stores read indexed normals
		
		InputStream is = ctx.getAssets().open(name); //Model file
		String newline;
		boolean firstpass = true;

		float[] vcoords = new float[3];
		float[] tcoords = new float[3];
		float[] ncoords = new float[3];
		String[] coordstext;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is), 8192);
		
		//parses and reads Model file
		int[] v;
		int[] n;
		int[] t;
		while (((newline = br.readLine()) != null)) 
		{
			
			newline = newline.trim();
			if (newline.length() > 0)
			{
				if (newline.charAt(0) == 'v' && newline.charAt(1) == ' ') 
				{
					coordstext = newline.split("\\s+");
					for (int i = 1; i < coordstext.length; i++)
						vcoords[i-1] = Float.valueOf(coordstext[i]).floatValue();
					
						if (firstpass) 
						{
							rightpoint = vcoords[0];
							leftpoint = vcoords[0];
							toppoint = vcoords[1];
							bottompoint = vcoords[1];
							nearpoint = vcoords[2];
							farpoint = vcoords[2];
							firstpass = false;
						}
						
						if (vcoords[0] > rightpoint) rightpoint = vcoords[0];
						if (vcoords[0] < leftpoint) leftpoint = vcoords[0];
						if (vcoords[1] > toppoint) toppoint = vcoords[1];
						if (vcoords[1] < bottompoint) bottompoint = vcoords[1];
						if (vcoords[2] > nearpoint) nearpoint = vcoords[2];
						if (vcoords[2] < farpoint) farpoint = vcoords[2];
						
						vertices.add(new Vector3D(vcoords[0], vcoords[1], vcoords[2]));
						Log.d("vert", "read");
					}
				
				if (newline.charAt(0) == 'v' && newline.charAt(1) == 't') 
				{
					coordstext = newline.split("\\s+");
					tcoords[0] = Float.valueOf(coordstext[1]).floatValue();
					tcoords[1] = Float.valueOf(coordstext[2]).floatValue();
					textCoords.add(new Vector2D(tcoords[0], tcoords[1]));
				}
				
				if (newline.charAt(0) == 'v' && newline.charAt(1) == 'n') 
				{
					coordstext = newline.split("\\s+");
					ncoords[0] = Float.valueOf(coordstext[1]).floatValue();
					ncoords[1] = Float.valueOf(coordstext[2]).floatValue();
					ncoords[2] = Float.valueOf(coordstext[3]).floatValue();
					normals.add(new Vector3D(ncoords[0], ncoords[1], ncoords[2]));
				}
				
				if (newline.charAt(0) == 'b' && newline.charAt(1) == 't') 
				{
					coordstext = newline.split("\\s+");
					baseTexture = coordstext[1];
				}
	
			}
		}
		
		
		//close streams
		if (br != null) 
		{
			br.close();
			is.close();
		}
		
		//Model Creation
		BoundingBox dimension = new BoundingBox(leftpoint, rightpoint, nearpoint, farpoint, bottompoint, toppoint);
		
		Model m = new Model(name, indexedVerts, indexedNormals, indexedTextCoords, baseTexture, dimension, Model.GENERICMODEL);
		ModelManager.add(m, m.getname());
		return m;
	}

	
	public static void unloadModel(String name)
	{
		
	}
	
	public static IModel loadModel(String name, Context ctx) throws IOException
	{
		IModel m = load(name, ctx);
		
		return m;
	}
}
