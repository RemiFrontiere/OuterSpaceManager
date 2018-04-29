package frontiere.com.outerspacemanager.outerspacemanager;

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

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Singleton mySing = Singleton.getInstance();





        Button batiments = (Button)findViewById(R.id.batimentsBtn);
        Button galaxie = (Button)findViewById(R.id.btnGalaxie);
        Button chantier = (Button)findViewById(R.id.btnChantier);
        Button general = (Button)findViewById(R.id.generalBtn);
        Button fleet = (Button)findViewById(R.id.fleetbtn);
        Button deco = (Button)findViewById(R.id.decobtn);
        Button search = (Button)findViewById(R.id.searchBtn);


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



                    username = (TextView)findViewById(R.id.username);
                    username.setText(mySing.getIdentifiant());
                    point = (TextView)findViewById(R.id.pointText);
                    point.setText(mySing.getMyUser().getPoints().toString());



                }
                else{
                    Toast.makeText(MainActivity.this, (String)response.message(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserGet> call, Throwable t) {

            }
        });


        batiments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, BuildingActivity.class);
                startActivity(intent);
            }
        });

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, GeneralActivity.class);
                startActivity(intent);
            }
        });

        galaxie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, GalaxieActivity.class);
                startActivity(intent);
            }
        });

        chantier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, ChantierSpatialActivity.class);
                startActivity(intent);
            }
        });
//        fleet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(MainActivity.this, FleetActivity.class);
//                startActivity(intent);
//            }
//        });
//        deco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(MainActivity.this, SignInActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
