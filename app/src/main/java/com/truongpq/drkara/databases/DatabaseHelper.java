package com.truongpq.drkara.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by TruongPQ on 5/15/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Karaoke.sqlite";

    private SQLiteDatabase database;

    private Context context;
    private String path;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        this.path = context.getFilesDir().getParent() + "/databases/" + DATABASE_NAME;
        createDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (db != null) {
            db.close();
            return true;
        }
        return false;
    }

    public void copyDatabase() {
        try {
            InputStream is =  context.getAssets().open(DATABASE_NAME);
            OutputStream os = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int lenght;
            while((lenght = is.read(buffer)) > 0){
                os.write(buffer, 0, lenght);
            }

            os.flush();
            os.close();
            is.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createDatabase() {
        boolean check = checkDatabase();
        if(check){
            Log.d("Database", "Máy đã có database");
        }else{
            Log.d("Database", "Máy chưa có database tiến hành copy dữ liệu");
            this.getWritableDatabase();
            copyDatabase();
        }
    }

    public SQLiteDatabase openDatabase(){
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);

    }
}
