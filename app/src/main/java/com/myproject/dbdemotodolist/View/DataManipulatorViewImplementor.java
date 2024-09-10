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

import com.myproject.dbdemotodolist.Controller.MVCDataManipulationController;
import com.myproject.dbdemotodolist.Model.MVCModelImplementor;
import com.myproject.dbdemotodolist.Model.bean.ToDo;
import com.myproject.dbdemotodolist.MyApplication;
import com.myproject.dbdemotodolist.R;

public class DataManipulatorViewImplementor implements IMVCDataManipulatorView {

    View rootView;

    MVCDataManipulationController mvcController;

    private MVCModelImplementor mvcModel;

    TextView textViewToBeModifiedToDoId, textViewToBeModifiedToDo, textViewToBeModifiedToDoPlace;
    Button buttonRemoveToDo, buttonModifyToDo;
    EditText editTextNewToDo;

    ToDo todo;
    int toDoId;


    public DataManipulatorViewImplementor(Context context, ViewGroup container, Intent intent) {
        rootView = LayoutInflater.from(context).inflate(R.layout.activity_data_manipulation, container);
        mvcModel = new MVCModelImplementor(MyApplication.getToDoListDBAdapter());
        toDoId = intent.getIntExtra("todoIt", 1);
        mvcModel = new MVCModelImplementor(MyApplication.getToDoListDBAdapter());
        mvcController = new MVCDataManipulationController(mvcModel, this);
    }


    @Override
    public void initViews() {
        textViewToBeModifiedToDoId = (TextView) rootView.findViewById(R.id.textViewToBeModifiedToDoId);
        textViewToBeModifiedToDo = (TextView) rootView.findViewById(R.id.textViewToBeModifiedToDo);
        textViewToBeModifiedToDoPlace = (TextView) rootView.findViewById(R.id.textViewToBeModifiedToDoPlace);

        buttonRemoveToDo = (Button) rootView.findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button) rootView.findViewById(R.id.buttonModifyToDo);

        editTextNewToDo = (EditText) rootView.findViewById(R.id.editTextNewToDo);

        buttonRemoveToDo.setOnClickListener(view -> {
            mvcController.onRemoveButtonClicked(toDoId);
        });

        buttonModifyToDo.setOnClickListener(view -> {
            mvcController.onModifyButtonClicked(toDoId, editTextNewToDo.getText().toString());
        });

    }

    @Override
    public void showSelectedToDos() {
        try {
            todo = mvcModel.getToDo(toDoId);
            textViewToBeModifiedToDoId.setText("Id: " + todo.getId());
            textViewToBeModifiedToDo.setText("ToDo: " + todo.getTodo());
            textViewToBeModifiedToDoPlace.setText("Place: " + todo.getPlace());

        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void updateViewOnRemove() {

    }

    @Override
    public void updateViewOnModify(ToDo toDo) {

    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(rootView.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View getRootView() {
        return rootView;
    }


    @Override
    public void bindDataToView() {
        mvcController.onViewLoaded();
    }
}
