package util;

import java.io.Serializable;
import java.util.Collection;

public interface Observable<Type> extends Serializable {
    void addListener(Type listener);
    boolean hasListener(Type listener);
    void removeListener(Type listener);
    Collection<Type> getListeners();
}
