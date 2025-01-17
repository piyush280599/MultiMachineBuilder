/**
 * 
 */
package mmb.content.ppipe;

import mmb.NN;

/**
 * @author oskar
 * Combines a pipe and its entry directionality
 */
public class PipeTunnelEntry {
	/**
	 * A pipe to enter
	 */
	@NN public final PipeTunnel pipe;
	/**
	 * A directionality required to enter the pipe
	 */
	@NN public final Direction dir;
	/**
	 * @param pipe the pipe
	 * @param dir pipe's entry directionality
	 */
	public PipeTunnelEntry(PipeTunnel pipe, Direction dir) {
		super();
		this.pipe = pipe;
		this.dir = dir;
	}
}
