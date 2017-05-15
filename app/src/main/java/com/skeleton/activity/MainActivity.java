package com.skeleton.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.skeleton.R;
import com.skeleton.fragment.SignInFragement;
import com.skeleton.fragment.SignUpFragement;

import java.util.ArrayList;
import java.util.List;

/**
 * main activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SignUpFragement());
        fragments.add(new SignInFragement());

        PagerAdapter pagerAdapter = new com.skeleton.adapter.PagerAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,
                R.color.colorPrimaryDark));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.tab_unpressed));
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray_light),
                ContextCompat.getColor(this, R.color.white));

    }
}
