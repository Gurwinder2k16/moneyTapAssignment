package com.assignment.appstreetassignment.model.response


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Terms(

	@field:SerializedName("alias")
	val alias: List<String>? = null,

	@field:SerializedName("description")
	val description: List<String>? = null,

	@field:SerializedName("label")
	val label: List<String?>? = null
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.createStringArrayList(),
		parcel.createStringArrayList(),
		parcel.createStringArrayList()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeStringList(alias)
		parcel.writeStringList(description)
		parcel.writeStringList(label)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Terms> {
		override fun createFromParcel(parcel: Parcel): Terms {
			return Terms(parcel)
		}

		override fun newArray(size: Int): Array<Terms?> {
			return arrayOfNulls(size)
		}
	}
}