package vdgapps.PhysicsEngine;

import java.util.concurrent.locks.ReentrantLock;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;

public class BaseMovable implements IMovable
{
	private String name;
	public Vector3D position;
	public Direction direction;
	public Vector3D movingVelocity;
	public Vector3D rotationVelocity;
	public boolean isColidable;
	private BoundingBox dimension;
	
	private ReentrantLock lock;
	
	public BaseMovable(String name, Vector3D position, Direction direction, Vector3D velocity) {
		this.direction = direction;
		this.position = position;
		this.movingVelocity = velocity;
		this.rotationVelocity = new Vector3D();
		this.lock = new ReentrantLock();
		this.isColidable = true;
		this.name = name;
		this.dimension = new BoundingBox();
	}
	
	
	public void lock()
	{
		this.lock.lock();
		position.lock();
		direction.lock();
		movingVelocity.lock();
		rotationVelocity.lock();
	}
	
	public void unLock()
	{
		this.lock.unlock();
		position.unLock();
		direction.unLock();
		movingVelocity.unLock();
		rotationVelocity.unLock();
	}
	
	public String getName()
	{
		return name;
	}
	
	//IMovable implementation
	
	@Override
	public Vector3D getPosition() {
		return this.position;
	}

	@Override
	public Vector3D getMovingVelocity() {
		return this.movingVelocity;
	}


	@Override
	public Vector3D getRotationVelocity() {
		return rotationVelocity;
	}


	@Override
	public Direction getDirection() {
		return direction;
	}


	@Override
	public boolean isColidable() {
		return isColidable;
	}


	@Override
	public BoundingBox getBoundingBox() {
		return dimension;
	}


}
