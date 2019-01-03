package id.rendesvouz.edlis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;

public class CallUs extends AppCompatActivity {

    String Email;
    String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Call Us");

        Email = getIntent().getStringExtra("dataEmail");
        Username = getIntent().getStringExtra("dataUsername");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallUs.this,MainActivity.class);
                intent.putExtra("dataUsername",Username);
                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();

            }
        });
    }
}
