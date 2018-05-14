package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class AdapterGalaxie extends ArrayAdapter<User> {

    public AdapterGalaxie(Context context, List<User> user) {
        super(context, 0, user);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
            viewHolder.image = (ImageView) convertView.findViewById(R.id.planetImg);
            viewHolder.postion = (TextView) convertView.findViewById(R.id.numberPosition);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets

        User user = getItem(position);
        int place = position + 1;
        viewHolder.postion.setText(String.valueOf(place));
        viewHolder.userName.setText(user.getUsername());


        if (user.getScore() == null){
            viewHolder.score.setText("NR");
        }
        else{
            viewHolder.score.setText(user.getScore().toString());
        }


        int randomNum = ThreadLocalRandom.current().nextInt(0, 3);

        switch (randomNum){
            case 0:
                viewHolder.image.setImageResource(R.drawable.planet01);
                break;
            case 1:
                viewHolder.image.setImageResource(R.drawable.planet02);
                break;
            case 2:
                viewHolder.image.setImageResource(R.drawable.planet03);
                break;
        }


        return convertView;
    }

    private class GalaxieViewHolder{
        public TextView userName;
        public TextView score;
        public ImageView image;
        public TextView postion;
    }
}
