package com.zukron.batiknya.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zukron.batiknya.R
import com.zukron.batiknya.adapter.AllAdapter
import com.zukron.batiknya.adapter.OnSelectedItemListener
import com.zukron.batiknya.adapter.PopularAdapter
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.tools.NetworkState
import com.zukron.batiknya.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), OnSelectedItemListener {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.getPopularBatik.observe(this) {
            val popularAdapter = PopularAdapter(it)
            popularAdapter.setOnSelectedItemListener(this)
            homeAct_viewPager2.adapter = popularAdapter

            homeAct_dotsIndicator.setViewPager2(homeAct_viewPager2)
        }

        mainViewModel.getAllBatik.observe(this) {
            val allAdapter = AllAdapter(it.subList(0, 5))
            allAdapter.setOnSelectedItemListener(this)

            homeAct_recyclerView.adapter = allAdapter
        }

        mainViewModel.networkState.observe(this) {
            if (it == NetworkState.LOADING) {
                homeAct_viewPager2.visibility = View.GONE
                homeAct_dotsIndicator.visibility = View.GONE
                homeAct_progressBarViewPager.visibility = View.VISIBLE

                homeAct_recyclerView.visibility = View.GONE
                homeAct_progressBarRecyclerView.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                homeAct_viewPager2.visibility = View.VISIBLE
                homeAct_dotsIndicator.visibility = View.VISIBLE
                homeAct_progressBarViewPager.visibility = View.GONE

                homeAct_recyclerView.visibility = View.VISIBLE
                homeAct_progressBarRecyclerView.visibility = View.GONE
            }
        }

        homeAct_btnSeeAll.setOnClickListener {
            startActivity(Intent(this, AllBatikActivity::class.java))
        }
    }

    override fun onBatikSelected(batik: BatikResponse.Batik) {
        val intent = Intent(this, DetailBatikActivity::class.java)
        intent.putExtra(DetailBatikActivity.EXTRA_BATIK, batik)
        startActivity(intent)
    }
}