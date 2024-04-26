package com.softnovo.base.generic;

import java.util.ArrayList;
import java.util.Collection;

public class SelfArrayList<E> extends ArrayList<E> {

//    public boolean add(<? extends E> e) {
//        return super.add(e);
//    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }
}
