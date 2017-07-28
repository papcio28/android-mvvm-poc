package com.urban.mvvmshowcase.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.urban.mvvmshowcase.model.entity.Person

class PeopleAdapter(val clickListener: ClickListener) :
        RecyclerView.Adapter<StandardOneLineViewHolder>() {
    var people: List<Person> = emptyList()
        get
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: StandardOneLineViewHolder, position: Int)
            = holder.bind(people[position])

    override fun getItemCount() = people.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = StandardOneLineViewHolder(parent, clickListener)
}