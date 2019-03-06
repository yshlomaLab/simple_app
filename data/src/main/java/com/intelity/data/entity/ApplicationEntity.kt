package com.intelity.data.entity

import android.content.Intent
import android.graphics.drawable.Drawable

class ApplicationEntity(title: String?, packageName: String?) {

    var packageName : String? = packageName

    var title : String? = title

    var icon : Drawable? = null

    var launchIntent : Intent? = null

    var ratingEntity : RatingEntity? = null

}