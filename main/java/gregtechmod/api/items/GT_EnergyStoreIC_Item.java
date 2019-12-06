package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_EnergyStoreIC_Item extends GT_EnergyStore_Item implements IElectricItem {
	public GT_EnergyStoreIC_Item(int aID, String aName, int aCharge, int aTransfer, int aTier, int aEmptyID, int aFullID) {
		super(aID, aName, aCharge, aTransfer, aTier, aEmptyID, aFullID);
	}

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return 0;
	}
}