package com.zukron.batiknya.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Project name is BatikNya
 * Created by Zukron Alviandy R on 9/24/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
data class BatikResponse(
    @SerializedName("hasil")
    val data: List<Batik>
) {
    @Parcelize
    data class Batik(
        @SerializedName("id")
        val id: Int,
        @SerializedName("nama_batik")
        val batikName: String,
        @SerializedName("daerah_batik")
        val originLocation: String,
        @SerializedName("makna_batik")
        val meaningOfBatik: String,
        @SerializedName("harga_rendah")
        val lowPrice: Int,
        @SerializedName("harga_tinggi")
        val highPrice: Int,
        @SerializedName("hitung_view")
        val countView: Int,
        @SerializedName("link_batik")
        val imageUrl: String
    ) : Parcelable
}