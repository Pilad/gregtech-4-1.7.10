package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_Cover_EnergyOnly extends GT_CoverBehavior {
	
	public GT_Cover_EnergyOnly(ItemStack aCover) {
		super(aCover);
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable=(aCoverVariable+1)%3;
		if (aCoverVariable == 0) GT_Utility.sendChatToPlayer(aPlayer, "Allow");
		if (aCoverVariable == 1) GT_Utility.sendChatToPlayer(aPlayer, "Allow (conditional)");
		if (aCoverVariable == 2) GT_Utility.sendChatToPlayer(aPlayer, "Disallow (conditional)");
		return aCoverVariable;
	}
	
	@Override
	public float getBlastProofLevel(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 20.0F;
	}
	
	@Override
	public boolean letsRedstoneGoIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean letsRedstoneGoOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean letsEnergyIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aCoverVariable > 1 && aTileEntity instanceof IMachineProgress && (((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable < 2)) return false;
		return true;
	}
	
	@Override
	public boolean letsEnergyOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aCoverVariable > 1 && aTileEntity instanceof IMachineProgress && (((IMachineProgress)aTileEntity).isAllowedToWork() != aCoverVariable < 2)) return false;
		return true;
	}
	
	@Override
	public boolean letsLiquidIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean letsLiquidOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean letsItemsIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean letsItemsOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean isGUIClickable(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean manipulatesSidedRedstoneOutput(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return false;
	}
	
	@Override
	public boolean onCoverRightclick(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		return false;
	}
	
	@Override
	public boolean onCoverRemoval(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
		return true;
	}
}
