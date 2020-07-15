package com.aputech.chitchat

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

class SplashScreen : AppCompatActivity() {
    private var RC_SIGN_IN=567
    private lateinit var instance:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        FirebaseApp.initializeApp(this)
        instance = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
        SignIn(this,FirebaseAuth.getInstance().uid,instance)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                //Sign in Successful
                val userid = FirebaseAuth.getInstance().currentUser
                //val user:User= User(userid.toString(),0,null)
               // databaseWork(user,this,instance)
                StartNewActivity(this)

            } else {
                // Sign in failed. If response is null the user canceled the
                Toast.makeText(this,"Did Not Complete Sign in",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
    fun StartNewActivity(mContext:Context){
        GlobalScope.launch(context = Dispatchers.Main) {
            println("launched coroutine 1")
            delay(1000)
            val In = Intent(mContext,MainActivity::class.java)
            startActivity(In)
            finish()
        }


    }
    fun databaseWork(user:User,mContext:Context,instance: AppDatabase){
        GlobalScope.launch {

            instance.userDao().insertAll(user)
        }
    }
    fun SignIn(mContext:Context,uid:String?,instance: AppDatabase){

        GlobalScope.launch {
//            val user= instance.userDao().loadId(uid)
//            Log.d("hello", "SignIn: "+user)
            if(uid!=null){
                StartNewActivity(mContext)
            }else{
                val providers = arrayListOf(AuthUI.IdpConfig.PhoneBuilder().build())
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN)

            }


        }
    }

}