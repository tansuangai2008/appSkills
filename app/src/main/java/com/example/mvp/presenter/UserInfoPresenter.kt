package com.example.mvp.presenter

import android.os.Handler
import com.example.mvp.UserInfo
import com.example.mvp.contract.UserInfoContract

/**
 *  author : ly
 *  date : 2020/12/23 15:33
 *  description :
 */
class UserInfoPresenter : UserInfoContract.Presenter {

    private var view: UserInfoContract.View? = null

    constructor(mView: UserInfoContract.View) {
        this.view = mView
        view?.setPresenter(this)
    }

    override fun loadUserInfo() {
        var userId: String? = view?.loadUserId()
        view?.showLoading()//接口请求显示loading
        //这里模拟接口请求回调
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //模拟接口返回的json，并转换为javaBean
//                UserInfoModel userInfoModel = new UserInfoModel("小宝", 1, "杭州");
//                view.showUserInfo(userInfoModel);
//                view.dismissLoading();
//            }
//        }, 3000);
        //这里模拟接口请求回调-
        Handler().postDelayed({ //模拟接口返回的json，并转换为javaBean
            val userInfoModel = UserInfo("小宝", 1, "杭州")
            view?.showUserInfo(userInfoModel)
            view?.dismissLoading()
        }, 3000)
    }


    override fun start() {
        loadUserInfo()
    }
}