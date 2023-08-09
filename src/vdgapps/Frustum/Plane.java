package vdgapps.Frustum;

import vdgapps.MathUtils.MathUtils;
import vdgapps.MathUtils.Vector3D;


public class Plane 
{
	private Vector3D vertex;
	private Vector3D normal;
	private float distance;
	
	
	public Plane()
	{
		vertex = new Vector3D();
		normal = new Vector3D();
		distance = 1;
	}
	
	public Plane(Vector3D vertex, Vector3D normal)
	{
		this.vertex = vertex;
		this.normal = normal;
		computeDistance();
	}
	
	private void computeDistance()
	{
		distance = -MathUtils.dotProduct(normal, vertex);
	}

	public float getSignedDistance(Vector3D vertex)
	{ 
		return normal.x * vertex.x + normal.y* vertex.y + normal.z*vertex.z + distance;
	}
    
    public float getDistance(Vector3D vertex)
    {
    	return Math.abs(getSignedDistance(vertex));
    }
    
    //Check to see if a point belongs to a plane 
    public boolean belongs(Vector3D vertex)
    {
    	 return ((getSignedDistance(vertex) == 0));
    }

	public Vector3D getVertex() {
		return vertex;
	}

	public Vector3D getNormal() {
		return normal;
	}

	public float getDistance() {
		return distance;
	}
    
}
