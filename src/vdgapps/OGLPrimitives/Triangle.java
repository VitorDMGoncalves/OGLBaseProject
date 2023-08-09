package vdgapps.OGLPrimitives;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import vdgapps.MathUtils.Vector3D;



public class Triangle 
{
 
	private ShortBuffer indexBuffer;
	private FloatBuffer vertexBuffer;
	private short[] indexArray = {0, 1, 2};
	
	public Triangle(Vector3D p1, Vector3D p2, Vector3D p3)
	{
		this.prepare(p1, p2, p3);
	}
	
	private void prepare(Vector3D p1, Vector3D p2, Vector3D p3)
	{
		//prepares vertex buffer
		ByteBuffer vbb = ByteBuffer.allocateDirect(3 * 3 * 4);
	    vbb.order(ByteOrder.nativeOrder());
	    vertexBuffer = vbb.asFloatBuffer();
	    
	    vertexBuffer.put(p1.toArray());
	    vertexBuffer.put(p2.toArray());
	    vertexBuffer.put(p3.toArray());
	    vertexBuffer.position(0);
	    
	    //prepares index buffer
	    ByteBuffer ibb = ByteBuffer.allocateDirect(3 * 2);
	    ibb.order(ByteOrder.nativeOrder());
	    indexBuffer = ibb.asShortBuffer();
	    indexBuffer.put(indexArray);	    
	    indexBuffer.position(0);
	}
	
	public void draw(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	    gl.glDrawElements(GL10.GL_TRIANGLES, 3, GL10.GL_UNSIGNED_SHORT, indexBuffer);   
	}
}
