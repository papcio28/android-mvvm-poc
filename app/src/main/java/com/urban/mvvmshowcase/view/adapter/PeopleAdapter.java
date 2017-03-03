package com.urban.mvvmshowcase.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urban.mvvmshowcase.model.entity.Person;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.StandardOneLineViewHolder> {
    private List<Person> mPeople = Collections.emptyList();

    public void setPeople(List<Person> people) {
        mPeople = people;
        notifyDataSetChanged();
    }

    @Override
    public StandardOneLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StandardOneLineViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(StandardOneLineViewHolder holder, int position) {
        Person person = mPeople.get(position);

        holder.mTextLine.setText(person.toString());
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    static class StandardOneLineViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextLine;

        private StandardOneLineViewHolder(@NonNull ViewGroup container) {
            super(LayoutInflater.from(container.getContext())
                    .inflate(android.R.layout.simple_list_item_1, container, false));

            mTextLine = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
