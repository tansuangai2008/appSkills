package com.example.mvp.contract

import com.example.mvp.UserInfo
import com.example.mvp.presenter.BasePresenter

/**
 *  author : ly
 *  date : 2020/12/23 15:26
 *  description :
 */
interface UserInfoContract {

    interface View : BaseView<Presenter> {
        fun showLoading()//展示加载框
        fun dismissLoading()//取消加载框展示
        fun showUserInfo(userInfo: UserInfo)//将网络请求得到的用户数据信息回调
        fun loadUserId(): String//假设接口需要一个userId
    }

    interface Presenter : BasePresenter {
        fun loadUserInfo()
    }


}