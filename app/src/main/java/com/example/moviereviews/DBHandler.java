package com.example.moviereviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private  static final String DB_NAME = "movieDB.db";

    public static final String TABLE = "movie";
    public static final String COLUMN_ID = "movieid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_REVIEW = "review";

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, " + COLUMN_REVIEW + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addMovie(String name, String review){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_REVIEW, review);
        db.insert(TABLE, null, values);
        db.close();
    }

    public Movie findMovie(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_NAME + "= \"" + name + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Movie movie = null;
        if(cursor. moveToFirst()){
            movie = new Movie(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        }
        cursor.close();
        db.close();
        return movie;
    }

    public boolean deleteMovie(String name){
        boolean result = false;
        Movie movie = findMovie(name);
        if(movie != null){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE, COLUMN_ID + "=?", new String[]{String.valueOf(movie.getId())});
            db.close();
            result = true;
        }

        return result;
    }
}
