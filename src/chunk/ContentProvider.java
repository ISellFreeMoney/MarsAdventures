package chunk;

import java.util.Collection;

public interface ContentProvider {

    void add(Object target);
    void remove(Object target);
    Collection<Object> getContent();
}
