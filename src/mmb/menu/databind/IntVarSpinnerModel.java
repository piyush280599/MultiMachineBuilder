/**
 * 
 */
package mmb.menu.databind;

import java.util.function.IntConsumer;

import javax.swing.SpinnerNumberModel;

import mmb.Nil;
import mmb.content.modular.gui.SafeCloseable;
import mmb.data.variables.ListenableInt;

/**
 *
 * @author oskar
 *
 */
public class IntVarSpinnerModel extends SpinnerNumberModel implements SafeCloseable {
	private static final long serialVersionUID = 2575894355881350617L;
	private final transient ListenableInt variable;
	private final transient IntConsumer handler = this::handle;
	
	/**
	 * Creates a spinner model for an int variable
	 * @param variable
	 */
	public IntVarSpinnerModel(ListenableInt variable) {
		this(variable, 0, null, 1);
	}
	/**
	 * Creates a spinner model for an int variable
	 * @param variable
	 * @param minimum 
	 * @param maximum 
	 * @param stepSize 
	 */
	public IntVarSpinnerModel(ListenableInt variable, @Nil Integer minimum, @Nil Integer maximum, int stepSize) {
		super(variable.get(), minimum, maximum, Integer.valueOf(stepSize));
		this.variable = variable;
		variable.add(handler);
	}


	@Override
	public Integer getValue() {
		return variable.getInt();
	}

	@Override
	public void setValue(@SuppressWarnings("null") Object value) {
		int intvalue = ((Number)value).intValue();
		if(variable.getInt() == intvalue) return;
		variable.set(intvalue);
		super.setValue(value);
	}

	@Override
	public void close() {
		variable.remove(handler);
	}
	private void handle(int value) {
		if(variable.getInt() == value) return;
		setValue(value);
	}

}
