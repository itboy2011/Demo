package com.itboy.demo.fragment


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView

import com.itboy.demo.R
import com.itboy.demo.base.*
import kotlinx.android.synthetic.main.fragment_a1.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentA1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a1, container, false)
    }

    override fun onStart() {
        super.onStart()
        val groupFriendBeans1 = java.util.ArrayList<GroupFriendBean>()
        for (i in 0..9) {
            val groupFriendBean = GroupFriendBean()
            groupFriendBean.groupId = i.toString() + ""
            groupFriendBean.groupName = "哈哈$i"
            groupFriendBean.sex = if (i % 2 == 0) "男" else "女"
            groupFriendBeans1.add(groupFriendBean)
        }
        val adapter = Adapter(context!!, groupFriendBeans1, object : MultiItemInterface<GroupFriendBean> {
            override fun getLayoutId(items: List<GroupFriendBean>, position: Int): Int {
                if (items[position].sex.equals("男"))
                    return R.layout.item_man
                else
                    return R.layout.item_women
            }
        })
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }


    inner class Adapter(context: Context, groupFriendBeans: List<GroupFriendBean>, multiItemInterface: MultiItemInterface<*>) : RecyclerCommonAdapter<GroupFriendBean>(context, groupFriendBeans, multiItemInterface) {

        override fun convert(holder: ViewHolder, item: GroupFriendBean, position: Int) {
            val itemViewType = getItemViewType(position)
            when(itemViewType){
                R.layout.item_man->{
//                    此处进行男生布局 byid  赋值 省略
                }

                R.layout.item_women->{
                   // 此处进行女生布局 byid  赋值 省略
                }
            }


        }
    }

}
