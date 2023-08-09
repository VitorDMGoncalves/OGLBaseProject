package vdgapps.Frustum;

public class ViewPort
{
	private int width;
	private int height;
	private float aspectRatio;
	public float nearPlane;
	public float farPlane;
	public float fovy;
	
	public ViewPort()
	{
		width = 1;
		height = 1;
		aspectRatio = width/height;
		fovy = 45;
		nearPlane = 0.1f;
		farPlane = 1000;
		
	}

	public ViewPort(int width, int height, float nearPlane, float farPlane, float fovy) 
	{
		this.width = width;
		this.height = height;
		this.setAspectRatio();
		this.nearPlane = nearPlane;
		this.farPlane = farPlane;
		this.fovy = fovy;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) 
	{
		this.width = width;
		setAspectRatio();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		setAspectRatio();
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	private void setAspectRatio() 
	{
		if(height>0)
		{
			aspectRatio = width/height;
		}
		else
		{
			aspectRatio = 1;
		}
	}
	
	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append(width + " ");
		b.append(height + " ");
		b.append(fovy + " ");
		b.append(nearPlane + " ");
		b.append(farPlane + " ");
		return b.toString();
	}

}
