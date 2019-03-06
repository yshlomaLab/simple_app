package com.intelity.data.entity.mapper

import com.intelity.data.entity.RatingEntity
import com.intelity.domain.data.RatingData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatingMapper {

    @Inject constructor()

    fun transform(r : RatingEntity?) : RatingData {
        val ratingData = RatingData()
        ratingData.rating = r?.rating
        ratingData.icon = r?.icon
        ratingData.packageName = r?.pakageName
        return ratingData
    }

    fun transform(r : RatingData?) : RatingEntity {
        val ratingEntity = RatingEntity()
        ratingEntity.rating = r?.rating
        ratingEntity.icon = r?.icon
        ratingEntity.pakageName = r?.packageName
        return ratingEntity
    }
}