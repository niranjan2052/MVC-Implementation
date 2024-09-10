package com.myproject.dbdemotodolist.View;

import com.myproject.dbdemotodolist.Model.bean.ToDo;

import java.util.List;

public interface IMVCMainActivityView extends IMVCView {
    void showAllToDos(List<ToDo> toDoList);

    void updateViewOnAdd(List<ToDo> toDoList);

    void showErrorToast(String errorMessage);

    void navigateToDataManipulationActivity(long id);

    void bindDataToView();

    void updateViewOnRemove(List<ToDo> toDoList);

    void updateViewOnModify(List<ToDo> toDoList);


}
