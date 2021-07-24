package com.example.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class CartAdapter extends FirebaseRecyclerAdapter<CartModel, CartViewHolder> {
    private CartItemClickListener cartItemClickListener;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartAdapter(@NotNull FirebaseRecyclerOptions<CartModel> options, CartItemClickListener cartClicked) {
        super(options);
        this.cartItemClickListener = cartClicked;
    }

    @Override
    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NotNull CartModel model) {
        holder.setData(model, position);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view, cartItemClickListener);
    }
}
