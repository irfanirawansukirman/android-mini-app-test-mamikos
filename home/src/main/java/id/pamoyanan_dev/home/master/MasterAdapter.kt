package id.pamoyanan_dev.home.master

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.pamoyanan_dev.home.databinding.MasterCategoryHeaderBinding
import id.pamoyanan_dev.home.databinding.MasterItemBinding
import id.pamoyanan_dev.l_extras.data.model.MovieFilter

class MasterAdapter(private val data: List<MovieFilter>,
                    private val masterItemCallback: MasterItemCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_TYPE) {
            HeaderHolder(MasterCategoryHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            BodyHolder(MasterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        return if (data[position].type == 0) {
            HEADER_TYPE
        } else {
            BODY_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (getItemViewType(pos)) {
            HEADER_TYPE -> {
                (holder as HeaderHolder).bindItem(masterItemCallback)
            }
            BODY_TYPE -> {
                (holder as BodyHolder).bindItem(data[pos], masterItemCallback)
            }
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val BODY_TYPE = 1
    }

    class HeaderHolder(private val masterCategoryHeaderBinding: MasterCategoryHeaderBinding) :
        RecyclerView.ViewHolder(masterCategoryHeaderBinding.root) {

        fun bindItem(masterItemCallback: MasterItemCallback) {
            masterCategoryHeaderBinding.apply {
                this.masterItemCallback = masterItemCallback
                executePendingBindings()
            }
        }
    }

    class BodyHolder(private val masterItemBinding: MasterItemBinding) :
        RecyclerView.ViewHolder(masterItemBinding.root) {

        fun bindItem(movieFilter: MovieFilter, masterItemCallback: MasterItemCallback) {
            masterItemBinding.apply {
                this.movieFilter = movieFilter
                this.masterItemCallback = masterItemCallback
                executePendingBindings()
            }
        }

    }

}
