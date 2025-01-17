/**
 * 
 */
package mmb.content.ppipe;

import mmb.NN;

/**
 * @author oskar
 *
 */
public enum Direction {
	FWD(0, 1) {
		@Override
		public Direction reverse() {
			return BWD;
		}
	},BWD(1, -1) {
		@Override
		public Direction reverse() {
			return FWD;
		}
	};
	
	/**
	 * The percentage of offset at entry
	 */
	public final double entry;
	/**
	 * The motion mutiplier
	 */
	public final double mul;
	Direction(double e, double m){
		entry = e;
		mul = m;
	}
	
	/**
	 * @return this direction reversed
	 */
	@NN public abstract Direction reverse();
}
