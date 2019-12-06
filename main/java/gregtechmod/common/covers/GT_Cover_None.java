package gregtechmod.common.covers;

import gregtechmod.api.interfaces.ICoverable;
import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class GT_Cover_None extends GT_CoverBehavior {
	
	/**
	 * This is the Dummy, if there is no Cover
	 */
	public GT_Cover_None() {
		super();
	}
	
	@Override
	public float getBlastProofLevel(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 30.0F;
	}
	
	@Override
	public boolean letsRedstoneGoIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public boolean letsRedstoneGoOut(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
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
	public boolean isGUIClickable(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
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
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 0;
	}
	
	@Override
	public boolean isSimpleCover() {
		return true;
	}
}
