package id.pamoyanan_dev.l_extras.util

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import id.pamoyanan_dev.l_extras.R
import id.pamoyanan_dev.l_extras.ext.horizontalListStyle
import id.pamoyanan_dev.l_extras.ext.verticalListStyle

object AppBindings {
    @BindingAdapter("app:recyclerviewData", "app:orientationList")
    @JvmStatic
    fun <T> setRecyclerviewData(
        recyclerView: RecyclerView,
        data: MutableLiveData<List<T>>,
        orientationList: Int?
    ) {
        try {
            if (recyclerView.adapter is RecyclerviewBindableAdapter<*>) {
                if (orientationList == 1) recyclerView.horizontalListStyle() else
                    recyclerView.verticalListStyle()
                (recyclerView.adapter as RecyclerviewBindableAdapter<T>)
                    .onSetListsData(data.value!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, imageUrl: String) {
        if (imageUrl.isNotEmpty()) {
            GlideApp.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.color.greyBackgroundDefault)
                .error(R.color.greyBackgroundDefault)
                .into(imageView)
        }
    }

    @BindingAdapter("app:imageUrlCircleForm")
    @JvmStatic
    fun setImageUrlCircleForm(imageView: ImageView, imageUrlCircleForm: String) {
        if (imageUrlCircleForm.isNotEmpty()) {
            GlideApp.with(imageView)
                    .load(imageUrlCircleForm)
                    .placeholder(R.color.greyBackgroundDefault)
                    .error(R.color.greyBackgroundDefault)
                    .circleCrop()
                    .into(imageView)

        }
    }
}