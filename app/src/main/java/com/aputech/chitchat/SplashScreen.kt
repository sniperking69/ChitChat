package com.aputech.chitchat

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    private var RC_SIGN_IN=567
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        FirebaseApp.initializeApp(this)
        val providers = arrayListOf(AuthUI.IdpConfig.PhoneBuilder().build())
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //Sign in Successful
                val userid = FirebaseAuth.getInstance().currentUser
                val user:User= User(userid.toString(),0,null)
             //   databaseWork(user,this)
                StartNewActivity()

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Toast.makeText(this,"Did Not Complete Sign in",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
    fun StartNewActivity(){
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            val In = Intent(this,MainActivity::class.java)
            startActivity(In)
        },3000)

    }
    fun databaseWork(user:User,mContext:Context){
        GlobalScope.launch {
            AppDatabase.getDatabase(mContext).userDao().insertAll(user)
        }

    }
}