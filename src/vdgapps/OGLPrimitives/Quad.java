package vdgapps.OGLPrimitives;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;
import vdgapps.OGLUtils.VertexArray;


public class Quad 
{
	private ArrayList<Vector3D> vertices;
	private ArrayList<Vector3D> normals;
	private ArrayList<Vector2D> textCoords;
	
	
	private boolean hasTextCoords;
	private boolean hasNormals;
	private boolean isLoaded;
	private VertexArray va;
	
	
	public Quad(Vector3D p1, Vector3D p2, Vector3D p3, Vector3D p4)
	{
		hasTextCoords = false;
		hasNormals = false;
		this.isLoaded = false;
		
		this.vertices = new ArrayList<Vector3D>();
		this.vertices.add(p2);
		this.vertices.add(p1);
		this.vertices.add(p3);
		this.vertices.add(p4);
		
	}
	
	private void prepare()
	{
		va = new VertexArray(vertices, VertexArray.TRIANGLESTRIP);
		if(hasNormals) va.setNormals(normals);
		if(hasTextCoords) va.setTextCoords(textCoords);
		this.isLoaded = true;
	}
	
	public void setTextCoords(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4)
	{
		this.textCoords = new ArrayList<Vector2D>();
		this.textCoords.add(p2);
		this.textCoords.add(p1);
		this.textCoords.add(p3);
		this.textCoords.add(p4);
		this.hasTextCoords = true;
		if(isLoaded)
			va.setTextCoords(textCoords);
	}
	
	public void setNormals(Vector3D p1, Vector3D p2, Vector3D p3, Vector3D p4)
	{
		this.normals = new ArrayList<Vector3D>();
		this.normals.add(p2);
		this.normals.add(p1);
		this.normals.add(p3);
		this.normals.add(p4);
		this.hasNormals = true;
		
	}
	
	public void draw(GL10 gl)
	{
		if(!this.isLoaded)
		{
			this.prepare();
		}
		
		va.draw(gl);
	}
}
