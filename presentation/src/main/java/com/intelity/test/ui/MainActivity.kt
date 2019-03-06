package com.intelity.test.ui

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intelity.domain.data.ApplicationData
import com.intelity.test.R
import com.intelity.test.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var applicationList : RecyclerView

    private lateinit var viewModel : MainViewModel

    private lateinit var appLiveData: MutableLiveData<MutableList<ApplicationData>>

    private lateinit var adapter: ApplicationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applicationList = findViewById(R.id.appsList)

        val layoutManager = LinearLayoutManager(this)
        applicationList.layoutManager = layoutManager

        adapter = ApplicationListAdapter(this)
        applicationList.adapter = adapter


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        appLiveData = viewModel.getAppLiveData()

        appLiveData.observe(this, Observer {
            list -> if (!list.isNullOrEmpty() && adapter != null) {
                adapter.updateList(list)
        }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun getLayout(): Int {
       return R.layout.activity_main
    }
}
