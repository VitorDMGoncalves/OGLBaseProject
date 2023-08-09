package vdgapps.ModelManager;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;
import vdgapps.OGLUtils.VertexArray;


public class Model implements IModel
{
	public static final int GENERICMODEL = 1;
	public static final int ALPHAMODEL = 2;
	
	private String name;
	private List<Vector3D> vertices;
	private List<Vector3D> normals;
	private List<Vector2D> textCoords;
	private int type;
	private BoundingBox dimension;
	private String baseTexture;
	private VertexArray vertexArray;

	public Model(String name, List<Vector3D> vertices, List<Vector3D> normals, List<Vector2D> textCoords, 
			String baseTexture, BoundingBox dimension, int type) 
	{
		this.name = name;
		this.vertices = vertices;
		this.normals = normals;
		this.textCoords = textCoords;
		this.baseTexture = baseTexture;
		this.dimension = dimension;
		this.type = type;
	}

	public void loadModel(GL10 gl)
	{
		vertexArray = new VertexArray(vertices, normals, textCoords, VertexArray.TRIANGLES);
	}

	public List<Vector3D> getVertices() {
		return vertices;
	}

	public List<Vector3D> getNormals() {
		return normals;
	}

	public List<Vector2D> getTextCoords() {
		return textCoords;
	}

	public String getBaseTexture() {
		return baseTexture;
	}
	
	public BoundingBox getDimension()
	{
		return dimension;
	}
	
	public String getname()
	{
		return name;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public VertexArray getVertexArray() {
		return vertexArray;
	}

	@Override
	public BoundingBox getModelDimensions() {
		return dimension;
	}

	@Override
	public void draw(GL10 gl) {
		vertexArray.draw(gl);
	}
}
