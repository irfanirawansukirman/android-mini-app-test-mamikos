package id.pamoyanan_dev.home.news

import id.pamoyanan_dev.l_extras.data.model.Article

interface NewsItemCallback {
    fun onItemNewsSelected(article: Article)
}