package id.rendesvouz.edlis.Victor.Topic.Listening;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.SelectMaterial;
import me.relex.circleindicator.CircleIndicator;

public class ListeningTopic extends AppCompatActivity {

    MyPageAdapter pageAdapter;
    ImageButton listening_Backbtn;
    ImageButton btn_down_arrow;
    String Username ="";
    String Email ="";

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_topic);

        Toolbar listening_toolbar = (Toolbar) findViewById(R.id.listening_toolbar);
        setSupportActionBar(listening_toolbar);

        Singelton Passing = Singelton.getInstance();
        Username = Passing.getPassingUsername();
        Email = Passing.getPassingEmail();
        listening_Backbtn = (ImageButton) findViewById(R.id.listening_Backbtn);
        btn_down_arrow = (ImageButton) findViewById(R.id.btn_down_arrow);

        List<Fragment> fragments1 = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments1);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        final CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.indicator);

        listening_Backbtn.setOnClickListener(new View.OnClickListener() {
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
//                    btn_down_arrow.setImageResource(R.drawable.minimize);
//                    getSupportFragmentManager().beginTransaction().add(R.id.topic_layout, new ReadPage()).commit();
                }else{
//                    btn_down_arrow.setImageResource(R.drawable.down_arrow);
//                    getSupportFragmentManager().beginTransaction().remove(topic_place).commit();
//                    pager.setAdapter(null);
//                    btn_down_arrow.setImageResource(R.drawable.down_arrow   );
//                    getSupportFragmentManager().beginTransaction().remove(topic_place).commit();
                }

            }
        });
    }

    private List<Fragment> getFragments(){

        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(ListeningPage.newInstance(3, 1));
        fList.add(ListeningPage.newInstance(3, 2));
        fList.add(ListeningPage.newInstance(3, 3));
        fList.add(ListeningPage.newInstance(3, 4));
        fList.add(ListeningPage.newInstance(3, 5));
        fList.add(ListeningPage.newInstance(3, 6));
        fList.add(ListeningPage.newInstance(3, 7));
        fList.add(ListeningPage.newInstance(3, 8));
        fList.add(ListeningPage.newInstance(3, 9));
        fList.add(ListeningPage.newInstance(3, 10));

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
