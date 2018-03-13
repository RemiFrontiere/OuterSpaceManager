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
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
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


    }
}


