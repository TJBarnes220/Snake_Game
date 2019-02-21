package com.example.travis.snakegame;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener,
        com.example.travis.snakegame.GameFragment.OnFragmentInteractionListener, MenuFragment.OnFragmentInteractionListener,
        ScoreBoardFragment.OnFragmentInteractionListener, SubmitFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
