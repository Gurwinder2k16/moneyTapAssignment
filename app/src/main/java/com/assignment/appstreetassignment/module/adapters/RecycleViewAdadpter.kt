package com.assignment.appstreetassignment.module.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.assignment.appstreetassignment.R
import com.assignment.appstreetassignment.model.response.PagesItem
import com.assignment.appstreetassignment.module.activity.DetailActivity
import com.assignment.appstreetassignment.utiles.ImageUtils
import kotlinx.android.synthetic.main.content_user_header.view.*


class RecycleViewAdadpter(private var pUserList: ArrayList<PagesItem>) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(pParent: ViewGroup, pViewType: Int): UserViewHolder {
        return UserViewHolder(
            pItemView = LayoutInflater.from(pParent.context).inflate(
                R.layout.item_view_users,
                pParent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pUserList.size
    }

    override fun onBindViewHolder(pHolder: UserViewHolder, pPosition: Int) {
        pHolder.mItemView.tv_title.text = pUserList[pPosition].title
        pHolder.mItemView.tv_desc.text = pUserList[pPosition].terms?.description?.get(0)!!
        if (pUserList[pPosition].thumbnail != null
            && pUserList[pPosition].thumbnail?.source != null
            && pUserList[pPosition].thumbnail?.source!!.isNotEmpty()
        ) {
            ImageUtils.newInstance().downloadImage(
                pUrl = pUserList[pPosition].thumbnail?.source!!,
                pView = pHolder.mItemView.iv_users
            )
        }
        pHolder.mItemView.setOnClickListener {
            onClickItems(pHolder = pHolder, pPosition = pPosition)
        }
    }

    private fun onClickItems(pHolder: UserViewHolder, pPosition: Int) {
        when (pHolder.mItemView.context != null && pPosition < pUserList.size) {
            true -> {
                val intent = Intent(pHolder.mItemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity::class.java.simpleName, pUserList[pPosition])
                val transitionName: String =
                    pHolder.mItemView.context.getString(R.string.transition)
                val options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        pHolder.mItemView.context as Activity,
                        pHolder.mItemView,
                        transitionName
                    )
                ActivityCompat.startActivity(pHolder.mItemView.context, intent, options.toBundle())
            }
        }
    }
}

class UserViewHolder(var pItemView: View) : RecyclerView.ViewHolder(pItemView) {
    var mItemView = pItemView
}