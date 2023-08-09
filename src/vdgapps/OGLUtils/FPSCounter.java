package vdgapps.OGLUtils;

public class FPSCounter 
{
	private Timer timer;
	private int count;
	private int lastCount;
	
	
	public FPSCounter()
	{
		this.timer = new Timer();
		this.timer.start();
		this.count = 0;
		this.lastCount = 60;
	}
	
	public int update()
	{
		this.count++;
		if(timer.getTimerElapsedSecs()>=1)
		{
			this.lastCount = this.count;
			this.count = 0;
			timer.reset();
		}
		return this.lastCount;
	}
}
