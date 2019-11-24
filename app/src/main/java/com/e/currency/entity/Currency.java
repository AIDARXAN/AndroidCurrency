package com.e.currency.entity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Currency {
    @PrimaryKey
    @NonNull
    private String country;
    private String currency;


    public Currency(@NonNull String country, String currency) {
        this.country = country;
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency1 = (Currency) o;
        return country.equals(currency1.country) &&
                currency.equals(currency1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, currency);
    }
}
