package id.pamoyanan_dev.home.transaction

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.home.databinding.TransactionItemBinding
import id.pamoyanan_dev.l_extras.data.model.MovieFilter

class TransactionAdapter(private val data: List<MovieFilter>) : RecyclerView.Adapter<TransactionAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(data[pos])
    }

    class ItemHolder(private val transactionItemBinding: TransactionItemBinding) : RecyclerView.ViewHolder(transactionItemBinding.root) {

        fun bindItem(movieFilter: MovieFilter) {
            transactionItemBinding.apply {
                this.movieFilter = movieFilter
            }

        }

    }

}