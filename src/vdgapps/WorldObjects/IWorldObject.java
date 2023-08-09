package vdgapps.WorldObjects;

import vdgapps.BoundingVolumes.BoundingBox;

public interface IWorldObject 
{
	public int getType();
	public String getName();
	public BoundingBox getBoundingBox();
}
