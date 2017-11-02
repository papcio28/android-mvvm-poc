package com.urban.mvvmshowcase.person.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.urban.mvvmshowcase.person.Person

class PeopleAdapter(val clickListener: ClickListener, val people: List<Person>) :
        RecyclerView.Adapter<StandardOneLineViewHolder>() {

    override fun onBindViewHolder(holder: StandardOneLineViewHolder, position: Int)
            = holder.bind(people[position])

    override fun getItemCount() = people.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = StandardOneLineViewHolder(parent, clickListener)
}