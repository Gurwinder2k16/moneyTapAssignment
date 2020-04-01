package com.assignment.appstreetassignment.model.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Thumbnail(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("source")
	val source: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(width)
		parcel.writeString(source)
		parcel.writeValue(height)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Thumbnail> {
		override fun createFromParcel(parcel: Parcel): Thumbnail {
			return Thumbnail(parcel)
		}

		override fun newArray(size: Int): Array<Thumbnail?> {
			return arrayOfNulls(size)
		}
	}
}