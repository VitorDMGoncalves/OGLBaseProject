package vdgapps.PartitionTree;

import java.util.List;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Vector3D;



public interface PartitionTree<E>
{
	public List<E> getAll();
	public void add(E elem, BoundingBox dimension);
	public void clear();
	public boolean remove(E elem, Vector3D center);
	public boolean fits(BoundingBox dimension);
	public boolean contains(Vector3D vertex);
	public BoundingBox getBoundingBox();
	public List<E> getElems();
	public List<PartitionTree<E>> getChildren();
}
