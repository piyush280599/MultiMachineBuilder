/**
 * 
 */
package mmb.WORLD.crafting.recipes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import mmb.WORLD.chance.Chance;
import mmb.WORLD.crafting.Craftings;
import mmb.WORLD.crafting.Recipe;
import mmb.WORLD.crafting.RecipeGroup;
import mmb.WORLD.crafting.RecipeOutput;
import mmb.WORLD.electric.VoltageTier;
import mmb.WORLD.gui.craft.LuckySimpleRecipeView;
import mmb.WORLD.inventory.Inventory;
import mmb.WORLD.items.ItemEntry;
import monniasza.collects.Collects;
import monniasza.collects.Identifiable;
import monniasza.collects.selfset.HashSelfSet;
import monniasza.collects.selfset.SelfSet;

/**
 * @author oskar
 *
 */
public class LuckySimpleProcessingRecipeGroup extends AbstractRecipeGroup<LuckySimpleProcessingRecipeGroup.ElectroLuckySimpleProcessingRecipe>{
	public LuckySimpleProcessingRecipeGroup(String id) {
		super(id);
	}
	/**
	 * @author oskar
	 * A recipe with one input item and output
	 */
	public class ElectroLuckySimpleProcessingRecipe implements Identifiable<ItemEntry>, Recipe<ElectroLuckySimpleProcessingRecipe>{
		private final Chance luck;
		public final double energy;
		@Nonnull public final VoltageTier voltage;
		@Nonnull public final ItemEntry input;
		@Nonnull public final RecipeOutput output;
		@Nonnull public final LuckySimpleProcessingRecipeGroup group;
		public ElectroLuckySimpleProcessingRecipe(double energy, VoltageTier voltage, ItemEntry input, RecipeOutput output, Chance luck) {
			super();
			this.energy = energy;
			this.voltage = voltage;
			this.input = input;
			this.output = output;
			this.luck = luck;
			group = LuckySimpleProcessingRecipeGroup.this;
		}
		@Override
		public ItemEntry id() {
			return input;
		}
		@Override
		public int maxCraftable(Inventory src, int amount) {
			return Inventory.howManyTimesThisContainsThat(src, input);
		}
		@Override
		public int craft(Inventory src, Inventory tgt, int amount) {
			return Craftings.transact(input, output, tgt, src, amount);
		}
		@Override
		public RecipeOutput output() {
			return output;
		}
		@Override
		public RecipeOutput inputs() {
			return input;
		}
		@Override
		public ItemEntry catalyst() {
			return null;
		}
		@Override
		public RecipeGroup group() {
			return group;
		}
		@Override
		public ElectroLuckySimpleProcessingRecipe that() {
			return this;
		}
		@Override
		public double energy() {
			return energy;
		}
		@Override
		public VoltageTier voltTier() {
			return voltage;
		}
		@Override
		public Chance luck() {
			return luck;
		}
	}
	private final SelfSet<ItemEntry, ElectroLuckySimpleProcessingRecipe> _recipes = new HashSelfSet<>();
	public final SelfSet<ItemEntry, ElectroLuckySimpleProcessingRecipe> recipes = Collects.unmodifiableSelfSet(_recipes);
	@Override
	public Set<? extends ItemEntry> supportedItems() {
		return supported0;
	}
	private final Set<ItemEntry> supported = new HashSet<>();
	private final Set<ItemEntry> supported0 = Collections.unmodifiableSet(supported);
	/**
	 * Adds a recipes to this recipe group
	 * @param in input item
	 * @param out output
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 */
	public ElectroLuckySimpleProcessingRecipe add(ItemEntry in, RecipeOutput out, VoltageTier voltage, double energy, Chance luck) {
		ElectroLuckySimpleProcessingRecipe recipe = new ElectroLuckySimpleProcessingRecipe(energy, voltage, in, out, luck);
		_recipes.add(recipe);
		GlobalRecipeRegistrar.addRecipe(recipe);
		supported.add(in);
		return recipe;
	}
	/**
	 * @param in input item
	 * @param out output item
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 */
	public ElectroLuckySimpleProcessingRecipe add(ItemEntry in, ItemEntry out, int amount, VoltageTier voltage, double energy, Chance luck) {
		return add(in, out.stack(amount), voltage, energy, luck);
	}
	/**
	 * Adds a recipes to this recipe group
	 * @param in input item
	 * @param out output
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 */
	public ElectroLuckySimpleProcessingRecipe add(ItemEntry in, RecipeOutput out, VoltageTier voltage, double energy) {
		return add(in, out, voltage, energy, Chance.NONE);
	}
	/**
	 * @param in input item
	 * @param out output item
	 * @param amount amount of output item
	 * @param voltage voltage tier required by this recipe
	 * @param energy energy consumed by this recipe
	 */
	public ElectroLuckySimpleProcessingRecipe add(ItemEntry in, ItemEntry out, int amount, VoltageTier voltage, double energy) {
		return add(in, out.stack(amount), voltage, energy);
	}
	@Override
	public SelfSet<ItemEntry, ElectroLuckySimpleProcessingRecipe> recipes() {
		return recipes;
	}
	@Override
	public LuckySimpleRecipeView createView() {
		return new LuckySimpleRecipeView();
	}
	@Override
	public boolean isCatalyzed() {
		return false;
	}

}
