package com.zukron.batiknya.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zukron.batiknya.R
import com.zukron.batiknya.model.BatikResponse
import kotlinx.android.synthetic.main.item_batik.view.*

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/25/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class AllPagedAdapter
    : PagedListAdapter<BatikResponse.Batik, RecyclerView.ViewHolder>(DiffCallback()) {

    private var onSelectedItemListener: OnSelectedItemListener? = null

    fun setOnSelectedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }

    inner class AllPagedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(item: BatikResponse.Batik?) {
            item?.let {
                itemView.itemBatik_tvBatikName.text = it.batikName
                itemView.itemBatik_tvOriginLocation.text = it.originLocation
                itemView.itemBatik_tvMeaningOfBatik.text = it.meaningOfBatik

                val countView = "Dilihat ${it.countView} kali"
                itemView.itemBatik_tvCountView.text = countView

                Glide.with(itemView.context)
                    .load(it.imageUrl)
                    .override(100)
                    .placeholder(R.drawable.icons8_no_image_100)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.itemBatik_imageView)

                itemView.setOnClickListener { _ ->
                    onSelectedItemListener?.onBatikSelected(it)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BatikResponse.Batik>() {
        override fun areItemsTheSame(
            oldItem: BatikResponse.Batik,
            newItem: BatikResponse.Batik
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BatikResponse.Batik,
            newItem: BatikResponse.Batik
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_batik, parent, false)
        return AllPagedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AllPagedViewHolder).bindTo(getItem(position))
    }
}