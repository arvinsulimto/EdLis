package id.rendesvouz.edlis.Hawari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.Hawari.Reading.ReadingEasy_Exam;
import id.rendesvouz.edlis.Hawari.Reading.ReadingHard_Exam;
import id.rendesvouz.edlis.Hawari.Reading.ReadingMedium_Exam;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuReading;
import id.rendesvouz.edlis.Victor.SelectMaterial;

public class ReadingExamResult extends AppCompatActivity {

    ImageButton btn_backArrow;
    ImageButton btn_tryAgain;
    ImageButton btn_endExam;
    String Username;
    String Email;
    TextView txt_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_score);
        final String type = getIntent().getStringExtra("typeID");
        int scores[] = getIntent().getIntArrayExtra("Score");
        TextView score = findViewById(R.id.txt_score);
        //ProgressBar bar = findViewById(R.id.progressBar);
        //bar.setProgress(scores[0],false);
        score.setText(Integer.toString(scores[0]));
        TextView t_true = findViewById(R.id.txt_true);
        TextView t_false = findViewById(R.id.txt_false);
        t_true.setText((Integer.toString(scores[1])));
        t_false.setText((Integer.toString(scores[2])));

        long Timer = getIntent().getLongExtra("Timer",0);
        int minutes = (int) Timer / 60000;
        int seconds = (int) Timer % 60000 / 1000;
        String temp = ""+minutes+" minutes"+" "+seconds+" seconds";


        txt_timer = findViewById(R.id.txt_timer);
        txt_timer.setText(temp);

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mm");
        TextView t_date = findViewById(R.id.txt_date);
        TextView t_time = findViewById(R.id.txt_clock);
        t_date.setText(date.format(Calendar.getInstance().getTime()));
        t_time.setText(time.format(Calendar.getInstance().getTime()));

        String tv_date = date.format(Calendar.getInstance().getTime()).toString();
        String tv_time = time.format(Calendar.getInstance().getTime()).toString();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();
        final int LevelID = getIntent().getIntExtra("LevelID", 0);
        int TypeID = getIntent().getIntExtra("TypeID", 0);
        databaseAccess.InsertScoreData(Email, LevelID, TypeID, scores[0], tv_date, tv_time);
        databaseAccess.close();

        btn_endExam = (ImageButton) findViewById(R.id.btn_endExam);
        btn_endExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingExamResult.this, SelectMaterial.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });

        btn_tryAgain = (ImageButton) findViewById(R.id.btn_tryAgain);
        btn_tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LevelID == 1){
                    Intent intent2 = new Intent(ReadingExamResult.this, ReadingEasy_Exam.class);
//                    intent2.putExtra("dataUsername",Username);
//                    intent2.putExtra("dataEmail",Email);
                    startActivity(intent2);
                    finish();
                }else if(LevelID == 2){
                    Intent intent3 = new Intent(ReadingExamResult.this, ReadingMedium_Exam.class);
//                    intent3.putExtra("dataUsername",Username);
//                    intent3.putExtra("dataEmail",Email);
                    startActivity(intent3);
                    finish();
                }else if(LevelID == 3){
                    Intent intent4 = new Intent(ReadingExamResult.this, ReadingHard_Exam.class);
//                    intent4.putExtra("dataUsername",Username);
//                    intent4.putExtra("dataEmail",Email);
                    startActivity(intent4);
                    finish();
                }

            }
        });

        btn_backArrow = (ImageButton) findViewById(R.id.btn_backArrow);
        btn_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ReadingExamResult.this, ExamMenuReading.class);
//                intent3.putExtra("dataUsername",Username);
//                intent3.putExtra("dataEmail",Email);
                startActivity(intent3);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
