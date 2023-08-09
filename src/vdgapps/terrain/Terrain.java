package vdgapps.terrain;



import java.util.List;

import vdgapps.BoundingVolumes.BoundingBox;
import vdgapps.MathUtils.Scale;
import vdgapps.PartitionTree.PartitionTree;
import vdgapps.PartitionTree.QuadTree;
import vdgapps.heightMap.HeightMap;



public class Terrain 
{
	private static int MINIMUMQUADTREESIZE = 100; //minimum vertices a quadtree must have
	private String name;
	private String baseTexture;
	private String detailTexture;
	private PartitionTree<HeightMap> heightMaps;
	private BoundingBox dimensions = new BoundingBox();
	
	
	public Terrain()
	{
		name = "";
		baseTexture = "";
		detailTexture = "";
		dimensions = new BoundingBox();
		heightMaps = new QuadTree<HeightMap>(dimensions, MINIMUMQUADTREESIZE);
	}
	
	public Terrain(String name, PartitionTree<HeightMap> heightmaps)
	{
		this.name = name;
		this.heightMaps = heightmaps;
		this.baseTexture = heightmaps.getAll().get(0).getModel().getBaseTexture();
		this.detailTexture = heightmaps.getAll().get(0).getModel().getDetailTexture();
		this.computeDimensions();
	}
	
	public Terrain(String name, List<HeightMap> heightmaps)
	{
		this.name = name;
		
		this.heightMaps = new QuadTree<HeightMap>(dimensions, MINIMUMQUADTREESIZE);
		for (HeightMap h : heightmaps) 
		{
			this.heightMaps.add(h, h.getModel().getDimension());
		}
		
		this.baseTexture = this.heightMaps.getAll().get(0).getModel().getBaseTexture();
		this.detailTexture = this.heightMaps.getAll().get(0).getModel().getDetailTexture();
		this.computeDimensions();
	}
	
	private void computeDimensions() 
	{
		BoundingBox d;
		for (HeightMap h : heightMaps.getAll()) 
		{
			d = h.getModel().getDimension();
			//left
			if(d.xMinus < dimensions.xMinus)
				dimensions.xMinus = d.xMinus;
			//right
			if(d.xPlus> dimensions.xPlus)
				dimensions.xPlus = d.xPlus;
			//bottom
			if(d.yMinus < dimensions.yMinus)
				dimensions.yMinus = d.yMinus;
			//top
			if(d.yPlus> dimensions.yPlus)
				dimensions.yPlus = d.yPlus;
			//far
			if(d.zMinus < dimensions.zMinus)
				dimensions.zMinus = d.zMinus;
			//near
			if(d.zPlus> dimensions.zPlus)
				dimensions.zPlus = d.zPlus;	
		}
	}

	//check which heightmap has the height and used it to get it
	public float computeHeight(float x, float z) 
	{
		for (HeightMap h : heightMaps.getAll()) 
		{
			if(h.hasPoint(x, z))
			{
				return h.computeHeight(x, z);
			}
		}
		return 0;
	}
	
	
	public void resize(float x, float y, float z)
	{
		Scale newScale = new Scale();
		newScale.xScale = x/dimensions.getXSize();
		newScale.zScale = z/dimensions.getZSize();
		newScale.yScale = newScale.xScale;
		for (HeightMap h : heightMaps.getAll()) 
		{
			h.reScale(newScale);
		}
		dimensions.scale(newScale);
	}
	
	public List<HeightMap> getHeighMaps()
	{
		return heightMaps.getAll(); 
	}
	
	public String getBaseTexture() {
		return baseTexture;
	}

	public void setBaseTexture(String baseTexture) {
		this.baseTexture = baseTexture;
	}

	public String getDetailTexture() {
		return detailTexture;
	}

	public void setDetailTexture(String detailTexture) {
		this.detailTexture = detailTexture;
	}


	public BoundingBox getDimensions() {
		return dimensions;
	}

	public String getName() {
		return name;
	}

	public void setName(String terrainName) {
		this.name = terrainName;
	}

	public void setHeightMaps(PartitionTree<HeightMap> heightsMaps) {
		this.heightMaps = heightsMaps;
	}

}
