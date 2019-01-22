package id.rendesvouz.edlis.Victor;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import id.rendesvouz.edlis.MainActivity;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.Fragment.Listening;
import id.rendesvouz.edlis.Victor.Fragment.Reading;
import id.rendesvouz.edlis.Victor.Fragment.Writing;

public class SelectMaterial extends AppCompatActivity {

    ImageButton SM_backbutton;
    ImageButton btn_reading;
    ImageButton btn_writing;
    ImageButton btn_listening;
    FrameLayout layout3;
    String Username;
    String Email;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_material);

        Toolbar toolbar = findViewById(R.id.toolbarSM);
        setSupportActionBar(toolbar);

        SM_backbutton = (ImageButton) findViewById(R.id.SM_Backbtn);

//        Username = getIntent().getStringExtra("dataUsername");
//        Email = getIntent().getStringExtra("dataEmail");
        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

        SM_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectMaterial.this,MainActivity.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
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
                    getSupportFragmentManager().beginTransaction().add(R.id.frame_layout1, new Reading()).commit();
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
