package vdgapps.GameEngine;

public class GameEngineState 
{
	public static final int Ended = 0;
	public static final int Running = 1;
	public static final int Paused = 2;
	
	public int state;
	
	public GameEngineState(int state)
	{
		this.state = state;
	}
}
