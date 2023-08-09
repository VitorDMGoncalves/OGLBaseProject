package vdgapps.WorldObjects;

import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.PhysicsEngine.BaseMovable;

public class WorldMovable extends BaseMovable implements IWorldObject
{
	private int type;
	
	public WorldMovable(String name, Vector3D position, Direction direction, Vector3D velocity, int type) 
	{
		super(name, position, direction, velocity);
		this.type = type;
		
	}

	@Override
	public int getType() {
		return type;
	}
	
}
