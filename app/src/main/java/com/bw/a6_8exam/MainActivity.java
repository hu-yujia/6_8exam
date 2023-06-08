package com.bw.a6_8exam;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.a6_8exam.mvvm.view.fg.BlankFragment;
import com.bw.a6_8exam.mvvm.view.fg.BlankFragment2;
import com.bw.a6_8exam.mvvm.view.fg.BlankFragment3;
import com.bw.a6_8exam.mvvm.viewModel.adapter.MyFragmentPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        nav = (BottomNavigationView) findViewById(R.id.nav);

        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment2());
        fragments.add(new BlankFragment3());

        MyFragmentPageAdapter myFragmentPageAdapter=new MyFragmentPageAdapter(getSupportFragmentManager(),fragments);
        vp.setAdapter(myFragmentPageAdapter);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nav.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.shoppingcar:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.mine:
                        vp.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
}