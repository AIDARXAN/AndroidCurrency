package com.e.currency.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.e.currency.App;
import com.e.currency.db.AppDatabase;
import com.e.currency.R;
import com.e.currency.entity.Currency;

import androidx.appcompat.app.AppCompatActivity;

public class AddCurrencyActivity extends AppCompatActivity {
    private EditText countryEditText;
    private EditText currencyEditText;
    private Button saveButton;

    private AppDatabase appDatabase = App.getAppDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        countryEditText = findViewById(R.id.contry_edit_text);
        currencyEditText = findViewById(R.id.currency_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryString = countryEditText.getText().toString();
                String currencyString = currencyEditText.getText().toString();
                Currency currency = new Currency(countryString, currencyString);
                saveToDatabase(currency);
            }
        });

    }

    private void saveToDatabase(final Currency currency){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.getWordDao().insert(currency);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                setResult(RESULT_OK);
                finish();
            }
        }.execute();
    }
}
