package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


/**
 * Created by rfrontiere on 23/01/2018.
 */

public class Adapter extends ArrayAdapter<Building> {

    public Adapter(Context context, List<Building> building) {
        super(context, 0, building);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_building_xml,parent, false);
        }

        BuildingViewHolder viewHolder = (BuildingViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BuildingViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.level = (TextView) convertView.findViewById(R.id.level);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.building = (TextView) convertView.findViewById(R.id.building);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Building building = getItem(position);
        viewHolder.name.setText(building.name);

        if(building.getMineralCostLevel0() != null){
            int totalTime = building.getTimeToBuildLevel0() + (building.getAmountOfEffectByLevel()*building.getLevel());
        }

//
//        viewHolder.progressBar.setProgress();

        if(building.level != null){
            viewHolder.level.setText(building.level.toString());
        }
        else{
            viewHolder.level.setText("Upgrading");
        }

        if(building.getBuilding() != true){
            viewHolder.building.setText("Ready to build !");
        }
        else{
            viewHolder.building.setText("Under construction !");
            viewHolder.level.setText(building.level.toString() + "-->" + (building.level + 1));
        }


        new DownLoadImageTask(viewHolder.img).execute(building.imageUrl);
        //GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);

        return convertView;
    }

    private class BuildingViewHolder{
        public TextView name;
        public TextView level;
        public ImageView img;
        public TextView building;
    }
}

class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}