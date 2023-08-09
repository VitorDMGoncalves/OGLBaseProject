package vdgapps.PartitionTree;

import java.util.ArrayList;
import java.util.List;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector3D;


public class QuadTree<E> implements PartitionTree<E> 
{
	private BoundingBox boundingBox; //quadtree bounding box
	private int minimumSize = 100;//quadtree minimum size in square meters
	private List<E> elems = new ArrayList<E>();
	private List<PartitionTree<E>> quadTrees = new ArrayList<PartitionTree<E>>();
	
	public QuadTree(BoundingBox dimension, int minimumSize)
	{
		this.minimumSize = minimumSize;
		this.boundingBox = dimension;
		this.create();
	}
	
	private void create()
	{
		float xSize = boundingBox.getXSize()/2; //quadtree x size
		float zSize = boundingBox.getZSize()/2; //quadtree z size
		
		if((xSize*zSize) >= minimumSize*minimumSize)
		{
			for(float x = boundingBox.xMinus; x < boundingBox.xPlus; x+= xSize)
			{
				for(float z = boundingBox.zMinus; z < boundingBox.zPlus; z+= zSize)
				{
					BoundingBox newDim = new BoundingBox(new Vector3D(x, boundingBox.yMinus, z), new Vector3D(x+ xSize, boundingBox.yPlus, z+zSize));
					QuadTree<E> newOcttree = new QuadTree<E>(newDim, minimumSize);
					quadTrees.add(newOcttree);
				}
			}
		}
	}
	
	public BoundingBox getBoundingBox()
	{
		return boundingBox;
	}

	public int getMinimumSize() {
		return minimumSize;
	}

	public List<PartitionTree<E>> getOctTrees() {
		return quadTrees;
	}
	
	
	@Override
	public List<E> getAll() {
		List<E> all = new ArrayList<E>(); 
		for (PartitionTree<E> qt : quadTrees) 
		{
			all.addAll(qt.getAll());
		}
		all.addAll(this.elems);
		return all;
	}

	@Override
	public void add(E elem, BoundingBox dimension) 
	{
		boolean added = false;
		for (PartitionTree<E> qt : this.quadTrees) 
		{
			if(qt.fits(dimension))
			{
				qt.add(elem, dimension);
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
	public void clear() {
		elems.clear();
		for (PartitionTree<E> qt : quadTrees) 
		{
			qt.clear();
		}
	}

	@Override
	public boolean remove(E elem, Vector3D center) {
		boolean removed = elems.remove(elem);
		if(!removed)
		{
			for (PartitionTree<E> qt : quadTrees) 
			{
				if(qt.contains(center))
				{
					removed = qt.remove(elem, center);
				}
			}
		}
		return removed;
	}

	@Override
	public boolean fits(BoundingBox dimension) {
		return this.boundingBox.fits(dimension);
	}

	@Override
	public boolean contains(Vector3D vertex) {
		return boundingBox.containsVertex(vertex);
	}

	@Override
	public List<E> getElems() {
		return elems;
	}

	@Override
	public List<PartitionTree<E>> getChildren() {
		return quadTrees;
	}

}
