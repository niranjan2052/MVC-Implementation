package com.myproject.dbdemotodolist.Controller;

import com.myproject.dbdemotodolist.Model.MVCModelImplementor;
import com.myproject.dbdemotodolist.View.DataManipulatorViewImplementor;

public class MVCDataManipulationController implements IMVCController {

    MVCModelImplementor mvcModel;
    DataManipulatorViewImplementor mvcView;

    public MVCDataManipulationController(MVCModelImplementor mvcModel, DataManipulatorViewImplementor mvcView) {
        this.mvcModel = mvcModel;
        this.mvcView = mvcView;
    }

    @Override
    public void onViewLoaded() {
        mvcView.showSelectedToDos();
    }

    public void onRemoveButtonClicked(int id) {
        try {
            boolean success = mvcModel.removeToDo(id);
            if (success) {
                mvcView.updateViewOnRemove();
            }
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }

    public void onModifyButtonClicked(int id, String newValue) {
        try {
            boolean success = mvcModel.modifyToDo(id, newValue);
            if (success) {
                mvcView.updateViewOnRemove();
            }
        } catch (Exception e) {
            mvcView.showErrorToast(e.getMessage());
        }
    }
}
