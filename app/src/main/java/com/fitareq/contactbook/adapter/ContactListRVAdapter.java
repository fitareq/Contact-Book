package com.fitareq.contactbook.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.fitareq.contactbook.R;
import com.fitareq.contactbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactListRVAdapter extends RecyclerView.Adapter<ContactListRVAdapter.ContactListViewHolder> implements Filterable {


    private List<ContactData> dataList;
    private List<ContactData> dataListFull;
    private Context context;
    private ItemOnClick itemOnClick;

    public ContactListRVAdapter(List<ContactData> dataList, Context context, ItemOnClick itemOnClick) {
        this.dataList = dataList;
        this.context = context;
        this.itemOnClick = itemOnClick;
        this.dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_card, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, int position) {
        ContactData data = dataList.get(position);
        String image = String.valueOf(data.getName().charAt(0));


        holder.contactImage.setText(image.toUpperCase());
        holder.contactName.setText(data.getName());

        if (data.getAddress() != null)
        {
            holder.contactAddress.setText(data.getAddress());
            holder.contactAddress.setVisibility(View.VISIBLE);
        }else {
            holder.contactAddress.setVisibility(View.GONE);
        }


        holder.mainContainer.setOnClickListener(v ->
        {
            itemOnClick.itemOnClickListener(data);
        });
        holder.contactLocation.setOnClickListener(v -> {

        });

    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size(): 0;
    }

    @Override
    public Filter getFilter()
    {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ContactData> filteredData = new ArrayList<>();

            if (constraint == null || constraint.length() == 0)
            {
                filteredData.addAll(dataListFull);

            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ContactData data: dataListFull)
                {
                    if (data.getName().toLowerCase().contains(filterPattern))
                    {
                        filteredData.add(data);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList.clear();
            dataList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };










    public class ContactListViewHolder extends RecyclerView.ViewHolder
    {
        TextView contactImage, contactName, contactAddress;
        ImageView contactLocation;
        ConstraintLayout mainContainer;
        public ContactListViewHolder(@NonNull View itemView) {
            super(itemView);

            contactImage = itemView.findViewById(R.id.contact_image_tv);
            contactName = itemView.findViewById(R.id.contact_name);
            contactAddress = itemView.findViewById(R.id.contact_address);
            contactLocation = itemView.findViewById(R.id.contact_location_iv);
            mainContainer = itemView.findViewById(R.id.contact_card_main_container);

        }
    }

    public interface ItemOnClick{
        void itemOnClickListener(ContactData data);
    }
}
