package com.LastSolutionTeam.tastit.ui.abmcarta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.LastSolutionTeam.tastit.R;

public class CartaFragment extends Fragment {

    private CartaViewModel cartaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartaViewModel =
                new ViewModelProvider(this).get(CartaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_carta, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        cartaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}