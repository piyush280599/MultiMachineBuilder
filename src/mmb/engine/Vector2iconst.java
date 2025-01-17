/**
 * 
 */
package mmb.engine;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import mmb.Nil;

/**
 * @author oskar
 *
 */
public class Vector2iconst implements Vector2ic {
	public final int x;
	public final int y;
	public Vector2iconst(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Vector2iconst(Vector2ic vector) {
		x = vector.x();
		y = vector.y();
	}

	@Override
	public Vector2i absolute(Vector2i dest) {
		return dest.set(Math.abs(x), Math.abs(y));
	}

	@Override
	public Vector2i add(Vector2ic sum, Vector2i dest) {
		return add(sum.x(), sum.y(), dest);
	}

	@Override
	public Vector2i add(int X, int Y, Vector2i dest) {
		return dest.set(X+x, Y+y);
	}

	@Override
	public double distance(Vector2ic v) {
		return Math.sqrt(distanceSquared(v.x(), v.y()));
	}

	@Override
	public double distance(int x, int y) {
		return Math.sqrt(distanceSquared(x, y));
	}

	@Override
	public long distanceSquared(Vector2ic v) {
		return distanceSquared(v.x(), v.y());
	}

	@Override
	public long distanceSquared(int X, int Y) {
		return Vector2i.distanceSquared(x, y, X, Y);
	}

	@Override
	public boolean equals(int X, int Y) {
		return x == X && y == Y;
	}

	@Override
	public ByteBuffer get(ByteBuffer buffer) {
		buffer.putInt(x);
		buffer.putInt(y);
		return buffer;
	}

	@Override
	public IntBuffer get(IntBuffer buffer) {
		buffer.put(x);
		return buffer.put(y);
	}

	@Override
	public int get(int i) throws IllegalArgumentException {
		switch(i) {
		case 0:
			return x;
		case 1:
			return y;
		default:
			throw new IllegalArgumentException("Expected 0 or 1 as index, got "+i);
		}
	}

	@Override
	public ByteBuffer get(int index, ByteBuffer buffer) {
		buffer.putInt(index, x);
		return buffer.putInt(index+4, y);
	}

	@Override
	public IntBuffer get(int index, IntBuffer buffer) {
		buffer.put(index, x);
		return buffer.put(index+1, y);
	}

	@Override
	public Vector2ic getToAddress(long arg0) {
		new Vector2i(this).getToAddress(arg0);
		return this;
	}

	@Override
	public long gridDistance(Vector2ic v) {
		return gridDistance(v.x(), v.y());
	}

	@Override
	public long gridDistance(int xx, int yy) {
		return Long.sum(Math.abs(x-xx), Math.abs(y-yy));
	}

	@Override
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	@Override
	public long lengthSquared() {
		return (Math.multiplyFull(x, x))+(Math.multiplyFull(y, y));
	}

	@Override
	public Vector2i max(Vector2ic other, Vector2i dest) {
		dest.x = Math.max(other.x(), x);
		dest.y = Math.max(other.y(), y);
		return dest;
	}

	@Override
	public int maxComponent() {
		return Math.max(x, y);
	}

	@Override
	public Vector2i min(Vector2ic other, Vector2i dest) {
		dest.x = Math.min(other.x(), x);
		dest.y = Math.min(other.y(), y);
		return dest;
	}

	@Override
	public int minComponent() {
		return Math.min(x, y);
	}

	@Override
	public Vector2i mul(int n, Vector2i dest) {
		dest.x = x*n;
		dest.y = y*n;
		return dest;
	}

	@Override
	public Vector2i mul(Vector2ic mul, Vector2i dest) {
		return mul(mul.x(), mul.y(), dest);
	}

	@Override
	public Vector2i mul(int xx, int yy, Vector2i dest) {
		dest.x = x*xx;
		dest.y = y*yy;
		return dest;
	}

	@Override
	public Vector2i negate(Vector2i dest) {
		dest.x = -x;
		dest.y = -y;
		return dest;
	}

	@Override
	public Vector2i sub(Vector2ic sub, Vector2i dest) {
		return sub(sub.x(), sub.y(), dest);
	}

	@Override
	public Vector2i sub(int xx, int yy, Vector2i dest) {
		dest.x = x-xx;
		dest.y = y-yy;
		return dest;
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(@Nil Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector2ic))
			return false;
		Vector2ic other = (Vector2ic) obj;
		if (x != other.x())
			return false;
		return y == other.y();
	}
	@Override
	public Vector2i div(float scalar, Vector2i dest) {
		dest.x = (int) (x/scalar);
		dest.y = (int) (y/scalar);
		return dest;
	}
	@Override
	public Vector2i div(int scalar, Vector2i dest) {
		dest.x = x/scalar;
		dest.y = y/scalar;
		return dest;
	}

}
