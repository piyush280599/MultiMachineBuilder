/**
 * 
 */
package mmb.WORLD.items;

import mmb.WORLD.item.Item;
import mmb.WORLD.tool.WindowTool;

/**
 * An item with assigned tool
 * @author oskar
 */
public class TooledItem extends Item{
	/**
	 * Creates a tooled item
	 * @param tool window tool to use
	 */
	public TooledItem(WindowTool tool) {
		this.tool = tool;
	}
	private final WindowTool tool;
	@Override
	public WindowTool getTool() {
		return tool;
	}

}