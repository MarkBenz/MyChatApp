package de.markbenz.mychatapp.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import de.markbenz.mychatapp.fragments.ChatsFragment
import de.markbenz.mychatapp.fragments.UsersFragment

class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 ->{
                return UsersFragment()
            }
            1 ->{
                return ChatsFragment()
            }
        }
        return null!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Freunde"
            1 -> return "Chats"
        }
        return null!!
    }
}