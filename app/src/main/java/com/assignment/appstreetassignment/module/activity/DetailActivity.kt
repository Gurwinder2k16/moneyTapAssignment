package com.assignment.appstreetassignment.module.activity

import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.assignment.appstreetassignment.R
import com.assignment.appstreetassignment.model.response.FetchWikiResponse
import com.assignment.appstreetassignment.model.response.PagesItem
import com.assignment.appstreetassignment.module.fragments.DetailFragment

class DetailActivity : BaseActivity() {


    private var mDefaultFragment: DetailFragment = DetailFragment.newInstance()
    private lateinit var mFetchWikiResponse: PagesItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        getDataFromPrevIntent()
        if (savedInstanceState == null && ::mFetchWikiResponse.isInitialized) {
            mDefaultFragment.setFetchWikiResponse(mFetchWikiResponse)
            setDefaultView(pFragment = mDefaultFragment)
        }
    }

    private fun setDefaultView(pFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, pFragment)
            .commitNow()
    }

    private fun getDataFromPrevIntent() {
        when (intent.hasExtra(DetailActivity::class.java.simpleName)) {
            true -> {
                mFetchWikiResponse = intent.getParcelableExtra(DetailActivity::class.java.simpleName)!!
            }
        }
    }

    override fun onBackPressed() {
        ActivityCompat.finishAfterTransition(this)
    }
}
