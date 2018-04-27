package com.example.licheng.autoindicatorviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.licheng.autoindicatorviewpager.AutoCirclePageIndicator;
import me.licheng.autoindicatorviewpager.AutoViewPager;

public class MainActivity extends AppCompatActivity {

    AutoViewPager viewPager;
    AutoCirclePageIndicator indicator;
    MyAdapter adapter;

    static List<String> mDataList = new ArrayList<>();

    static {
        mDataList.add("4");
        mDataList.add("1");
        mDataList.add("2");
        mDataList.add("3");
        mDataList.add("4");
        mDataList.add("1");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator);

        adapter = new MyAdapter();

        indicator.setViewPager(viewPager);
        indicator.setAuto(mDataList.size() >= 4);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(mDataList.size());
        viewPager.addOnPageChangeListener(indicator);
        viewPager.setAuto(mDataList.size() >= 4);
        viewPager.startAuto();
    }

    private class MyAdapter extends PagerAdapter {

        private int mChildCount = 0;

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_viewpager, null);
            TextView tv = view.findViewById(R.id.tv_viewpager);
            tv.setText(mDataList.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
