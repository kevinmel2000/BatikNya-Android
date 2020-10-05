package com.zukron.batiknya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zukron.batiknya.R
import com.zukron.batiknya.model.BatikResponse
import kotlinx.android.synthetic.main.item_popular.view.*

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/24/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class PopularAdapter(private val listBatik: List<BatikResponse.Batik>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    private var onSelectedItemListener: OnSelectedItemListener? = null

    fun setOnSelectedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(batik: BatikResponse.Batik) {
            itemView.itemPopular_name.text = batik.batikName
            itemView.itemPopular_originLocation.text = batik.originLocation

            Glide.with(itemView.context)
                .load(batik.imageUrl)
                .override(300)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.icons8_no_image_100)
                .into(itemView.itemPopular_image)

            itemView.setOnClickListener {
                onSelectedItemListener?.onBatikSelected(batik)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bindTo(listBatik[position])
    }

    override fun getItemCount(): Int {
        return listBatik.size
    }
}