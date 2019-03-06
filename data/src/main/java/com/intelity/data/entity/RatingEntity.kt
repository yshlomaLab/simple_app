package com.intelity.data.entity

import com.google.gson.annotations.SerializedName

class RatingEntity {

    @SerializedName("rating")
    var rating: Double? = null

    @SerializedName("package_name")
    var pakageName: String? = null

    @SerializedName("icon_72")
    var icon: String? = null
}