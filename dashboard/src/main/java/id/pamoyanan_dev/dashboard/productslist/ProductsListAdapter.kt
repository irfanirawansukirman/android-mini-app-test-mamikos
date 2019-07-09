package id.pamoyanan_dev.dashboard.productslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.gson.Gson
import id.pamoyanan_dev.dashboard.databinding.ProductsListItemBinding
import id.pamoyanan_dev.l_extras.R
import id.pamoyanan_dev.l_extras.data.model.ContentProducts
import id.pamoyanan_dev.l_extras.util.GlideApp
import kotlinx.android.synthetic.main.products_list_item.view.*

class ProductsListAdapter(
    private val productsList: List<ContentProducts>,
    private val productsListItemListener: ProductsListItemListener
) :
    RecyclerView.Adapter<ProductsListAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ItemHolder {
        return ItemHolder(ProductsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) {
        holder.bindItem(productsList[pos], productsListItemListener, pos, productsList)
    }

    class ItemHolder(private val productsListItemBinding: ProductsListItemBinding) :
        RecyclerView.ViewHolder(productsListItemBinding.root) {

        fun bindItem(
            item: ContentProducts,
            productsListItemListener: ProductsListItemListener,
            pos: Int,
            productsList: List<ContentProducts>
        ) {
            productsListItemBinding.apply {
                // Start set item view
                GlideApp.with(root.img_product)
                    .load(item.image_url)
                    .placeholder(R.color.greyBackgroundDefault)
                    .error(R.color.greyBackgroundDefault)
                    .into(root.img_product)
                this.item = item
                // End set item view

                // Start set view listener
                root.lin_productList_itemContainer.setOnClickListener {
                    productsListItemListener.onProductClicked(pos, Gson().toJson(productsList))
                }
                // End set view listener

                executePendingBindings()
            }
        }
    }
}