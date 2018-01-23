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
 * Created by rfrontiere on 23/01/2018.
 */

public class AdapterShip extends ArrayAdapter<Ship> {

    public AdapterShip(Context context, List<Ship> ship) {
        super(context, 0, ship);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ship,parent, false);
        }

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
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Ship ship = getItem(position);
        viewHolder.name.setText(ship.getName());
        viewHolder.gas.setText("GasCost: "+ship.getGasCost().toString());
        viewHolder.mineral.setText("MineralCost: "+ship.getMineralCost().toString());

        viewHolder.speed.setText("Speed: "+ship.getSpeed().toString());
        viewHolder.maxAttack.setText("maxAttack: "+ship.getMaxAttack().toString());
        viewHolder.life.setText("Life: "+ship.getLife().toString());
        viewHolder.shield.setText("Shield: "+ship.getShield().toString());

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
    }
}
