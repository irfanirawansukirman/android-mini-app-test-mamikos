package id.pamoyanan_dev.home.profile

import id.pamoyanan_dev.l_extras.base.BaseViewModel
import id.pamoyanan_dev.l_extras.util.SingleLiveEvent
import id.pamoyanan_dev.movieshop.MainApp

class ProfileVM(mainApp: MainApp) : BaseViewModel(mainApp) {

    val imageUrl = SingleLiveEvent<String>()
    val fullname = SingleLiveEvent<String>()
    val email = SingleLiveEvent<String>()

}
