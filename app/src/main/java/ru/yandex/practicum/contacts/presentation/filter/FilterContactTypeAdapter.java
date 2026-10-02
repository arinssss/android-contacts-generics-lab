package ru.yandex.practicum.contacts.presentation.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import ru.yandex.practicum.contacts.databinding.ItemFilterBinding;
import ru.yandex.practicum.contacts.model.ContactType;
import ru.yandex.practicum.contacts.presentation.base.CommonAdapter;
import ru.yandex.practicum.contacts.presentation.filter.model.FilterContactType;
import ru.yandex.practicum.contacts.presentation.filter.model.FilterContactTypeUi;
import ru.yandex.practicum.contacts.utils.model.ContactTypeUtils;
import ru.yandex.practicum.contacts.utils.model.FilterContactTypeUtils;

public class FilterContactTypeAdapter extends CommonAdapter<FilterContactTypeUi, FilterContactTypeAdapter.ViewHolder> {

    public FilterContactTypeAdapter(Consumer<FilterContactTypeUi> clickListener) {
        super(clickListener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ItemFilterBinding binding = ItemFilterBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding, clickListener);
    }

    @Override
    protected void bind(@NonNull ViewHolder holder, @NonNull FilterContactTypeUi item) {
        holder.bind(item);
    }

    static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        private final ItemFilterBinding binding;
        private FilterContactTypeUi data;

        public ViewHolder(@NonNull ItemFilterBinding binding, Consumer<FilterContactTypeUi> clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(v -> clickListener.accept(data));
            this.binding.selected.setOnClickListener(v -> clickListener.accept(data));
        }

        public void bind(FilterContactTypeUi data) {
            this.data = data;
            final int sortResId = FilterContactTypeUtils.getStringRes(data.getContactType());
            binding.text.setText(sortResId);
            binding.selected.setChecked(data.isSelected());
            if (data.getContactType() == FilterContactType.ALL) {
                binding.logo.setVisibility(View.GONE);
            } else {
                final ContactType contactType = FilterContactTypeUtils.toContactType(data.getContactType());
                final int iconRes = ContactTypeUtils.getIconRes(contactType);
                binding.logo.setVisibility(View.VISIBLE);
                binding.logo.setImageResource(iconRes);
            }
        }
    }
}