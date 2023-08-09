package vdgapps.InputManager;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class InputManager 
{
	
	public static final int rotateUpAction = 5;
	public static final int rotateDownAction = 6;
	public static final int rotateLeftAction = 7;
	public static final int rotateRightAction = 8;
	public static final int moveForwardAction = 9;
	public static final int moveBackAction = 10;
	public static final int moveLeftAction = 11;
	public static final int moveRightAction = 12;
	public static final int moveUpAction = 13;
	public static final int moveDownAction = 14;
	public static int test = 3;
	
	
	private static LinkedBlockingQueue<Input> inputQueue = new LinkedBlockingQueue<Input>();
	
	public InputManager()
	{
		inputQueue = new LinkedBlockingQueue<Input>();
	}
	
	
	public static void clear()
	{
		inputQueue.clear();
	}
	
	public static void add(Input i) throws InterruptedException
	{
		inputQueue.put(i);
	}
	
	
	public static boolean hasInput()
	{
		return !inputQueue.isEmpty();
	}
	
	public static Input get() throws Exception
	{
		Input p = inputQueue.poll(); 
		if(p==null)
			throw new Exception("input queue is empty");
		return p;
	}
	
	public static int count()
	{
		return inputQueue.size();
	}
	
	public static ArrayList<Input> getAll()
	{
		ArrayList<Input> all = new ArrayList<Input>();
		for (Input input : inputQueue)
		{
			all.add(input);
		}
		
		return all;
	}
	
}
