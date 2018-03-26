package com.vlad.famouspeople;

import java.util.Comparator;

/**
 * Created by Vlad on 26.03.2018.
 */

public class PointsComparator implements Comparator<User> {
    @Override
    public int compare(User user, User t1) {
        return user.getEmail().compareTo(t1.getEmail());
    }
}
