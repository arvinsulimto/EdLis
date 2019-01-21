package id.rendesvouz.edlis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class Setting extends AppCompatActivity {
    ImageButton setting_backBtn;

    String Email;
    String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();
//        Email = getIntent().getStringExtra("dataEmail");
//        Username = getIntent().getStringExtra("dataUsername");

        setting_backBtn = findViewById(R.id.setting_Backbtn);
        setting_backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this,MainActivity.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Setting.this,MainActivity.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
//                startActivity(intent);
//                finish();
//
//            }
//        });
    }
}
