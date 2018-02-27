package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class AdapterGalaxie extends ArrayAdapter<User> {

    public AdapterGalaxie(Context context, List<User> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.galaxie,parent, false);
        }

        GalaxieViewHolder viewHolder = (GalaxieViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new GalaxieViewHolder();
            viewHolder.userName = (TextView) convertView.findViewById(R.id.UsernameText);
            viewHolder.score = (TextView) convertView.findViewById(R.id.ScoreText);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        User user = getItem(position);
        viewHolder.userName.setText(user.getUsername());


        if (user.getScore() == null){
            viewHolder.score.setText("NR");
        }
        else{
            viewHolder.score.setText(user.getScore().toString());
        }

        return convertView;
    }

    private class GalaxieViewHolder{
        public TextView userName;
        public TextView score;
    }
}
