/**
 * 
 */
package mmb.engine.block;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import mmb.Nil;

/**
 * @author oskar
 * A definition for a block that does not hold any data.
 */
public abstract class BlockEntityDataless extends BlockEntity implements Cloneable{
	@Override
	public void load(@Nil JsonNode data) {
		//unused
	}
	@Override
	public final JsonNode save() {
		return new TextNode(type().id());
	}
	@Override
	public final BlockEntity blockCopy() {
		return (BlockEntity) type().createBlock();
	}
}
