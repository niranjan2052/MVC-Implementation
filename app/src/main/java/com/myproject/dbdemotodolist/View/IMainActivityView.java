package com.myproject.dbdemotodolist.View;

import com.myproject.dbdemotodolist.Model.bean.ToDo;

import java.util.List;

public interface IMainActivityView extends IMVCView {
    void bindDataToView();

    void showAllToDos(List<ToDo> toDoList);

    void updateViewOnAdd(List<ToDo> toDoList);

    void updateViewOnRemove(List<ToDo> toDoList);

    void updateViewOnModify(List<ToDo> toDoList);

    void showErrorToast(String errorMessage);

    //Binding the data to different UI Elements

    //Challenges in View

    // 1. How to separate the Layout Inflation from Activity?
    // 2. How to separate the View instantiation from Activity?
    // 3. How to set EventListeners away from Activity?
    // 4. How does Life cycle of Activity plays in to all of this?
}
