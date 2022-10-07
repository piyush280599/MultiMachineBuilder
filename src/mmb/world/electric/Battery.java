/**
 * 
 */
package mmb.world.electric;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;

import mmb.beans.Saver;
import mmb.data.json.JsonTool;
import mmb.world.block.BlockEntity;
import mmb.world.electric.Electricity.SettablePressure;

/**
 * @author oskar
 *
 */
public class Battery implements SettablePressure, Saver<JsonNode>, Comparable<@Nonnull Battery>{
	public double maxPower;
	/**
	 * The energy capacity in coulombs
	 */
	public double capacity;
	/**
	 * Stored energy in coulombs
	 */
	public double amt;
	public double pressure = 0;
	public double pressureWt = 1;
	@Nullable private final BlockEntity blow;
	/**
	 * The voltage tier of this battery
	 */
	@Nonnull public VoltageTier voltage;

	/**
	 * Create battery with capacity and power limits
	 * @param maxPower max powr in coulombs per tick
	 * @param capacity power capacity in coulombs
	 * @param blow block which owns this battery. Used to blow up the block if the battery is overvoltaged
	 * @param voltage voltage tier
	 */
	public Battery(double maxPower, double capacity, @Nullable BlockEntity blow, VoltageTier voltage) {
		super();
		this.maxPower = maxPower;
		this.capacity = capacity;
		this.blow = blow;
		this.voltage = voltage;
	}

	/**
	 * Creates a copy of the battery
	 * @param bat battery to copy
	 */
	public Battery(Battery bat) {
		maxPower = bat.maxPower;
		capacity = bat.capacity;
		amt = bat.amt;
		blow = bat.blow;
		voltage = bat.voltage;
	}

	public double amount() {
		return amt;
	}
	
	private void blow() {
		blow.owner().place(blow.type().leaveBehind(), blow.posX(), blow.posY());
	}

	@Override
	public double insert(double amt, VoltageTier volt) {
		int cmp = volt.compareTo(voltage);
		if(cmp > 0) blow();
		if(cmp != 0) return 0;
		if(amt < 0) return 0;
		double max = Math.min(amt, Math.min(maxPower, remain()));
		if(pressure < 0) {
			pressure += amt;
			if(pressure > 0) pressure = 0;
		}
		double dpressure = amt - max;
		pressure += dpressure;
		this.amt += max;
		return max;
	}
	@Override
	public double extract(double amt, VoltageTier volt, Runnable blow) {
		int cmp = voltage.compareTo(volt);
		if(cmp > 0) blow.run();
		if(cmp != 0) return 0;
		if(volt != voltage) return 0;
		if(amt < 0) return 0;
		double max = Math.min(amt, Math.min(maxPower, this.amt));
		if(pressure > 0) {
			pressure -= amt;
			if(pressure < 0) pressure = 0;
		}
		double dpressure = amt - max;
		pressure -= dpressure;
		this.amt -= max;
		return max;
	}

	@Override
	public void load(@Nullable JsonNode data) {
		if(data == null || data.isEmpty()) return;
		maxPower = data.get(0).asDouble();
		capacity = data.get(1).asDouble();
		amt = data.get(2).asDouble();
		if(data.size() >= 5) {
			pressure = data.get(3).asDouble();
			pressureWt = data.get(4).asDouble();
		}
	}

	@Override
	public JsonNode save() {
		return JsonTool.newArrayNode().add(maxPower).add(capacity).add(amt).add(pressure).add(pressureWt);
	}
	
	@Override
	public VoltageTier voltage() {
		return voltage;
	}

	@Override
	public double pressure() {
		return pressure;
	}

	@Override
	public double pressureWeight() {
		return pressureWt;
	}

	/**
	 * @return remaining charge in coulombs
	 */
	public double remain() {
		return capacity - amt;
	}
	
	/**
	 * @param elec
	 */
	public void extractTo(Electricity elec) {
		double insert = elec.insert(Math.min(amt, maxPower), voltage);
		if(maxPower > amt) pressure -= amt*voltage.volts;
		amt -= insert;
	}
	
	/**
	 * @param elec
	 */
	public void takeFrom(Electricity elec) {
		double remain = remain();
		double insert = elec.extract(Math.min(remain, maxPower), voltage, blow::blow);
		if(maxPower > remain) pressure += remain*voltage.volts;
		amt += insert;
	}

	/**
	 * Sets all properties of this battery to those of other battery
	 * @param bat battery with new settings
	 */
	public void set(Battery bat) {
		maxPower = bat.maxPower;
		capacity = bat.capacity;
		amt = bat.amt;
		pressure = bat.pressure;
		pressureWt = bat.pressureWt;
	}

	@Override
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return stored enery in joules
	 */
	public double energy() {
		return amt*voltage.volts;
	}

	
	//Value
	@Override
	public int compareTo(Battery o) {
		int cmp = voltage.compareTo(o.voltage);
		if(cmp != 0) return cmp;
		cmp = Double.compare(amt, o.amt);
		if(cmp != 0) return cmp;
		cmp = Double.compare(capacity, o.capacity);
		if(cmp != 0) return cmp;
		cmp = Double.compare(maxPower, o.maxPower);
		if(cmp != 0) return cmp;
		cmp = Double.compare(pressure, o.pressure);
		if(cmp != 0) return cmp;
		return Double.compare(pressureWt, o.pressureWt);
	}
	@Override
	public String toString() {
		return "Battery [maxPower=" + maxPower + ", capacity=" + capacity + ", amt=" + amt + ", pressure=" + pressure
				+ ", pressureWt=" + pressureWt + ", voltage=" + voltage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(capacity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxPower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pressure);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pressureWt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + voltage.hashCode();
		return result;
	}
	@Override
	public boolean equals(@Nullable Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Battery other = (Battery) obj;
		if (Double.doubleToLongBits(amt) != Double.doubleToLongBits(other.amt))
			return false;
		if (Double.doubleToLongBits(capacity) != Double.doubleToLongBits(other.capacity))
			return false;
		if (Double.doubleToLongBits(maxPower) != Double.doubleToLongBits(other.maxPower))
			return false;
		if (Double.doubleToLongBits(pressure) != Double.doubleToLongBits(other.pressure))
			return false;
		if (Double.doubleToLongBits(pressureWt) != Double.doubleToLongBits(other.pressureWt))
			return false;
		if (voltage != other.voltage)
			return false;
		return true;
	}
}