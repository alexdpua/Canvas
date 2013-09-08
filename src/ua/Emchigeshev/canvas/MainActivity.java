package ua.Emchigeshev.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}
	
	public void onClickSingle (View view){
		Intent i = new Intent(getBaseContext(), SinglePlayerActivity.class);
		startActivity(i);
	}
	
	public void onClickServer (View view){
		
	}
	
	public void onClickClient (View view){
		
	}
	

}
