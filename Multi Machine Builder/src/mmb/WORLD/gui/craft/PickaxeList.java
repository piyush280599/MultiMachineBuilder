/**
 * 
 */
package mmb.WORLD.gui.craft;

import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import mmb.WORLD.crafting.recipes.PickaxeGroup;
import mmb.WORLD.crafting.recipes.PickaxeGroup.PickaxeInfo;
import java.awt.Component;
import java.awt.Dimension;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JList;

/**
 * @author oskar
 * @param <T> type of the recipe
 * @param <Tg> type of the recipe group
 *
 */
public class PickaxeList extends JScrollPane {
	private static final long serialVersionUID = 828762086772542204L;
	private final PickaxeView rv;
	public PickaxeList(PickaxeGroup group) {
		rv = new PickaxeView();
		JList<RecipeEntry> list = new JList<>();
		
			@SuppressWarnings("unchecked")
			RecipeEntry[] data = new RecipeEntry[group.recipes().size()];
			int i = 0;
			for(PickaxeInfo recipe: group.recipes()) {
				data[i] = new RecipeEntry(recipe);
				i++;
			}
			list.setListData(data);
			list.setCellRenderer(new CellRenderer());
		setViewportView(list);
	}
	static class RecipeEntry{
		@Nonnull public final PickaxeInfo recipe;
		public RecipeEntry(PickaxeInfo recipe2) {
			this.recipe = recipe2;
		}
	}
	class CellRenderer implements ListCellRenderer<RecipeEntry>{
		public CellRenderer() {
			setPreferredSize(new Dimension(600, 200));
		}
		@Override
		public Component getListCellRendererComponent(
				@SuppressWarnings("null") JList<? extends RecipeEntry> list,
						@Nullable RecipeEntry value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value != null) rv.set(value.recipe);
			return rv;
		} 
	}
}
