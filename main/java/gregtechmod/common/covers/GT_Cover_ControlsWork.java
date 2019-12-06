package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.interfaces.IMachineProgress;
import gregtechmod.api.util.GT_CoverBehavior;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_Cover_ControlsWork extends GT_CoverBehavior {
	
	public GT_Cover_ControlsWork(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		if (aTileEntity instanceof IMachineProgress) {
			if ((aInputRedstone>0) == (aCoverVariable==0) && aCoverVariable != 2) ((IMachineProgress)aTileEntity).enableWorking(); else ((IMachineProgress)aTileEntity).disableWorking();
			((IMachineProgress)aTileEntity).setWorkDataValue(aInputRedstone);
		}
		return aCoverVariable;
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
	public boolean onCoverRemoval(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, boolean aForced) {
		if (aTileEntity instanceof IMachineProgress) {
			((IMachineProgress)aTileEntity).enableWorking();
			((IMachineProgress)aTileEntity).setWorkDataValue((byte)0);
		}
		return true;
	}
	
	@Override
	public int onCoverScrewdriverclick(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity, EntityPlayer aPlayer, float aX, float aY, float aZ) {
		aCoverVariable=(aCoverVariable+1)%3;
		if (aCoverVariable== 0) GT_Utility.sendChatToPlayer(aPlayer, "Normal");
		if (aCoverVariable== 1) GT_Utility.sendChatToPlayer(aPlayer, "Inverted");
		if (aCoverVariable== 2) GT_Utility.sendChatToPlayer(aPlayer, "No Work at all");
		return aCoverVariable;
	}
	
	@Override
	public short getTickRate(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 1;
	}
}