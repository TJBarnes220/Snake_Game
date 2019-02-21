package com.example.travis.snakegame.HighScoreComponents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travis.snakegame.R;

import java.util.ArrayList;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ViewHolder> {
    private ArrayList<Score> scores;
    private LayoutInflater mLayoutInflater;

    public ScoreListAdapter(Context context, ArrayList<Score> scores){
        this.scores = scores;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.score_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Score current = scores.get(i);
        viewHolder.value.setText("" + current.getValue());
        viewHolder.owner.setText("" + current.getOwner());
    }


    @Override
    public int getItemCount() {
        return scores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView value;
        TextView owner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.value = itemView.findViewById(R.id.score_text);
            this.owner = itemView.findViewById(R.id.name_text);
        }
    }
}
