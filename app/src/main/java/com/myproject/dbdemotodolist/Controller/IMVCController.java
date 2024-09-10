package com.myproject.dbdemotodolist.Controller;

public interface IMVCController {
    void onViewLoaded();
    void onAddButtonClicked(String toDoItem, String place);
    void onRemoveBottomClicked(int id);
    void onModifyButtonClicked(int id, String newValue);
}
