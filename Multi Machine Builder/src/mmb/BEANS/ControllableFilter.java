/**
 * 
 */
package mmb.BEANS;

import javax.annotation.Nullable;

import mmb.WORLD.gui.machine.FilterGUI;
import mmb.WORLD.inventory.storage.SingleItemInventory;

/**
 * @author oskar
 *
 */
public interface ControllableFilter extends DestroyTab{
	/**
	 * 
	 * @return
	 */
	public SingleItemInventory[] getFilters();
	/**
	 * Gets the list of titles.
	 * Returns null if filters are not named
	 * If result is non-null, the number of titles must match number of filters,
	 * otherwise the {@link FilterGUI} will fail with {@link IllegalStateException);
	 * @return
	 */
	@Nullable public String[] getTitles();
	
}
