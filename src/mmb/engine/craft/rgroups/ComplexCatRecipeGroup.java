/**
 * 
 */
package mmb.engine.craft.rgroups;

import io.vavr.Tuple2;
import mmb.NN;
import mmb.Nil;
import mmb.content.electric.VoltageTier;
import mmb.engine.chance.Chance;
import mmb.engine.craft.RecipeOutput;
import mmb.engine.item.ItemEntry;
import mmb.menu.world.craft.ComplexCatalyzedRecipeView;
import mmb.menu.world.craft.RecipeView;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 * A group of recipes with a complex input and a catalyst
 */
public class ComplexCatRecipeGroup extends
AbstractRecipeGroupCatalyzed<@NN RecipeOutput, @NN ComplexCatRecipeGroup.ComplexCatalyzedRecipe>{
	/**
	 * The minimum amount of ingredients
	 */
	public final int minIngredients;
	/**
	 * Creates a list of complex catalyzed recipes
	 * @param id the title of this recipe group
	 * @param minIngredients minimum amount of ingredients, must be >= 1
	 */
	public ComplexCatRecipeGroup(String id, int minIngredients) {
		super(id, ComplexCatalyzedRecipe.class);
		if(minIngredients < 1) throw new IllegalArgumentException("The minimum ingredient count must be >=1, got "+minIngredients);
		this.minIngredients = minIngredients;
	}
	
	/**
	 * @author oskar
	 * A recipe with a complex input and a catalyst
	 */
	public class ComplexCatalyzedRecipe extends BaseElectricRecipe<ComplexCatalyzedRecipe> implements Identifiable<@NN Tuple2<@NN RecipeOutput, @NN ItemEntry>>{
		/** The input item list */
		@NN public final RecipeOutput input;
		/** Required catalyst */
		@Nil public final ItemEntry catalyst;
		/** Item tuple */
		@NN private final Tuple2<@NN RecipeOutput, @NN ItemEntry> id;
		
		/**
		 * Creates a complex catalyzed recipe
		 * @param energy energy required for completion in joules
		 * @param voltage voltage tier required for this recipe
		 * @param in the input item list
		 * @param output deterministic output of this recipe
		 * @param catalyst required catalyst
		 * @param luck randomized output of this recipe
		 */
		public ComplexCatalyzedRecipe(double energy, VoltageTier voltage, RecipeOutput in, RecipeOutput output, @Nil ItemEntry catalyst, Chance luck) {
			super(energy, voltage, output, luck);
			this.input = in;
			this.id = new Tuple2<>(input, catalyst);
			this.catalyst = catalyst;
		}
		@Override
		public @NN Tuple2<@NN RecipeOutput, @NN ItemEntry> id() {
			return id;
		}
		@Override
		public RecipeOutput inputs() {
			return input;
		}
		@Override
		public ItemEntry catalyst() {
			return catalyst;
		}
		@Override
		public ComplexCatRecipeGroup group() {
			return ComplexCatRecipeGroup.this;
		}
		@Override
		public ComplexCatalyzedRecipe that() {
			return this;
		}
	}	
	//Recipe addition
	/**
	 * Adds a recipe to this recipe group
	 * @param in input item
	 * @param out output
	 * @param catalyst catalyst required to run the recipe
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @param luck random chanced items
	 * @return a new recipe
	 */
	public ComplexCatalyzedRecipe add(RecipeOutput in, RecipeOutput out, @Nil ItemEntry catalyst, VoltageTier voltage, double energy, Chance luck) {
		if(in.getContents().size() < minIngredients) throw new IllegalArgumentException("The recipe must have at least "+minIngredients+" inputs");
		if(in.getContents().size() == 0) throw new IllegalArgumentException("The recipe must have at least 1 input");
		ComplexCatalyzedRecipe recipe = new ComplexCatalyzedRecipe(energy, voltage, in, out, catalyst, luck);
		insert(recipe);
		return recipe;
	}
	/**
	 * Adds a recipe to this recipe group
	 * @param in input item
	 * @param out output item
	 * @param catalyst catalyst required to run the recipe
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @param luck random chanced items
	 * @return a new recipe
	 */
	public ComplexCatalyzedRecipe add(RecipeOutput in, ItemEntry out, @Nil ItemEntry catalyst, int amount, VoltageTier voltage, double energy, Chance luck) {
		return add(in, out.stack(amount), catalyst, voltage, energy, luck);
	}
	/**
	 * Adds a recipe to this recipe group
	 * @param in input item
	 * @param out output
	 * @param catalyst catalyst required to run the recipe
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @return a new recipe
	 */
	public ComplexCatalyzedRecipe add(RecipeOutput in, RecipeOutput out, @Nil ItemEntry catalyst, VoltageTier voltage, double energy) {
		return add(in, out, catalyst, voltage, energy, Chance.NONE);
	}
	/**
	 * Adds a recipe to this recipe group
	 * @param in input item
	 * @param out output item
	 * @param catalyst catalyst required to run the recipe
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 * @return a new recipe
	 */
	public ComplexCatalyzedRecipe add(RecipeOutput in, ItemEntry out, @Nil ItemEntry catalyst, int amount, VoltageTier voltage, double energy) {
		return add(in, out.stack(amount), catalyst, voltage, energy, Chance.NONE);
	}
	
	//Others
	@Override
	public RecipeView<ComplexCatalyzedRecipe> createView() {
		return new ComplexCatalyzedRecipeView();
	}
}
