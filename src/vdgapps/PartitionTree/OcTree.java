package vdgapps.PartitionTree;

import java.util.ArrayList;
import java.util.List;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector3D;


public class OcTree<E> implements PartitionTree<E> 
{
	private BoundingBox dimension; //octtree dimension
	private int minimumSize = 100;//quadtree minimum size in cubic meters
	private List<E> elems = new ArrayList<E>();
	
	private List<PartitionTree<E>> ocTrees = new ArrayList<PartitionTree<E>>(); 
	
	public OcTree(BoundingBox dimension, int minimumSize)
	{
		this.dimension = dimension;
		this.minimumSize = minimumSize;
		this.create();
	}
	
	private void create()
	{
	
		float xSize = dimension.getXSize()/2; //Octtree x size
		float ySize = dimension.getYSize()/2; //Octtree y size
		float zSize = dimension.getZSize()/2; //Octtree z size
		
		
		if((xSize*ySize*zSize)> minimumSize*minimumSize)
		{
			for(float x = dimension.xMinus; x < dimension.xPlus; x+= xSize)
			{
				for(float y = dimension.yMinus; y < dimension.yPlus; y+= ySize)
				{
					for(float z = dimension.zMinus; z < dimension.zPlus; z+= zSize)
					{
						BoundingBox newDim = new BoundingBox(new Vector3D(x, y, z), new Vector3D(x+ xSize, y+ySize, z+zSize));
						OcTree<E> newOcttree = new OcTree<E>(newDim, minimumSize);
						ocTrees.add(newOcttree);
					}
				}
			}
		}
	}
	
	public BoundingBox getBoundingBox()
	{
		return dimension;
	}

	public int getMinimumSize() {
		return minimumSize;
	}

	@Override
	public void add(E elem, BoundingBox dimension) 
	{
		boolean added = false;
		for (PartitionTree<E> oct : this.ocTrees) 
		{
			if(oct.fits(dimension))
			{
				oct.add(elem, dimension);
				added = true;
				return;
			}
		}
		if(!added)
		{
			this.elems.add(elem);
		}
		
	}

	@Override
	public boolean remove(E elem, Vector3D center) 
	{
		boolean removed = elems.remove(elem);
		if(!removed)
		{
			for (PartitionTree<E> octt : ocTrees) 
			{
				if(octt.contains(center))
				{
					removed = octt.remove(elem, center);
				}
			}
		}
		return removed;
	}

	@Override
	public void clear() 
	{
		elems.clear();
		for (PartitionTree<E> octt : ocTrees) 
		{
			octt.clear();
		}
	}

	@Override
	public List<E> getAll() 
	{
		List<E> all = new ArrayList<E>(); 
		for (PartitionTree<E> octt : ocTrees) 
		{
			all.addAll(octt.getAll());
		}
		all.addAll(this.elems);
		return all;
	}

	@Override
	public boolean fits(BoundingBox dimension) 
	{
		return this.dimension.fits(dimension);
	}

	@Override
	public boolean contains(Vector3D vertex) 
	{
		return dimension.containsVertex(vertex);
	}

	@Override
	public List<E> getElems() {
		return elems;
	}

	@Override
	public List<PartitionTree<E>> getChildren() 
	{
		return ocTrees;
	}

}
