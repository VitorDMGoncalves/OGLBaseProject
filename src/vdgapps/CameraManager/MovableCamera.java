package vdgapps.CameraManager;

import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.PhysicsEngine.BaseMovable;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.WorldObjects.WorldObjectsType;

public class MovableCamera extends BaseMovable implements ICameraAttachable, IWorldObject
{

	public MovableCamera(String name,Vector3D position, Direction direction,
			Vector3D velocity) 
	{
		super(name, position, direction, velocity);
	}

	@Override
	public void AttachCamera(Camera cam) 
	{
		cam.lock();
		cam.position = position;
		cam.view = direction;
		cam.unLock();
	}

	@Override
	public int getType() {
		
		return WorldObjectsType.MOVABLE;
	}
	
}
