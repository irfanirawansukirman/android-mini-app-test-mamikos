package id.pamoyanan_dev.home.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.home.databinding.NewsItemBinding
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.ext.dateFormatFromTimeString

class NewsAdapter(
    private val data: List<Article>,
    private val newsItemCallback: NewsItemCallback
) :
    RecyclerView.Adapter<NewsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ItemHolder {
        return ItemHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(data[pos], newsItemCallback)
    }

    class ItemHolder(private val newsItemBinding: NewsItemBinding) :
        RecyclerView.ViewHolder(newsItemBinding.root) {

        fun bindItem(article: Article, newsItemCallback: NewsItemCallback) {
            val publishDateCleansing = article.publishedAt?.replace("T", " ")?.replace("Z", "")

            newsItemBinding.apply {
                this.article = article
                this.newsItemCallback = newsItemCallback
                this.publishDate = String().dateFormatFromTimeString(
                    publishDateCleansing ?: "",
                    "yyyy-MM-dd HH:mm:ss",
                    "EEEE MMM dd yyyy",
                    false
                )
                this.publishTime = String().dateFormatFromTimeString(
                    publishDateCleansing ?: "",
                    "yyyy-MM-dd HH:mm:ss",
                    "hh:mm a",
                    false
                )
                executePendingBindings()
            }
        }

    }

}