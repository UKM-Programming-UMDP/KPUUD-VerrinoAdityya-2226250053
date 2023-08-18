package com.aplikasi1.myapplication

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import org.w3c.dom.Node

data class UserModel(

	@field:SerializedName("gists_url")
	val gistsUrl: String? = null,

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@field:SerializedName("starred_url")
	val starredUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("gravatar_id")
	val gravatarId: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = null
)	: Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(), // gistsUrl
		parcel.readString(), // reposUrl
		parcel.readString(), // followingUrl
		parcel.readString(), // starredUrl
		parcel.readString(), // login
		parcel.readString(), // followersUrl
		parcel.readString(), // type
		parcel.readString(), // url
		parcel.readString(), // subscriptionsUrl
		parcel.readString(), // receivedEventsUrl
		parcel.readString(), // avatarUrl
		parcel.readString(), // eventsUrl
		parcel.readString(), // htmlUrl
		siteAdmin = parcel.readInt() != 0, // siteAdmin
		parcel.readInt(), // id
		parcel.readString(), // gravatarId
		parcel.readString(), // NodeId
		parcel.readString() // OrganizationsUrl
		// ... initialize other fields from the parcel
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(gistsUrl)
		parcel.writeString(reposUrl)
		parcel.writeString(followingUrl)
		parcel.writeString(starredUrl)
		parcel.writeString(login)
		parcel.writeString(followersUrl)
		parcel.writeString(type)
		parcel.writeString(url)
		parcel.writeString(subscriptionsUrl)
		parcel.writeString(receivedEventsUrl)
		parcel.writeString(avatarUrl)
		parcel.writeString(eventsUrl)
		parcel.writeString(htmlUrl)
		parcel.writeInt(if (siteAdmin == true) 1 else 0)
		parcel.writeInt(id)
		parcel.writeString(gravatarId)
		parcel.writeString(nodeId)
		parcel.writeString(organizationsUrl)

		// ... write other fields to the parcel
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<UserModel> {
		override fun createFromParcel(parcel: Parcel): UserModel {
			return UserModel(parcel)
		}

		override fun newArray(size: Int): Array<UserModel?> {
			return arrayOfNulls(size)
		}
	}
}
