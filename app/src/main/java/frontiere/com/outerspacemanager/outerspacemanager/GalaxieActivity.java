package frontiere.com.outerspacemanager.outerspacemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class GalaxieActivity extends AppCompatActivity {
    private ListView listView;
    private Singleton mySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxie);

        this.mySingleton = Singleton.getInstance();
        this.listView = (ListView) findViewById(R.id.lv);

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Manager service = retrofit.create(Manager.class);
        Call<Users> request = service.users(mySingleton.getMyToken());


        request.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Log.i("Alo SINGLETON TOKEN", mySingleton.getMyToken());
                Log.i("Alo RESPONSE IS", response.message());

                if(response.isSuccessful()){


                        List<User> users = new ArrayList<>();

                        for(int i = 0; i < response.body().getUsers().size(); i++){
                            users.add(new User(response.body().getUsers().get(i).getUsername(),response.body().getUsers().get(i).getScore()));
                        }

                        mySingleton.setMyUsers(users);

                    AdapterGalaxie adapter = new AdapterGalaxie(GalaxieActivity.this, users);
                    listView.setAdapter(adapter);
                    ArrayAdapter<User> myadapter = new ArrayAdapter<User>(GalaxieActivity.this, android.R.layout.simple_list_item_1, users );
                    listView.setAdapter(adapter);
                }
                else
                {
                    Toast.makeText(GalaxieActivity.this, (String)response.message(),
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
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }
}
