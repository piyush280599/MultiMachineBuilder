/**
 * 
 */
package mmb.WORLD.chance;

import org.ainslec.picocog.PicoWriter;

import mmb.WORLD.inventory.io.InventoryWriter;
import mmb.WORLD.items.ItemEntry;
import mmb.WORLD.worlds.world.World;

/**
 * @author oskar
 *
 */
public class RandomOrElseChance implements Chance {
	
	public final double chance;
	public final Chance wrap;
	public final Chance other;

	/**
	 * Creates a random chance object
	 * @param chance the chance
	 * @param wrap the item which may be rewarded
	 * @param other the result when first chance is not selected
	 */
	public RandomOrElseChance(double chance, Chance wrap, Chance other) {
		super();
		this.chance = chance;
		this.wrap = wrap;
		this.other = other;
	}

	@Override
	public boolean drop(InventoryWriter inv, World map, int x, int y) {
		if(Math.random() < chance) return wrap.drop(inv, map, x, y);
		return other.drop(inv, map, x, y);
	}

	@Override
	public void produceResults(InventoryWriter tgt, int amount) {
		if(Math.random() < chance) wrap.produceResults(tgt, amount);
		else other.produceResults(tgt, amount);
	}

	@Override
	public void represent(PicoWriter out) {
		out.write((chance*100)+"% chance with else [");
		wrap.represent(out);
		out.write("] else [");
		other.represent(out);
		out.write("]");
	}

	
	@Override
	public boolean contains(ItemEntry item) {
		return wrap.contains(item) || other.contains(item);
	}

}