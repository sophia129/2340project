package com.example.breadscrumbs.donation_tracker.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.breadscrumbs.donation_tracker.R;

import java.util.List;

import Model.Donation;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView item, value, category;

    public SearchViewHolder(View itemView) {
        super(itemView);
        item = (TextView)itemView.findViewById(R.id.item);
        value = (TextView)itemView.findViewById(R.id.value);
        category = (TextView)itemView.findViewById(R.id.category);
//        Locations = (TextView)itemView.findViewById(R.id.Locations);
    }

}
public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Donation> donation;

    public SearchAdapter(Context context, List<Donation> donation) {
        this.context = context;
        this.donation = donation;
    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        holder.item.setText(donation.get(position).getItem());
        holder.value.setText(donation.get(position).getValue());
        holder.category.setText(donation.get(position).getCategory());
//        holder.Locations.setText(donation.get(position).getLocations());


    }

    @Override
    public int getItemCount() {
        return donation.size();
    }
}
