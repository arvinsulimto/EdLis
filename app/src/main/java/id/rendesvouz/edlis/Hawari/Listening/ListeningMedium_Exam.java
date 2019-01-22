package id.rendesvouz.edlis.Hawari.Listening;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

import id.rendesvouz.edlis.Hawari.ListeningExamResult;
import id.rendesvouz.edlis.Hawari.ListeningExam_Fragment;
import id.rendesvouz.edlis.Hawari.Singelton2;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuListening;

public class ListeningMedium_Exam extends AppCompatActivity {

    ImageButton btn_backArrow;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    String Username;
    String Email;
    Integer ID = 2;
    Integer Type = 3;
    Chronometer crTimer;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_exam);

        Toolbar toolbar = (Toolbar) findViewById(R.id.listeningExam_toolbar);
        setSupportActionBar(toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

        btn_backArrow = (ImageButton) findViewById(R.id.btn_backArrow);

        final Singelton2 data = Singelton2.getInstance();
        data.getExamData(3,2,getApplicationContext());
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        crTimer = findViewById(R.id.crTimer);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        crTimer.setBase(SystemClock.elapsedRealtime());
        crTimer.start();
        final long ab = SystemClock.elapsedRealtime() - crTimer.getBase();

        btn_backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ListeningMedium_Exam.this, ExamMenuListening.class);
                startActivity(intent2);
                finish();
            }
        });

        ImageButton endExam = findViewById(R.id.btn_endExam);
        endExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score[] = data.countScore();
                long elapsedMillis = SystemClock.elapsedRealtime() - crTimer.getBase();
                Intent intent = new Intent(ListeningMedium_Exam.this, ListeningExamResult.class);
                intent.putExtra("dataUsername",Username);
                intent.putExtra("dataEmail",Email);
                intent.putExtra("Score",score);
                intent.putExtra("LevelID", ID);
                intent.putExtra("TypeID", Type);
                intent.putExtra("Timer", ab);
                intent.putExtra("Timer", elapsedMillis);
                startActivity(intent);
                finish();
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return ListeningExam_Fragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            return 10;
        }
    }
    @Override
    public void onBackPressed() {

    }
}