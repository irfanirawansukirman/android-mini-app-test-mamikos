package id.pamoyanan_dev.dashboard.productslist

interface ProductsListItemListener {
    fun onProductClicked(pos: Int, contentAsString: String)
}