package ru.yandex.practicum.contacts.presentation.sort;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.yandex.practicum.contacts.R;
import ru.yandex.practicum.contacts.databinding.ItemSortBinding;
import ru.yandex.practicum.contacts.presentation.base.CommonAdapter;
import ru.yandex.practicum.contacts.presentation.sort.model.SortType;

public class SortTypeAdapter extends CommonAdapter<SortTypeUI, SortTypeAdapter.ViewHolder> {

    public SortTypeAdapter(Consumer<SortTypeUI> clickListener) {
        super(clickListener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemSortBinding binding = ItemSortBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding, clickListener);
    }

    @Override
    protected void bind(@NonNull ViewHolder holder, @NonNull SortTypeUI item) {
        holder.bind(item);
    }

    static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        private final ItemSortBinding binding;
        private SortTypeUI data;

        public ViewHolder(@NonNull ItemSortBinding binding, Consumer<SortTypeUI> clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(v -> clickListener.accept(data));
        }

        public void bind(SortTypeUI data) {
            this.data = data;
            final int sortResId = resource(data.getSortType());
            binding.text.setText(sortResId);
            binding.selected.setVisibility(data.isSelected() ? View.VISIBLE : View.GONE);
        }

        private int resource(SortType sortType) {
            switch (sortType) {
                case BY_NAME: return R.string.sort_by_name;
                case BY_NAME_REVERSED: return R.string.sort_by_name_reversed;
                case BY_SURNAME: return R.string.sort_by_surname;
                case BY_SURNAME_REVERSED: return R.string.sort_by_surname_reversed;
                default: throw new IllegalArgumentException("Not supported SortType");
            }
        }
    }
}