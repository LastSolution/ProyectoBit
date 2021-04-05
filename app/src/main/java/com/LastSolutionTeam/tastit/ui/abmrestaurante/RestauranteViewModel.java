package com.LastSolutionTeam.tastit.ui.abmrestaurante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RestauranteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RestauranteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}