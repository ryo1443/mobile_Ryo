package com.e.kadai_07

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.kadai_07.WebViewFragment.Companion.newInstance
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
            holder.view.textView_article_author.text = article.user.name
        }

        holder?.articleUrl = article
    }

}

class CustomViewHolder(val view: View, var articleUrl: HomeFeed? = null): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager

            val fragment = newInstance(articleUrl)

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                                                    R.anim.slide_in_left, R.anim.slide_out_right)
            fragmentTransaction.add(R.id.container, fragment, "fragment")
            fragmentTransaction.addToBackStack("fragment")

            fragmentTransaction.commit()

        }
    }

}