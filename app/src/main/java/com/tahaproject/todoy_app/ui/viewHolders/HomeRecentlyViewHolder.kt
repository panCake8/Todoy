package com.tahaproject.todoy_app.ui.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahaproject.todoy_app.R

class HomeRecentlyViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {
    val textTitle : TextView = viewItem.findViewById(R.id.text_view_recently_title)
    val textBody : TextView = viewItem.findViewById(R.id.text_view_recently_body)
    val textTime : TextView = viewItem.findViewById(R.id.recently_card_time)
    val textDate : TextView = viewItem.findViewById(R.id.recently_card_date)

}