package com.urban.mvvmshowcase.view.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urban.mvvmshowcase.model.entity.Person;

public class StandardOneLineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ClickListener clickListener;
    private final TextView textLine;
    private Person person;

    StandardOneLineViewHolder(@NonNull ViewGroup container,
                              @NonNull ClickListener clickListener) {
        super(LayoutInflater.from(container.getContext())
                .inflate(android.R.layout.simple_list_item_1, container, false));
        this.clickListener = clickListener;
        this.textLine = (TextView) itemView.findViewById(android.R.id.text1);

        itemView.setOnClickListener(this);
    }

    void bind(Person person) {
        textLine.setText(person.toString());
        this.person = person;
    }

    @Override
    public void onClick(View v) {
        clickListener.onPersonClick(person);
    }
}
