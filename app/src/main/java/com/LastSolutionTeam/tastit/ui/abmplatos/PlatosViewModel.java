package com.LastSolutionTeam.tastit.ui.abmplatos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlatosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlatosViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}