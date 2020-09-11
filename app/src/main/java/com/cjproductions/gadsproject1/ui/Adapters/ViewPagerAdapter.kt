package com.cjproductions.gadsproject1.ui.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cjproductions.gadsproject1.Fragments.LearningLeadersFragment
import com.cjproductions.gadsproject1.Fragments.SkillIQFragment

class ViewPagerAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position){
            0->{
                return LearningLeadersFragment()
            }

            1 -> {
                return SkillIQFragment()
            }

            else -> {
                return LearningLeadersFragment()
            }
        }
    }

}