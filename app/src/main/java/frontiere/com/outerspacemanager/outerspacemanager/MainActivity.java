package frontiere.com.outerspacemanager.outerspacemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Singleton mySing = Singleton.getInstance();

        this.username = (TextView)findViewById(R.id.username);
        this.username.setText(mySing.getIdentifiant());

        this.point = (TextView)findViewById(R.id.pointText);
//        this.point.setText(mySing.getScore());

        Button batiments = (Button)findViewById(R.id.batimentsBtn);
        Button galaxie = (Button)findViewById(R.id.btnGalaxie);
        Button chantier = (Button)findViewById(R.id.btnChantier);

        batiments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, BuildingActivity.class);
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
    }
}
