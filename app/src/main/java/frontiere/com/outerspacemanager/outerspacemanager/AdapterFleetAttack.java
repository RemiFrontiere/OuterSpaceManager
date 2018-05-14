package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rfrontiere on 16/04/2018.
 */

public class AdapterFleetAttack extends ArrayAdapter<Ship>{
    public Fleet myFleet;
    public Ship ship;

    public AdapterFleetAttack(Context context, List<Ship> ships, Fleet fleet) {
        super(context, 0, ships);
        myFleet = fleet;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Singleton mySing = Singleton.getInstance();

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ship_attack,parent, false);
        }

        AttackFleetViewHolder viewHolder = (AttackFleetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AttackFleetViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.NameText);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.tvSeek);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.xImage);
            viewHolder.seek = (SeekBar) convertView.findViewById(R.id.sBattack);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ship = getItem(position);


        viewHolder.name.setText(ship.getName());
        viewHolder.amount.setText("0");
        viewHolder.seek.setMax(ship.getAmount());

        final AttackFleetViewHolder finalViewHolder = viewHolder;
        viewHolder.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                finalViewHolder.amount.setText(Integer.toString(progress));
                Toast.makeText(getContext(), finalViewHolder.name.toString(),
                        Toast.LENGTH_LONG).show();

                switch (finalViewHolder.name.toString()){
                    case "Chasseur léger":
                        mySing.chasseurLegerValueForAttack = progress;
                        break;
                    case "Chasseur lourd":
                        mySing.chasseurLourdValueForAttack = progress;
                        break;
                    case "Sonde d'espionnage":
                        mySing.espionValueForAttack = progress;
                        break;
                    case "Destroyer":
                        mySing.destroyerValueForAttack = progress;
                        break;
                    case "Etoile de la mort":
                        mySing.etoileMortLourdValueForAttack = progress;
                        break;
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        HashMap<String, String> amount = new HashMap<>();
        amount.put("amount", Integer.toString(finalViewHolder.seek.getProgress()));
        finalViewHolder.seek.getProgressDrawable().setColorFilter(Color.parseColor("#33cc33"), PorterDuff.Mode.SRC_ATOP);



        switch (ship.getName()){
            case "Chasseur léger":
                viewHolder.image.setImageResource(R.drawable.leger);
                break;
            case "Chasseur lourd":
                viewHolder.image.setImageResource(R.drawable.lourd);
                break;
            case "Sonde d'espionnage":
                viewHolder.image.setImageResource(R.drawable.espion);
                break;
            case "Destroyer":
                viewHolder.image.setImageResource(R.drawable.destroyer);
                break;
            case "Etoile de la mort":
                viewHolder.image.setImageResource(R.drawable.etoile);
                break;
        }
        return convertView;
    }

    private class AttackFleetViewHolder{

        public TextView name;
        public  TextView amount;
        public ImageView image;
        public SeekBar seek;
    }
}