package ua.Emchigeshev.canvas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DB extends SQLiteOpenHelper implements BaseColumns {

	public static final String FIELD_NAME = "name";
	public static final String FIELD_SCORE = "score";
	public static final String TABLE_NAME = "Score_Table";

	public DB(Context context) {
		super(context, "score.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + DB._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_NAME
				+ " VARCHAR (255), " + FIELD_SCORE + " VARCHAR (255));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void insertScore(String name, String score) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("INSERT INTO " + TABLE_NAME + "(" + FIELD_NAME + ", "
				+ FIELD_SCORE + ") VALUES (\"" + name + "\", \"" + score + "\");");
	}
}
