package vdgapps.MathUtils;

public class MathUtils 
{
	public static final float PI =3.1416f; 
	public static float getAngleBetweenAB(float ax, float ay, float bx, float by)
	{
		
		return (float)(Math.atan2(ay-by, ax-bx));
	}
	
	
	public static Vector3D crossProduct(Vector3D p1, Vector3D p2)
	{
		Vector3D res = new Vector3D();
		res.x = ((p1.y * p2.z) - (p1.z * p2.y));
		res.y = ((p1.z * p2.x) - (p1.x * p2.z));
		res.z = ((p1.x * p2.y) - (p1.y * p2.x));
		return res;
	}
	
	
	public static Vector3D calcTriangleNormal(Vector3D p1, Vector3D p2, Vector3D p3)
	{
		Vector3D p2MinusP1 = new Vector3D();
		Vector3D p3MinusP1 = new Vector3D();
		p2MinusP1.x = (p2.x - p1.x);
		p2MinusP1.y = (p2.y - p1.y);
		p2MinusP1.z = (p2.z - p1.z);
		p3MinusP1.x = (p3.x - p1.x);
		p3MinusP1.y = (p3.y - p1.y);
		p3MinusP1.z = (p3.z - p1.z);

		Vector3D res = crossProduct(p2MinusP1, p3MinusP1);
		return res;
	}
	
	//returns the distances between two points
	public static float getDistance(Vector3D a, Vector3D b)
	{
		return (float)Math.sqrt((b.x - a.x) * (b.x - a.x) + 
								(b.y - a.y) * (b.y - a.y) + 
								(b.z - a.z) * (b.z - a.z));
	}
	
	
	// dot product between two vectors
	public static float dotProduct(Vector3D v1 , Vector3D v2)
	{
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z; 
	}
	
	public static float toDegs(float rads)
	{	
		return (rads*180)/(float)PI;
	}
	
	public static float toRads(float degs)
	{
		return (float)(degs*PI)/180;
	}
}