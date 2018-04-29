package frontiere.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
import android.widget.Toast;

/**
 * Created by rfrontiere on 23/01/2018.
 */

public class AdapterShip extends ArrayAdapter<Ship> {

    public AdapterShip(Context context, List<Ship> ship) {
        super(context, 0, ship);
    }

    private Singleton mySing;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ship,parent, false);
        }

        this.mySing = Singleton.getInstance();

        ShipViewHolder viewHolder = (ShipViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ShipViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.NameText);
            viewHolder.gas = (TextView) convertView.findViewById(R.id.gas);
            viewHolder.mineral = (TextView) convertView.findViewById(R.id.mineral);
            viewHolder.life = (TextView) convertView.findViewById(R.id.lifeText);
            viewHolder.maxAttack = (TextView) convertView.findViewById(R.id.maxAttackText);
            viewHolder.shield = (TextView) convertView.findViewById(R.id.shieldText);
            viewHolder.speed = (TextView) convertView.findViewById(R.id.speedText);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image) ;
            viewHolder.seek = (SeekBar) convertView.findViewById(R.id.seek);
            viewHolder.tvSeek = (EditText) convertView.findViewById(R.id.tvSeek);
            viewHolder.buildBt = (Button) convertView.findViewById(R.id.buildBT);
            convertView.setTag(viewHolder);
        }


        Integer myGas = mySing.getMyUser().getGas().intValue();
        Integer myMineral = mySing.getMyUser().getMinerals().intValue();





        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        final Ship ship = getItem(position);
        viewHolder.name.setText(ship.getName());
        viewHolder.gas.setText("Gas: "+ship.getGasCost().toString());
        viewHolder.mineral.setText("Mineral: "+ship.getMineralCost().toString());

        viewHolder.speed.setText("Speed: "+ship.getSpeed().toString());
        viewHolder.maxAttack.setText("MaxAttack: "+ship.getMaxAttack().toString());
        viewHolder.life.setText("Life: "+ship.getLife().toString());
        viewHolder.shield.setText("Shield: "+ship.getShield().toString());

        viewHolder.seek.setMax(myGas/ship.getGasCost());

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

        final ShipViewHolder finalViewHolder1 = viewHolder;
        viewHolder.buildBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Log.i("x", String.valueOf(ship.getShipId()));
                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://outer-space-manager-staging.herokuapp.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Manager service = retrofit.create(Manager.class);

                HashMap<String, String> amount = new HashMap<>();
                amount.put("amount", Integer.toString(finalViewHolder1.seek.getProgress()));
                finalViewHolder1.seek.getProgressDrawable().setColorFilter(Color.parseColor("#33cc33"), PorterDuff.Mode.SRC_ATOP);

                Call<Ships> request = service.createship(mySing.getMyToken(), ship.getShipId().toString(), amount);
                Log.i("x", ship.getShipId().toString());

                request.enqueue(new Callback<Ships>() {
                    @Override
                    public void onResponse(Call<Ships> call, Response<Ships> response) {
                        Log.i("x", (String) response.message() + " / dededed");

                        Toast.makeText(getContext(), (String) response.message(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Ships> call, Throwable t) {
                        Log.i("x", "NO");
                    }
                });
            }});

        final ShipViewHolder finalViewHolder = viewHolder;
        viewHolder.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                finalViewHolder.tvSeek.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });


        return convertView;
    }

    private class ShipViewHolder{
        public TextView name;
        public  TextView gas;
        public  TextView mineral;
        public TextView shield;
        public TextView speed;
        public TextView maxAttack;
        public TextView life;
        public ImageView image;
        public SeekBar seek;
        public EditText tvSeek;
        public Button buildBt;

    }
}
