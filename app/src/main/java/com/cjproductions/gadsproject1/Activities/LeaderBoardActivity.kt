package com.cjproductions.gadsproject1.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.ui.Adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_leader_board.*

class LeaderBoardActivity : BaseActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var pagerAdapter: ViewPagerAdapter
    lateinit var submitButton: Button


    override fun displayProgressBar(bool: Boolean) {
        if(bool){
            progress_bar.visibility = View.VISIBLE
        }
        else{
            progress_bar.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)
        setupActionBar()
        submitButton = findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
        }

        //for viewPager and tabLayout
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.pager)
        pagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = pagerAdapter
        val tabTitles = arrayOf(resources.getString(R.string.learning_leaders_tab),
            resources.getString(R.string.skill_iq_leaders_tab))

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        tab.text = tabTitles[position]
            viewPager.setCurrentItem(tab.position, true)
        }.attach()

    }


    //set actionBar to Toolbar here because it is defined the activity not fragment
    private fun setupActionBar(){
        setSupportActionBar(tool_bar)
    }

//    override fun expandAppBar() {
//        findViewById<AppBarLayout>(R.id.app_bar).setExpanded(true)
//    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}