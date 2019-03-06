package com.intelity.domain.data

import android.content.Intent
import android.graphics.drawable.Drawable

class ApplicationData {

    var title : String? = null

    var packageName : String? = null

    var ratingData : RatingData? = null

    var icon : Drawable? = null

    var launchIntent : Intent? = null
}