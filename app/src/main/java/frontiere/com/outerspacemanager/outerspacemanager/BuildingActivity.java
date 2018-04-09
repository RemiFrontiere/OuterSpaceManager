package frontiere.com.outerspacemanager.outerspacemanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingActivity extends AppCompatActivity {


    private ListView listV;
    private Singleton mySingleton;

    String[] prenoms = new String[]{
            "Antoine"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);



        this.mySingleton = Singleton.getInstance();
        this.listV = (ListView) findViewById(R.id.listV);

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Manager service = retrofit.create(Manager.class);
        Call<Buildings> request = service.building(mySingleton.getMyToken());


        request.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                if(response.isSuccessful()){
//                    Log.i("Alo RESPONSE IS", response.message());
//                    Log.i("Alo RESPONSE IS", response.body().getSize().toString());

                        List<Building> buildings = new ArrayList<>();

                        for(int i = 0; i < response.body().buildings.size(); i++){
                            buildings.add(new Building(
                                    response.body().buildings.get(i).getLevel(),
                                    response.body().buildings.get(i).getAmountOfEffectByLevel(),
                                    response.body().buildings.get(i).getAmountOfEffectLevel0(),
                                    response.body().buildings.get(i).getBuildingId(),
                                    response.body().buildings.get(i).getBuilding(),
                                    response.body().buildings.get(i).getEffect(),
                                    response.body().buildings.get(i).getGasCostByLevel(),
                                    response.body().buildings.get(i).getGasCostLevel0(),
                                    response.body().buildings.get(i).getImageUrl(),
                                    response.body().buildings.get(i).getMineralCostByLevel(),
                                    response.body().buildings.get(i).getMineralCostLevel0(),
                                    response.body().buildings.get(i).getName(),
                                    response.body().buildings.get(i).getTimeToBuildByLevel(),
                                    response.body().buildings.get(i).getTimeToBuildLevel0()
                                    ));
                        mySingleton.setMyBuildings(buildings);
                    }

                    Adapter adapter = new Adapter(BuildingActivity.this,mySingleton.getMyBuildings());
                    listV.setAdapter(adapter);


                    ArrayAdapter<String> myadapter = new ArrayAdapter<String>(BuildingActivity.this, android.R.layout.simple_list_item_1, prenoms );
                    listV.setAdapter(adapter);
                }
                else {
                    Toast.makeText(BuildingActivity.this, (String)response.message(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {

            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final Building value = (Building) parent.getItemAtPosition(position);
                if(value.getLevel() != null){
                    Integer level = value.getLevel() + 1;


                    final Dialog dialog = new Dialog(BuildingActivity.this);
                    dialog.setContentView(R.layout.dialog_building);
                    dialog.setTitle("Upgrade");

                    TextView name = (TextView) dialog.findViewById(R.id.name);
                    TextView actual_lvl = (TextView) dialog.findViewById(R.id.actual_lvl);
                    TextView next_lvl = (TextView) dialog.findViewById(R.id.next_lvl);
                    TextView effect = (TextView) dialog.findViewById(R.id.effect);
                    TextView timeToBuild = (TextView) dialog.findViewById(R.id.timeToBuild);
                    TextView mineral_cost = (TextView) dialog.findViewById(R.id.mineral_cost);
                    TextView gaz_cost = (TextView) dialog.findViewById(R.id.gaz_cost);
                    name.setText(value.getName());
                    actual_lvl.setText("Level " + value.getLevel().toString());
                    Integer nextLvl = value.getLevel() + 1;
                    next_lvl.setText("Level " + nextLvl.toString());
                    effect.setText("Effect : " + value.getEffect());
                    timeToBuild.setText("Building Time : " + (value.getTimeToBuildLevel0() + value.getTimeToBuildByLevel() + value.getLevel()) );
                    mineral_cost.setText("Mineral : " + (value.getMineralCostLevel0() + value.getMineralCostByLevel() + value.getLevel()));
                    gaz_cost.setText("Gaz : " + (value.getGasCostLevel0() + value.getGasCostByLevel() + value.getLevel()));

                    Button ok = (Button)dialog.findViewById(R.id.ok);
                    Button cancel = (Button)dialog.findViewById(R.id.cancel);

                    if(value.getBuilding()){
                        ok.setEnabled(false);
                    }

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                    .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                            Manager service = retrofit.create(Manager.class);
                            Call<Buildings> request = service.createbuilding(mySingleton.getMyToken(), value.getBuildingId().toString());
                            Log.i("Alo BUILDINGID IS", value.getBuildingId().toString());

                            request.enqueue(new Callback<Buildings>() {
                                @Override
                                public void onResponse(Call<Buildings> call, Response<Buildings> response) {

                                    if(response.isSuccessful()){
                                        Toast.makeText(BuildingActivity.this, "La construction est lancée !",
                                                Toast.LENGTH_LONG).show();



                                        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                                                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();
                                        Manager service = retrofit.create(Manager.class);
                                        Call<Buildings> request = service.building(mySingleton.getMyToken());


                                        request.enqueue(new Callback<Buildings>() {
                                            @Override
                                            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
                                                if(response.isSuccessful()){
//                    Log.i("Alo RESPONSE IS", response.message());
//                    Log.i("Alo RESPONSE IS", response.body().getSize().toString());

                                                    List<Building> buildings = new ArrayList<>();

                                                    for(int i = 0; i < response.body().buildings.size(); i++){
                                                        buildings.add(new Building(
                                                                response.body().buildings.get(i).getLevel(),
                                                                response.body().buildings.get(i).getAmountOfEffectByLevel(),
                                                                response.body().buildings.get(i).getAmountOfEffectLevel0(),
                                                                response.body().buildings.get(i).getBuildingId(),
                                                                response.body().buildings.get(i).getBuilding(),
                                                                response.body().buildings.get(i).getEffect(),
                                                                response.body().buildings.get(i).getGasCostByLevel(),
                                                                response.body().buildings.get(i).getGasCostLevel0(),
                                                                response.body().buildings.get(i).getImageUrl(),
                                                                response.body().buildings.get(i).getMineralCostByLevel(),
                                                                response.body().buildings.get(i).getMineralCostLevel0(),
                                                                response.body().buildings.get(i).getName(),
                                                                response.body().buildings.get(i).getTimeToBuildByLevel(),
                                                                response.body().buildings.get(i).getTimeToBuildLevel0()
                                                        ));
                                                        mySingleton.setMyBuildings(buildings);
                                                    }

                                                    Adapter adapter = new Adapter(BuildingActivity.this,mySingleton.getMyBuildings());
                                                    listV.setAdapter(adapter);


                                                    ArrayAdapter<String> myadapter = new ArrayAdapter<String>(BuildingActivity.this, android.R.layout.simple_list_item_1, prenoms );
                                                    listV.setAdapter(adapter);
                                                }
                                                else {
                                                    Toast.makeText(BuildingActivity.this, (String)response.message(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Buildings> call, Throwable t) {

                                            }
                                        });




                                    }
                                    else{
                                        Toast.makeText(BuildingActivity.this, (String)response.message(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Buildings> call, Throwable t) {

                                }
                            });
                            dialog.cancel();
                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    dialog.show();
                }




//                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
////                // if button is clicked, close the custom dialog
//                dialogButton.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });


//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(BuildingActivity.this);
//                LayoutInflater inflater = BuildingActivity.this.getLayoutInflater();
//                builder.setView(inflater.inflate(R.layout.dialog_building, null))
//                        .setPositiveButton(
//                                "Oui",
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                                                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
//                                                .addConverterFactory(GsonConverterFactory.create())
//                                                .build();
//                                        Manager service = retrofit.create(Manager.class);
//                                        Call<Buildings> request = service.createbuilding(mySingleton.getMyToken(), value.getBuildingId().toString() );
//                                        Log.i("Alo BUILDINGID IS", value.getBuildingId().toString());
//
//                                        request.enqueue(new Callback<Buildings>() {
//                                            @Override
//                                            public void onResponse(Call<Buildings> call, Response<Buildings> response) {
//                                                if(response != null){
//                                                    Log.i("Alo RESPONSE IS", response.message());
//                                                }
//
//                                                if (response.errorBody() != null) {
//                                                    try {
//                                                        Log.i("erreur", response.errorBody().string());
//                                                    } catch (IOException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Buildings> call, Throwable t) {
//
//                                            }
//                                        });
//
//                                        dialog.cancel();
//                                    }
//                                })
//                            .setNegativeButton(
//                            "Non",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            });
////
//                             AlertDialog alert11 = builder.create();

//
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(BuildingActivity.this);
//
//
//                builder1.setMessage("Monter au niveau " + level + " ?");
//                builder1.setCancelable(true);

//                builder1.setPositiveButton(
//                        "Oui",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
//                                        .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
//                                        .addConverterFactory(GsonConverterFactory.create())
//                                        .build();
//                                Manager service = retrofit.create(Manager.class);
//                                Call<Buildings> request = service.createbuilding(mySingleton.getMyToken(), value.getBuildingId().toString() );
//                                Log.i("Alo BUILDINGID IS", value.getBuildingId().toString());
//
//                                request.enqueue(new Callback<Buildings>() {
//                                    @Override
//                                    public void onResponse(Call<Buildings> call, Response<Buildings> response) {
//                                        if(response != null){
//                                            Log.i("Alo RESPONSE IS", response.message());
//                                        }
//
//                                        if (response.errorBody() != null) {
//                                            try {
//                                                Log.i("erreur", response.errorBody().string());
//                                            } catch (IOException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<Buildings> call, Throwable t) {
//
//                                    }
//                                });
//
//                                dialog.cancel();
//                            }
//                        });

//                builder1.setNegativeButton(
//                        "Non",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert11 = builder1.create();
//                alert11.show();

            }
        });
    }

}


