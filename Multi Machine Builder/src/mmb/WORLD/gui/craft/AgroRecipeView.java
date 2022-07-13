/**
 * 
 */
package mmb.WORLD.gui.craft;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import mmb.WORLD.crafting.recipes.AgroRecipeGroup.AgroProcessingRecipe;
import mmb.WORLD.inventory.ItemStack;
import mmb.WORLD.items.ItemEntry;

import javax.swing.JList;

/**
 * @author oskar
 *
 */
public class AgroRecipeView extends RecipeView<AgroProcessingRecipe>{
	private static final long serialVersionUID = -2864705123116802475L;
	private JLabel lblIncoming;
	private JLabel lblOutgoing;
	private JLabel lblIn;
	private JList<ItemStack> outList;
	
	public AgroRecipeView() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][]"));
		
		lblMachine = new JLabel(CRConstants.MACHINE);
		add(lblMachine, "cell 0 0,growx");
		
		lblEvery = new JLabel("New label");
		add(lblEvery, "cell 1 0,alignx left");
		
		lblIncoming = new JLabel(CRConstants.IN);
		add(lblIncoming, "cell 0 1,growx");
		
		lblOutgoing = new JLabel(CRConstants.OUT);
		add(lblOutgoing, "cell 1 1,growx");
		
		lblIn = new JLabel();
		add(lblIn, "cell 0 2,grow");
		
		outList = new JList<>();
		outList.setCellRenderer(ItemStackCellRenderer.instance);
		add(outList, "cell 1 2,growx,aligny center");
	}
	@Override public void set(AgroProcessingRecipe recipe) {
		lblMachine.setText(CRConstants.MACHINE+recipe.group.title());
		lblEvery.setText(CRConstants.EVERYTIME+(recipe.duration/50.0)+CRConstants.SECONDS);
		ItemEntry item = recipe.input;
		lblIn.setIcon(item.icon());
		lblIn.setText(item.title());
		outList.setListData(VectorUtils.list2arr(recipe.output));
	}
	
	private JLabel lblMachine;
	private JLabel lblEvery;
}
