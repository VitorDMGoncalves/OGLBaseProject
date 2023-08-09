package vdgapps.DecisionEngine;

import vdgapps.GameData.GameData;
import vdgapps.InputManager.Input;
import vdgapps.InputManager.InputManager;
import vdgapps.PhysicsEngine.IMovable;
import vdgapps.WorldObjects.IWorldObject;

public class DecisionEngine implements IDecider
{
	private GameData gameData;
	
	public DecisionEngine(GameData gameData)
	{
		this.gameData = gameData;
	}
	
	public void update()
	{
		
		if(InputManager.count()>0)
		{
			Input i;
			try 
			{
				i = InputManager.get();
				try 
				{
					IWorldObject o = gameData.getWorld().getWorldObject("cam1");
					IMovable mc = (IMovable)o;
					switch (i.type) 
					{
						//left controls
						case InputManager.rotateDownAction:
						{
							mc.getRotationVelocity().x--;
							break;
						}
							
						case InputManager.rotateUpAction:
						{
							mc.getRotationVelocity().x++;
							break;
						}
						
						case InputManager.rotateLeftAction:
						{
							mc.getRotationVelocity().y--;
							break;
						}
							
						case InputManager.rotateRightAction:
						{
							mc.getRotationVelocity().y++;
							break;
						}
						
						case InputManager.moveForwardAction:
						{
							mc.getMovingVelocity().z++;
							break;
						}
							
						case InputManager.moveBackAction:
						{
							mc.getMovingVelocity().z--;
							break;
						}
						
						//right controls
						case InputManager.moveUpAction:
						{
							mc.getMovingVelocity().y++;
							break;
						}
						
						case InputManager.moveDownAction:
						{
							mc.getMovingVelocity().y--;
							break;
						}
						
						case InputManager.moveLeftAction:
						{
							mc.getMovingVelocity().x--;
							break;
						}
						
						case InputManager.moveRightAction:
						{
							mc.getMovingVelocity().x++;
							break;
						}
						
						default:
							break;
					}		
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
					
			} 
			catch (Exception e) 
			{
				//e.printStackTrace();
			}
		}
	}
}
