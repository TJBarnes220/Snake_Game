package com.example.travis.snakegame.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.travis.snakegame.GameComponents.Controller;
import com.example.travis.snakegame.GameComponents.Tile;
import com.example.travis.snakegame.HighScoreComponents.Score;
import com.example.travis.snakegame.HighScoreComponents.ScoreListAdapter;
import com.example.travis.snakegame.R;

import java.util.ArrayList;

public class HighScoreFragment extends Fragment {
    RecyclerView list;
    ArrayList<Score> scores;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_screen, container, false);
        list = view.findViewById(R.id.score_list);
        scores = new ArrayList<>();
        return view;
    }

    public void updateList(){
        list.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));
        ScoreListAdapter adapter = new ScoreListAdapter(this.getActivity(),scores);
        list.setAdapter(adapter);
    }

}
