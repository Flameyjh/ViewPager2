package com.yjhpermissionx.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var viewpager: ViewPager2

    //底部导航栏
    lateinit var llChat: LinearLayout
    lateinit var llContact: LinearLayout
    lateinit var llFind: LinearLayout
    lateinit var llMine: LinearLayout
    lateinit var ivChat: ImageView
    lateinit var ivContact: ImageView
    lateinit var ivFind: ImageView
    lateinit var ivMine: ImageView
    lateinit var ivCurrent: ImageView //记录当前图片

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPager()
        initTabView()
    }

    private fun initTabView() {
        llChat = findViewById(R.id.id_tab_weixin)
        llContact = findViewById(R.id.id_tab_contact)
        llFind = findViewById(R.id.id_tab_find)
        llMine = findViewById(R.id.id_tab_mine)
        ivChat = findViewById(R.id.tab_iv_weixin)
        ivContact = findViewById(R.id.tab_iv_contact)
        ivFind = findViewById(R.id.tab_iv_find)
        ivMine = findViewById(R.id.tab_iv_mine)

        llChat.setOnClickListener (this)
        llContact.setOnClickListener(this)
        llFind.setOnClickListener(this)
        llMine.setOnClickListener(this)

        ivChat.isSelected = true
        ivCurrent = ivChat
    }

    private fun initPager() {
        viewpager = findViewById<ViewPager2>(R.id.id_viewpager)
        val fragments = ArrayList<Fragment>()
        fragments.add(BlankFragment.newInstance("微信聊天"))
        fragments.add(BlankFragment.newInstance("通讯录"))
        fragments.add(BlankFragment.newInstance("发现"))
        fragments.add(BlankFragment.newInstance("我的"))
        viewpager.adapter = MyFragmentPagerAdapter(supportFragmentManager, lifecycle, fragments)

        //viewPager2提供给外部的监听
        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                changeTab(position) //随着页面滑动，底部导航栏改变
            }
        })
    }

    //随着页面滑动，底部导航栏改变
    private fun changeTab(position: Int) {
        ivCurrent.isSelected = false
        when(position){
            0 -> {
                ivChat.isSelected = true
                ivCurrent = ivChat
            }
            1 -> {
                ivContact.isSelected = true
                ivCurrent = ivContact
            }
            2 -> {
                ivFind.isSelected = true
                ivCurrent = ivFind
            }
            3 -> {
                ivMine.isSelected = true
                ivCurrent = ivMine
            }
        }
    }

    //点击底部导航栏，页面改变
    override fun onClick(v: View?) {
        when(v){
            llChat -> {
                viewpager.setCurrentItem(0)
            }
            llContact -> {
                viewpager.setCurrentItem(1)
            }
            llFind -> {
                viewpager.setCurrentItem(2)
            }
            llMine -> {
                viewpager.setCurrentItem(3)
            }
        }
    }

}