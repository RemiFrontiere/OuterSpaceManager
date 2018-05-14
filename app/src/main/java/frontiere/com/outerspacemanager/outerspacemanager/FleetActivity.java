package frontiere.com.outerspacemanager.outerspacemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rfrontiere on 16/04/2018.
 */

public class FleetActivity extends AppCompatActivity{

    private ListView listV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);

        final Singleton mySing = Singleton.getInstance();
        this.listV = (ListView) findViewById(R.id.lv);

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Manager service = retrofit.create(Manager.class);
        Call<Fleet> request = service.getFleet(mySing.getMyToken());


        request.enqueue(new Callback<Fleet>() {
            @Override
            public void onResponse(Call<Fleet> call, Response<Fleet> response) {
                if(response != null){

                    if(response.isSuccessful()){

                        List<Ship> ships = new ArrayList<>();
                        Log.i("Alo efefefef IS", (response.body().getShips().toString()));

                        for (int i = 0; i < response.body().getShips().size(); i++) {

                            Ship myShip = new Ship(
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
                            );

                            myShip.setAmount(response.body().getShips().get(i).getAmount());

                            ships.add(myShip);
                            // mySingleton.setMyBuildings(buildings);
                        }

                        Fleet fleet = new Fleet(
                                response.body().getSize(),
                                ships);
//                    mySing.setMyUser(user);


                        AdapterFleet adapter = new AdapterFleet(FleetActivity.this, ships,fleet);
                        listV.setAdapter(adapter);

//
//                        ArrayAdapter<Ship> myadapter = new ArrayAdapter<Ship>(FleetActivity.this, android.R.layout.simple_list_item_1, ships);
//                        listV.setAdapter(adapter);

                    }
                    else{
                        Toast.makeText(FleetActivity.this, (String)response.message(),
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(FleetActivity.this, "Response is NULL",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Fleet> call, Throwable t) {

            }
        });

    }
}
