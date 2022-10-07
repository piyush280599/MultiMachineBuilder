/**
 * 
 */
package mmb.data.variables;

/**
 * @author oskar
 *
 */
public class DataValue<T> implements Variable<T> {
	private T value;
	/**
	 * 
	 */
	public DataValue(T data) {
		value = data;
	}
	@Override
	public T get() {
		return value;
	}
	@Override
	public void set(T newValue) {
		value = newValue;
	}

}