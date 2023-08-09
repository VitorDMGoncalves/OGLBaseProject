package vdgapps.GraphicsEngine;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;

public interface IDrawable 
{
	public String getName();
	public String getModelName();
	public Vector3D getPosition();
	public Direction getDirection();
	public BoundingBox getBoundingBox();
}
