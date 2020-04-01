package com.assignment.appstreetassignment.module.fragments.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.appstreetassignment.application.ApplicationShared
import com.assignment.appstreetassignment.model.response.FetchWikiResponse
import com.assignment.appstreetassignment.network.networkapi.NetworkApi
import com.assignment.appstreetassignment.network.networkutil.apiConfig.ApiClientConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


class MainViewModel @Inject constructor() : ViewModel() {


    private var mListOfUsers = MutableLiveData<FetchWikiResponse>()

    fun getUsersList(): MutableLiveData<FetchWikiResponse> {
        return mListOfUsers
    }

    fun downloadUserList(pTitle: String, pItemCounts: Int=30, pShowProgress: Boolean = true) {
        if (ApplicationShared.mCurrentInstance?.isConnected()!!) {
            val mObservable =
                ApiClientConfig().getRetrofit().create(NetworkApi::class.java).downloadData(
                    titles = pTitle,
                    pilimit = pItemCounts
                )
            when (pShowProgress) {
                true -> ApplicationShared.mCurrentInstance?.showProgress()
            }
            mObservable.subscribeOn(Schedulers.io()).let {
                it.observeOn(AndroidSchedulers.mainThread())
                it.subscribe(this::handleResponse, this::handleError)
            }
        }
    }

    private fun handleResponse(pResponse: Response<FetchWikiResponse>) {
        ApplicationShared.mCurrentInstance?.hideProgressDialog()
        mListOfUsers.postValue(pResponse.body())
    }

    private fun handleError(error: Throwable) {
        ApplicationShared.mCurrentInstance?.hideProgressDialog()
        Log.e(MainViewModel::class.java.simpleName, error.localizedMessage!!)
    }
}
