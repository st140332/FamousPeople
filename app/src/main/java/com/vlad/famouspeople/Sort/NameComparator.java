package com.vlad.famouspeople.Sort;

import com.vlad.famouspeople.User;
import com.vlad.famouspeople.UserPoint;

import java.util.Comparator;


/**
 * Created by Vlad on 26.03.2018.
 */

public class NameComparator implements Comparator<UserPoint>
{

    @Override
    public int compare(UserPoint userPoint, UserPoint t1) {
        return userPoint.getFirstName().compareTo(t1.getFirstName());
    }
}
