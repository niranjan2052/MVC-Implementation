package com.myproject.dbdemotodolist.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myproject.dbdemotodolist.Controller.MVCMainActivityController;
import com.myproject.dbdemotodolist.DataManipulationActivity;
import com.myproject.dbdemotodolist.Model.MVCModelImplementor;
import com.myproject.dbdemotodolist.Model.Observer;
import com.myproject.dbdemotodolist.Model.bean.ToDo;
import com.myproject.dbdemotodolist.MyApplication;
import com.myproject.dbdemotodolist.R;

import java.util.List;

public class MainActivityViewImplementor implements IMVCMainActivityView, Observer {

    // This rootView Holds reference to mainActivity layout ie. R.layout.main_activity
    View rootView;

    // This mvcController Holds reference to mvcController class.
    MVCMainActivityController mvcController;

    // This mvcModel holds model
    private MVCModelImplementor mvcModel;

    private EditText editTextNewToDoString, editTextPlace, editTextToDoId, editTextNewToDo;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    public MainActivityViewImplementor(Context context, ViewGroup viewGroup) {
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup);
        mvcModel = new MVCModelImplementor(MyApplication.getToDoListDBAdapter());
//        mvcModel.registerObserver(this);
        mvcController = new MVCMainActivityController(mvcModel, this);
    }

    @Override
    public void bindDataToView() {
        mvcController.onViewLoaded();
    }

    @Override
    public void showAllToDos(List<ToDo> toDoList) {
        textViewToDos.setText(toDoList.toString());
        clearEditTexts();
    }

    @Override
    public void updateViewOnAdd(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void updateViewOnRemove(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void updateViewOnModify(List<ToDo> toDoList) {
        this.showAllToDos(toDoList);
        clearEditTexts();
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(rootView.getContext(), errorMessage, Toast.LENGTH_LONG).show();
        if (errorMessage.equals("Empty To Do List")) {
            clearEditTexts();
            textViewToDos.setText("");
        }
    }

    @Override
    public void navigateToDataManipulationActivity(long id) {
        Intent intent = new Intent(rootView.getContext(), DataManipulationActivity.class);
        intent.putExtra("todoId", id);
        rootView.getContext().startActivity(intent);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    private void clearEditTexts() {
        editTextNewToDo.setText("");
        editTextToDoId.setText("");
        editTextPlace.setText("");
        editTextToDoId.setText("");
    }

    @Override
    public void initViews() {

        editTextNewToDoString = (EditText) rootView.findViewById(R.id.editTextNewToDoString);
        editTextPlace = (EditText) rootView.findViewById(R.id.editTextPlace);
        editTextToDoId = (EditText) rootView.findViewById(R.id.editTextToDoId);
        editTextNewToDo = (EditText) rootView.findViewById(R.id.editTextNewToDo);

        textViewToDos = (TextView) rootView.findViewById(R.id.textViewToDos);

        buttonAddToDo = (Button) rootView.findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo = (Button) rootView.findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button) rootView.findViewById(R.id.buttonModifyToDo);

        buttonAddToDo.setOnClickListener(view -> {
            mvcController.onAddButtonClicked(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
        });

//        buttonRemoveToDo.setOnClickListener(view -> {
//            mvcController.onRemoveBottomClicked(Integer.parseInt(editTextToDoId.getText().toString()));
//        });
//
//        buttonModifyToDo.setOnClickListener(view -> {
//            mvcController.onModifyButtonClicked(Integer.parseInt(editTextToDoId.getText().toString()), editTextNewToDo.getText().toString());
//        });
    }

    @Override
    public void update() {
        try {
            this.showAllToDos(mvcModel.getAllToDos());
        } catch (Exception e) {
            showErrorToast(e.getMessage());
        }
    }
}
