package com.example.mvp.contract

/**
 *  author : ly
 *  date : 2020/12/23 15:24
 *  description :
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}