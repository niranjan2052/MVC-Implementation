package com.myproject.dbdemotodolist.Model;

import android.util.Log;

import com.myproject.dbdemotodolist.Model.bean.ToDo;
import com.myproject.dbdemotodolist.Model.db.ToDoListDBAdapter;
import com.myproject.dbdemotodolist.exception.ToDoNotFoundException;

import java.util.List;

public class MVCModelImplementor implements IMVCModel {


    List<ToDo> toDoItems;
    ToDoListDBAdapter toDoListDBAdapter;

    List<Observer> observers;

    public MVCModelImplementor(ToDoListDBAdapter toDoListDBAdapter) {
        this.toDoListDBAdapter = toDoListDBAdapter;
        toDoItems = this.toDoListDBAdapter.getAllToDos();
    }

//    public void registerObserver(Observer observer) {
//        Log.d("Observer", "registerObserver: "+observer);
//        observers.add(observer);
//    }

//    public void notifyObservers() {
//        for (Observer observer : observers) {
//            observer.update();
//        }
//    }

    public ToDo getToDo(long id) throws Exception{
        ToDo toDo = null;
        for(ToDo toDo1: toDoItems){
            if(toDo1.getId()==id){
                toDo = toDo1;
                break;
            }
        }
        if(toDo==null){
            throw new ToDoNotFoundException("Id is wrong");
        }
        return toDo;
    }

    private void refresh() {
        toDoItems.clear();
        toDoItems = this.toDoListDBAdapter.getAllToDos();
    }

    public boolean addToDoItem(String todo, String place) throws Exception {
        boolean addSuccess = toDoListDBAdapter.insert(todo, place);
        if (addSuccess) {
            refresh();
//            notifyObservers();
        } else {
            throw new Exception("Something went Wrong!!");
        }
        return addSuccess;
    }

    @Override
    public boolean removeToDo(int id) throws Exception {
        boolean deleteSuccess = toDoListDBAdapter.delete(id);
        if (deleteSuccess) {
            refresh();
//            notifyObservers();
        } else
            throw new ToDoNotFoundException("Id is wrong");
        return deleteSuccess;
    }

    @Override
    public boolean modifyToDo(int id, String newValue) throws Exception {
        boolean modifySuccess = toDoListDBAdapter.modify(id, newValue);
        if (modifySuccess){
            refresh();
//            notifyObservers();
        }
        else
            throw new ToDoNotFoundException("Id is wrong");
        return false;
    }

    @Override
    public List<ToDo> getAllToDos() throws Exception {
        if (this.toDoItems != null && !this.toDoItems.isEmpty())
            return this.toDoItems;
        else
            throw new Exception("Empty To Do List");
    }
}
