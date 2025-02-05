package com.myproject.dbdemotodolist.Model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myproject.dbdemotodolist.Model.bean.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoListDBAdapter {

    private static final String TAG = ToDoListDBAdapter.class.getSimpleName();

    private static final String DB_NAME = "todoList.db";
    private static final int DB_VERSION = 2;

    private static final String TABLE_TODO = "table_todo";
    private static final String COLUMN_TODO_ID = "task_id";
    private static final String COLUMN_TODO = "todo";
    private static final String COLUMN_PLACE = "place";

    //create table table_todo(task_id integer primary key, todo text not null);
    private static String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO + "(" + COLUMN_TODO_ID + " INTEGER PRIMARY KEY, " + COLUMN_TODO + " TEXT NOT NULL, " +
            COLUMN_PLACE + " TEXT )";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private static ToDoListDBAdapter toDoListDBAdapterInstance;

    private ToDoListDBAdapter(Context context) {
        this.context = context;
        sqLiteDatabase = new ToDoListDBHelper(this.context, DB_NAME, null, DB_VERSION).getWritableDatabase();
    }

    public static ToDoListDBAdapter getToDoListDBAdapterInstance(Context context) {
        if (toDoListDBAdapterInstance == null) {
            toDoListDBAdapterInstance = new ToDoListDBAdapter(context);
        }
        return toDoListDBAdapterInstance;
    }
    //insert,delete,modify,query methods

    public boolean insert(String toDoItem){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TODO,toDoItem);

        return sqLiteDatabase.insert(TABLE_TODO,null,contentValues)>0;
    }

    public boolean insert(String toDoItem, String place){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TODO,toDoItem);
        contentValues.put(COLUMN_PLACE,place);

        return sqLiteDatabase.insert(TABLE_TODO,null,contentValues)>0;
    }

    public boolean delete(int taskId){
        return sqLiteDatabase.delete(TABLE_TODO, COLUMN_TODO_ID+" = "+taskId,null)>0;
    }

    public boolean modify(int taskId, String newToDoItem){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TODO,newToDoItem);

        return sqLiteDatabase.update(TABLE_TODO,contentValues, COLUMN_TODO_ID+" = "+taskId,null)>0;
    }

    public List<ToDo> getAllToDos(){
        List<ToDo> toDoList=new ArrayList<ToDo>();

        Cursor cursor=sqLiteDatabase.query(TABLE_TODO,new String[]{COLUMN_TODO_ID,COLUMN_TODO, COLUMN_PLACE},null,null,null,null,null,null);

        if(cursor!=null &cursor.getCount()>0){
            while(cursor.moveToNext()){
                ToDo toDo=new ToDo(cursor.getLong(0),cursor.getString(1), cursor.getString(2));
                toDoList.add(toDo);

            }
        }
        cursor.close();
        return toDoList;
    }
    //Database Helper class
    private static class ToDoListDBHelper extends SQLiteOpenHelper {
        public ToDoListDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int dbVersion) {
            super(context, databaseName, factory, dbVersion);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
            Log.i(TAG, "Inside onConfigure");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_TODO);
            Log.i(TAG, "Inside onCreate");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                              int oldVersion, int newVersion) {
            //Not implemented now

            if (oldVersion == 1) {
                sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_TODO + " ADD COLUMN " + COLUMN_PLACE + " TEXT");
            }
            Log.i(TAG, "Inside onUpgrade");
        }
    }

}
