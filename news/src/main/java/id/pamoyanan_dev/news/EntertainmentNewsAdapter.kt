package id.pamoyanan_dev.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.news.databinding.EntertainmentNewsItemBinding

class EntertainmentNewsAdapter(private val data: List<Article>) :
    RecyclerView.Adapter<EntertainmentNewsAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ItemHolder {
        return ItemHolder(EntertainmentNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(data[pos])
    }

    class ItemHolder(private val entertainmentNewsItemBinding: EntertainmentNewsItemBinding) :
        RecyclerView.ViewHolder(entertainmentNewsItemBinding.root) {

        fun bindItem(article: Article) {
            entertainmentNewsItemBinding.apply {
                this.article = article
                executePendingBindings()
            }
        }

    }

}