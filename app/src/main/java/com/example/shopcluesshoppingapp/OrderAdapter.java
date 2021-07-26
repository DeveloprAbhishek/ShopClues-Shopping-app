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

public class OrderAdapter extends FirebaseRecyclerAdapter<OrderModel, OrderViewHolder> {
    private OrderClickListener orderClickListener;
    private ProgressBar progressBar;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdapter(@NonNull @NotNull FirebaseRecyclerOptions<OrderModel> options, OrderClickListener orderClickListener, ProgressBar progressBar) {
        super(options);
        this.orderClickListener = orderClickListener;
        this.progressBar = progressBar;
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull OrderViewHolder holder, int position, @NonNull @NotNull OrderModel model) {
        holder.setData(model);
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(view, orderClickListener);
    }
}
