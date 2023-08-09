package vdgapps.WorldObjects;

import vdgapps.GraphicsEngine.BaseDrawable;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;

public class WorldDrawable extends BaseDrawable implements IWorldObject
{
	private int type;
	
	public WorldDrawable(String name, String modelName, Vector3D position,Direction direction, int type) 
	{
		super(name, modelName, position, direction);
		this.type = type;
	}

	@Override
	public int getType() {
		return type;
	}
	
}
