package chunk.concurrent;

import util.Indexable;
import util.MatrixIterator;
import util.MatrixList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMatrixList<Type extends Indexable> implements MatrixList<Type> {

    private int elementSize;
    private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>> chunks;

    public ConcurrentMatrixList(){
        elementSize = 0;
        chunks = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>>();
    }

    @Override
    public boolean add(Type element) {
        if( chunks.containsKey(element.getIndexX())) {
            ConcurrentHashMap<Integer, Type> yMap = chunks.get(element.getIndexX());
            if(!yMap.containsKey(element.getIndexY())) {
                yMap.put(element.getIndexY(), element);
                elementSize++;
                return true;
            }else{
                return false;
            }
        }else {
            ConcurrentHashMap<Integer, Type> yChunkMap = new ConcurrentHashMap<Integer, Type>();
            yChunkMap.put(element.getIndexY(), element);
            chunks.put(element.getIndexX(), yChunkMap);
            elementSize++;
            return  true;
        }
    }

    @Override
    public boolean addAll(Collection<? extends Type> objects) {
        boolean changed = false;
        for(Type object : objects){
            if(!changed) {
                changed = add(object);
            }
        }
        return  changed;
    }

    @Override
    public void clear() {
        chunks.clear();
    }

    @Override
    public boolean contains(Object o) {
        if(o instanceof Indexable){
            Indexable indexable = (Indexable) o;
            return contains(indexable.getIndexX(), indexable.getIndexY());
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        for(Type elem : this) {
            if(!objects.contains(elem)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return chunks.isEmpty();
    }

    @NotNull
    @Override
    public Iterator<Type> iterator() {
        return new MatrixIterator<Type>(chunks.values().iterator());
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Indexable){
            Indexable indexable = (Indexable)o;
            return remove(indexable.getIndexX(), indexable.getIndexY());
        }else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;

        for (Object object : c){
            if(!changed){
                changed = remove(object);
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll( Collection<?> c) {

        boolean changed = false;

        for(Object o : this) {
            if(!c.contains(o)){
                remove(o);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public int size() {
        return elementSize;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray( T[] objects) {
        if(objects.length != size()){
            objects = (T[]) new Object[size()];
        }
        int index = 0;

        for (Type type : this){
            objects[index++] = (T) type;
        }
        return objects;
    }

    @Override
    public boolean remove(int indexX, int indexY) {
        ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(indexX);

        if(yChunkMap != null){
            yChunkMap.remove(indexY);
            if(yChunkMap.isEmpty()){
                chunks.remove(indexX);
            } else {
                return false;
            }
            elementSize --;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public MatrixList<Type> copy() {
        MatrixList<Type> copyList = new ConcurrentMatrixList<Type>();
        copyList.addAll(this);
        return copyList;
    }

    @Override
    public boolean contains(int indexX, int indexY) {
        return get(indexX, indexY) != null;
    }

    @Override
    public Type get(int indexX, int indexY) {
        ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(indexX);

        if(yChunkMap != null){
            Type element = yChunkMap.get(indexX);

            if(element != null) {
                return element;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void set(MatrixList<Type> matrixList) {
        this.elementSize = matrixList.size();
        clear();
        for(Type elem : matrixList){
            add(elem);
        }
    }
}
