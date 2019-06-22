package id.pamoyanan_dev.home.transaction

import android.arch.lifecycle.Observer
import id.pamoyanan_dev.home.R
import id.pamoyanan_dev.home.databinding.TransactionFragmentBinding
import id.pamoyanan_dev.l_extras.base.BaseFragment
import id.pamoyanan_dev.l_extras.data.model.MovieFilter
import id.pamoyanan_dev.l_extras.ext.putArgs
import id.pamoyanan_dev.l_extras.ext.verticalListStyle
import id.pamoyanan_dev.movieshop.MainApp
import kotlinx.android.synthetic.main.transaction_fragment.*

class TransactionFragment : BaseFragment<TransactionFragmentBinding, TransactionVM>() {

    override fun bindLayoutRes() = R.layout.transaction_fragment

    override fun onSetViewModel(): TransactionVM {
        return TransactionVM(MainApp.instance)
    }

    override fun onLoadObserver(baseViewModel: TransactionVM) {
        baseViewModel.apply {
            moviesFilterListEvent.observe(this@TransactionFragment, Observer { data ->
                data?.let {
                    setupTransactionsList(it)
                }
            })
        }
    }

    override fun onStartWork() {

    }

    override fun onSetInstrument() {
        baseViewModel.let {
            viewBinding.apply {
                transactionVM = it
            }
        }
    }

    private fun setupTransactionsList(data: List<MovieFilter>) {
        rec_transaction.apply {
            verticalListStyle()
            adapter = TransactionAdapter(data)
        }
    }

    companion object {
        fun newInstance() = TransactionFragment().putArgs { }
    }
}