package com.myproject.dbdemotodolist.Model;

import com.myproject.dbdemotodolist.Model.bean.ToDo;

import java.util.List;

public interface IMVCModel {
    boolean addToDoItem(String todo, String place) throws Exception;

    boolean removeToDo(int id) throws Exception;

    boolean modifyToDo(int id, String newValue) throws Exception;

    List<ToDo> getAllToDos() throws Exception;
}
