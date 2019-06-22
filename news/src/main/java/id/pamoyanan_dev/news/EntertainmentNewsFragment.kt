package id.pamoyanan_dev.news

import android.arch.lifecycle.Observer
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.verticalListStyle
import id.pamoyanan_dev.movieshop.MainApp
import id.pamoyanan_dev.news.databinding.EntertainmentNewsFragmentBinding
import kotlinx.android.synthetic.main.entertainment_news_fragment.*

class EntertainmentNewsFragment : BaseFragment<EntertainmentNewsFragmentBinding, EntertainmentNewsVM>() {

    override fun bindLayoutRes() = R.layout.entertainment_news_fragment

    override fun onSetViewModel(): EntertainmentNewsVM {
        return EntertainmentNewsVM(MainApp.instance)
    }

    override fun onLoadObserver(baseViewModel: EntertainmentNewsVM) {
        baseViewModel.apply {
            entertainmentNewsList.observe(this@EntertainmentNewsFragment, Observer { data ->
                data?.let {
                    setupEntertainmentNewsList(it)
                }
            })
        }
    }

    override fun onStartWork() {

    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                entertainmentNewsVM = it
            }
        }
    }

    private fun setupEntertainmentNewsList(data: List<Article>) {
        rec_entertainmentNews.apply {
            verticalListStyle()
            adapter = EntertainmentNewsAdapter(data)
        }
    }

    companion object {
        fun newInstance() = EntertainmentNewsFragment().putArgs { }
    }

}