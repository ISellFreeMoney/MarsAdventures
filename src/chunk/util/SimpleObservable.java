package chunk.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleObservable<Type>  implements Observable<Type>{

    private static final long serialVersionUID = 1L;
    private List<Type> listeners;

    public SimpleObservable(){
        listeners = new CopyOnWriteArrayList<Type>();
    }

    @Override
    public void addListener(Type listener) {
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    @Override
    public boolean hasListener(Type listener) {
        return listeners.contains(listener);
    }

    @Override
    public void removeListener(Type listener) {
        listeners.remove(listener);
    }

    @Override
    public Collection<Type> getListeners() {
        return listeners;
    }
}