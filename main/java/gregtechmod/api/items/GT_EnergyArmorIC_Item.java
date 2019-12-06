package gregtechmod.api.items;

import ic2.api.item.IElectricItem;
import ic2.api.item.IMetalArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_EnergyArmorIC_Item extends GT_EnergyArmor_Item implements IMetalArmor, IElectricItem {
	public GT_EnergyArmorIC_Item(int aID, String aName, int aCharge, int aTransfer, int aTier, int aDamageEnergyCost, int aSpecials, double aArmorAbsorbtionPercentage, boolean aChargeProvider, int aType, int aArmorIndex) {
		super(aID, aName, aCharge, aTransfer, aTier, aDamageEnergyCost, aSpecials, aArmorAbsorbtionPercentage, aChargeProvider, aType, aArmorIndex);
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