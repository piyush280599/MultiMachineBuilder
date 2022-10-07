/**
 * 
 */
package mmb.world.items;

import javax.annotation.Nonnull;

import mmb.beans.Intoxicating;
import mmb.menu.wtool.ToolAlcohol;
import mmb.menu.wtool.WindowTool;
import mmb.world.crafting.RecipeOutput;
import mmb.world.item.Item;
import mmb.world.recipes.CraftingGroups;

/**
 * @author oskar
 * This item represents an AlcoPod, a fictional alcoholic beverage available in pods
 *  +-----------------------------------------------------+
 *  |REAL-LIFE ALCOHOL CONSUMPTION IS HARMFUL             |
 *  |½ LITER OF BEER CONTAINS 25g OF ETHYL ALCOHOL        |
 *  |SALE OF LIQUOR TO PEOPLE BELOW 18y IS A CRIME        |
 *  |EVEN THAT AMOUNT HARMS HEALTH OF PREGNANT WOMEN      |
 *  |AND IS DANGEROUS TO DRIVERS                          |
 *  +-----------------------------------------------------+
 */
public class AlcoPod extends Item implements Intoxicating {
	
	private final double dose;
	@Nonnull private final RecipeOutput drop;
	public AlcoPod(double dose, RecipeOutput drop) {
		super();
		this.dose = dose;
		this.drop = drop;
	}
	private static final WindowTool TOOL = new ToolAlcohol();
	@Override
	public WindowTool getTool() {
		return TOOL;
	}
	@Override
	public double alcoholicity() {
		return dose;
	}
	@Override
	public RecipeOutput postdrink() {
		return drop;
	}
	@Override
	public void onregister() {
		CraftingGroups.alcohol.add(this, drop, dose);
	}
}