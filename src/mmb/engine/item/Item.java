/**
 * 
 */
package mmb.engine.item;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.engine.debug.Debugger;
import mmb.engine.texture.BlockDrawer;
import monniasza.collects.Identifiable;

/**
 * @author oskar
 *
 */
public class Item extends ItemBase implements ItemEntry {
	private static final Debugger debug = new Debugger("ITEMS");
	/**
	 * Get the hash code, which is always the same as ID's hash code.
	 * @return item's hash code.
	 * @see java.lang.String#hashCode()
	 */
	@Override
	public final int hashCode() {
		return id().hashCode();
	}
	@Override
	public final boolean equals(@Nullable Object other) {
		if(this == other) return true;
		if(other == null) return false;
		if(!(other instanceof Identifiable)) return false;
		Object id1 = ((Identifiable<?>)other).id();
		if(id() == id1) return true;
		if(id1 == null) return false;
		return id1.equals(id());
	}

	@Override
	public ItemEntry create() {
		return this;
	}
	@Override
	public ItemEntry itemClone() {
		return this;
	}
	@Override
	public ItemType type() {
		return this;
	}
	@Override
	public void load(@Nullable JsonNode node) {
		debug.printl("Attempting to load a non-data item");
	}

	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture path to texture, starting from `/textures`
	 * @return this
	 */
	@Override
	@NonNull public Item texture(String texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	@NonNull public Item texture(BufferedImage texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture color
	 * @return this
	 */
	@Override
	@NonNull public Item texture(Color texture) {
		setTexture(BlockDrawer.ofColor(texture));
		return this;
	}
	/**
	 * Sets texture. This is a convenience chainable method
	 * @param texture texture
	 * @return this
	 */
	@Override
	@NonNull public Item texture(BlockDrawer texture) {
		setTexture(texture);
		return this;
	}
	/**
	 * Sets title.This is a convenience chainable method
	 * @param title title
	 * @return this
	 */
	@Override
	@NonNull public Item title(String title) {
		setTitle(title);
		return this;
	}
	/**
	 * Sets description.This is a convenience chainable method
	 * @param description description
	 * @return this
	 */
	@Override
	@NonNull public Item describe(String description) {
		setDescription(description);
		return this;
	}
	/**
	 * Registers this item. This is a convenience chainable method
	 * @param id block id
	 * @return this
	 */
	@Override
	@NonNull public Item finish(String id) {
		register(id);
		return this;
	}
	/**
	 * Sets volume. This is a convenience chainable method
	 * @param volume volume
	 * @return this
	 */
	@Override
	@NonNull public Item volumed(double volume) {
		setVolume(volume);
		return this;
	}
	
	public int compare0(ItemEntry o) {
		return 0;
	}
}
