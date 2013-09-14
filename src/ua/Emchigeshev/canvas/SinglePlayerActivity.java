package ua.Emchigeshev.canvas;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class SinglePlayerActivity extends Activity {
	private MyView V;
	private TextView tvScore;
	private DB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new CoolView(this));
		/*db = new DB(SinglePlayerActivity.this);
		setContentView(R.layout.activity_game);
		tvScore = (TextView) findViewById(R.id.Points);
		V = (MyView) findViewById(R.id.MyView);
		V.setListener(new MyView.Listener() {

			@Override
			public void onPointsChange(int count) {
				tvScore.setText("Score:" + (count - 1));

			}
		});*/
	}

	public DB getDb() {
		return db;
	}

	public Dialog addScore() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Add your score");
		final View view = LayoutInflater.from(this).inflate(
				R.layout.dialog_add_score, null);
		dialog.setView(view);
		dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				String name = ((EditText) view.findViewById(R.id.player_add_name))
						.getText().toString();
				String score = ((TextView) view.findViewById(R.id.player_add_score)).getText().toString();
				db.insertScore(name, score);
			}
		});
		dialog.setNegativeButton("Cancel", null);
		return dialog.create();
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		if (1 == id) {
			return addScore();
		}
		return super.onCreateDialog(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		showDialog(1);
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
 * MainActivity (Client , Server, SinglePlayer)
 * 
 * Clientотображение доступных серверов для подключения
 * 
 * Serverожидание клиента для начала игры
 * 
 * SinglePlayer одиночная игра
 */
