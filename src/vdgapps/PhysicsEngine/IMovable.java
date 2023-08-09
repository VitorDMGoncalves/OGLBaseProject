package vdgapps.PhysicsEngine;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;

public interface IMovable 
{
	public boolean isColidable();
	public String getName();
	public Vector3D getPosition();
	public Vector3D getMovingVelocity();
	public Vector3D getRotationVelocity();
	public Direction getDirection();
	public void lock();
	public void unLock();
	public BoundingBox getBoundingBox();
}
