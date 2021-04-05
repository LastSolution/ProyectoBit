package com.LastSolutionTeam.tastit.ui.abmcarta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CartaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CartaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}