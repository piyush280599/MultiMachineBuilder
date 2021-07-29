/**
 * 
 */
package mmb.WORLD.blocks.machine;

import com.fasterxml.jackson.databind.node.ObjectNode;

import mmb.BEANS.BlockActivateListener;
import mmb.WORLD.RotatedImageGroup;
import mmb.WORLD.block.BlockType;
import mmb.WORLD.block.SkeletalBlockEntityRotary;
import mmb.WORLD.blocks.ContentsBlocks;
import mmb.WORLD.gui.window.WorldWindow;
import mmb.WORLD.worlds.world.BlockMap;
import mmb.WORLD.worlds.world.World;

/**
 * @author oskar
 *
 */
public class CycleAssembler extends SkeletalBlockEntityRotary implements BlockActivateListener {

	public CycleAssembler(int x, int y, BlockMap owner2) {
		super(x, y, owner2);
	}

	@Override
	public BlockType type() {
		return ContentsBlocks.CYCLEASSEMBLY;
	}
	
	private static final RotatedImageGroup TEXTURE = RotatedImageGroup.create("machine/cyclic assembler.png");

	@Override
	public RotatedImageGroup getImage() {
		return TEXTURE;
	}

	@Override
	public void click(int blockX, int blockY, World map, WorldWindow window) {
		if(window == null) return;
	}

	@Override
	protected void save1(ObjectNode node) {
		// TODO Auto-generated method stub
		super.save1(node);
	}

	@Override
	protected void load1(ObjectNode node) {
		// TODO Auto-generated method stub
		super.load1(node);
	}

}
