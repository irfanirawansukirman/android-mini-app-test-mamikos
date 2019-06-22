package id.pamoyanan_dev.home.news

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import id.pamoyanan_dev.home.R
import id.pamoyanan_dev.home.databinding.NewsFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.ext.getViewModel
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.verticalListStyle
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : BaseFragment<NewsFragmentBinding, NewsVM>(), NewsItemCallback {

    override fun onItemNewsSelected(article: Article) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(article.url)))
    }

    override fun bindLayoutRes() = R.layout.news_fragment

    override fun onSetViewModel(): NewsVM {
        return getViewModel { NewsVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: NewsVM) {
        baseViewModel.apply {
            entertainmentNewsList.observe(this@NewsFragment, Observer { data ->
                data?.let {
                    setupNewsList(it)
                }
            })
        }
    }

    override fun onStartWork() {

    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                newsVM = it
            }
        }
    }

    private fun setupNewsList(data: List<Article>) {
        rec_news.apply {
            verticalListStyle()
            adapter = NewsAdapter(data, this@NewsFragment)
        }
    }

    companion object {
        fun newInstance() = NewsFragment().putArgs { }
    }

}