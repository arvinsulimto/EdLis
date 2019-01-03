package id.rendesvouz.edlis;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private TextView tvUsername;
    private ImageView img_option,img_user,img_option2,img_setting,img_callus;
    private LinearLayout linear_frame;

    String username = "";
    String email ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_id);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        img_option = (ImageView) findViewById(R.id.img_option);
        img_user = (ImageView) findViewById(R.id.img_user);
        img_option2 = (ImageView) findViewById(R.id.img_option2);
        img_setting = (ImageView) findViewById(R.id.img_setting);
        img_callus = (ImageView) findViewById(R.id.img_callus);
        linear_frame = (LinearLayout) findViewById(R.id.linear_frame);

        linear_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUsername.setVisibility(View.VISIBLE);
                img_user.setVisibility(View.VISIBLE);
                img_option.setVisibility(View.VISIBLE);
                img_option2.setVisibility(View.GONE);
                img_setting.setVisibility(View.GONE);
                img_callus.setVisibility(View.GONE);
            }
        });


        username = getIntent().getStringExtra("dataUsername");
        email = getIntent().getStringExtra("dataEmail");
        tvUsername.setText(username);

        Singelton Passing = Singelton.getInstance();
        Passing.setPassingUsername(username);
        Passing.setPassingEmail(email);

        img_option2.setVisibility(View.GONE);
        img_setting.setVisibility(View.GONE);
        img_callus.setVisibility(View.GONE);

        img_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUsername.setVisibility(View.GONE);
                img_user.setVisibility(View.GONE);
                img_option.setVisibility(View.GONE);
                img_option2.setVisibility(View.VISIBLE);
                img_setting.setVisibility(View.VISIBLE);
                img_callus.setVisibility(View.VISIBLE);

                img_setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Setting.class);
                        intent.putExtra("dataUsername",username);
                        intent.putExtra("dataEmail",email);
                        startActivity(intent);
                        finish();
                    }
                });

                img_callus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,CallUs.class);
                        intent.putExtra("dataUsername",username);
                        intent.putExtra("dataEmail",email);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        img_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUsername.setVisibility(View.VISIBLE);
                img_user.setVisibility(View.VISIBLE);
                img_option.setVisibility(View.VISIBLE);
                img_option2.setVisibility(View.GONE);
                img_setting.setVisibility(View.GONE);
                img_callus.setVisibility(View.GONE);
            }
        });



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentHome(),"Home");
        adapter.AddFragment(new FragmentLogin(),"Login");
        adapter.AddFragment(new FragmentRecord(),"Record");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
