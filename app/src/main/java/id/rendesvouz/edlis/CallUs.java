package id.rendesvouz.edlis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageButton;

public class CallUs extends AppCompatActivity {

    ImageButton callus_backBtn;
    String Email;
    String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);

        Toolbar toolbar = findViewById(R.id.toolbarcallus);
        setSupportActionBar(toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

//        Email = getIntent().getStringExtra("dataEmail");
//        Username = getIntent().getStringExtra("dataUsername");

        callus_backBtn = findViewById(R.id.callus_Backbtn);
        callus_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallUs.this, MainActivity.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });
    }
}
