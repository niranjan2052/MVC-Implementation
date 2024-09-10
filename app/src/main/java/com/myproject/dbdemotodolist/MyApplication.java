package com.myproject.dbdemotodolist;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.myproject.dbdemotodolist.Model.db.ToDoListDBAdapter;

public class MyApplication extends Application {

    static ToDoListDBAdapter toDoListDBAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(this);
    }

    public static ToDoListDBAdapter getToDoListDBAdapter(){
        return toDoListDBAdapter;
    }
}
