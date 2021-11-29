package com.janta.shopcluesshoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

public class CategoryDataShowAdapter extends FirebaseRecyclerAdapter<OffersModel, CategoryDataShowViewHolder> {
    private ProgressBar progressBar;
    private CategoryDataShowCllickListener listener;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryDataShowAdapter(@NonNull FirebaseRecyclerOptions<OffersModel> options, CategoryDataShowCllickListener listener, ProgressBar progressBar) {
        super(options);
        this.listener = listener;
        this.progressBar = progressBar;
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoryDataShowViewHolder holder, int position, @NotNull OffersModel model) {
        holder.setOffersData(model);
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public CategoryDataShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_show_data_layout, parent, false);
        return new CategoryDataShowViewHolder(view, listener);
    }
}
