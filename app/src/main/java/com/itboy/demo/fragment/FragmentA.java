package com.itboy.demo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.itboy.demo.R;
import com.itboy.demo.base.BaseFragment;
import com.itboy.demo.base.PagerSlidingTabStrip;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends BaseFragment {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager_layout)
    ViewPager viewPagerLayout;
    Unbinder unbinder;
    private FragmentA1 fragmentA1;
    private FragmentA2 fragmentA2;
    private FragmentA3 fragmentA3;
    private DisplayMetrics dm;

    public FragmentA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        dm = getResources().getDisplayMetrics();
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPagerLayout.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPagerLayout);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }





    public class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {

            super(fm);

        }


        private final String[] titles = {"A1", "A2", "A3"};


        @Override

        public CharSequence getPageTitle(int position) {

            return titles[position];

        }


        @Override

        public int getCount() {

            return titles.length;

        }


        @Override

        public Fragment getItem(int position) {

            switch (position) {

                case 0:

                    if (fragmentA1 == null) {

                        fragmentA1 = new FragmentA1();

                    }

                    return fragmentA1;

                case 1:

                    if (fragmentA2 == null) {

                        fragmentA2 = new FragmentA2();

                    }

                    return fragmentA2;

                case 2:

                    if (fragmentA3 == null) {

                        fragmentA3 = new FragmentA3();

                    }

                    return fragmentA3;

                default:

                    return null;

            }

        }


    }



}
