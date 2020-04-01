package com.assignment.appstreetassignment.module.fragments;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.appstreetassignment.R
import com.assignment.appstreetassignment.model.response.FetchWikiResponse
import com.assignment.appstreetassignment.model.response.PagesItem
import com.assignment.appstreetassignment.module.fragments.viewmodels.DetailViewModel
import com.assignment.appstreetassignment.utiles.ImageUtils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.content_user_header.*
import kotlinx.android.synthetic.main.layout_actionbar.*
import kotlinx.android.synthetic.main.layout_sub_content.*
import javax.inject.Inject

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    @Inject
    lateinit var mViewModel: DetailViewModel

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    private lateinit var mFetchWikiResponse: PagesItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(DetailViewModel::class.java)
        iv_back.setOnClickListener { activity?.onBackPressed() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToView()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun setFetchWikiResponse(pFetchWikiResponse: PagesItem) {
        mFetchWikiResponse = pFetchWikiResponse
    }

    private fun setDataToView() {
        when (::mFetchWikiResponse.isInitialized) {
            true -> {
                tv_activity_title.text=mFetchWikiResponse.title
                tv_title.text = mFetchWikiResponse.title
                tv_desc.text = getString(R.string.user_type_s, mFetchWikiResponse.pageid)
                when(mFetchWikiResponse.thumbnail!=null && mFetchWikiResponse.thumbnail?.source!=null && mFetchWikiResponse.thumbnail?.source?.isNotEmpty()!!){
                    true->{
                        ImageUtils.newInstance().downloadImage(
                            pUrl = mFetchWikiResponse.thumbnail?.source.toString(),
                            pView = iv_users
                        )
                    }
                }
                tv_full_desc.text = getString(R.string.description_n_s,getFullDescription(mFetchWikiResponse.terms?.description))
                tv_url.text = getString(R.string.repo_url_s,"")
            }
        }
    }

    private fun getFullDescription(pStringList:List<String>?):String{
        var temp="Full Description not available."
        when(!pStringList.isNullOrEmpty()){
            true->{
                temp=""
                for(element in pStringList){
                    temp= element.plus("\n")
                }
                return temp
            }
        }
        return temp
    }
}
