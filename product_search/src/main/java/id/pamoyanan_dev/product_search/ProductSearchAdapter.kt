package id.pamoyanan_dev.product_search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.product_search.databinding.ProductSearchItemBinding

class ProductSearchAdapter(private val data: List<MovieFilter>) : RecyclerView.Adapter<ProductSearchAdapter.BodyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyHolder {
        return BodyHolder(ProductSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BodyHolder, pos: Int) {
        holder.bindItem(data[pos])
    }

    class BodyHolder(private val productSearchItemBinding: ProductSearchItemBinding) :
            RecyclerView.ViewHolder(productSearchItemBinding.root) {

        fun bindItem(movieFilter: MovieFilter) {
            productSearchItemBinding.apply {
                this.movieFilter = movieFilter
                executePendingBindings()
            }
        }

    }

}
