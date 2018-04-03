package com.vlad.famouspeople.Sort;

import com.vlad.famouspeople.User;
import com.vlad.famouspeople.UserPoint;

import java.util.Comparator;

/**
 * Created by Vlad on 26.03.2018.
 */

public class PointsComparator implements Comparator<UserPoint> {
    @Override
    public int compare(UserPoint userPoint, UserPoint t1) {
        return userPoint.getCount().compareTo(t1.getCount());
    }
}
