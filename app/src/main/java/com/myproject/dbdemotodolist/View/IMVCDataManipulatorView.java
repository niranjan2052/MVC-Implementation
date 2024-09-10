package com.myproject.dbdemotodolist.View;

import com.myproject.dbdemotodolist.Model.bean.ToDo;

public interface IMVCDataManipulatorView extends IMVCView{
    void showSelectedToDos();
    void updateViewOnRemove();
    void updateViewOnModify(ToDo toDo);
    void showErrorToast(String errorMessage);
}
