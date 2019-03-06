package com.intelity.data.entity.mapper

import com.intelity.data.entity.ApplicationEntity
import com.intelity.domain.data.ApplicationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplicationMapper {

    @Inject lateinit var ratingMapper : RatingMapper

    @Inject constructor()

    fun transform(app : ApplicationEntity?) : ApplicationData {
        val appData = ApplicationData()
        appData.title = app?.title
        appData.packageName = app?.packageName
        appData.icon = app?.icon
        appData.launchIntent = app?.launchIntent
        appData.ratingData = ratingMapper.transform(app?.ratingEntity)
        return appData
    }

    fun transform(application : ApplicationData?) : ApplicationEntity {
        val appEntity = ApplicationEntity(application?.title, application?.packageName)
        appEntity.ratingEntity = ratingMapper.transform(application?.ratingData)
        return appEntity
    }

    fun transformList(list : MutableList<ApplicationEntity>) : MutableList<ApplicationData> {
        val result = mutableListOf<ApplicationData>()
        for (entity in list) {
            val data = transform(entity)
            result.add(data)
        }
        return result
    }

    fun transform(list : MutableList<ApplicationData>) : MutableList<ApplicationEntity> {
        val result = mutableListOf<ApplicationEntity>()
        for (data in list) {
            val entity = transform(data)
            result.add(entity)
        }
        return result
    }
}