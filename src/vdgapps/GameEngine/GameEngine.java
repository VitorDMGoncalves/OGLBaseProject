package vdgapps.GameEngine;

import vdgapps.DecisionEngine.DecisionEngine;
import vdgapps.GameData.GameData;
import vdgapps.GraphicsEngine.GraphicsEngine;
import vdgapps.Oglbaseproject.OGLBaseProjectActivity;
import vdgapps.PhysicsEngine.PhysicsEngine;
import vdgapps.config.Config;
import android.app.Activity;
import android.opengl.GLSurfaceView;

public class GameEngine extends Thread
{
	private GLSurfaceView glsv;
	private GameData gData;
	private GameEngineState geState;
	private GraphicsEngine gEngine;
	private PhysicsEngine pEngine;
	private DecisionEngine dEngine;
	
	
	public GameEngine(GameEngineState geState, GLSurfaceView glSurface, GameData gData, Activity act)
	{
		Config.context = act.getApplicationContext();
		this.geState = geState;
		this.gData = gData;
		this.gData.load();
		this.glsv = glSurface;
		this.pEngine = new PhysicsEngine(gData);
		this.dEngine = new DecisionEngine(gData);
		this.gEngine = new GraphicsEngine(gData);
		this.glsv.setRenderer(gEngine);
		this.glsv.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}


	private void mainLoop()
	{
		
		gData.geData.timer.forceTimeRecord();
		gData.peData.timer.forceTimeRecord();
		
		while (geState.state != GameEngineState.Ended )
		{
			if(geState.state == GameEngineState.Running)
			{
				dEngine.update();
				pEngine.update();
				glsv.requestRender();
			}
			else
			{
				gData.geData.timer.forceTimeRecord();
				gData.peData.timer.forceTimeRecord();
			}
		} 
	}
	
	@Override
	public void run() 
	{
		this.mainLoop();
	}

	
}
