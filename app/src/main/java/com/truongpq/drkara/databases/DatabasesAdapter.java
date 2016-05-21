package com.truongpq.drkara.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.truongpq.drkara.models.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TruongPQ on 5/16/16.
 */
public class DatabasesAdapter {

    private SQLiteDatabase db;
    private DatabaseHelper helper;
    private String tableName;

    public static final String KEY_ID = "Z_PK";
    public static final String KEY_SONG_ID = "ZROWID";
    public static final String KEY_LYRIC = "ZSLYRIC";
    public static final String KEY_LYRICCLEAN = "ZSLYRICCLEAN";
    public static final String KEY_MUSICAN = "ZSMETA";
    public static final String KEY_MUSICANCLEAN = "ZSMETACLEAN";
    public static final String KEY_NAME = "ZSNAME";
    public static final String KEY_NAMECLEAN = "ZSNAMECLEAN";

    public DatabasesAdapter(Context context, String tableName) {
        helper = new DatabaseHelper(context);
        db = helper.openDatabase();
        this.tableName = tableName;
    }

    public List<Song> getAll() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT " +
                KEY_ID + ", " +
                KEY_SONG_ID + ", " +
                KEY_LYRIC + ", " +
                KEY_LYRICCLEAN + ", " +
                KEY_MUSICAN + ", " +
                KEY_MUSICANCLEAN + ", " +
                KEY_NAME + ", " +
                KEY_NAMECLEAN +
                " FROM " + tableName;

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            songs.add(new Song(cursor.getString(0), cursor.getString(1), cursor.getString(6), cursor.getString(2), cursor.getString(4), cursor.getString(7), cursor.getString(3), cursor.getString(5)));
        }

        return songs;
    }

    public Song findByID(String id) {
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) cursor.moveToFirst();

        return new Song(cursor.getString(cursor.getColumnIndex(KEY_ID)),
                cursor.getString(cursor.getColumnIndex(KEY_SONG_ID)),
                cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                cursor.getString(cursor.getColumnIndex(KEY_LYRIC)),
                cursor.getString(cursor.getColumnIndex(KEY_MUSICAN)),
                cursor.getString(cursor.getColumnIndex(KEY_NAMECLEAN)),
                cursor.getString(cursor.getColumnIndex(KEY_LYRICCLEAN)),
                cursor.getString(cursor.getColumnIndex(KEY_MUSICANCLEAN)));
    }
}
