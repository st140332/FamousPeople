package com.vlad.famouspeople.Sort;

import com.vlad.famouspeople.User;

import java.util.Comparator;

/**
 * Created by Vlad on 26.03.2018.
 */

public class SurnameComparator implements Comparator<User> {
    @Override
    public int compare(User user, User t1) {
        return user.getLastName().compareTo(t1.getLastName());
    }
}
