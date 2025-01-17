/**
 * 
 */
package mmb.engine.java2d;

import java.awt.Color;
import java.util.Arrays;

import mmb.Nil;

/**
 * @author oskar constructor javadoc, getters and setters <br>
 * @author <a href="https://stackoverflow.com/users/1831987/vgr">VGR</a> original code
 * <br> from: <a href="https://stackoverflow.com/a/27464772/11654970">https://stackoverflow.com/a/27464772/11654970</a>
 */
public class ColorMapperRGBA extends ColorMapper {
	private final int[] from;
    private final int[] to;

    /**
     * @param from source color
     * @param to target color
     */
    public ColorMapperRGBA(Color from, Color to) {
        super(0, 4);
        this.from = new int[] {
            from.getRed(),
            from.getGreen(),
            from.getBlue(),
            from.getAlpha(),
        };
        this.to = new int[] {
            to.getRed(),
            to.getGreen(),
            to.getBlue(),
            to.getAlpha(),
        };
    }

    @Override
    public int[] lookupPixel(@SuppressWarnings("null") int[] src,
                             int @Nil [] dest) {
    	int[] dest0;
        if (dest == null) dest0 = new int[src.length];
        else dest0 = dest;

        int[] newColor = (Arrays.equals(src, from) ? to : src);
        System.arraycopy(newColor, 0, dest0, 0, newColor.length);

        return dest0;
    }

	/**
	 * @return source color, bound to the color mapper
	 */
	@Override
	public int[] getFrom() {
		return from;
	}

	/**
	 * @return target color, bound to the color mapper
	 */
	@Override
	public int[] getTo() {
		return to;
	}
    
	/**
	 * Sets the source color
	 * @param c color to be replaced
	 */
	@Override
	public void setFrom(Color c) {
		from[0] = c.getRed();
		from[1] = c.getGreen();
		from[2] = c.getBlue();
		from[3] = c.getAlpha();
	}
    
	/**
	 * Sets the destination color
	 * @param c replacement color
	 */
	@Override
	public void setTo(Color c) {
		to[0] = c.getRed();
		to[1] = c.getGreen();
		to[2] = c.getBlue();
		to[3] = c.getAlpha();
	}

	/**
	 * @return source color, in form of copied {@link Color} object from source array
	 */
	@Override
	public Color getFromColor() {
		return new Color(from[0], from[1], from[2], from[3]);
	}
	/**
	 * @return destination color, in form of copied {@link Color} object from destination array
	 */
	@Override
	public Color getToColor() {
		return new Color(to[0], to[1], to[2], to[3]);
	}
}

