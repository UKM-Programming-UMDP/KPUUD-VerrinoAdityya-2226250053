package com.aplikasi1.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi1.myapplication.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import com.aplikasi1.myapplication.api.ApiConfig
import com.aplikasi1.myapplication.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainAdapter(private val listener: (UserModel) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var data = listOf<UserModel>()

    fun set(data: List<UserModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvLocation = view.findViewById<TextView>(R.id.tv_location)
        private val ivImage = view.findViewById<CircleImageView>(R.id.iv_user)

        fun bind(user: UserModel) {
            tvName.text = user.login ?: "Nama Kosong"
            tvLocation.text = user.htmlUrl ?: "URL Kosong"
//            CoroutineScope(Dispatchers.IO).launch {
//                val detailUser = ApiConfig.apiService.detailUser(user?.login ?: "")
//                CoroutineScope(Dispatchers.Main).launch {
//                    tvLocation.text = detailUser.location
//                }
//            }
            Picasso.get().load(user.avatarUrl).into(ivImage)
            itemView.setOnClickListener {
                listener(user)
            } // test
        }
    }
}
