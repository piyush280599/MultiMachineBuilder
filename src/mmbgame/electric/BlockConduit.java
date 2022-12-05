/**
 * 
 */
package mmbgame.electric;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mmbeng.block.BlockEntityData;
import mmbeng.block.BlockEntry;
import mmbeng.block.BlockType;
import mmbeng.debug.Debugger;
import mmbeng.rotate.Side;
import mmbeng.worlds.MapProxy;

/**
 * @author oskar
 *
 */
public class BlockConduit extends BlockEntityData {
	@Nonnull private Electricity u, d, l, r;
	@Nonnull private final BlockType type;
	@Nonnull private TransferHelper tf;
	private static final Debugger debug = new Debugger("CONDUIT");
	@Nonnull public final VoltageTier volt;
	/**
	 * @param type block type
	 * @param cap capacity in coulombs per tick
	 * @param volt maximum voltage
	 */
	public BlockConduit(BlockType type, double cap, VoltageTier volt) {
		tf = new TransferHelper(this, cap, volt);
		tf.pressureWt = cap*100;
		this.type = type;
		this.volt = volt;
		u = tf.proxy(Side.D);
		d = tf.proxy(Side.U);
		l = tf.proxy(Side.R);
		r = tf.proxy(Side.L);
	}

	@Override
	public BlockType type() {
		return type;
	}

	@Override
	public Electricity getElectricalConnection(Side s) {
		switch(s) {
		case U:
			return u;
		case D:
			return d;
		case L:
			return l;
		case R:
			return r;
		default:
			return null;
		}
	}

	@Override
	public void load(@Nullable JsonNode data) {
		//unused
	}

	@Override
	protected void save0(ObjectNode node) {
		//unused
	}
	
	/**
	 * @return maximum power in coulombs per tick
	 */
	public double condCapacity() {
		return tf.power;
	}
	
	public TransferHelper getTransfer() {
		return tf;
	}

	@Override
	public void onTick(MapProxy map) {
		Electricity.equatePPs(this, map, tf, 0.9);
	}

	@Override
	public BlockEntry blockCopy() {
		BlockConduit copy = new BlockConduit(type, tf.power, volt);
		copy.tf.set(tf);
		return copy;
	}
}