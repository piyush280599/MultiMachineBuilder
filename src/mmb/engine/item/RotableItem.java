/**
 * 
 */
package mmb.engine.item;

import java.awt.image.BufferedImage;

import mmb.engine.rotate.RotatedImageGroup;

/**
 * @author oskar
 *
 */
public class RotableItem extends Item implements RotableItemEntry {
	private RotatedImageGroup rig;
	@Override
	public void setTexture(BufferedImage texture) {
		rig = RotatedImageGroup.create(texture);
		super.setTexture(texture);
	}
	public RotableItem setTexture(RotatedImageGroup texture) {
		rig = texture;
		super.setTexture(texture.U);
		return this;
	}
	/** @return the rotated texture */
	@Override
	public RotatedImageGroup rig() {
		return rig;
	}
}
