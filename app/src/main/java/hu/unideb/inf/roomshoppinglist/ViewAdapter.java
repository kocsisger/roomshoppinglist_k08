package hu.unideb.inf.roomshoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.unideb.inf.roomshoppinglist.databinding.ItemLayoutBinding;
import hu.unideb.inf.roomshoppinglist.model.ShoppingListItem;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ViewHolder holder, int position) {
        holder.setItemData(data.get(position).getName());
    }

    private List<ShoppingListItem> data;

    public ViewAdapter(List<ShoppingListItem> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemLayoutBinding.bind(itemView);
        }

        void setItemData(String dataItem){
            binding.itemTextView.setText(dataItem);
        }
    }
}
