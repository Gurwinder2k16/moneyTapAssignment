package com.assignment.appstreetassignment.model.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Query(

	@field:SerializedName("pages")
	val pages: List<PagesItem>? = null
):Parcelable {
	constructor(parcel: Parcel) : this(parcel.createTypedArrayList(PagesItem)) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeTypedList(pages)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Query> {
		override fun createFromParcel(parcel: Parcel): Query {
			return Query(parcel)
		}

		override fun newArray(size: Int): Array<Query?> {
			return arrayOfNulls(size)
		}
	}
}