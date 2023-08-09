package vdgapps.heightMap;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;
import vdgapps.ModelManager.IModel;
import vdgapps.ModelManager.Model;
import vdgapps.OGLUtils.VertexArray;

public class HeightMapModel implements IModel
{
	private List<Vector3D> heights;
	private List<Vector3D> vertices;
	private List<Vector3D> normals;
	private List<Vector2D> textCoords;
	
	private String name = "";
	private String baseTexture = "";
	private String detailTexture = "";
	private BoundingBox dimension;
	private int type;
	private VertexArray vertexArray;
	
	public HeightMapModel(String name, String baseTexture, String detailTexture, 
			BoundingBox dimension,List<Vector3D> heights, List<Vector3D> vertices,
			List<Vector3D> normals, List<Vector2D> textCoords, int type)
	{
		this.name = name;
		this.baseTexture = baseTexture;
		this.detailTexture = detailTexture;
		this.dimension = dimension;
		this.heights = heights;
		this.vertices = vertices;
		this.normals = normals;
		this.textCoords = textCoords;
		this.type = type;
	}

	public void computeDimension()
	{
		dimension = new BoundingBox(Float.MAX_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MIN_VALUE);
		for (Vector3D v : vertices) 
		{
			if(v.x < dimension.xMinus) dimension.xMinus = v.x;
			if(v.x > dimension.xPlus) dimension.xPlus = v.x;
			if(v.z > dimension.zPlus) dimension.zPlus = v.z;
			if(v.z < dimension.zMinus) dimension.zMinus = v.z;
			if(v.y < dimension.yMinus) dimension.yMinus = v.y;
			if(v.y > dimension.yPlus) dimension.yPlus= v.y;
		}
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


	public List<Vector3D> getHeights() {
		return heights;
	}


	public BoundingBox getDimension() {
		return dimension;
	}

	public String getName() {
		return name;
	}

	public String getBaseTexture() {
		return baseTexture;
	}

	public void setBaseTexture(String baseTexture) {
		this.baseTexture = baseTexture;
	}

	public String getDetailTexture() {
		return detailTexture;
	}

	public void setDetailTexture(String detailTexture) {
		this.detailTexture = detailTexture;
	}


	@Override
	public int getType() {
		return type;
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
	public void loadModel(GL10 gl) {
		vertexArray = new VertexArray(vertices, normals, textCoords, VertexArray.TRIANGLESTRIP);
	}


	@Override
	public void draw(GL10 gl) {
		vertexArray.draw(gl);
	}
	
	
}
