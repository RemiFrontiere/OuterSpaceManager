package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rfrontiere on 16/04/2018.
 */

public class AdapterFleet extends ArrayAdapter<Ship>{
    public Fleet myFleet;

    public AdapterFleet(Context context, List<Ship> ships, Fleet fleet) {
        super(context, 0, ships);
        myFleet = fleet;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ship_fleet,parent, false);
        }

        FleetViewHolder viewHolder = (FleetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FleetViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.NameText);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.tvSeek);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.xImage);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Ship ship = getItem(position);


        viewHolder.name.setText(ship.getName());
        viewHolder.amount.setText("Amount: "+ship.getAmount());

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

    private class FleetViewHolder{

        public TextView name;
        public  TextView amount;
        public ImageView image;
    }
}