package vdgapps.WorldManager;



public class ObjectData 
{
	public float x;
	public float y;
	public float z;
	public boolean isMovable;
	public boolean isDrawable;
	public boolean isColidable;
	public int theta;
	public int phi;
	public String modelName;
	public String name;
	
	
	public ObjectData()
	{
		x = 0;
		y = 0;
		z = 0;
		isMovable = true;
		isDrawable = true;
		isColidable = true;
		theta = 0;
		phi = 0;
		modelName = "na";
		name ="na";
	}
	
	public ObjectData(float x, float y, float z, String name, String modelName) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.isMovable = true;
		this.isDrawable =  true;
		this.isColidable =  true;
		this.theta = 0;
		this.phi = 0;
		this.modelName = modelName;
		this.name = name;
	}
	
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		out.append(name+ " ");
		out.append(modelName + " ");
		out.append(x + " ");
		out.append(y + " ");
		out.append(z + " ");
		out.append(phi +" ");
		out.append(theta + " ");
		out.append(isMovable + " ");
		out.append(isDrawable + " ");
		out.append(isColidable);
		
		return out.toString(); 
	}
	
	
	
}
