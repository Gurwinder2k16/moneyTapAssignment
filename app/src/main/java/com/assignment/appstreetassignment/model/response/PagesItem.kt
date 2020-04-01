package com.assignment.appstreetassignment.model.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class PagesItem(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("pageimage")
	val pageimage: String? = null,

	@field:SerializedName("ns")
	val ns: Int? = null,

	@field:SerializedName("terms")
	val terms: Terms? = null,

	@field:SerializedName("pageid")
	val pageid: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readParcelable(Thumbnail::class.java.classLoader),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readParcelable(Terms::class.java.classLoader),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeParcelable(thumbnail, flags)
		parcel.writeString(pageimage)
		parcel.writeValue(ns)
		parcel.writeParcelable(terms, flags)
		parcel.writeValue(pageid)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<PagesItem> {
		override fun createFromParcel(parcel: Parcel): PagesItem {
			return PagesItem(parcel)
		}

		override fun newArray(size: Int): Array<PagesItem?> {
			return arrayOfNulls(size)
		}
	}
}