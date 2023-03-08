package com.example.studiappfinished;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="App.db";
    private static final String TABLE_NAME="deadlines";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TITEL="deadline_titel";
    private static final String COLUMN_BESCHREIBUNG="deadline_beschreibung";
    static int currentUserId = 0;
    private final Context context;

    public DBHelper(Context context) {
        super(context,"App.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase AppDB) {
        AppDB.execSQL("create Table users (id INTEGER primary key autoincrement, username TEXT,password TEXT)");
        AppDB.execSQL("create Table deadlines(id INTEGER primary key autoincrement, " +
                "deadline_titel TEXT, deadline_beschreibung TEXT, " +
                "userId INTEGER," + " FOREIGN KEY (userId) REFERENCES users (id) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase AppDB, int i, int i1) {
        AppDB.execSQL("drop Table if exists users"); //
        AppDB.execSQL("drop Table if exists " + TABLE_NAME);
        onCreate(AppDB);
    }

    public Boolean einfuegenData(String username, String password){
        SQLiteDatabase AppDB=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = AppDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    @SuppressLint("SuspiciousIndentation")
    public Boolean testusername(String username){
        SQLiteDatabase AppDB = this.getWritableDatabase();
        Cursor cursor = AppDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @SuppressLint("SuspiciousIndentation")
    public Boolean testusernamepassword (String username, String password){
        SQLiteDatabase AppDB = this.getWritableDatabase();
        Cursor cursor = AppDB.rawQuery("Select id from users where username = ? and password = ?", new String[] {username, password});
        if (cursor.moveToFirst()){
            currentUserId = cursor.getInt(0);
            return true;
        }else{
            return false;
        }
    }

    public void addDeadline(String titel, String beschreibung){
        SQLiteDatabase AppDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("deadline_titel", titel);
        cv.put("deadline_beschreibung", beschreibung);
        cv.put("userId", currentUserId);
        long result2 = AppDB.insert("deadlines", null, cv);
        if(result2 ==-1){
            Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, " hinzufügen erfolgreich", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM deadlines WHERE userId = " + currentUserId ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String titel, String beschreibung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("deadline_titel", titel);
        cv.put("deadline_beschreibung", beschreibung);

        long result = db.update("deadlines", cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
