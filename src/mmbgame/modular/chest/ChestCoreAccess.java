/**
 * 
 */
package mmbgame.modular.chest;

import javax.swing.JPanel;

import mmb.menu.world.inv.InventoryController;
import mmbgame.modular.gui.SafeCloseable;

/**
 *
 * @author oskar
 *
 */
public class ChestCoreAccess extends JPanel implements SafeCloseable {
	private static final long serialVersionUID = -1462133036548585770L;

	public ChestCoreAccess(InventoryController invctrl, ChestCore<?> core) {
		
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}