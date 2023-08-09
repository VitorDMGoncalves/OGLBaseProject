package vdgapps.LightManager;

import vdgapps.MathUtils.Vector3D;
import vdgapps.MathUtils.Vector4D;
import vdgapps.OGLUtils.Color4F;

public class Light 
{
	public Color4F diffuse;
	public Color4F specular;
	public Color4F ambient;
	public Color4F emissive;
	public Vector4D position;
	
	
	
	public Light(Color4F diffuse, Color4F specular, Color4F ambient,
			Color4F emissive, Vector4D position) {
		
		this.diffuse = diffuse;
		this.specular = specular;
		this.ambient = ambient;
		this.emissive = emissive;
		this.position = position;
	}
	
	public Light(Color4F diffuse, Vector4D position)
	{
		this.diffuse = diffuse;
		this.position = position;
		this.specular = new Color4F();
		this.emissive = new Color4F(0, 0, 0, 0);
		this.ambient = new Color4F(0, 0, 0, 0);
	}
	
}
