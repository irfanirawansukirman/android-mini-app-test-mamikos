package id.pamoyanan_dev.news

import id.pamoyanan_dev.l_extras.base.BaseActivity
import id.pamoyanan_dev.news.databinding.EntertainmentNewsActivityBinding
import kotlinx.android.synthetic.main.entertainment_news_activity.*

class EntertainmentNewsActivity : BaseActivity<EntertainmentNewsActivityBinding>() {

    override fun bindLayoutRes() = R.layout.entertainment_news_activity

    override fun bindToolbarId() = R.id.toolbar

    override fun bindRootFragment() = EntertainmentNewsFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        txt_toolbar_title.text = "Entertainment News"
    }

}
