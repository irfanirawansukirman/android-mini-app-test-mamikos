package id.pamoyanan_dev.home.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.pamoyanan_dev.home.R
import id.pamoyanan_dev.l_extras.ext.putArgs

class NewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        return view
    }

    companion object {
        fun newInstance() = NewsFragment().putArgs {  }
    }
}