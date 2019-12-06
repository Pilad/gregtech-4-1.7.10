package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneSignalizer extends GT_CoverBehavior {
	public GT_Cover_RedstoneSignalizer(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable = (aCoverVariable + 1) & 15;
		GT_Utility.sendChatToPlayer(aPlayer, "Signal = " + aCoverVariable);
		return aCoverVariable;
	}
	
	@Override
	public boolean letsRedstoneGoIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}

	@Override
	public boolean letsEnergyIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsEnergyOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsLiquidIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsLiquidOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsItemsIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsItemsOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public byte getRedstoneInput(byte aSide, byte aInputRedstone, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return (byte)(aCoverVariable&15);
	}
}