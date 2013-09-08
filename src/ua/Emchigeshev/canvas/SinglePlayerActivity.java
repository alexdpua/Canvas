package ua.Emchigeshev.canvas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class SinglePlayerActivity extends Activity {
	MyView V;
	TextView tvScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_game);
		tvScore = (TextView) findViewById(R.id.Points);
		V = (MyView) findViewById(R.id.MyView);
		V.setListener(new MyView.Listener() {
			
			@Override
			public void onPointsChange(int count) {
			tvScore.setText("Score:" + (count-1));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void stateBtn(View view) {
		if (V.isPaused()) {
			V.resume();
		} else
			V.pause();

	}
	
}
/*
*MainActivity (Client , Server, SinglePlayer)
*
*Client
*отображение доступных серверов для подключения
*
*Server
*ожидание клиента для начала игры
*
*SinglePlayer
* одиночная игра
* 
* 
*/
