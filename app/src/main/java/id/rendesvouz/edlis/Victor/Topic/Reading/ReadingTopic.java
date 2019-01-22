package id.rendesvouz.edlis.Victor.Topic.Reading;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.DatabaseHelper;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.SelectMaterial;
import me.relex.circleindicator.CircleIndicator;

public class ReadingTopic extends AppCompatActivity{

    MyPageAdapter pageAdapter;
    ImageButton reading_Backbtn;
    ImageButton btn_down_arrow;
    String Username = "";
    String Email = "";

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_topic);

        Toolbar reading_toolbar = (Toolbar) findViewById(R.id.reading_toolbar);
        setSupportActionBar(reading_toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email =  Passing.getPassingEmail();

        reading_Backbtn = (ImageButton) findViewById(R.id.reading_Backbtn);
        btn_down_arrow = (ImageButton) findViewById(R.id.btn_down_arrow);

        final List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        final CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.indicator);


        reading_Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectMaterial.class);
//                intent.putExtra("dataUsername",Username);
//                intent.putExtra("dataEmail",Email);
                startActivity(intent);
                finish();
            }
        });

        btn_down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment topic_place = getSupportFragmentManager().findFragmentById(R.id.viewpager);
                if(topic_place == null){
                    btn_down_arrow.setImageResource(R.drawable.minimize);
                    pager.setAdapter(pageAdapter);
                    circleIndicator.setViewPager(pager);
                }else {
//                    btn_down_arrow.setImageResource(R.drawable.down_arrow);
//                    getSupportFragmentManager().beginTransaction().remove(topic_place).commit();
//                    pager.setAdapter(null);
                }
            }
        });
    }

    private List<Fragment> getFragments(){

        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(ReadPage.newInstance(1, 1));
        fList.add(ReadPage.newInstance(1, 2));
        fList.add(ReadPage.newInstance(1, 3));


        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private int[] mResources;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }


    }
}
