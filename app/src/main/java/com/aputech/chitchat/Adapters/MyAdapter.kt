package com.aputech.chitchat.Adapters

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aputech.chitchat.FragmentsUI.Calls
import com.aputech.chitchat.FragmentsUI.Camera
import com.aputech.chitchat.FragmentsUI.Inbox
import com.aputech.chitchat.FragmentsUI.Status

class MyAdapter(private val mContext: Context, fa:FragmentActivity, internal var numTabs:Int) :FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
      return numTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                return Camera()
            }
            1->{
                return Inbox()
            }
            2->{
                return Status()
            }
            3->{
                return Calls()
            }
            else -> Inbox()
        }
    }

}