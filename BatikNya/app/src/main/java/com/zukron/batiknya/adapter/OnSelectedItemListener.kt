package com.zukron.batiknya.adapter

import com.zukron.batiknya.model.BatikResponse

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/25/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
interface OnSelectedItemListener {
    fun onBatikSelected(batik: BatikResponse.Batik)
}