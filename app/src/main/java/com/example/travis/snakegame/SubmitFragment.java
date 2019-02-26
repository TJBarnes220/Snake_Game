package com.example.travis.snakegame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.travis.snakegame.HighScoreComponents.Score;
import com.example.travis.snakegame.HighScoreComponents.ScoreData;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubmitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitFragment extends Fragment implements OnBackPressedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button retryBtn;
    //Button leaveBtn;
    Button submitBtn;
    TextView title;
    EditText submission;
    int score;


    public SubmitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubmitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitFragment newInstance(String param1, String param2) {
        SubmitFragment fragment = new SubmitFragment();
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
        View view = inflater.inflate(R.layout.fragment_submit, container, false);
        retryBtn = view.findViewById(R.id.submit_retry_button);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRetry();
            }
        });

        title = view.findViewById(R.id.temp_score_text);

       // leaveBtn = view.findViewById(R.id.submit_leave_button);
        //leaveBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_submit_to_menuFragment));

        submission = view.findViewById(R.id.submission_text);
        submitBtn = view.findViewById(R.id.submit_button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSubmit();
            }
        });

        return view;
    }

    public void clickSubmit(){
        if(submission.getText().toString().equals("")){
            return;
        }
        ScoreData data = new ScoreData(this.getContext());
        data.addScore(new Score(score,submission.getText().toString()));
        submission.setVisibility(View.GONE);
        submitBtn.setVisibility(View.GONE);
    }

    public void clickRetry(){
        NavController controller = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);
        controller.popBackStack();
    }




    @Override
    public void onViewCreated(View v, Bundle stuff){
        super.onViewCreated(v,stuff);
        score = getArguments().getInt("Score");
        title.setText("Score: " + score);
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

    @Override
    public void onBackPressed() {
        NavController controller = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);
        controller.popBackStack();
        controller.popBackStack();
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
