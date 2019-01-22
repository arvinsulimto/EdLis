package id.rendesvouz.edlis.Victor.Exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.Hawari.Question;
import id.rendesvouz.edlis.Hawari.Reading.ReadingEasy_Exam;
import id.rendesvouz.edlis.Hawari.Reading.ReadingHard_Exam;
import id.rendesvouz.edlis.Hawari.Reading.ReadingMedium_Exam;
import id.rendesvouz.edlis.Hawari.Writing.WritingEasy_Exam;
import id.rendesvouz.edlis.Hawari.Writing.WritingHard_Exam;
import id.rendesvouz.edlis.Hawari.Writing.WritingMedium_Exam;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.SelectMaterial;

public class ExamMenuReading extends AppCompatActivity {

    ImageButton startMudah, startSedang, startSulit;
    ImageButton backbtn;
    String Username = "";
    String Email = "";
    TextView tvSkor_reading_sulit, tvSkor_reading_mudah, tvSkor_reading_sedang;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_menu_reading);

        Toolbar exam_toolbar = (Toolbar) findViewById(R.id.examRead_toolbar);
        setSupportActionBar(exam_toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

        backbtn = findViewById(R.id.reading_Backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectMaterial.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });

        tvSkor_reading_mudah = findViewById(R.id.tvSkor_reading_mudah);
        tvSkor_reading_sedang = findViewById(R.id.tvSkor_reading_sedang);
        tvSkor_reading_sulit = findViewById(R.id.tvSkor_reading_sulit);

        startMudah = (ImageButton) findViewById(R.id.startMudah);
        startSedang = (ImageButton) findViewById(R.id.startSedang);
        startSulit = (ImageButton) findViewById(R.id.startSulit);

        startMudah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ReadingEasy_Exam.class);
                startActivity(intent2);
                finish();
            }
        });

        startSedang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), ReadingMedium_Exam.class);
                startActivity(intent3);
                finish();
            }
        });

        startSulit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), ReadingHard_Exam.class);
                startActivity(intent4);
                finish();
            }
        });

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String Score1 = databaseAccess.getHighScore(1, 1, Email);
        if(Score1.equals("")){
            Score1 = "0";
        }
        tvSkor_reading_mudah.setText("Last Score : " +Score1);

        String Score2 = databaseAccess.getHighScore(1, 2, Email);
        if(Score2.equals("")){
            Score2 = "0";
        }
        tvSkor_reading_sedang.setText("Last Score : " +Score2);

        String Score3 = databaseAccess.getHighScore(1, 3, Email);
        if(Score3.equals("")){
            Score3 = "0";
        }
        tvSkor_reading_sulit.setText("Last Score : " +Score3);

        databaseAccess.close();
    }
}
