package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private EditText password;
    private EditText identifiant;
    private EditText mail;
    public Singleton mySingleton;
    private User myUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.mySingleton = Singleton.getInstance();
        this.identifiant = (EditText) findViewById(R.id.identifiantEditText);
        this.password = (EditText) findViewById(R.id.PasswordEditText);
        this.mail = (EditText) findViewById(R.id.MailEditText);
        Button valider = (Button) findViewById(R.id.btValider);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (identifiant != null && password != null) {
                    myUser = new User(identifiant.getText().toString(), password.getText().toString(), mail.getText().toString());


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://outer-space-manager.herokuapp.com/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Manager service = retrofit.create(Manager.class);
                    Call<Token> request = service.user(myUser);


                    request.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {

                            if(response.isSuccessful()){
                                Log.i("Alo RESPONSE IS", response.message());
                                Log.i("Alo TOKEN",response.body().getToken());
                                mySingleton.setUser(myUser.getUsername(), myUser.getPassword(), myUser.getEmail());
                                mySingleton.setMyToken(response.body().getToken());

                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignUpActivity.this, (String)response.message(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

}



