/**
 * 
 */
package mmb.engine.craft;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import mmb.NN;
import mmb.content.electric.VoltageTier;
import mmb.engine.item.ItemEntry;
import monniasza.collects.indexar.Database;
import monniasza.collects.indexar.Index;
import monniasza.collects.indexar.ManyToManyIndex;
import monniasza.collects.indexar.OneToManyIndex;

/**
 * @author oskar
 * An utility class that keeps track of all known recipes.
 */
public class GlobalRecipeRegistrar {
	private GlobalRecipeRegistrar() {}
	
	/** A full set of recipe groups */
	private static final Set<RecipeGroup<?>> rgroups0 = new HashSet<>();
	public static final Set<RecipeGroup<?>> rgroups = Collections.unmodifiableSet(rgroups0);
	/**
	 * Adds a recipe. Should be used by recipe groups
	 * @param recipe
	 */
	public static void addRecipe(Recipe<?> recipe) {
		recipes.add(recipe);
	}
	/**
	 * Adds a recipe group. Should be used by recipe groups
	 * @param recipe
	 */
	public static void addRecipeGroup(RecipeGroup<?> recipe) {
		rgroups0.add(recipe);
	}
	
	//Indexing
	@SuppressWarnings("rawtypes")
	/** Recipes grouped by input items */
	@NN public static final ManyToManyIndex<@NN Recipe, ItemEntry> inputs   = new ManyToManyIndex<>(recipe -> recipe.inputs().items());
	/** Recipes grouped by output items */
	@SuppressWarnings("rawtypes")
	@NN public static final ManyToManyIndex<@NN Recipe, ItemEntry> output   = new ManyToManyIndex<>(recipe -> recipe.output().items());
	/** Recipes grouped by the catalyst */
	@SuppressWarnings("rawtypes")
	@NN public static final ManyToManyIndex<@NN Recipe, ItemEntry> chance   = new ManyToManyIndex<>(recipe -> recipe.luck().items());
	/** Recipes grouped by randomized outputs */
	@SuppressWarnings("rawtypes")
	@NN public static final  OneToManyIndex<@NN Recipe, ItemEntry> catalyst = new OneToManyIndex<>(Recipe::catalyst);
	/** Recipes grouped by voltage tiers, exact*/
	@SuppressWarnings("rawtypes")
	@NN public static final  OneToManyIndex<@NN Recipe, VoltageTier> volt   = new OneToManyIndex<>(Recipe::voltTier);
	@SuppressWarnings("rawtypes")
	@NN public static final Database<@NN Recipe> recipes = new Database<>(Recipe.class)
			.addIndex(inputs)
			.addIndex(output)
			.addIndex(chance)
			.addIndex(catalyst)
			.addIndex(volt);
	@NN public static final Set<ItemEntry> chanceable = chance.multimap().keySet();
	@NN public static final Set<ItemEntry> craftable = output.multimap().keySet();
	@NN public static final Set<ItemEntry> consumable = inputs.multimap().keySet();
	@NN public static final Set<ItemEntry> obtainable = Sets.union(craftable, chanceable);
}