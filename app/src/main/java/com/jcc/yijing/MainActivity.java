package com.jcc.yijing;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private MyDatabaseHelper dbHelper;
    String s ="";
    String gua = "";
    String sixYao="";
    String zhan_Bu="";
    String guaCi ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "Yi.db", null, 2);
        dbHelper.getReadableDatabase();
        dbHelper.getWritableDatabase();

        if(!InitDb.queryDb(dbHelper)){
            AssetManager assetManager = this.getAssets();
            try {

                //不需要加后缀
                InputStream is = assetManager.open("yijing");
                //         getAssets().open("passwords.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while ((s = br.readLine()) != null) {
                    if(s.length() <3){
                        gua=gua+s+"\\n\\r";
                    }

                   else if(!s.substring(0,3).equals("***")){
                       gua=gua+s+"\\n\\r";
                   }else {
                       sixYao = s.substring(3,9);
                       InitDb.insertDB(dbHelper,sixYao,gua);
                       sixYao = "";
                       gua    = "";
                   }
                }


                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        final Button zhanBu = (Button) findViewById(R.id.zhan_bu);
        zhanBu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               //取得卦象
                for(int i = 0;i<6;i++) {
                    zhan_Bu = zhan_Bu+InitDb.getBo();
                }
                guaCi = InitDb.get_guaCi(dbHelper,zhan_Bu);
                Intent intent = new Intent(MainActivity.this, CiActivity.class);
                intent.putExtra("gua_ci", guaCi);
                startActivity(intent);
                finish();
            }
        });


 //       Button yanDu = (Button) findViewById(R.id.yan_du);
 //       yanDu.setOnClickListener(new OnClickListener() {
   //         @Override
 //           public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                Cursor cursor = db.query("Book", null, null, null, null, null,
//                        null);
//                if (cursor.moveToFirst()) {
//                    do {
//                        String name = cursor.getString(cursor
//                                .getColumnIndex("name"));
//                        String author = cursor.getString(cursor
//                                .getColumnIndex("author"));
//                        int pages = cursor.getInt(cursor
//                                .getColumnIndex("pages"));
//                        double price = cursor.getDouble(cursor
//                                .getColumnIndex("price"));
//                        Log.d("MainActivity", "book name is " + name);
//                        Log.d("MainActivity", "book author is " + author);
//                        Log.d("MainActivity", "book pages is " + pages);
//                        Log.d("MainActivity", "book price is " + price);
//                    } while (cursor.moveToNext());
//                }
//                cursor.close();
  //          }
 //       });

    }
    @Override
    public void onBackPressed(){

        finish();
    }
}
