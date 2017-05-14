package com.mmg.common;

import com.mmg.entity.BaseObject;

/**
 * Created by yj on 2017/5/13.
 */
public class MyComparator<T extends BaseObject> implements Comparable<T>{
    public int compareTo(T o) {
        return 0;
    }
}
