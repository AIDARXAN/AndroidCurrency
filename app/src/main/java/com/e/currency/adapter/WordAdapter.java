package com.e.currency.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.e.currency.App;
import com.e.currency.db.AppDatabase;
import com.e.currency.R;
import com.e.currency.entity.Currency;
import com.e.currency.activity.WordsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Currency> currencies;
    private AppDatabase appDatabase = App.getAppDatabase();
    public Currency currency;

    WordsActivity wordsActivity = new WordsActivity();
    private ImageButton deleteItemButton;

    public WordAdapter(){
        currencies = new ArrayList<>();
    }

    public void setCurrencies(List<Currency> currencies){
        if(!this.currencies.isEmpty()) this.currencies.clear();

        this.currencies.addAll(currencies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.word_item_view, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.bind(currency);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordTextView;
        private TextView translateTextView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.country_text_view);
            translateTextView = itemView.findViewById(R.id.currency_text_view);
            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View view) {
                    deleteItem(getAdapterPosition());
                    return true;
                }
            });
        }

        public void bind(Currency currency) {
            wordTextView.setText(currency.getCountry());
            translateTextView.setText(currency.getCurrency());
        }
    }

    public void deleteItem(int position) {
        Currency currency = currencies.get(position);
        currencies.remove(position);
        notifyItemRemoved(position);

        deleteWordFromDatabase(currency);
    }

    public void deleteWordFromDatabase(final Currency currency){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.getWordDao().delete(currency);
                return null;
            }
        }.execute();
    }
}
