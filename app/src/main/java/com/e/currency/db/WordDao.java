package com.e.currency.db;

import com.e.currency.entity.Currency;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface WordDao {

    @Insert
    void insert(Currency currency);

    @Query("SELECT * FROM Currency")
    List<Currency> getWords();

    @Delete
    void delete(Currency currency);

    @Query("DELETE FROM Currency")
    void deleteAll();
}
