/**
 * 
 */
package mmb.world.items.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.world.crafting.ItemLists;
import mmb.world.crafting.RecipeOutput;
import mmb.world.crafting.SimpleItemList;
import mmb.world.item.ItemEntity;
import mmb.world.items.ContentsItems;
import mmb.world.items.ItemEntry;

/**
 * @author oskar
 * An item for Bill Of Materials, which contains a list of items.
 */
public final class ItemBOM extends ItemEntity {

	/**
	 * Creates a Bill of Materials
	 */
	public ItemBOM() {
		super(ContentsItems.BOM);
	}
	
	public ItemBOM(SimpleItemList items) {
		super(ContentsItems.BOM);
		this.items = items;
	}
	public ItemBOM(RecipeOutput items) {
		super(ContentsItems.BOM);
		if(items instanceof SimpleItemList) 
			this.items = (SimpleItemList) items;
		else
			this.items = new SimpleItemList(items);
	}
	
	@Override
	public ItemEntry itemClone() {
		return this;
	}

	@Nonnull private SimpleItemList items = SimpleItemList.EMPTY;
	
	/**
	 * @return the item list for this Bill Of Materials. The returned item list is immutable
	 */
	@Nonnull public RecipeOutput contents() {
		return items;
	}
	
	@Override
	public void load(@Nullable JsonNode data) {
		if(data == null) return;
		SimpleItemList list0 = ItemLists.read(data);
		if(list0 == null) list0 = SimpleItemList.EMPTY;
		items = list0;	
	}
	@Override
	public JsonNode save() {
		return ItemLists.save(items);
	}

	@Override
	protected int hash0() {
		return items.hashCode();
	}

	@Override
	protected boolean equal0(ItemEntity other) {
		if(other instanceof ItemBOM)
			return ((ItemBOM) other).contents().equals(items);
		return false;
	}

	

}