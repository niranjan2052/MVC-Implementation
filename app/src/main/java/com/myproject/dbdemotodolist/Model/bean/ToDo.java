package com.myproject.dbdemotodolist.Model.bean;

public class ToDo {
    long id;
    String todo;
    String place;

    public ToDo(long aLong, String string, String string1) {
        this.id = aLong;
        this.todo = string;
        this.place = string1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "("+ id+", "+todo+", "+place+")";
    }
}
