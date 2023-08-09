package vdgapps.BoundingVolumes;

import vdgapps.MathUtils.Scale;
import vdgapps.MathUtils.Vector3D;


public class BoundingBox
{
	public float yPlus = 0;      // y+
	public float yMinus = 0;   // y-
	public float xMinus = 0;     // x-
	public float xPlus = 0;    // x+
	public float zMinus = 0;      // z-
	public float zPlus = 0;     // z+
	
	public BoundingBox()
	{
		this.yPlus = 0;
		this.yMinus = 0;
		this.xMinus = 0;
		this.xPlus = 0;
		this.zMinus = 0;
		this.zPlus = 0;
	}
	
	public BoundingBox(float xMinus, float xPlus, float zPlus, float zMinus, float yMinus, float yPlus) 
	{
		this.yPlus = yPlus;
		this.yMinus = yMinus;
		this.xMinus = xMinus;
		this.xPlus = xPlus;
		this.zMinus = zMinus;
		this.zPlus = zPlus;
	}
	

	public boolean containsVertex(Vector3D vertex)
	{
		if(vertex.x> xPlus || vertex.x < xMinus
			||vertex.y > yPlus || vertex.y < yMinus
			|| vertex.z > zPlus || vertex.z < zMinus )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean intersects(BoundingBox dimension)
	{
		if((xPlus < dimension.xMinus || xMinus > dimension.xPlus)
				||(zPlus < dimension.zMinus || zMinus > dimension.zPlus)
				||(yPlus < dimension.yMinus || yMinus > dimension.yPlus))
		{
			return false;
		}
			
		else
		{
			return true;
		}
			
	}
	
	public BoundingBox(Vector3D bottomLeftFarPoint, Vector3D topRightNearPoint)
	{
		this.yPlus = topRightNearPoint.y;
		this.yMinus = bottomLeftFarPoint.y;
		this.xMinus = bottomLeftFarPoint.x;
		this.xPlus = topRightNearPoint.x;
		this.zMinus = bottomLeftFarPoint.z;
		this.zPlus = topRightNearPoint.z;
	}
	
	public boolean fits(BoundingBox dimension) 
	{
		if(this.zMinus > dimension.zMinus	|| this.zPlus < dimension.zPlus 
			|| this.xMinus > dimension.xMinus || this.xPlus < dimension.xPlus
			|| this.yPlus < dimension.yPlus || this.yMinus > dimension.yMinus)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

	
	public Vector3D getCenter()
	{
		return new Vector3D((xPlus + xMinus)/2,
				(yPlus + yMinus)/2, 
				(zPlus + zMinus)/2);
	}
	
	public float getXSize()
	{
		return Math.abs(xPlus - xMinus);
	}
	
	public float getYSize()
	{
		return Math.abs(yPlus - yMinus);
	}
	
	public float getZSize()
	{
		return Math.abs(zPlus - zMinus);
	}


	public void scale(Scale scale) 
	{
		xMinus *= scale.xScale;
		xPlus *= scale.xScale;
		zPlus *= scale.zScale;
		zMinus *= scale.zScale;
		yMinus *= scale.yScale;
		yPlus *= scale.yScale;
	}
	
	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append(xMinus + ";" + xPlus + ";" + zMinus + ";" + zPlus +";" + yMinus +";" + yPlus);
		return b.toString();
	}
}


