/**
 * 
 */
package mmb.COLLECTIONS;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import mmb.Identifiable;

/**
 * @author oskar
 * @param <K> the key type
 * @param <V> the value type
 *
 */
public class HashSelfSet<K, V extends Identifiable<K>> implements SelfSet<K, V> {

	private final HashMap<K, V> map = new HashMap<>();
	private final Collection<V> vals = map.values();
	private final Set<K> keys = map.keySet();

	@Override
	public boolean add(V e) {
		return map.put(e.identifier(), e) == null;
	}

	@Override
	public boolean addAll(Collection<? extends V> c) {
		for(V value: c) {
			map.put(value.identifier(), value);
		}
		return true;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o) || map.containsValue(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return keys.containsAll(c) || vals.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Iterator<V> iterator() {
		return vals.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return keys.remove(o) || vals.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return keys.removeAll(c) || vals.removeAll(c);
	}

	private class Bool{boolean val = false;}
	@Override
	public boolean retainAll(Collection<?> c) {
		Bool changed = new Bool();
		map.entrySet().removeIf(e ->{
			if(c.contains(e.getKey())) return false;
			if(c.contains(e.getValue())) return false;
			changed.val = true;
			return changed.val;
		});
		return true;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Object[] toArray() {
		return map.values().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return map.values().toArray(a);
	}

	@Override
	public Set<K> keys() {
		return new Set<K>(){

			@Override
			public boolean add(K e) {
				throw new UnsupportedOperationException("add() by key not supported");
			}

			@Override
			public boolean addAll(Collection<? extends K> c) {
				throw new UnsupportedOperationException("addAll() by key not supported");
			}

			@Override
			public void clear() {
				map.clear();
			}

			@Override
			public boolean contains(Object o) {
				return map.containsKey(o);
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return keys.containsAll(c);
			}

			@Override
			public boolean isEmpty() {
				return map.isEmpty();
			}

			@Override
			public Iterator<K> iterator() {
				return keys.iterator();
			}

			@Override
			public boolean remove(Object o) {
				return keys.remove(o);
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return keys.removeAll(c);
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return keys.retainAll(c);
			}

			@Override
			public int size() {
				return map.size();
			}

			@Override
			public Object[] toArray() {
				return keys.toArray();
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return keys.toArray(a);
			}
			
		};
	}

	private final HashSelfSet<K, V> that = this;
	@Override
	public Set<V> values() {
		return new Set<V>(){

			@Override
			public boolean add(V e) {
				map.put(e.identifier(), e);
				return true;
			}

			@Override
			public boolean addAll(Collection<? extends V> c) {
				Bool changed = new Bool();
				for(V value: c) {
					if(that.add(value)) changed.val = true;
				}
				return changed.val;
			}

			@Override
			public void clear() {
				that.clear();
			}

			@Override
			public boolean contains(Object o) {
				return that.contains(o);
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return that.containsAll(c);
			}

			@Override
			public boolean isEmpty() {
				return map.isEmpty();
			}

			@Override
			public Iterator<V> iterator() {
				return vals.iterator();
			}

			@Override
			public boolean remove(Object o) {
				return vals.remove(o);
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return vals.removeAll(c);
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return vals.retainAll(c);
			}

			@Override
			public int size() {
				return map.size();
			}

			@Override
			public Object[] toArray() {
				return vals.toArray();
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return vals.toArray(a);
			}
			
		};
	}

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public V getOrDefault(K key, V defalt) {
		return map.getOrDefault(key, defalt);
	}

}