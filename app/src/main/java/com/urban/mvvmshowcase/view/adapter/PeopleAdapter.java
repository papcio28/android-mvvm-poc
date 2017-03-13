package com.urban.mvvmshowcase.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.StandardOneLineViewHolder> {
    private final ClickListener clickListener;
    private List<Person> people = Collections.emptyList();

    public PeopleAdapter(@NonNull ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
        notifyDataSetChanged();
    }

    @Override
    public StandardOneLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StandardOneLineViewHolder(parent, clickListener);
    }

    @Override
    public void onBindViewHolder(StandardOneLineViewHolder holder, int position) {
        holder.bind(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public interface ClickListener {
        void onPersonClick(Person person);
    }

    static class StandardOneLineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ClickListener clickListener;
        private final TextView textLine;
        private Person person;

        private StandardOneLineViewHolder(@NonNull ViewGroup container,
                                          @NonNull ClickListener clickListener) {
            super(LayoutInflater.from(container.getContext())
                    .inflate(android.R.layout.simple_list_item_1, container, false));
            this.clickListener = clickListener;
            this.textLine = (TextView) itemView.findViewById(android.R.id.text1);

            itemView.setOnClickListener(this);
        }

        private void bind(Person person) {
            textLine.setText(person.toString());
            this.person = person;
        }

        @Override
        public void onClick(View v) {
            clickListener.onPersonClick(person);
        }
    }
}
