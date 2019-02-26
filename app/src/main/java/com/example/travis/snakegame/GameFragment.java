package com.example.travis.snakegame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.travis.snakegame.GameComponents.Controller;
import com.example.travis.snakegame.GameComponents.Coordinates;
import com.example.travis.snakegame.GameComponents.Direction;
import com.example.travis.snakegame.GameComponents.Snake;
import com.example.travis.snakegame.GameComponents.Tile;
import com.example.travis.snakegame.GameComponents.TileType;

import java.util.Random;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Game Related Variables
    TableLayout layout;
    Tile[][] board;
    Snake snek;
    Tile pelletPosition;
    Controller gamePad;
    TextView score;
    TextView gameOverText;
    /*
     * Below is method used to run game and update UI constantly
     * mSampleDurationTime dictates how long intervals are
     * continueToRun dictates whether handler continues
     */
    int mSampleDurationTime = 100; // 1/10 of a sec
    boolean continueToRun = true;
    Handler mHandler = new Handler();
    private final Runnable mRunnable = new Runnable(){
        public void run() {
            traverse();
            gamePad.setDelay(false);
            if(continueToRun){
                mHandler.postDelayed(mRunnable, mSampleDurationTime);
            }

        }
    };

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        layout = view.findViewById(R.id.tableLayout);
        score = view.findViewById(R.id.score_text);
        gameOverText = view.findViewById(R.id.game_over_text);

        board = new Tile[0][0];
        gamePad = new Controller((Button) view.findViewById(R.id.up_btn),(Button) view.findViewById(R.id.down_btn),
                (Button) view.findViewById(R.id.left_btn),(Button) view.findViewById(R.id.right_btn));
        start();
        return view;
    }

    public void start(){
        //Makes game board
        makeBoard();
        //Creates player snake and assigns to controller
        snek = new Snake(board[0][0]);
        gamePad.setPlayer(snek);
        //Places pellet on game board
        placePellet();
        //Starts Handler loop to update UI
        continueToRun = true;
        mHandler.postDelayed(mRunnable, mSampleDurationTime);
    }

    public void gameover(){
        continueToRun = false;
        gameOverText.performClick();
        Bundle bundle = new Bundle();
        bundle.putInt("Score",snek.getSize());
        //Navigation.findNavController(gameOverText).popBackStack();
        Navigation.findNavController(gameOverText).navigate((R.id.action_gameFragment_to_submit),bundle);
    }

    public void makeBoard(){
        int rows = 20;
        int columns = 20;
        layout.removeAllViews();
        board = new Tile[rows][columns];

        //board = new TableLayout(game);
        final TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT,1);
        final TableRow.LayoutParams imgParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,1);
        for(int i = 0; i < rows; i++){
            TableRow row = new TableRow(layout.getContext());
            row.setLayoutParams(rowParams);
            for(int j = 0; j < columns; j++){

                final ImageView pic = new ImageView(layout.getContext());
                pic.setLayoutParams(imgParams);
                Tile tile = new Tile(pic, new Coordinates(j,i));
                pic.setTag(tile);
                board[j][i] = tile;
                row.addView(pic);
                Log.d("Board Fragment","i: " + i + " j: " + j);
            }
            Log.d("Board Fragment","added a row");
            layout.addView(row,rowParams);
        }
    }

    public void placePellet(){
        Random random = new Random();
        boolean acceptableSpot = false;
        while(!acceptableSpot) {
            int x = random.nextInt(board.length);
            int y = random.nextInt(board[0].length);
            if(board[x][y].getType() == TileType.Empty){
                acceptableSpot = true;
                pelletPosition = board[x][y];
                pelletPosition.setType(TileType.Pellet);
            }
        }

    }

    public void traverse(){
        Tile next;
        Coordinates nextLocation = new Coordinates(0,0);
        Direction velocity = snek.getVelocity();
        if(velocity == Direction.Down){
            nextLocation = snek.getHead().getLocation().getSouth();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(nextLocation.getX(),0);
            }
        }
        else if(velocity == Direction.Up){
            nextLocation = snek.getHead().getLocation().getNorth();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(nextLocation.getX(),board[0].length - 1);
            }
        }
        else if(velocity == Direction.Left){
            nextLocation = snek.getHead().getLocation().getWest();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(board.length - 1, nextLocation.getY());
            }
        }
        else if(velocity == Direction.Right){
            nextLocation = snek.getHead().getLocation().getEast();
            if(!inBounds(nextLocation)){
                nextLocation = new Coordinates(0, nextLocation.getY());
            }
        }
        next = board[nextLocation.getX()][nextLocation.getY()];
        int tag = snek.Move(next);
        if(tag == 2){
            score.setText("Score: " + snek.getSize());
            placePellet();
        }
        if(tag == 3){
            gameover();
        }
        //if 3 then gameover
    }

    public boolean inBounds(Coordinates location){
        if(location.getX() < 0 | location.getX() >= board.length){
            return false;
        }
        if(location.getY() < 0 | location.getY() >= board[0].length){
            return false;
        }
        return true;
    }








    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
