package com.e.kadai_07

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_row.view.*

class MainAdapter(val homeFeed: Array<HomeFeed>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return homeFeed.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val article = homeFeed.get(position)

        holder.view.textView_article_title.text = article.title

        if (article.user.name == "") {
            holder.view.textView_article_author.text = "名無しの投稿者"
        } else {
            holder.view.textView_article_author.text = article.user.name.toString()
        }


        holder?.articleUrl = article
    }

}

class CustomViewHolder(val view: View, var articleUrl: HomeFeed? = null): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager

            val url = articleUrl?.url
            val bundle = Bundle()
            bundle.putString("BUNDLE_KEY_URL", url)
            val fragment = WebViewFragment()
            fragment.arguments = bundle

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.container, WebViewFragment(), "fragment")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            fragmentTransaction.commit()

        }
    }

}