package com.assignment.appstreetassignment.network.networkapi

import com.assignment.appstreetassignment.model.response.FetchWikiResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("api.php")
    fun downloadData(
        @Query("prop") prop: String? = "pageimages|pageterms",
        @Query("format") format: String? = "json",
        @Query("formatversion") formatversion: Int? = 2,
        @Query("action") action: String? = "query",
        @Query("gpssearch") titles: String? = null,
        @Query("generator") generator: String? = "prefixsearch",
        @Query("gpslimit") pilimit: Int? = 20,
        @Query("redirects") redirect: Int? = 1,
        @Query("wbptterms") wbptterms: String? = "description",
        @Query("piprop") piprop: String? = "thumbnail"
    ): Single<Response<FetchWikiResponse>>
}
