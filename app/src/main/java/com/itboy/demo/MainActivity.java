package com.itboy.demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.itboy.demo.base.BaseActivity;
import com.itboy.demo.base.GroupFriendBean;
import com.itboy.demo.base.MultiItemInterface;
import com.itboy.demo.base.RecyclerCommonAdapter;
import com.itboy.demo.base.ViewHolder;
import com.itboy.demo.fragment.FragmentA;
import com.itboy.demo.fragment.FragmentB;
import com.itboy.demo.fragment.FragmentC;
import com.itboy.demo.fragment.FragmentD;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentA.startFragment(R.id.frameLayout_activity,FragmentA.class,new Bundle(),getSupportFragmentManager(),1);
    }

    @OnClick({R.id.home_button_a, R.id.home_button_b, R.id.home_button_c, R.id.home_button_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_button_a:
                FragmentA.startFragment(R.id.frameLayout_activity,FragmentA.class,new Bundle(),getSupportFragmentManager(),1);
                break;
            case R.id.home_button_b:
                FragmentB.startFragment(R.id.frameLayout_activity,FragmentB.class,new Bundle(),getSupportFragmentManager(),1);
                break;
            case R.id.home_button_c:
                FragmentC.startFragment(R.id.frameLayout_activity,FragmentC.class,new Bundle(),getSupportFragmentManager(),1);
                break;
            case R.id.home_button_d:
                FragmentD.startFragment(R.id.frameLayout_activity,FragmentD.class,new Bundle(),getSupportFragmentManager(),1);
                break;
        }

        new Adapter(getBaseContext(), new ArrayList<GroupFriendBean>(), new MultiItemInterface() {
            @Override
            public int getLayoutId(List items, int position) {
                return 0;
            }
        });
    }


    public class Adapter extends RecyclerCommonAdapter<GroupFriendBean>{

        public Adapter(Context context, List<GroupFriendBean> groupFriendBeans, MultiItemInterface multiItemInterface) {
            super(context, groupFriendBeans, multiItemInterface);

            for (int i = 0; i < 10; i++) {
                GroupFriendBean groupFriendBean = new GroupFriendBean();
                groupFriendBean.setGroupId(i+"");
                groupFriendBean.setGroupName("哈哈"+i);
                groupFriendBean.setSex((i%2==0?"男":"女"));
            }
                 }

        @Override
        protected void convert(ViewHolder holder, GroupFriendBean item, int position) {

        }
    }
}
