package com.janta.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.janta.shopcluesshoppingapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OffersAdapter extends FirebaseRecyclerAdapter<OffersModel, OffersViewHolder> {
    private ProgressBar progressBar;
    private OffersClickListener offersClickListener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OffersAdapter(@NonNull FirebaseRecyclerOptions<OffersModel> options, OffersClickListener offersClickListener, ProgressBar progressBar) {
        super(options);
        this. offersClickListener = offersClickListener;
        this.progressBar = progressBar;
    }

    @Override
    protected void onBindViewHolder(@NonNull OffersViewHolder holder, int position, @NonNull OffersModel model) {
        holder.setOffersData(model);
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_layout, parent, false);
        return new OffersViewHolder(view, offersClickListener);

    }
}
