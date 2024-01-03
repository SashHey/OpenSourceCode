package com.example.opsc_moviefans;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CurrentUser {

    String Username;
    String Password;

    public List<Category> Categories;

    public CurrentUser(String username, String password){

        Username = username;
        Password = password;
    }
}
