package com.example.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.mvp.contract.UserInfoContract
import com.example.mvp.presenter.UserInfoPresenter
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActPersonInfoBinding

/**
 *  author : ly
 *  date : 2020/12/23 15:44
 *  description :
 */
public class UserInfoActivity : BaseActivity(), UserInfoContract.View {

    companion object {
        val TAG: String = UserInfoActivity::class.java.simpleName
        fun startAct(context: Context) {
            val intent = Intent()
            intent.setClass(context, UserInfoActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActPersonInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActPersonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UserInfoPresenter(this)
        presenter?.start()
        SexTest.setSex(SexTest.MAN)

    }

    private var presenter: UserInfoContract.Presenter? = null


    override fun showLoading() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_LONG).show()
    }

    override fun dismissLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_LONG).show();
    }

    override fun showUserInfo(userInfo: UserInfo) {
        if (userInfo != null) {
            binding.tvName.text = "" + userInfo.age
            binding.tvAge.text = java.lang.String.valueOf(userInfo.age)
            binding.tvAddress.text = userInfo.address
        }
    }

    override fun loadUserId(): String {
        return "1001"
    }

    override fun setPresenter(presenter: UserInfoContract.Presenter) {
        this.presenter = presenter
    }
}