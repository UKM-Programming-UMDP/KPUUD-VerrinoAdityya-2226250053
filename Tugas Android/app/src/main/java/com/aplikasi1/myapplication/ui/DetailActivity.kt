package com.aplikasi1.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.aplikasi1.myapplication.R
import com.aplikasi1.myapplication.api.ApiConfig
import com.aplikasi1.myapplication.model.UserDetailModel
import com.aplikasi1.myapplication.model.UserModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "user"
    }

    private var getIntentData: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        retrieveData()

        if(getIntentData != null) {
            getDetailUser()
        }
    }

    private fun retrieveData() {
        getIntentData = intent.getParcelableExtra(EXTRA_USER)
    }

    private fun getDetailUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val detailUser = ApiConfig.apiService.detailUser(getIntentData?.login ?: "")
            CoroutineScope(Dispatchers.Main).launch {
                updateUI(detailUser)
            }
        }
    }

    private fun updateUI(userDetail: UserDetailModel?) {
        findViewById<TextView>(R.id.tv_login).text = userDetail?.login ?: "N/A"
        findViewById<TextView>(R.id.tv_name).text = userDetail?.name ?: "N/A"
        findViewById<TextView>(R.id.tv_location).text = userDetail?.location ?: "N/A"
        findViewById<TextView>(R.id.tv_company).text = userDetail?.company ?: "N/A"
        findViewById<TextView>(R.id.tv_url).text = userDetail?.url ?: "N/A"
        findViewById<TextView>(R.id.tv_follower).text = userDetail?.followers?.toString() ?: "N/A"
        findViewById<TextView>(R.id.tv_following).text = userDetail?.following?.toString() ?: "N/A"
        findViewById<TextView>(R.id.tv_repository).text = userDetail?.publicRepos?.toString() ?: "N/A"

        Picasso.get().load(userDetail?.avatarUrl).fit().centerCrop().into(findViewById<ImageView>(R.id.iv_avatar))

    }
}
