package frontiere.com.outerspacemanager.outerspacemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rfrontiere on 16/04/2018.
 */

public class AttackActivity extends AppCompatActivity{

    private ListView listV;
    private User user;
    private TextView nameTv;
    private Button btAttack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);
        listV = (ListView) findViewById(R.id.lv);
        nameTv = (TextView) findViewById(R.id.nameTV);
        btAttack = (Button) findViewById(R.id.Attack);
        this.user = (User)getIntent().getSerializableExtra("user");
        nameTv.setText(user.getUsername());
        final Singleton mySing = Singleton.getInstance();

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


                        AdapterFleetAttack adapter = new AdapterFleetAttack(AttackActivity.this, ships,fleet);
                        listV.setAdapter(adapter);

//
//                        ArrayAdapter<Ship> myadapter = new ArrayAdapter<Ship>(FleetActivity.this, android.R.layout.simple_list_item_1, ships);
//                        listV.setAdapter(adapter);

                    }
                    else{
                        Toast.makeText(AttackActivity.this, (String)response.message(),
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(AttackActivity.this, "Response is NULL",
                            Toast.LENGTH_LONG).show();
                }



                btAttack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                .baseUrl("https://outer-space-manager-staging.herokuapp.com/api/v1/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Manager service = retrofit.create(Manager.class);

                        ArrayList<ShipAttack> myShips = new ArrayList<>();
                        for(int i=0; i < 5; i++){
                            switch(i){
                                case 0:
                                    Toast.makeText(AttackActivity.this, String.valueOf(mySing.chasseurLegerValueForAttack),
                                            Toast.LENGTH_LONG).show();
                                    myShips.add(new ShipAttack(i,mySing.chasseurLegerValueForAttack));
                                    break;
                                case 1:
                                    myShips.add(new ShipAttack(i,mySing.chasseurLourdValueForAttack));
                                    break;
                                case 2:
                                    myShips.add(new ShipAttack(i,mySing.espionValueForAttack));
                                    break;
                                case 3:
                                    myShips.add(new ShipAttack(i,mySing.destroyerValueForAttack));
                                    break;
                                case 4:
                                    myShips.add(new ShipAttack(i,mySing.etoileMortLourdValueForAttack));
                                    break;
                            }

                        }
                                                    
                        HashMap<String, ArrayList<ShipAttack>> ships = new HashMap<>();
                        ships.put("ships", myShips);

                        Call<Fleet> request = service.attackPlayer(user.getUsername(), ships);

                        request.enqueue(new Callback<Fleet>() {
                            @Override
                            public void onResponse(Call<Fleet> call, Response<Fleet> response) {
                                if (response != null) {
                                    Toast.makeText(AttackActivity.this, response.message(),
                                            Toast.LENGTH_LONG).show();

                                    Toast.makeText(AttackActivity.this, String.valueOf(response.code()),
                                            Toast.LENGTH_LONG).show();
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
                            public void onFailure(Call<Fleet> call, Throwable t) {

                            }
                        });
                    }
                });

            }

            @Override
            public void onFailure(Call<Fleet> call, Throwable t) {

            }
        });

    }
}
