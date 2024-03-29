package id.pamoyanan_dev.home.master

import android.arch.lifecycle.Observer
import com.google.gson.Gson
import id.pamoyanan_dev.home.R
import id.pamoyanan_dev.home.databinding.MasterFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.ext.*
import id.pamoyanan_dev.movieshop.AppConst.MOVIE_ITEM
import id.pamoyanan_dev.movieshop.AppNavigator.getProductDetailRoute
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.master_fragment.*

class MasterFragment : BaseFragment<MasterFragmentBinding, MasterVM>(), MasterItemCallback {

    override fun onCategoryMovieSelected(genre: String) {
        requireContext().showToast("You choose $genre movie")
    }

    override fun onItemMovieSelected(movieFilter: MovieFilter) {
        requireContext().apply {
            showToast("You choose ${movieFilter.title} movie")
            navigatorImplicit(getProductDetailRoute()) {
                putExtra(MOVIE_ITEM, Gson().toJson(movieFilter))
            }
        }
    }

    override fun bindLayoutRes() = R.layout.master_fragment

    override fun onSetViewModel(): MasterVM {
        return getViewModel { MasterVM(MainApp.instance) }
    }

    override fun onLoadObserver(baseViewModel: MasterVM) {
        baseViewModel.apply {
            movieFilterEvent.observe(this@MasterFragment, Observer { data ->
                data?.let {
                    setupMastersList(it)
                }
            })
        }
    }

    override fun onStartWork() {
    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                masterVM = it
            }
        }
    }

    private fun setupMastersList(data: List<MovieFilter>) {
        rec_master.apply {
            verticalListStyle()
            adapter = MasterAdapter(data, this@MasterFragment)
        }
    }

    companion object {
        fun newInstance() = MasterFragment().putArgs { }
    }
}