package com.evans.offlineapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;
    //for id
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    //for email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    //for password
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}

