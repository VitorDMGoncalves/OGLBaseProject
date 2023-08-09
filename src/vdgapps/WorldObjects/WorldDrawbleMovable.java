package vdgapps.WorldObjects;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.GraphicsEngine.IDrawable;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.ModelManager.ModelManager;
import vdgapps.PhysicsEngine.IMovable;

public class WorldDrawbleMovable implements IMovable, IDrawable, IWorldObject
{

	private String name;
	private String modelName;
	private Vector3D position;
	private Direction direction;
	public Vector3D movingVelocity;
	public Vector3D rotationVelocity;
	public boolean isColidable;
	private int type;
	
	public WorldDrawbleMovable(String name, String modelName,Vector3D position, 
			Direction direction , boolean isColidable, int type) 
	{
		this.name = name;
		this.modelName = modelName;
		this.position = position;
		this.direction = direction;
		this.movingVelocity = new Vector3D();
		this.rotationVelocity = new Vector3D();
		this.isColidable = isColidable;
		this.type = type;
	}

	
	public String getName() {
		return name;
	}

	public String getModelName() {
		return modelName;
	}

	public Vector3D getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public Vector3D getMovingVelocity() {
		return movingVelocity;
	}

	public Vector3D getRotationVelocity() {
		return rotationVelocity;
	}

	public boolean isColidable() {
		return isColidable;
	}

	public int getType() {
		return type;
	}
	
	public BoundingBox getBoundingBox()
	{
		try 
		{
			return ModelManager.get(modelName).getModelDimensions();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new BoundingBox();
		}
	}

	@Override
	public void lock() 
	{
		
	}

	@Override
	public void unLock()
	{
		
	}

}
