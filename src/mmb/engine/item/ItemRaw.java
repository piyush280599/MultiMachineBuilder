/**
 * 
 */
package mmb.engine.item;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.UncheckedExecutionException;

import mmb.NN;
import mmb.engine.settings.GlobalSettings;

/**
 * A simple item wrapper for ItemEntities
 * @author oskar
 */
public class ItemRaw extends Item {
	@NN private static final String RAW = " "+GlobalSettings.$res("rawitem");
	@NN private static final LoadingCache<@NN ItemEntityType, @NN ItemRaw> memoizer
	= CacheBuilder.newBuilder().build(CacheLoader.from(ItemRaw::new));
	
	/**
	 * Obtains a raw item for the item entity type. If it does not exist, the item will be registered and set up
	 * @param iet item entity type
	 * @return a raw item
	 * @throws InternalError when constructor evaluation fails (this WILL be a bug)
	 */
	@NN public static ItemRaw make(ItemEntityType iet) {
		try {
			return memoizer.get(iet);
		} catch (ExecutionError|UncheckedExecutionException|ExecutionException e) {
			throw new InternalError("Evaluation failed for "+iet.id(), e);
		}
	}
	
	@NN public final ItemEntityType iet;
	private ItemRaw(ItemEntityType iet) {
		this.iet = iet;
		title(iet.title()+RAW);
		texture(iet.getTexture());
		describe(iet.description());
		volumed(iet.volume());
		finish("rawitem."+iet.id());
		
		Items.tagsItem(this, Items.btags.get(iet));
		Items.tagItem("raw", this);
	}
	@Override
	public String toString() {
		return "ItemRaw " + iet;
	}
}
