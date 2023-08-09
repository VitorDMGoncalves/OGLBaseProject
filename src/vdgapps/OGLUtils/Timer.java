package vdgapps.OGLUtils;

public class Timer 
{
	private double initTime;
	private double nowTime;
	private double newElapsedTime;
	private double lastElapsedTime;
	
	
	public Timer()
	{
		this.initTime = android.os.SystemClock.elapsedRealtime();
		this.newElapsedTime = 0;
	}
	
	public void start()
	{
		this.recordTime();
	}
	
	public static double staticNowSeconds()
	{

		return (android.os.SystemClock.elapsedRealtime()*0.001);
	}
	
	public static double getNowMinuts()
	{

		return (android.os.SystemClock.elapsedRealtime()*0.001/60);
	}
	
	public static double getNowHours()
	{
		return (android.os.SystemClock.elapsedRealtime()*0.001/60/60);
	}
	
	public double getTimerElapsedMs()
	{
		this.recordTime();
		return (this.newElapsedTime);
	}
	
	public double getTimerElapsedSecs()
	{
		this.recordTime();
		return (this.newElapsedTime*0.001f);
	}
	
	public double getTimerElapsedMins()
	{
		this.recordTime();
		return (this.newElapsedTime*0.001/60);
	}
	
	public double getTimerElapsedHours()
	{
		this.recordTime();
		return (this.newElapsedTime*0.001/3600);
	}
	
	public double getTimerRecordsDiffMs()
	{
		this.recordTime();
		return this.newElapsedTime - this.lastElapsedTime;
	}
	
	public double getTimerRecordsDiffSecs()
	{
		this.recordTime();
		return (this.newElapsedTime - this.lastElapsedTime)*0.001f;
	}
	
	public double getTimerRecordsDiffMins()
	{
		this.recordTime();
		return (this.newElapsedTime - this.lastElapsedTime)*0.001/60;
	}
	
	public double getTimerRecordsDiffHours()
	{
		this.recordTime();
		return (this.newElapsedTime - this.lastElapsedTime)*0.001/3600;
	}
	
	private void recordTime()
	{
		this.nowTime = (android.os.SystemClock.elapsedRealtime());
		this.lastElapsedTime = this.newElapsedTime;
		this.newElapsedTime = this.nowTime - this.initTime;
	}
	
	public void forceTimeRecord()
	{
		this.recordTime();
	}
	
	public void reset()
	{
		this.initTime = android.os.SystemClock.elapsedRealtime();
		this.recordTime();
	}
}
