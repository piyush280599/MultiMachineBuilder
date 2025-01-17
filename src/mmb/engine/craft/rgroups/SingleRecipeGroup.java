/**
 * 
 */
package mmb.engine.craft.rgroups;

import mmb.NN;
import mmb.Nil;
import mmb.content.electric.VoltageTier;
import mmb.engine.chance.Chance;
import mmb.engine.craft.RecipeOutput;
import mmb.engine.craft.singles.SimpleRecipe;
import mmb.engine.craft.singles.SimpleRecipeGroup;
import mmb.engine.item.ItemEntry;
import mmb.menu.world.craft.SingleRecipeView;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 *
 */
public class SingleRecipeGroup extends AbstractRecipeGroupUncatalyzed<@NN ItemEntry, @NN SingleRecipeGroup.SingleRecipe>
implements SimpleRecipeGroup<SingleRecipeGroup.SingleRecipe>{
	/**
	 * Creates a list of single-item recipes
	 * @param id group ID
	 */
	public SingleRecipeGroup(String id) {
		super(id, SingleRecipe.class);
	}
	/**
	 * A recipe with a single input item
	 * @author oskar
	 */
	public class SingleRecipe extends BaseElectricRecipe<SingleRecipe> implements Identifiable<ItemEntry>, SimpleRecipe<SingleRecipe>{
		/** The input item */
		@NN public final ItemEntry input;
		
		/**
		 * Creates a single recipe
		 * @param energy energy required for completion in joules
		 * @param voltage voltage tier required for this recipe
		 * @param input the input item
		 * @param output deterministic output of this recipe
		 * @param luck randomized output of this recipe
		 */
		public SingleRecipe(double energy, VoltageTier voltage, ItemEntry input, RecipeOutput output, Chance luck) {
			super(energy, voltage, output, luck);
			this.input = input;
		}
		@Override
		public ItemEntry id() {
			return input;
		}
		@Override
		public ItemEntry inputs() {
			return input;
		}
		@Override
		public ItemEntry catalyst() {
			return null;
		}
		@Override
		public SingleRecipeGroup group() {
			return SingleRecipeGroup.this;
		}
		@Override
		public SingleRecipe that() {
			return this;
		}
	}
	
	//Recipe addition
	/**
	 * Adds a recipes to this recipe group
	 * @param in input item
	 * @param out output
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @param luck random chanced items
	 * @return new recipe
	 */
	public SingleRecipe add(ItemEntry in, RecipeOutput out, VoltageTier voltage, double energy, Chance luck) {
		@NN SingleRecipe recipe = new SingleRecipe(energy, voltage, in, out, luck);
		insert(recipe);
		return recipe;
	}
	/**
	 * @param in input item
	 * @param out output item
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @param luck random chanced items
	 * @return new recipe
	 */
	public SingleRecipe add(ItemEntry in, ItemEntry out, int amount, VoltageTier voltage, double energy, Chance luck) {
		return add(in, out.stack(amount), voltage, energy, luck);
	}
	/**
	 * Adds a recipes to this recipe group
	 * @param in input item
	 * @param out output
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @return new recipe
	 */
	public SingleRecipe add(ItemEntry in, RecipeOutput out, VoltageTier voltage, double energy) {
		return add(in, out, voltage, energy, Chance.NONE);
	}
	/**
	 * @param in input item
	 * @param out output item
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @return new recipe
	 */
	public SingleRecipe add(ItemEntry in, ItemEntry out, int amount, VoltageTier voltage, double energy) {
		return add(in, out.stack(amount), voltage, energy);
	}
	
	//Others
	@Override
	public SingleRecipeView createView() {
		return new SingleRecipeView();
	}
	@Override
	public SingleRecipe findRecipe(@Nil ItemEntry catalyst, ItemEntry in) {
		return recipes().get(in);
	}
}
