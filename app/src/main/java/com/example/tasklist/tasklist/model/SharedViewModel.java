package com.example.tasklist.tasklist.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private boolean isEdit;


    private final MutableLiveData<Task> selectedItem= new MutableLiveData<>();

    public void selectItem(Task task){
        selectedItem.setValue(task);
    }

    public LiveData<Task> getSelectedItem(){
        return selectedItem;
    }

    public void setIsEdit(boolean isEdit){
        this.isEdit=isEdit;
    }

    public boolean getIsEdit(){
        return isEdit;
    }

}
