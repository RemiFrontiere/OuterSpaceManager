package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v4.content.ContextCompat.startActivity;

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


        int randomNum = ThreadLocalRandom.current().nextInt(0, 1);

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

//        final AdapterGalaxie.GalaxieViewHolder finalViewHolder1 = viewHolder;
//        viewHolder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(v.getContext(), AttackActivity.class);
//                v.getContext().startActivity(new Intent());
//            }});
//
        return convertView;
    }

    private class GalaxieViewHolder{
        public TextView userName;
        public TextView score;
        public ImageView image;
        public TextView postion;
    }
}
