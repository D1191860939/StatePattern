package com.xlh.statepattern

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchLayout.onLoginClickListener = object : LoginSwitchLayout.OnLoginClickListener{
            override fun doProduceLogin() {

            }

            override fun doTransportLogin() {

            }

            override fun doDisposalLogin() {

            }

        }
    }
}
