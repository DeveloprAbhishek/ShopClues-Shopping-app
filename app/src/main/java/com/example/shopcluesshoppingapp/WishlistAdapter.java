package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class WishlistAdapter extends FirebaseRecyclerAdapter<OffersModel, WishlistViewHolder> {
    private onWishlistClickListener onWishlistClickListener;
    private ProgressBar progressBar;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public WishlistAdapter(@NonNull FirebaseRecyclerOptions<OffersModel> options, onWishlistClickListener onWishlistClickListener, ProgressBar progressBar) {
        super(options);
        this.onWishlistClickListener = onWishlistClickListener;
        this.progressBar = progressBar;
    }

    @Override
    protected void onBindViewHolder(@NonNull WishlistViewHolder holder, int position, @NonNull OffersModel model) {
        String key = getRef(position).getKey();
        holder.setOffersData(model, key, position);
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
        return new WishlistViewHolder(view, onWishlistClickListener);
    }
}
