package com.e.currency.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.e.currency.App;
import com.e.currency.db.AppDatabase;
import com.e.currency.R;
import com.e.currency.entity.Currency;
import com.e.currency.adapter.WordAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WordsActivity extends AppCompatActivity {
    private static final int ADD_WORD_REQUEST_CODE = 1001;

    private FloatingActionButton addButton;
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;
    private WordAdapter deleteAdapter;
    private FloatingActionButton deleteButton;
    private AppDatabase appDatabase = App.getAppDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.word_recycle_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        wordAdapter = new WordAdapter();
        recyclerView.setAdapter(wordAdapter);
        loadWords();
        deleteButton = findViewById(R.id.delete_all_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteWords();
                deleteAdapter = new WordAdapter();
                recyclerView.setAdapter(deleteAdapter);
            }
        });
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordsActivity.this, AddCurrencyActivity.class);
                startActivityForResult(intent, ADD_WORD_REQUEST_CODE);

            }
        });
    }

    private void loadWords(){
        new AsyncTask<Void, Void, List<Currency>>() {
            @Override
            protected List<Currency> doInBackground(Void... voids) {
                return appDatabase.getWordDao().getWords();
            }

            @Override
            protected void onPostExecute(List<Currency> currencies) {
                wordAdapter.setCurrencies(currencies);
            }
        }.execute();
    }

    private void deleteWords(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
               appDatabase.getWordDao().deleteAll();
               return null;
            }
        }.execute();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_WORD_REQUEST_CODE && resultCode == RESULT_OK){
            loadWords();
            recyclerView.setAdapter(wordAdapter);
        }
    }



}
