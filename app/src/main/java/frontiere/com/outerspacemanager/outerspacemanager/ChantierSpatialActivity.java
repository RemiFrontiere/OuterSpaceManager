package frontiere.com.outerspacemanager.outerspacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChantierSpatialActivity extends AppCompatActivity {

    private ListView listV;
    private Singleton mySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chantier_spatial);



        this.mySingleton = Singleton.getInstance();
        this.listV = (ListView) findViewById(R.id.lv);

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Manager service = retrofit.create(Manager.class);
        Call<Ships> request = service.ships(mySingleton.getMyToken());


        request.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {
                if(response != null){
//                    Log.i("Alo RESPONSE IS", response.message());
//                    Log.i("Alo RESPONSE IS", response.body().getSize().toString());

                    List<Ship> ships = new ArrayList<>();

                    for(int i = 0; i < response.body().getShips().size(); i++){
                        ships.add(new Ship(
                                response.body().getShips().get(i).getGasCost(),
                                response.body().getShips().get(i).getLife(),
                                response.body().getShips().get(i).getMaxAttack(),
                                response.body().getShips().get(i).getMinAttack(),
                                response.body().getShips().get(i).getMineralCost(),
                                response.body().getShips().get(i).getName(),
                                response.body().getShips().get(i).getShield(),
                                response.body().getShips().get(i).getShipId(),
                                response.body().getShips().get(i).getSpatioportLevelNeeded(),
                                response.body().getShips().get(i).getSpeed(),
                                response.body().getShips().get(i).getTimeToBuild()
                        ));
                       // mySingleton.setMyBuildings(buildings);
                    }


                    LayoutInflater inflater = getLayoutInflater();
                    ViewGroup header = (ViewGroup)inflater.inflate(R.layout.listview_header,listV,false);
                    listV.addHeaderView(header);


                    AdapterShip adapter = new AdapterShip(ChantierSpatialActivity.this,ships);
                    listV.setAdapter(adapter);


                    ArrayAdapter<Ship> myadapter = new ArrayAdapter<Ship>(ChantierSpatialActivity.this, android.R.layout.simple_list_item_1, ships );
                    listV.setAdapter(adapter);
                }

                if (response.code() != 200) {
                    try {
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Ships> call, Throwable t) {

            }
        });

    }
}
