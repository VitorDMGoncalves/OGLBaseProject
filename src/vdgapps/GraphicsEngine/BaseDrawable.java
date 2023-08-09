package vdgapps.GraphicsEngine;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.ModelManager.ModelManager;

public class BaseDrawable implements IDrawable
{
	private String name;
	private String modelName;
	private Vector3D position;
	private Direction direction;
	
	public BaseDrawable(String name, String modelName,Vector3D position, Direction direction) 
	{
		this.name = name;
		this.modelName = modelName;
		this.position = position;
		this.direction = direction;
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


	@Override
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
	
	
	

	
}
