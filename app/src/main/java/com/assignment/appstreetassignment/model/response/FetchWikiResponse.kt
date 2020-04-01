package com.assignment.appstreetassignment.model.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class FetchWikiResponse(
	@field:SerializedName("query")
	val query: Query
)