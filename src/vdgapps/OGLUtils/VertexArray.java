package vdgapps.OGLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.MathUtils.Vector2D;
import vdgapps.MathUtils.Vector3D;




public class VertexArray 
{
	public static final int LINES = 1;
	public static final int LINESTRIP = 2;
	public static final int TRIANGLES = 3;
	public static final int TRIANGLESTRIP = 4;
	
	private ByteBuffer vbb;
	private ByteBuffer nbb;
	private ByteBuffer tbb;
	private ByteBuffer ibb;
	
	private int vertexCount;
	private int drawType;
	private boolean hasNormals;
	private boolean hasTexture;
	
	public VertexArray(List<Vector3D> vertices, int drawType)
	{
		vertexCount = vertices.size();
	    prepareVertexBuffer(vertices);
	    prepareIndexBuffer(vertexCount);
	    this.drawType = drawType;
	    hasNormals = false;
	    hasTexture = false;
	}
	
	public VertexArray(List<Vector3D> vertices, List<Vector3D> normals, int drawType)
	{
		vertexCount = vertices.size();
	    prepareVertexBuffer(vertices);
	    prepareIndexBuffer(vertexCount);
	    prepareNormlas(normals);
	    this.drawType = drawType;
	    hasNormals = true;
	}
	public VertexArray(List<Vector3D> vertices, List<Vector3D> normals, List<Vector2D> textCoords, int drawType)
	{
		vertexCount = vertices.size();
	    prepareVertexBuffer(vertices);
	    prepareIndexBuffer(vertexCount);
	    prepareNormlas(normals);
	    prepareTextCoords(textCoords);
	    this.drawType = drawType;
	    hasNormals = true;
	    hasTexture = true;
	}

	//put the normals in the normal buffer
	private void prepareNormlas(List<Vector3D> normals) 
	{
		//prepares normal buffer
		nbb = ByteBuffer.allocateDirect(4 * 3 * normals.size());
	    nbb.order(ByteOrder.nativeOrder());
	    
	    for (Vector3D Vector3D : normals) {
	    	nbb.putFloat(Vector3D.x);
	    	nbb.putFloat(Vector3D.y);
	    	nbb.putFloat(Vector3D.z);
		}
	    
	    nbb.position(0);
	}
	
	//put the texture coordinates in the textCoords buffer
	private void prepareTextCoords(List<Vector2D> textCoords) 
	{
		//prepares textCoords buffer
		tbb = ByteBuffer.allocateDirect(4 * 2 * textCoords.size());
	    tbb.order(ByteOrder.nativeOrder());
	    
	    for (Vector2D Vector2D : textCoords) {
	    	tbb.putFloat(Vector2D.x);
	    	tbb.putFloat(Vector2D.y);
		}
	    tbb.position(0);
	}

	//creates the index buffer with sequential order
	private void prepareIndexBuffer(int vertexCount)
	{
	    ibb = ByteBuffer.allocateDirect(vertexCount * 2);
	    ibb.order(ByteOrder.nativeOrder());

	    for (int i = 0; i< vertexCount; i++ )
    	{
    		ibb.putShort((short)i);
    	}
	    
	    ibb.position(0);
	}
	
	
	//sets the index buffer
	public void setIndex(short[] indexArray, int count)
	{
		ibb = ByteBuffer.allocateDirect(count * 2);
	    ibb.order(ByteOrder.nativeOrder());
	   
	    for(int i =0 ; i< count; i++) 
	    {
	    	ibb.putShort((short) indexArray[i]);
	    }
	    
	    ibb.position(0);
	}
	
	
	//put the vertices in the vertex buffer
	private void prepareVertexBuffer(List<Vector3D> vertices)
	{
		//prepares vertex buffer
		vbb = ByteBuffer.allocateDirect(4 * 3 * vertices.size());
	    vbb.order(ByteOrder.nativeOrder());
	   
	    for (Vector3D Vector3D : vertices) {
	    	vbb.putFloat(Vector3D.x);
	    	vbb.putFloat(Vector3D.y);
	    	vbb.putFloat(Vector3D.z);
		}
	  
	    vbb.position(0);
	}
	
	public void setNormals(List<Vector3D> normals )
	{
		prepareNormlas(normals);
		hasNormals = true;
	}
	
	public void setTextCoords(List<Vector2D> textCoords)
	{
		prepareTextCoords(textCoords);
		hasTexture = true;
	}
	
	public void draw(GL10 gl)
	{		
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbb);
		if(hasNormals)
		{
			gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
			gl.glNormalPointer(GL10.GL_FLOAT, 0, nbb);
		}
		if(hasTexture)
		{
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, tbb);
		}
		
		switch (drawType) {
		case 1: gl.glDrawElements(GL10.GL_LINES, vertexCount, GL10.GL_UNSIGNED_SHORT, ibb) ;break;
		case 2: gl.glDrawElements(GL10.GL_LINE_STRIP, vertexCount, GL10.GL_UNSIGNED_SHORT, ibb) ;break;
		case 3: gl.glDrawElements(GL10.GL_TRIANGLES, vertexCount, GL10.GL_UNSIGNED_SHORT, ibb) ;break;
		case 4: gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, vertexCount, GL10.GL_UNSIGNED_SHORT, ibb) ;break;

		default:
			break;
		}
		
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
}
