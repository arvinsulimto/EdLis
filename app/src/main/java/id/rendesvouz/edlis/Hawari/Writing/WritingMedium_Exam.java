package id.rendesvouz.edlis.Hawari.Writing;

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

import id.rendesvouz.edlis.Hawari.WritingExamResult;
import id.rendesvouz.edlis.Hawari.Exam_Fragment;
import id.rendesvouz.edlis.Hawari.Singelton2;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuWriting;

public class WritingMedium_Exam extends AppCompatActivity {

    ImageButton btn_backArrow;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    String Username;
    String Email;
    Integer ID = 2;
    Integer Type = 2;
    Chronometer crTimer;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_exam);

        Toolbar toolbar = (Toolbar) findViewById(R.id.writingExam_toolbar);
        setSupportActionBar(toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

        btn_backArrow = (ImageButton) findViewById(R.id.btn_backArrow);

        final Singelton2 data = Singelton2.getInstance();
        data.getExamData(2,2,getApplicationContext());
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
                Intent intent2 = new Intent(WritingMedium_Exam.this, ExamMenuWriting.class);
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
                Intent intent = new Intent(WritingMedium_Exam.this,WritingExamResult.class);
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

            return Exam_Fragment.newInstance(position + 1);
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
