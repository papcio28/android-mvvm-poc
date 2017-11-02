package com.urban.mvvmshowcase.person.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.urban.mvvmshowcase.person.Person

class StandardOneLineViewHolder(container: ViewGroup, private val clickListener: ClickListener) :
        RecyclerView.ViewHolder(LayoutInflater.from(container.context)
                .inflate(android.R.layout.simple_list_item_1, container, false)),
        View.OnClickListener {
    private lateinit var person: Person
    private val textLine = itemView.findViewById(android.R.id.text1) as TextView

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(person: Person) {
        textLine.text = person.toString()
        this.person = person
    }

    override fun onClick(v: View?) = clickListener.onPersonClick(person)
}