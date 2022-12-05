/**
 * 
 */
package mmbeng.block;

import java.awt.Point;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mmb.beans.Positioned;
import mmbeng.CatchingEvent;
import mmbeng.debug.Debugger;
import mmbeng.rotate.Side;
import mmbeng.texture.BlockDrawer;
import mmbeng.worlds.MapProxy;
import mmbeng.worlds.world.World;
import mmbgame.event.BlockEntityDemolitionEvent;

/**
 * @author oskar
 * For machines, use {@link mmbeng.mbmachine.Machine}. For blocks, use {@link BlockEntityType}
 * Events: <ul>
 * 	<li>Motion: runs when block entity is moved</li>
 * 	<li>Demolition: runs when block entity is mined</li>
 * 	<li></li>
 * 	<li></li>
 * </ul>
 */
public abstract class BlockEntity implements SensitiveBlock{
	
	private static final Debugger bedebug = new Debugger("BLOCK ENTITIES");
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	//Positioning
	private int x, y;
	@Override
	public int posX() {
		return x;
	}
	@Override
	public void resetMap(@Nullable World map, int x, int y) {
		if(map != null && (!map.get(x, y).isSurface())) throw new IllegalStateException("The position ["+x+","+y+"] on target map is not surface");
		owner = map;
		this.x = x;
		this.y = y;
	}
	@Override
	public int posY() {
		return y;
	}
	@Nonnull public Point getPosition() {
		return new Point(x, y);
	}
	
	//Rendering
	public BlockDrawer getTexture() {
		return type().getTexture();
	}
		
	//Containment
	/**
	 * The map, in which the BlockEntity is located
	 */
	@Nullable private World owner;
	@Override @Nullable public World nowner() {
		return owner;
	}
	
	//Block entity methods
	/**
	 * Checks if BlockEntity is of given type
	 * @param type type to check
	 * @return is given BlockEntity of given type?
	 */
	public boolean typeof(BlockEntityType type) {
		return type() == type;
	}
	@Override
	public BlockEntity nasBlockEntity() {
		return this;
	}
	@Override
	public boolean isBlockEntity() {
		return true;
	}
	@Override
	public BlockEntity asBlockEntity() {
		return this;
	}	
	
	public BlockEntry getAtSide(Side s) {
		return owner().getAtSide(s, x, y);
	}
	
	/**
	 * Invoked on every tick
	 * @param map map proxy
	 */
	public void onTick(MapProxy map) {
		//Optional
	}
	
	//Block demolition event
	
	/**
	 * Clears listeners
	 */
	protected void clearListeners() {
		eventDemolition.clear();
	}
	private boolean underDemolition;
	@Override public final void onBreak(World blockMap, int x, int y) {
		if(underDemolition) return;
		underDemolition = true;
		BlockEntityDemolitionEvent event = new BlockEntityDemolitionEvent(x, y, this, blockMap);
		eventDemolition.trigger(event);
		eventRemoval.trigger(blockMap);
		underDemolition = false;
	}
	
	private boolean underShutdown;
	@Override public final void onShutdown(World map) {		
		if(underShutdown) return;
		underShutdown = true;
		eventRemoval.trigger(map);
		eventShutdown.trigger(map);
		underShutdown = false;
	}
	
	
	/** Breaks this block entity */
	public void blow() {
		owner().set(type().leaveBehind().createBlock(), posX(), posY());
	}

	//Events
	public final CatchingEvent<BlockEntityDemolitionEvent> eventDemolition = new CatchingEvent<>(bedebug, "Failed to run a block entity demolition event");
	public final CatchingEvent<World> eventShutdown = new CatchingEvent<>(bedebug, "Failed to run a block entity world shutdown event");
	public final CatchingEvent<World> eventRemoval = new CatchingEvent<>(bedebug, "Failed to run a block entity removal event");

	

	
}