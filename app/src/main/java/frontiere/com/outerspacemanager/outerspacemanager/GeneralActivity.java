package frontiere.com.outerspacemanager.outerspacemanager;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeneralActivity extends AppCompatActivity {


    private TextView gas;
    private TextView minerals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        final Singleton mySing = Singleton.getInstance();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Manager service = retrofit.create(Manager.class);
        Call<UserGet> request = service.getUser(mySing.getMyToken());


        request.enqueue(new Callback<UserGet>() {
            @Override
            public void onResponse(Call<UserGet> call, Response<UserGet> response) {

                if(response.isSuccessful()){

                    UserGet user = new UserGet(
                            response.body().getUsername(),
                            response.body().getPoints(),
                            response.body().getGas(),
                            response.body().getMinerals(),
                            response.body().getGasModifier(),
                            response.body().getMineralsModifier());
                    mySing.setMyUser(user);

                }
                else{
                    Toast.makeText(GeneralActivity.this, (String)response.message(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserGet> call, Throwable t) {

            }
        });

        this.gas = (TextView)findViewById(R.id.tVGas);
        Integer myGas = mySing.getMyUser().getGas().intValue();
        Integer myMinerals = mySing.getMyUser().getMinerals().intValue();

        this.gas.setText(myGas.toString());

        this.minerals = (TextView)findViewById(R.id.tVMinerals);
        this.minerals.setText(myMinerals.toString());







        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Manager.class);
        Call<Fleet> request2 = service.getFleet(mySing.getMyToken());


        request2.enqueue(new Callback<Fleet>() {
            @Override
            public void onResponse(Call<Fleet> call, Response<Fleet> response) {
                if(response != null){

                    if(response.isSuccessful()){

                        List<Ship> ships = new ArrayList<>();

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


////                        Log.i("Alo efefefef IS", fleet.getShips().get(2).getAmount().toString());
//                        Toast.makeText(GeneralActivity.this, "Vous avez "+ fleet.getShips().get(2).getAmount().toString()+" "+fleet.getShips().get(2).getName(),
//                                Toast.LENGTH_LONG).show();
//                        Toast.makeText(GeneralActivity.this, "Vous avez "+ fleet.getShips().get(1).getAmount().toString()+" "+fleet.getShips().get(1).getName(),
//                                Toast.LENGTH_LONG).show();
//                        Toast.makeText(GeneralActivity.this, "Vous avez "+ fleet.getShips().get(0).getAmount().toString()+" "+fleet.getShips().get(0).getName(),
//                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(GeneralActivity.this, (String)response.message(),
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(GeneralActivity.this, "Response is NULL",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Fleet> call, Throwable t) {

            }
        });

    }
}


