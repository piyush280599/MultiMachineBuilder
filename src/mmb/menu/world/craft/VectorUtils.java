/**
 * 
 */
package mmb.menu.world.craft;

import java.util.Vector;
import java.util.stream.Collectors;

import mmb.NN;
import mmb.engine.craft.RecipeOutput;
import mmb.engine.inv.ItemStack;

/**
 * @author oskar
 *
 */
public class VectorUtils {
	private VectorUtils() {}
	/**
	 * Converts an item list into an array
	 * @param output
	 * @return
	 */
	@NN public static Vector<ItemStack> list2vector(RecipeOutput output){
		return output
				.getContents()
				.object2IntEntrySet()
				.stream()
				.map(ent -> new ItemStack(ent.getKey(), ent.getIntValue()))
				.collect(Collectors.toCollection(() -> new Vector<ItemStack>()));
	}

	@NN public static ItemStack[] list2arr(RecipeOutput output){
		return output
				.getContents()
				.object2IntEntrySet()
				.stream()
				.map(entry -> new ItemStack(entry.getKey(), entry.getIntValue()))
				.toArray(n -> new ItemStack[n]);
	}

}
