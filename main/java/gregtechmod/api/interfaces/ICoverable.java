package gregtechmod.api.interfaces;

import gregtechmod.api.util.GT_CoverBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICoverable extends IRedstoneTileEntity, IHasWorldObjectAndCoords, IHasInventory, IBasicEnergyContainer {
	public boolean			canPlaceCoverIDAtSide	(byte aSide, Item aID);
	public boolean			canPlaceCoverItemAtSide	(byte aSide, ItemStack aCover);
	public boolean			dropCover				(byte aSide, byte aDroppedSide, boolean aForced);
	public void				setCoverDataAtSide		(byte aSide, int aData);
	public void				setCoverIDAtSide		(byte aSide, Item aID);
	public void				setCoverItemAtSide		(byte aSide, ItemStack aCover);
	public int				getCoverDataAtSide		(byte aSide);
	public Item				getCoverIDAtSide		(byte aSide);
	public ItemStack		getCoverItemAtSide		(byte aSide);
	public GT_CoverBehavior	getCoverBehaviorAtSide	(byte aSide);
	
	/**
	 * For use by the regular MetaTileEntities. Returns the Cover Manipulated input Redstone.
	 * Don't use this if you are a Cover Behavior. Only for MetaTileEntities.
	 */
	public byte getInternalInputRedstoneSignal(byte aSide);
	
	/**
	 * For use by the regular MetaTileEntities. This makes it not conflict with Cover based Redstone Signals.
	 * Don't use this if you are a Cover Behavior. Only for MetaTileEntities.
	 */
	public void setInternalOutputRedstoneSignal(byte aSide, byte aStrength);
	
	/**
	 * Causes a general Cover Texture update.
	 * Sends 6 Integers to Client + causes @issueTextureUpdate()
	 */
	public void issueCoverUpdate(byte aSide);
}