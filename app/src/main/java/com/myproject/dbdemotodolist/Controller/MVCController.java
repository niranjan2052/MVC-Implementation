package com.myproject.dbdemotodolist.Controller;

import com.myproject.dbdemotodolist.Model.MVCModelImplementor;
import com.myproject.dbdemotodolist.View.MainActivityViewImplementor;

public class MVCController implements IMVCController {
    MVCModelImplementor mvcModel;
    MainActivityViewImplementor mvcView;

    //constructor
    public MVCController(MVCModelImplementor mvcModel, MainActivityViewImplementor mvcView) {
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }


    @Override
    public void onViewLoaded() {
        try {
            mvcView.showAllToDos(mvcModel.getAllToDos());
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }

    @Override
    public void onAddButtonClicked(String toDoItem, String place) {
        try {
            boolean success = mvcModel.addToDoItem(toDoItem, place);
            if (success) {
                mvcView.updateViewOnAdd(mvcModel.getAllToDos());
            }
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }

    @Override
    public void onRemoveBottomClicked(int id) {
        try {
            boolean success = mvcModel.removeToDo(id);
            if (success) {
                mvcView.updateViewOnRemove(mvcModel.getAllToDos());
            }
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }

    @Override
    public void onModifyButtonClicked(int id, String newValue) {
        try {
            boolean success = mvcModel.modifyToDo(id, newValue);
            if (success) {
                mvcView.updateViewOnModify(mvcModel.getAllToDos());
            }
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }
}
