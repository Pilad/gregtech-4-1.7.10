package gregtechmod.common.covers;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.interfaces.ICoverable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_Cover_RedstoneTransmitterExternal extends GT_Cover_RedstoneWirelessBase {
	
	public GT_Cover_RedstoneTransmitterExternal(ItemStack aStack) {
		super(aStack);
	}
	
	@Override
	public int doCoverThings(byte aSide, byte aInputRedstone, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		GregTech_API.sWirelessRedstone.put(aCoverVariable, aInputRedstone);
		return aCoverVariable;
	}
	
	@Override
	public boolean letsRedstoneGoIn(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return true;
	}
	
	@Override
	public short getTickRate(byte aSide, Item aCoverID, int aCoverVariable, ICoverable aTileEntity) {
		return 1;
	}
}
