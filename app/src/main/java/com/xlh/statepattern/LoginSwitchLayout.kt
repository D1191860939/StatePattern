package com.xlh.statepattern

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.layout_login_switch.view.*

/**
 * Created by xlh on 2019/8/7.
 */
class LoginSwitchLayout(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    var onLoginClickListener: OnLoginClickListener? = null

    /**
     * A角色登录状态
     */
    private val produceState: LoginState = object : LoginState {

        override fun showViews() {
            tvForget.visibility = View.VISIBLE
            tvAgreementTop.visibility = View.INVISIBLE
            btnLogin.setText(R.string.roleA_login)
            btnRegister.visibility = View.VISIBLE
            tvLeftLogin.setText(R.string.roleB_login)
            tvRightLogin.setText(R.string.roleC_login)
            tvAgreementBottom.visibility = View.VISIBLE
        }

        override fun onTopClick() {
            onLoginClickListener?.doProduceLogin()
        }

        override fun onLeftClick() {
            changeState(disposalState)
        }

        override fun onRightClick() {
            changeState(transportState)
        }
    }

    /**
     * B角色登录
     */
    private val transportState: LoginState = object : LoginState {

        override fun showViews() {
            tvForget.visibility = View.INVISIBLE
            tvAgreementTop.visibility = View.VISIBLE
            btnLogin.setText(R.string.roleC_login)
            btnRegister.visibility = View.INVISIBLE
            tvLeftLogin.setText(R.string.roleB_login)
            tvRightLogin.setText(R.string.roleA_login)
            tvAgreementBottom.visibility = View.INVISIBLE
        }

        override fun onTopClick() {
            onLoginClickListener?.doTransportLogin()
        }

        override fun onLeftClick() {
            changeState(disposalState)
        }

        override fun onRightClick() {
            changeState(produceState)
        }
    }

    /**
     * C角色登录
     */
    private val disposalState: LoginState = object : LoginState {

        override fun showViews() {
            tvForget.visibility = View.INVISIBLE
            tvAgreementTop.visibility = View.VISIBLE
            btnLogin.setText(R.string.roleB_login)
            btnRegister.visibility = View.INVISIBLE
            tvLeftLogin.setText(R.string.roleA_login)
            tvRightLogin.setText(R.string.roleC_login)
            tvAgreementBottom.visibility = View.INVISIBLE
        }

        override fun onTopClick() {
            onLoginClickListener?.doDisposalLogin()
        }

        override fun onLeftClick() {
            changeState(produceState)
        }

        override fun onRightClick() {
            changeState(transportState)
        }

    }

    private var mCurState: LoginState = produceState

    init {

        LayoutInflater.from(context).inflate(R.layout.layout_login_switch, this)

        btnLogin.setOnClickListener {
            mCurState.onTopClick()
        }

        btnRegister.setOnClickListener {
            //go register page
        }

        tvLeftLogin.setOnClickListener {
            mCurState.onLeftClick()
        }

        tvRightLogin.setOnClickListener {
            mCurState.onRightClick()
        }

        tvAgreementBottom.setOnClickListener {
            //go agreement page
        }

        tvAgreementTop.setOnClickListener {
            //go agreement page
        }

        tvForget.setOnClickListener {
            //go forget page
        }

        mCurState.showViews()
    }


    private fun changeState(nextState: LoginState) {
        mCurState = nextState
        mCurState.showViews()
    }


    private interface LoginState {

        fun showViews()

        fun onTopClick()

        fun onLeftClick()

        fun onRightClick()
    }

    interface OnLoginClickListener {

        fun doProduceLogin()

        fun doTransportLogin()

        fun doDisposalLogin()
    }

}