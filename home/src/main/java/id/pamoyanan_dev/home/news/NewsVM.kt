package id.pamoyanan_dev.home.news

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.data.model.Article
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsVM(mainApp: MainApp): BaseViewModel(mainApp) {

    val entertainmentNewsList = SingleLiveEvent<List<Article>>()

    init {
        getEntertainmentNewsList()
    }

    override fun onCleared() {
        super.onCleared()
        getEntertainmentNewsList().cancel()
    }

    private fun getEntertainmentNewsList() = scope.launch {
        eventShowProgress.postValue(true)
        val entertaimentNewsResponse = repository.getAllEntertainmentNews()

        withContext(Dispatchers.Main) {
            eventShowProgress.value = false
            entertainmentNewsList.value = entertaimentNewsResponse
        }
    }

}