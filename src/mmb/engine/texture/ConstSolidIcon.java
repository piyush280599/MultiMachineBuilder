/**
 * 
 */
package mmb.engine.texture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import mmb.Nil;

/**
 * @author oskar
 *
 */
public class ConstSolidIcon implements Icon {
	private final int w, h;
	public final Color c;

	public ConstSolidIcon(int w, int h, Color c) {
		super();
		this.w = w;
		this.h = h;
		this.c = c;
	}

	@Override
	public int getIconHeight() {
		return h;
	}

	@Override
	public int getIconWidth() {
		return w;
	}

	@Override
	public void paintIcon(@Nil Component cm, Graphics g, int x, int y) {
		g.setColor(c);
		g.fillRect(x, y, w, h);
	}

}
