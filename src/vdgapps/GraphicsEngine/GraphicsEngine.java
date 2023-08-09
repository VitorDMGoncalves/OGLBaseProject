package vdgapps.GraphicsEngine;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.CameraManager.CameraManager;
import vdgapps.Frustum.Frustum;
import vdgapps.GameData.GameData;
import vdgapps.LightManager.LightManager;
import vdgapps.MathUtils.Vector3D;
import vdgapps.ModelManager.IModel;
import vdgapps.ModelManager.ModelManager;
import vdgapps.OGLUtils.FPSCounter;
import vdgapps.PartitionTree.PartitionTree;
import vdgapps.TextureManager.TextureManager;
import vdgapps.WorldObjects.IWorldObject;
import vdgapps.WorldObjects.WorldObjectsType;
import vdgapps.config.Config;
import vdgapps.heightMap.HeightMap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.Log;

public class GraphicsEngine implements Renderer 
{
	private FPSCounter fpsC;
	private GameData gData;
	private Frustum frustum;
	private int count = 0;
	
	public GraphicsEngine(GameData gData)
	{
		this.gData = gData;
		fpsC = new FPSCounter();
		frustum = new Frustum();
	}
	
	
	private void draw(PartitionTree<IWorldObject> objs,GL10 gl )
	{
		count++;
		if(objs.getChildren().size()>0 && frustum.testVolume(objs.getBoundingBox())>=0)
		{
			for (IWorldObject o : objs.getElems()) 
			{
				if(o.getType()== WorldObjectsType.DRAWABLE)
				{
					IDrawable d = (IDrawable)o;
					if(frustum.testVolume(d.getBoundingBox())>=0)
					{
						count++;
						ModelManager.draw(d.getModelName(), gl);
					}
				}		
			}
			
			for (PartitionTree<IWorldObject> pt : objs.getChildren()) 
			{
				if(pt.getElems().size()>0 && pt.getChildren().size()>0)
				{
					draw(pt, gl);
				}
			}
			
		}
	}
	
	@Override
	public void onDrawFrame(GL10 gl) 
	{
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		//gData.cManager.bindCamera(gl);
		CameraManager.bindCamera(gl);
		//Log.d("Framerate", "" + fpsC.update());
		LightManager.bindLight(GL10.GL_LIGHT0, gl);
		
		frustum.update(CameraManager.getActiveCamera(), OglConfig.viewport);
		
		gl.glColor4f(1f, 0f, 0f, 1f);
		
		gl.glPushMatrix();
		{
			draw( gData.getWorld().getWorldObjectsTree(), gl);
			Log.d("Frustrum count", ""+ count);
			count = 0;
		}
		gl.glPopMatrix();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		OglConfig.viewport.setWidth(width);
		OglConfig.viewport.setHeight(height);
		
		gl.glViewport(0, 0, width, height);
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		gl.glLoadIdentity();
		
		GLU.gluPerspective(gl, OglConfig.viewport.fovy, OglConfig.viewport.getAspectRatio(), 
							OglConfig.viewport.nearPlane, OglConfig.viewport.farPlane);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		
		gl.glLoadIdentity();
		
		Log.d("viewpor", OglConfig.viewport.toString());
		
	}

 
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) 
	{
		gl.glClearColor(1f, 1f, 1f, 1f);
		
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnable(GL10.GL_TEXTURE);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		OglConfig.gl10 = gl;
		OglConfig.viewport.farPlane = 4000;
		OglConfig.viewport.nearPlane = 0.1f;
		OglConfig.viewport.fovy = 45f;
		
		loadData(gl);
	}

	private void loadData(GL10 gl) 
	{
		Log.d("Loading", "loading  graphics objects started");
		for (IModel m : ModelManager.getAll()) 
		{
			m.loadModel(gl);
			TextureManager.loadTexture(gl, Config.context, m.getBaseTexture());
		}
		Log.d("Loading", "loading  graphics objects ended");
	}
	
}
