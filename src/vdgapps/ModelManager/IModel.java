package vdgapps.ModelManager;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.OGLUtils.VertexArray;

public interface IModel 
{
	public int getType();
	public String getName();
	public VertexArray getVertexArray();
	public BoundingBox getModelDimensions();
	public void loadModel(GL10 gl);
	public String getBaseTexture();
	public void draw(GL10 gl);
}
