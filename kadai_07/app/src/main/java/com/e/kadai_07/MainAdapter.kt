package com.e.kadai_07

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.e.kadai_07.WebViewFragment.Companion.newInstance
import kotlinx.android.synthetic.main.article_row.view.*

class MainAdapter(val homeFeed: Array<HomeFeed>): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    lateinit var listener: OnItemClickListener

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

        holder.textView_article_title.text = article.title

        if (article.user.name == "") {
            holder.textView_article_author.text = "名無しの投稿者"
        } else {
            holder.textView_article_author.text = article.user.name
        }

        holder.view.setOnClickListener {
            listener.onItemClickListener(it, position, article.url)
        }
    }
    // インターフェース
    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, url: String)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val textView_article_author = view.textView_article_author
        val textView_article_title = view.textView_article_title
    }
}


