package com.e.kadai_07

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebViewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webview_article.settings.javaScriptEnabled = true
        webview_article.settings.loadWithOverviewMode = true
        webview_article.settings.useWideViewPort = true

        val args = arguments?.getString("BUNDLE_KEY_URL")
        webview_article.loadUrl(args)
    }

    companion object {

        fun newInstance(url: String): WebViewFragment {
            val fragment = WebViewFragment()
            val args = Bundle()

            args.putString("BUNDLE_KEY_URL", url)
            fragment.arguments = args

            return fragment
        }
    }
}
