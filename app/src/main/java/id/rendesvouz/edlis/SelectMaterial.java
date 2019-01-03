package id.rendesvouz.edlis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class SelectMaterial extends AppCompatActivity {


    ImageButton btn_reading;
    ImageButton btn_writing;
    ImageButton btn_listening;
    FrameLayout layout3;
    String Username;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_material);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Material");

        Username = getIntent().getStringExtra("dataUsername");
        Email = getIntent().getStringExtra("dataEmail");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMaterial.this,MainActivity.class);
                intent.putExtra("dataUsername",Username);
                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });

        layout3 = (FrameLayout) findViewById(R.id.frame_layout3);
        btn_reading = (ImageButton) findViewById(R.id.imgbtn_reading);
        btn_listening = (ImageButton) findViewById(R.id.imgbtn_listening);
        btn_writing = (ImageButton) findViewById(R.id.imgbtn_writing);

        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment read_fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout1);
                if(read_fragment != null){
                    getSupportFragmentManager().beginTransaction().remove(read_fragment).commit();
                    ((ViewGroup.MarginLayoutParams)btn_writing.getLayoutParams()).topMargin -=250;
                    btn_writing.requestLayout();
                    ((ViewGroup.MarginLayoutParams)btn_listening.getLayoutParams()).topMargin -=250;
                    btn_listening.requestLayout();
                }
                else{
                    ((ViewGroup.MarginLayoutParams)btn_writing.getLayoutParams()).topMargin +=250;
                    btn_writing.requestLayout();
                    ((ViewGroup.MarginLayoutParams)btn_listening.getLayoutParams()).topMargin +=250;
                    btn_listening.requestLayout();
                    getSupportFragmentManager().beginTransaction().add(R.id.frame_layout1, new ReadingFragment()).commit();
                }
            }
        });

        btn_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment writing_fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout2);
                if(writing_fragment != null){
                    getSupportFragmentManager().beginTransaction().remove(writing_fragment).commit();
                    ((ViewGroup.MarginLayoutParams)btn_listening.getLayoutParams()).topMargin -=250;
                    btn_listening.requestLayout();
                }
                else{
                    ((ViewGroup.MarginLayoutParams)btn_listening.getLayoutParams()).topMargin +=250;
                    btn_listening.requestLayout();
                    getSupportFragmentManager().beginTransaction().add(R.id.frame_layout2, new Writing()).commit();
                }
            }
        });

        btn_listening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment listening_fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout3);
                if(listening_fragment != null){
                    getSupportFragmentManager().beginTransaction().remove(listening_fragment).commit();
                    layout3.setVisibility(View.GONE);
                }
                else{
                    layout3.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().add(R.id.frame_layout3, new Listening()).commit();
                }
            }
        });
    }

}
