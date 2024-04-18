package com.example.rpsjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class GameLogAdapter extends ArrayAdapter<GameLog> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public GameLogAdapter(Context context, List<GameLog> gameLogs){
        super(context, 0, gameLogs);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.history_view,parent,false);
        }
        GameLog currentGameLog = getItem(position);

        TextView dateTextView = listItemView.findViewById(R.id.game_log_date_text_view);
        dateTextView.setText(dateFormat.format(currentGameLog.getDate()));

        TextView resulttv =listItemView.findViewById(R.id.game_log_date_text_view);
        resulttv.setText(currentGameLog.getResult());

        TextView myScoretv = listItemView.findViewById(R.id.game_log_date_text_view);
        myScoretv.setText("MY SCORE: "+ currentGameLog.getMyScore());

        TextView oppScoretv = listItemView.findViewById(R.id.game_log_date_text_view);
        oppScoretv.setText("OPPONENT SCORE: "+ currentGameLog.getOppScore());

        return listItemView;

    }
}
