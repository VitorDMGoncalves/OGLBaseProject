package vdgapps.heightMap;

import java.util.ArrayList;
import java.util.List;


import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.GraphicsEngine.IDrawable;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.MathUtils;
import vdgapps.MathUtils.Scale;
import vdgapps.MathUtils.Vector3D;
import vdgapps.PartitionTree.PartitionTree;
import vdgapps.PartitionTree.QuadTree;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.WorldObjects.WorldObjectsType;


public class HeightMap implements IWorldObject, IDrawable
{
	private HeightMapModel model;
	private String name = "";
	private Scale scale;
	private List<Vector3D> heights;
	private BoundingBox dimension;
	private Vector3D position = new Vector3D(0,0,0);
	private Direction direction = new Direction(0,0); 
	
	//heights matrix size
	private int length; //length size
	private int width; //width size
	
	
	public HeightMap(HeightMapModel model, String name) 
	{
		this.model = model;
		this.name = name;
		heights = model.getHeights();
		dimension = model.getDimension();
		width = (int)Math.sqrt(heights.size());
		length = (int)Math.sqrt(heights.size());
		computeScale();
	}
	
	

	private void computeScale() 
	{
		float xScale = dimension.getXSize()/(width-1);
		float zScale = dimension.getZSize()/(length-1);
		
		//we dont need to change the y scale
		scale = new Scale(xScale, 1, zScale);
	}

	public boolean hasPoint(float x, float z)
	{
		if(x< dimension.xMinus || x> dimension.xPlus || z> dimension.zPlus|| z< dimension.zMinus)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public float computeHeight(float x, float z)
	{
		//if the pair(x,z) is outside of of this heightmap we return 0
		if (x < dimension.xMinus || x > dimension.xPlus || z < dimension.zMinus || z >dimension.zPlus) return 0;
		
		//because the (x,z) pair is a terrain point we need to map it to the correspondent pair in that heightmap
		//we do it by subtracting the heightmap lower bounds 
		x-=dimension.xMinus; 
		z-= dimension.zMinus;
		
		x = x/scale.xScale;
		z = z/scale.zScale;
		int hx = Math.round(x); 
		int hz = Math.round(z);
		double angle = MathUtils.getAngleBetweenAB(x, z, hx, hz);
		
		
		
		int index = hz*(width) + hx;
		
		Vector3D p1 = heights.get(index);
		Vector3D p2 = new Vector3D();
		Vector3D p3 = new Vector3D();
		
		if(angle < 0) angle = (2*Math.PI)+angle; //if the angle is negative we get its inverse
		
		if(hx==x && hz == z) //in this case we already know the vertex we want. it is the index vertex.
		{
			return p1.y;
		}
		
		x = x*scale.xScale;
		z = z*scale.zScale;
		
		if(angle == 0)
		{
			if(z < dimension.getZSize())
			{
				p2 = heights.get(index + 1);
				p3 = heights.get(index + 1 + width);
			}
			else
			{
				p2 = heights.get(index - width);
				p3 = heights.get(index +1);
			}
		}
		
		if(angle>0 && angle<= Math.PI/4)
		{
			p2 = heights.get(index + 1);
			p3 = heights.get(index + 1 + width);
		}

		if(angle == Math.PI/2)
		{
			if(x< dimension.getXSize())
			{
				p2 = heights.get(index + 1 + width);
				p3 = heights.get(index + width);
			}
			else
			{
				p2 = heights.get(index + width);
				p3 = heights.get(index -1);
			}
		}
		
		if(angle>Math.PI/4 && angle< Math.PI/2)
		{
			p2 = heights.get(index + 1 + width);
			p3 = heights.get(index + width);
		}

		if(angle == Math.PI)
		{
			if(z < dimension.getZSize())
			{
				p2 = heights.get(index + width);
				p3 = heights.get(index -1);
			}
			else
			{
				p2 = heights.get(index - 1);
				p3 = heights.get(index - width -1);
			}
		}
		
		
		if(angle> Math.PI/2 && angle< Math.PI)
		{
			p2 = heights.get(index + width);
			p3 = heights.get(index -1);
		}

		if(angle>Math.PI && angle<= 5*Math.PI/4)
		{
			p2 = heights.get(index - 1);
			p3 = heights.get(index - width -1);
		}

		if(angle == 3*Math.PI/2)
		{
			if(x < dimension.getXSize())
			{
				p2 = heights.get(index - width);
				p3 = heights.get(index +1);
			}
			else
			{
				p2 = heights.get(index -width -1);
				p3 = heights.get(index - width);
			}
		}
		
		if(angle>5*Math.PI/4 && angle< 3*Math.PI/2)
		{
			p2 = heights.get(index -width -1);
			p3 = heights.get(index - width);
		}
		
		if(angle>3*Math.PI/2 && angle<= 2*Math.PI)
		{
			p2 = heights.get(index - width);
			p3 = heights.get(index +1);
		}
		
		
		x+=dimension.xMinus; 
		z+= dimension.zMinus;
		
		Vector3D norm = MathUtils.calcTriangleNormal(p1, p2, p3);
		float h = (float)(((norm.x*(x - p1.x) + norm.z*(z-p1.z))/(norm.y*-1)) +p1.y);
		return h;
	}
	
	//scales the heightmap from its original one.
	public void reScale(Scale newScale)
	{
		//rescale vertices
		for (Vector3D v : model.getVertices()) 
		{
			v.x = v.x*newScale.xScale;
			v.y = v.y*newScale.yScale;
			v.z = v.z*newScale.zScale;
		}
		
		//rescale heights
		for (Vector3D h : model.getHeights()) 
		{
			h.x = h.x*newScale.xScale;
			h.y = h.y*newScale.yScale;
			h.z = h.z*newScale.zScale;
		}
		
		dimension.scale(newScale); //recompute dimensions for new scale
		computeScale();
	}

	public HeightMapModel getModel()
	{
		return model;
	}
	
	public String getName()
	{
		return name;
	}



	@Override
	public int getType() {
		return WorldObjectsType.DRAWABLE;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return dimension;
	}



	@Override
	public String getModelName() {
		return model.getName();
	}


	@Override
	public Vector3D getPosition() {
		return position;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}
	
}
