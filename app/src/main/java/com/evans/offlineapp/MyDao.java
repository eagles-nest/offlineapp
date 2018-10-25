package com.evans.offlineapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addUser(User user);

    @Query("Select * from users")
    public List<User> getUsers();

}
