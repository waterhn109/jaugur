package com.jcc.yijing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String CREATE_Yi = "create table if not exists Yi ("
			+ "id integer primary key autoincrement, " 
			+ "sixYao text, "
			+ "ci text, "
			+ "num1 integer, "
			+ "num2 integer, "
			+ "string1 text, "
			+ "string2 text, "
			+ "string3 text, "
			+ "string4 text)";

	private Context mContext;

	public MyDatabaseHelper(Context context, String name,
							CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_Yi);
		//Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists Yi");
		onCreate(db);
	}

}
