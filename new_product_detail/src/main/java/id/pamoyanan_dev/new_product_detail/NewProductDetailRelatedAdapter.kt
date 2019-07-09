package id.pamoyanan_dev.new_product_detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.l_extras.R
import id.pamoyanan_dev.l_extras.data.model.ContentProducts
import id.pamoyanan_dev.l_extras.util.GlideApp
import id.pamoyanan_dev.new_product_detail.databinding.NewProductDetailItemBinding
import kotlinx.android.synthetic.main.new_product_detail_item.view.*

class NewProductDetailRelatedAdapter(
    private val productsList: List<ContentProducts>
) :
    RecyclerView.Adapter<NewProductDetailRelatedAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ItemHolder {
        return ItemHolder(NewProductDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(productsList[pos])
    }

    class ItemHolder(private val newProductDetailItemBinding: NewProductDetailItemBinding) :
        RecyclerView.ViewHolder(newProductDetailItemBinding.root) {

        fun bindItem(
            item: ContentProducts
        ) {
            newProductDetailItemBinding.apply {
                // Start set item view
                GlideApp.with(root.img_product)
                    .load(item.image_url)
                    .placeholder(R.color.greyBackgroundDefault)
                    .error(R.color.greyBackgroundDefault)
                    .into(root.img_product)
                this.item = item
                // End set item view

                executePendingBindings()
            }
        }
    }
}