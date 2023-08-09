package vdgapps.PhysicsEngine;

import android.util.Log;
import vdgapps.GameData.GameData;
import vdgapps.MathUtils.Direction;
import vdgapps.MathUtils.Vector3D;
import vdgapps.OGLUtils.Timer;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.WorldObjects.WorldObjectsType;

public class PhysicsEngine
{
	private Timer timer;
	private GameData gData;
	
	public PhysicsEngine(GameData gData)
	{
		this.timer = gData.peData.timer;
		this.gData = gData;
	}
	
	
	public void update()
	{
		double timeDiff = timer.getTimerRecordsDiffSecs();
	
		for (IWorldObject o : gData.getWorld().getWorldObjectMap().values()) 
		{
			if(o.getType()== WorldObjectsType.MOVABLE)
			{
				IMovable iMovable = (IMovable)o;
				iMovable.lock();	
				rotateX(iMovable, timeDiff);
				rotateY(iMovable, timeDiff);
				moveX(iMovable, timeDiff);
				moveY(iMovable, timeDiff);
				moveZ(iMovable, timeDiff);
				checkTerrainCollision(iMovable);
				iMovable.unLock();
			}
		}
	}


	private void checkTerrainCollision(IMovable iMovable) 
	{
		Vector3D p = iMovable.getPosition();
		try 
		{
		//	p.y = gData.getWorld().getTerrain().computeHeight(p.x, p.z) + 0.5f;
			//Log.d("h", "" +gData.getWorld().getTerrain().computeHeight(9.5f, 1.5f));
		} 
		catch (Exception e) 
		{
			//iMovable keeps the same height if there isn't a current terrain
		}
		
	}


	private void rotateY(IMovable iMovable, double time) 
	{
		Direction dir = iMovable.getDirection();
		Vector3D vel = iMovable.getRotationVelocity();
		
		dir.theta += vel.x* (float)time;
		dir.phi += vel.y* (float)time;	
	}


	private void rotateX(IMovable iMovable, double time) 
	{
		Direction dir = iMovable.getDirection();
		Vector3D vel = iMovable.getRotationVelocity();
		
		//dir.theta += vel.x* (float)time;
		dir.phi += vel.y* (float)time;
	}


	private void moveX(IMovable iMovable, double time) 
	{
		Vector3D pos = iMovable.getPosition();
		Direction dir = iMovable.getDirection();
		Vector3D vel = iMovable.getMovingVelocity();
		Direction pdir = new Direction(dir.theta, dir.phi);
		
		pdir.phi += 90;
		pos.x += pdir.getX() * vel.x* (float)time;
		//pos.y += pdir.getY() * vel.x* (float)time;
		pos.z += pdir.getZ() * vel.x* (float)time;
	}
	
	private void moveY(IMovable iMovable, double time) 
	{
		Vector3D pos = iMovable.getPosition();
		Direction dir = iMovable.getDirection();
		Vector3D vel = iMovable.getMovingVelocity();
		Direction pdir = new Direction(dir.theta, dir.phi);
		
		pdir.theta += 90;
		pos.x += pdir.getX() * vel.y* (float)time;
		pos.y += pdir.getY() * vel.y* (float)time;
		pos.z += pdir.getZ() * vel.y* (float)time;	
	}

	private void moveZ(IMovable iMovable, double time) 
	{
		Vector3D pos = iMovable.getPosition();
		Direction dir = iMovable.getDirection();
		Vector3D vel = iMovable.getMovingVelocity();
		
		pos.x += dir.getX() * vel.z* (float)time;
		pos.y += dir.getY() * vel.z* (float)time;
		pos.z += dir.getZ() * vel.z* (float)time;
		
	}
	
}
