package com.vlad.famouspeople.Sort;

import com.vlad.famouspeople.User;

import java.util.Comparator;


/**
 * Created by Vlad on 26.03.2018.
 */

public class NameComparator implements Comparator<User>
{
    @Override
    public int compare(User user, User t1)
    {
        return user.getFirstName().compareTo(t1.getFirstName());
    }
}
