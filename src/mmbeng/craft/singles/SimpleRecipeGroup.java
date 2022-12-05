/**
 * 
 */
package mmbeng.craft.singles;

import javax.annotation.Nullable;

import mmbeng.craft.RecipeGroup;
import mmbeng.item.ItemEntry;

/**
 * @author oskar
 * A recipe group optimized for single-item recipes.
 * @param <T> type of recipes
 */
public interface SimpleRecipeGroup<T extends SimpleRecipe<?>> extends RecipeGroup<T> {
	/**
	 * Finds a recipe which meets criteria
	 * @param catalyst required catalyst (null if none)
	 * @param in required input item
	 * @return a matching recipe or null if not found
	 */
	public T findRecipe(@Nullable ItemEntry catalyst, ItemEntry in);
}