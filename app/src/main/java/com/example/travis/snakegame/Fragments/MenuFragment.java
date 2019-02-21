package com.example.travis.snakegame.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.travis.snakegame.GameComponents.Controller;
import com.example.travis.snakegame.GameComponents.Tile;
import com.example.travis.snakegame.R;

public class MenuFragment extends Fragment {
    Button start;
    Button scores;
    Button settings;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen, container, false);
        start = view.findViewById(R.id.start_btn);

        settings = view.findViewById(R.id.settings_btn);
        return view;
    }
}
