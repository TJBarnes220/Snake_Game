package com.example.travis.snakegame.HighScoreComponents;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ScoreData {
    private final String FILENAME = "ScoresFile";
    Context context;

    public ScoreData(Context context){
        this.context = context;
    }

    public void addScore(Score addition){
        ArrayList<Score> updatedList = new ArrayList<Score>();
        ArrayList<Score> currentList = read();
        boolean inserted = false;
        for(int i = 0; i < currentList.size(); i++){
            if(!inserted & currentList.get(i).getValue() < addition.getValue()){
                updatedList.add(addition);
                inserted = true;
            }
            updatedList.add(currentList.get(i));
        }
        if (!inserted){
            updatedList.add(addition);
        }
        write(updatedList);
    }

    public ArrayList<Score> read(){
        //Buffer for reading data
        StringBuffer fileContent = new StringBuffer("");
        byte[] buffer = new byte[1024];
        try {
            //opening file
            FileInputStream inputStream = context.openFileInput(FILENAME);
            int n = 0;
            //loop to read information
            while ((n = inputStream.read(buffer)) != -1)
            {
                fileContent.append(new String(buffer, 0, n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Converting input to an actual string
        String input = String.valueOf(fileContent);
        ArrayList<Score> list = new ArrayList<>();
        String[] parts = input.split("%");
        for(int i = 0; i < parts.length; i++){
            Log.d("Read",parts[i]);
        }

        String[] names = new String[parts.length / 2];
        int[] scores = new int[parts.length / 2];
        if(names.length == 0 | scores.length == 0){
            return list;
        }

        int j = 0;
        for (int i = 0; i < parts.length; i += 2) {
            names[j] = parts[i];
            scores[j] = Integer.parseInt(parts[i + 1]);
            j++;
        }

        for (int i = 0; i < names.length; i++) {
            list.add(new Score(scores[i], names[i]));
        }
        return list;
    }

    public void write(ArrayList<Score> list){
        String data = "";
        for(int i = 0; i < list.size(); i++){
            data += list.get(i).getOwner() + "%";
            data += list.get(i).getValue();
            if(i < list.size() - 1){
                data += "%";
            }
        }
        Log.d("Write","" + data);
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
