package com.zukron.batiknya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class AllAdapter(private val batikList: List<BatikResponse.Batik>) :
    RecyclerView.Adapter<AllAdapter.AllViewHolder>() {
    private var onSelectedItemListener: OnSelectedItemListener? = null

    fun setOnSelectedItemListener(onSelectedItemListener: OnSelectedItemListener) {
        this.onSelectedItemListener = onSelectedItemListener
    }

    inner class AllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTo(batik: BatikResponse.Batik) {
            itemView.itemBatik_tvBatikName.text = batik.batikName
            itemView.itemBatik_tvOriginLocation.text = batik.originLocation
            itemView.itemBatik_tvMeaningOfBatik.text = batik.meaningOfBatik

            val countView = "Dilihat ${batik.countView} kali"
            itemView.itemBatik_tvCountView.text = countView

            Glide.with(itemView.context)
                .load(batik.imageUrl)
                .override(100)
                .placeholder(R.drawable.icons8_no_image_100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.itemBatik_imageView)

            itemView.setOnClickListener {
                onSelectedItemListener?.onBatikSelected(batik)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_batik, parent, false)
        return AllViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        holder.bindTo(batikList[position])
    }

    override fun getItemCount(): Int {
        return batikList.size
    }
}