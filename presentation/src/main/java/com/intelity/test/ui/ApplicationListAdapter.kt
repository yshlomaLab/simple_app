package com.intelity.test.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intelity.domain.data.ApplicationData
import com.intelity.test.R
import com.intelity.test.utils.inflate

/**
 * Recycler view application adapter.
 */
class ApplicationListAdapter(context: Context) : RecyclerView.Adapter<ApplicationListAdapter.ViewHolder>() {

    private var applicationsList = mutableListOf<ApplicationData>()
    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.application_item, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return applicationsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var appData = applicationsList[position]
        holder.bind(appData)
    }

    fun updateList(list : MutableList<ApplicationData>) {
        applicationsList.clear()
        applicationsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var view : View
        var icon : ImageView
        var title : TextView
        var rating : TextView

        init {
            this.view = view
            icon = view.findViewById(R.id.icon)
            title = view.findViewById(R.id.title)
            rating = view.findViewById(R.id.rating)
        }

        fun bind(applicationData: ApplicationData) {
            title.text = applicationData.title
            if (applicationData.ratingData?.rating != null) {
                rating.text = String.format(context.getString(R.string.rating),
                    applicationData.ratingData?.rating!!.toString())
            } else {
                rating.text = ""
            }
            if (applicationData.icon != null)
                icon.setImageDrawable(applicationData.icon)
            if (applicationData.launchIntent != null)
                view.setOnClickListener{context.startActivity(applicationData.launchIntent)}
        }

    }
}