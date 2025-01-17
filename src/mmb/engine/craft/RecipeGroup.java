/**
 * 
 */
package mmb.engine.craft;

import java.util.Set;

import javax.swing.ListCellRenderer;

import mmb.NN;
import mmb.beans.Titled;
import mmb.engine.item.ItemEntry;
import mmb.menu.world.craft.RecipeView;
import monniasza.collects.Identifiable;

/**
 * Stores recipes.
 * @author oskar
 * @param <T> type of recipes
 * 
 */
public interface RecipeGroup<@NN T extends Recipe<?>> extends Identifiable<String>, Titled{
	/**@return a set with recipes*/
	@NN public Set<@NN T> recipes();
	/** @return a set with all supported items */
	@NN public Set<@NN ? extends ItemEntry> supportedItems();
	/** @return a component which displays recipes */
	public @NN RecipeView<T> createView();
	/**
	 * @return a cell renderer for compatible recipes
	 */
	public @NN ListCellRenderer<? super T> cellRenderer();
	/**
	 * @return does the recipe group support catalysts?
	 */
	public boolean isCatalyzed();
}
