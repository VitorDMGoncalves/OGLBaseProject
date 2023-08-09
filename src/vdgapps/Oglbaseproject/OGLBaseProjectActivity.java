package vdgapps.Oglbaseproject;


import vdgapps.GameData.GameData;
import vdgapps.GameEngine.GameEngine;
import vdgapps.GameEngine.GameEngineState;
import vdgapps.InputManager.Input;
import vdgapps.InputManager.InputManager;
import vdgapps.Oglbaseproject.R;
import vdgapps.config.Config;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OGLBaseProjectActivity extends Activity implements android.view.View.OnClickListener {
    /** Called when the activity is first created. */
   
	private GameEngineState geState;
	private GameEngine gm;
	private GLSurfaceView glsv;
	private GameData gData;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);
        
        Button pauseButton = (Button)findViewById(R.id.pause);
        pauseButton.setOnClickListener(this);
        
        Button resumeButton = (Button)findViewById(R.id.resume);
        resumeButton.setOnClickListener(this);
        
        Button exitButton = (Button)findViewById(R.id.exit);
        exitButton.setOnClickListener(this);
        
        //left controls
        Button lcupButton = (Button)findViewById(R.id.up);
        lcupButton.setOnClickListener(this);
        
        Button lcdownButton = (Button)findViewById(R.id.down);
        lcdownButton.setOnClickListener(this);
        
        Button lcleftButton = (Button)findViewById(R.id.left);
        lcleftButton.setOnClickListener(this);
        
        Button lcrightButton = (Button)findViewById(R.id.right);
        lcrightButton.setOnClickListener(this);
        
        Button lcbackButton = (Button)findViewById(R.id.back);
        lcbackButton.setOnClickListener(this);
        
        Button lcforwardButton = (Button)findViewById(R.id.forward);
        lcforwardButton.setOnClickListener(this);
        
        //right controls
        Button rcupButton = (Button)findViewById(R.id.rcup);
        rcupButton.setOnClickListener(this);
        
        Button rcdownButton = (Button)findViewById(R.id.rcdown);
        rcdownButton.setOnClickListener(this);
        
        Button rcleftButton = (Button)findViewById(R.id.rcleft);
        rcleftButton.setOnClickListener(this);
        
        Button rcrightButton = (Button)findViewById(R.id.rcright);
        rcrightButton.setOnClickListener(this);
       
        glsv =  (GLSurfaceView)findViewById(R.id.glrenderer);
        
        gData = new GameData("w2");
        geState = new GameEngineState(GameEngineState.Paused);
        gm = new GameEngine(geState, glsv, gData, this);
        gm.start();
    }

	private void resume()
	{
		geState.state = GameEngineState.Running;
	}
	
	private void exit()
	{
		geState.state = GameEngineState.Ended;
		this.finish();
	}
	
	private void pause()
	{
		geState.state = GameEngineState.Paused;
	}
	
	@Override
	public void onClick(View v) 
	{
		int op = v.getId();
		
		try{
		
		switch (op) 
		{
			case R.id.pause:
			{
				pause(); 
				InputManager.add(new Input(1, 9, 0));
				break;
			}
			
			case R.id.resume:
				{
					resume();
					InputManager.add(new Input(2, 9, 0));
					break;
				}
			
			case R.id.up:
			{
				InputManager.add(new Input(InputManager.rotateUpAction, 9, 0));
				break;
			}
			
			case R.id.down:
			{
				InputManager.add(new Input(InputManager.rotateDownAction, 9, 0));
				break;
			}
			
			case R.id.left:
			{
				InputManager.add(new Input(InputManager.rotateLeftAction, 9, 0));
				break;
			}
			
			case R.id.right:
			{
				InputManager.add(new Input(InputManager.rotateRightAction, 9, 0));
				break;
			}
			
			case R.id.rcup:
			{
				InputManager.add(new Input(InputManager.moveUpAction, 9, 0));
				break;
			}
			
			case R.id.rcdown:
			{
				InputManager.add(new Input(InputManager.moveDownAction, 9, 0));
				break;
			}
			
			case R.id.rcleft:
			{
				InputManager.add(new Input(InputManager.moveLeftAction, 9, 0));
				break;
			}
			
			case R.id.rcright:
			{
				InputManager.add(new Input(InputManager.moveRightAction, 9, 0));
				break;
			}
			
			case R.id.forward:
			{
				InputManager.add(new Input(InputManager.moveForwardAction, 9, 0));
				break;
			}
			
			case R.id.back:
			{
				InputManager.add(new Input(InputManager.moveBackAction, 9, 0));
				break;
			}
				
			
			case R.id.exit: exit(); break;

			default: break;
		}
		}
		catch (Exception e) 
		{
			
			Toast.makeText(this, "Input error", 1000);
		}
	}
	
	
	
}