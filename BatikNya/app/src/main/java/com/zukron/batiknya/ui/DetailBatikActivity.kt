package com.zukron.batiknya.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.zukron.batiknya.R
import com.zukron.batiknya.model.BatikResponse
import kotlinx.android.synthetic.main.activity_detail_batik.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class DetailBatikActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BATIK: String = "extra_batik"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_batik)

        // toobar
        setSupportActionBar(detailBatikAct_toolbar)
        detailBatikAct_toolbar.setNavigationOnClickListener {
            finish()
        }

        val batik = intent.getParcelableExtra<BatikResponse.Batik>(EXTRA_BATIK)

        batik?.let {
            detailBatikAct_tvBatikName.text = it.batikName
            detailBatikAct_tvOriginLocation.text = it.originLocation
            detailBatikAct_tvMeaningOfBatik.text = it.meaningOfBatik

            val priceRange = "${toRupiah(it.lowPrice)} - ${toRupiah(it.highPrice)}"
            detailBatikAct_tvPriceRange.text = priceRange

            Glide.with(this)
                .load(it.imageUrl)
                .placeholder(R.drawable.icons8_no_image_100)
                .into(detailBatikAct_imageView)
        }
    }

    private fun toRupiah(value: Int): String {
        val kursIndonesia: DecimalFormat = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val formatRp = DecimalFormatSymbols()

        formatRp.currencySymbol = "Rp. "
        formatRp.monetaryDecimalSeparator = ','
        formatRp.groupingSeparator = '.'

        kursIndonesia.decimalFormatSymbols = formatRp
        return kursIndonesia.format(value)
    }
}