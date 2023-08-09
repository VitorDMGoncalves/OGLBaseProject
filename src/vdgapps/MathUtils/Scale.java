package vdgapps.MathUtils;

public class Scale 
{
	public float xScale = 0;
	public float yScale = 0;
	public float zScale = 0;
	
	
	public Scale()
	{
		xScale = 1;
		yScale = 1;
		zScale = 1;
	}
	
	public Scale(float xScale, float yScale, float zScale) 
	{
		this.xScale = xScale;
		this.yScale = yScale;
		this.zScale = zScale;
	}
	
	public String toString()
	{
		StringBuilder out = new StringBuilder();
		out.append(xScale + " ");
		out.append(yScale + " ");
		out.append(zScale);
		return out.toString();
	}
	
	
}
