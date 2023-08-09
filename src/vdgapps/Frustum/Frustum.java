package vdgapps.Frustum;

import java.util.ArrayList;
import java.util.List;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.CameraManager.Camera;
import vdgapps.MathUtils.MathUtils;
import vdgapps.MathUtils.Vector3D;



public class Frustum 
{
	private static int NEARPLANE = 0;
	private static int FARPLANE = 1;
	private static int LEFTPLANE = 2;
	private static int RIGHTPLANE = 3;
	private static int BOTTOMPLANE = 4;
	private static int TOPPLANE = 5;
	private static int OUTSIDE = 7;
	private static int INSIDE = 8;
	private static int INTERSECT = 9;
	
	private List<Plane> planes;
	private Vector3D camP;
	
	
	public Frustum()
	{
		planes = new ArrayList<Plane>(6);
		camP = new Vector3D();
	}
	
	
	public void update(Camera camera, ViewPort viewport)
	{
	
		camP = camera.position;
		Vector3D view = camera.view.toVector();
		//Vector3D zAxis = new Vector3D(camP.x - view.x, camP.y- view.y, camP.z - view.z);
		Vector3D zAxis = view;
		zAxis.normalize();
		
		Vector3D xAxis =  MathUtils.crossProduct(camera.upVector.toVector(), zAxis);
		xAxis.normalize();
		
		Vector3D yAxis = MathUtils.crossProduct(zAxis, xAxis);
		yAxis.normalize();
		
		float farDist = viewport.farPlane;
		float nearDist = viewport.nearPlane;
		float nearHeight = (float)(2 * Math.tan(MathUtils.toRads(viewport.fovy / 2)) * nearDist);
		float nearWidth = nearHeight* viewport.getAspectRatio();
		float farHeight = (float)(2 * Math.tan(MathUtils.toRads(viewport.fovy / 2)) * farDist);
		float farWidth = farHeight * viewport.getAspectRatio();
 		
		Vector3D fc = new Vector3D();
		Vector3D nc = new Vector3D();
		
		fc.x = camP.x + view.x*farDist; fc.y = camP.y + view.y*farDist; fc.z = camP.z + view.z*farDist;
		nc.x = camP.x + view.x *nearDist; nc.y = camP.y + view.y *nearDist; nc.z = camP.z + view.z *nearDist;  
		
		
		
		
		planes.clear();
		//near plane
		Plane  p = new Plane(nc, zAxis);
		planes.add(p);
		
		//far plane
		p = new Plane(fc, new Vector3D(zAxis.x * -1, zAxis.y * -1, zAxis.z * -1));
		planes.add(p);
		
		
		//top plane
		Vector3D aux = new Vector3D();
		aux.x = (nc.x + yAxis.x*nearHeight/2) - camP.x;
		aux.y = (nc.y + yAxis.y*nearHeight/2) - camP.y;
		aux.z = (nc.z + yAxis.z*nearHeight/2) - camP.z;
		aux.normalize();
		
		Vector3D normal = MathUtils.crossProduct(xAxis, aux);
		normal.normalize();
		Vector3D point = new Vector3D(nc.x + yAxis.x*nearHeight, nc.y + yAxis.y*nearHeight, nc.z + yAxis.z*nearHeight);
		//p = new Plane(point, normal);
		p = new Plane(camP, normal);
		planes.add(p);
		
		//bottom plane
		aux = new Vector3D();
		aux.x = (nc.x - yAxis.x*nearHeight/2) - camP.x;
		aux.y = (nc.y - yAxis.y*nearHeight/2) - camP.y;
		aux.z = (nc.z - yAxis.z*nearHeight/2) - camP.z;
		aux.normalize();
		
		normal = MathUtils.crossProduct(aux, xAxis);
		normal.normalize();
		point = new Vector3D(nc.x - yAxis.x*nearHeight, nc.y - yAxis.y*nearHeight, nc.z - yAxis.z*nearHeight);
		//p = new Plane(point, normal);
		p = new Plane(camP, normal);
		planes.add(p);
		
		//left plane
		aux = new Vector3D();
		aux.x = (nc.x + xAxis.x*nearWidth/2) - camP.x;
		aux.y = (nc.y + xAxis.y*nearWidth/2) - camP.y;
		aux.z = (nc.z + xAxis.z*nearWidth/2) - camP.z;
		aux.normalize();
		
		normal = MathUtils.crossProduct(aux, yAxis);
		normal.normalize();
		point = new Vector3D(nc.x + xAxis.x*nearWidth, nc.y + xAxis.y*nearWidth, nc.z + xAxis.z*nearWidth);
		//p = new Plane(point, normal);
		p = new Plane(camP, normal);
		planes.add(p);
		
		//right plane
		aux = new Vector3D();
		aux.x = (nc.x - xAxis.x*nearWidth/2) - camP.x;
		aux.y = (nc.y - xAxis.y*nearWidth/2) - camP.y;
		aux.z = (nc.z - xAxis.z*nearWidth/2) - camP.z;
		aux.normalize();
		
		normal = MathUtils.crossProduct(yAxis, aux);
		normal.normalize();
		point = new Vector3D(nc.x - xAxis.x*nearWidth, nc.y - xAxis.y*nearWidth, nc.z - xAxis.z*nearWidth);
		//p = new Plane(point, normal);
		p = new Plane(camP, normal);
		planes.add(p);
		
	}
	
	
	
	public float testVolume(BoundingBox bbox)
	{
	    int is_at  = INSIDE; 
	    Vector3D pos = new  Vector3D(bbox.xMinus, bbox.yMinus, bbox.zMinus); 
	    Vector3D neg = new Vector3D(bbox.xPlus, bbox.yPlus, bbox.zPlus); 
	  
	    for( int i=0 ; i < 6 ; i++)
	    {
	        pos.x = bbox.xMinus;
	        pos.y = bbox.yMinus;
	        pos.z = bbox.zMinus;
	        
	        neg.x = bbox.xPlus;
	        neg.y = bbox.yPlus;
	        neg.z = bbox.zPlus;

	        Vector3D normal = planes.get(i).getNormal(); 
	        if (normal.x >=0 )
	        {
	           pos.x = bbox.xPlus; 
	           neg.x = bbox.xMinus; 
	        }
	        if (normal.y >=0)
	        {
	            pos.y = bbox.yPlus;
	            neg.y = bbox.yMinus; 
	        }
	        if (normal.z >= 0)
	        {
	            pos.z = bbox.zPlus; 
	            neg.z = bbox.zMinus; 
	        }
	       
	        if(planes.get(i).getSignedDistance(pos)< 0)
	        {
	        	is_at = OUTSIDE;
	        	break;
	        }
	        else 
	        {
	        	if(planes.get(i).getSignedDistance(neg)< 0)
	        	{
	        		is_at = INTERSECT;  
	        	}
	        }
	    }
	    
	    if (is_at == OUTSIDE) 
    	{
    		return -1; 
    	}
	    
	    return MathUtils.getDistance(camP, bbox.getCenter()); //MathAuxFunc::getDistance(camPos, c); 
	}
	
}
