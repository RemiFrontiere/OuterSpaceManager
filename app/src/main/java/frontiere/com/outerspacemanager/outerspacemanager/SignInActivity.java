package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignInActivity extends AppCompatActivity {


    private EditText password;
    private EditText identifiant;
    private Singleton mySingleton;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.mySingleton = Singleton.getInstance();
        this.identifiant = (EditText) findViewById(R.id.identifiantEditText);
        this.password = (EditText) findViewById(R.id.PasswordEditText);
        Button valider = (Button) findViewById(R.id.btValider);
        Button create = (Button) findViewById(R.id.btCreate);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUser = new User(identifiant.getText().toString(), password.getText().toString());

                retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Manager service = retrofit.create(Manager.class);
                Call<Token> request = service.login(myUser);


                request.enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {

                        if(response != null){
                            Log.i("Alo RESPONSE IS", response.message());
                            Log.i("Alo TOKEN",response.body().getToken());
                            mySingleton.setUser(myUser.getUsername(), myUser.getPassword(), myUser.getEmail());
                            mySingleton.setMyToken(response.body().getToken());

                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
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
                    public void onFailure(Call<Token> call, Throwable t) {

                    }
                });
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                    startActivity(intent);
            }
        });
    }
}

