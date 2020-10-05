package com.zukron.batiknya.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zukron.batiknya.R
import com.zukron.batiknya.adapter.AllPagedAdapter
import com.zukron.batiknya.adapter.OnSelectedItemListener
import com.zukron.batiknya.model.BatikResponse
import com.zukron.batiknya.tools.NetworkState
import com.zukron.batiknya.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_all_batik.*

class AllBatikActivity : AppCompatActivity(), OnSelectedItemListener {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: AllPagedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_batik)

        // toolbar
        setSupportActionBar(allBatikAct_toolbar)
        allBatikAct_toolbar.setNavigationOnClickListener {
            finish()
        }

        // adapter
        adapter = AllPagedAdapter()
        adapter.setOnSelectedItemListener(this)

        // view model
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.getAllPagedBatik.observe(this) {
            adapter.submitList(it)

            allBatikAct_recyclerView.adapter = adapter
        }

        mainViewModel.networkState.observe(this) {
            if (it == NetworkState.LOADING) {
                allBatikAct_progressBar.visibility = View.VISIBLE
            }

            if (it == NetworkState.LOADED) {
                allBatikAct_progressBar.visibility = View.GONE
            }
        }
    }

    override fun onBatikSelected(batik: BatikResponse.Batik) {
        val intent = Intent(this, DetailBatikActivity::class.java)
        intent.putExtra(DetailBatikActivity.EXTRA_BATIK, batik)
        startActivity(intent)
    }
}