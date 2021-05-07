package util;

import java.util.ArrayList;

public class SynchronizedList<T> extends ArrayList<T> {

    @Override
    public synchronized boolean add(T t) {
        return super.add(t);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }
}
