package com.example.canwesaychallengeor;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper{
   public static final String DATABASE_NAME="BOOKLIBRAYDB.db";
   public static final int DATABASE_Version=1;
    public static final String COLUMN_ID="book_id";
    public static final String COLUMN_TITLE="book_title";
    public static final String COLUMN_AUTHOR="book_author";
    public static final String COLUMN_LOCATION="book_location";
   private final Context context;

   public DatabaseHandler(@Nullable Context context) {
       super(context, DATABASE_NAME, null, DATABASE_Version);
       this.context=context;
   }



   // On Create Method

    @Override
    public void onCreate(SQLiteDatabase db) {

       String query= " CREATE TABLE  Booklibrary  ( " + COLUMN_ID +
               " INTEGER PRIMARY KEY AUTOINCREMENT , " +  COLUMN_TITLE + " TEXT , " +  COLUMN_AUTHOR + " TEXT , " + COLUMN_LOCATION + " TEXT ) ; " ;
       try{
           db.execSQL(query);
       } catch (Exception e){
           Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
       }

   }



   //On Upgrade Method
    @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


  }
    public void AddBook(String title,String author,String location){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String insertQuery = " INSERT INTO Booklibrary ( " + COLUMN_TITLE + ", " + COLUMN_AUTHOR + " , " + COLUMN_LOCATION + " ) VALUES ( "+ title + " , " + author + ", " + location + " )";



    }
    public Cursor FetchData(){
       String sql= " SELECT * FROM Booklibrary " ;
       SQLiteDatabase fetchdatafromdb=getReadableDatabase();
        Cursor cursor=null;
       if(fetchdatafromdb!=null){
            cursor= fetchdatafromdb.rawQuery( sql,null);
       }
        return  cursor;
   }
   public void UpdateData(String row_id,String title, String author,String location){
        SQLiteDatabase sdb=this.getWritableDatabase();
       ContentValues contentValues=new ContentValues();
       contentValues.put(COLUMN_TITLE,title);
        contentValues.put(COLUMN_AUTHOR,author);
        contentValues.put(COLUMN_LOCATION,location);
       long result = sdb.update(" Booklibrary " , contentValues , " id " , new String[]{row_id});
       if(result==1){
           Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
       else {
           Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
       }

   }
   public void deletedata (String row_id){
        SQLiteDatabase sdb=  getWritableDatabase();
       long result= sdb.delete(" Booklibrary " , " id = ? " , new String [] {row_id} );
       if(result==-1){
           Toast.makeText(context, "Failed to delete!", Toast.LENGTH_SHORT).show();
       }
        else{
           Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }

    }
    public void DeleteAllData(){
       SQLiteDatabase sqLiteDatabase=getWritableDatabase();
       sqLiteDatabase.execSQL( " DELETE  FROM  Booklibrary " );
   }
}





