package gregtechmod.api.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.metatileentity.BaseMetaPipeEntity;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GT_Spray_Hardener_Item extends GT_Tool_Item {
	public GT_Spray_Hardener_Item(int aID, String aName, int aMaxDamage, int aEntityDamage) {
		super(aID, aName, "Construction Foam Hardener", aMaxDamage, aEntityDamage);
		setCraftingSound(GregTech_API.sSoundList.get(102));
		setBreakingSound(GregTech_API.sSoundList.get(102));
		setEntityHitSound(GregTech_API.sSoundList.get(102));
		setUsageAmounts(16, 3, 1);
	}
	
	@Override
	public Item getEmptyItem(ItemStack aStack) {
		return GT_OreDictUnificator.getFirstOre("craftingSprayCan", 1).getItem();
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
    		return false;
    	}
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
    	
    	try {
    		if (GT_Utility.getClassName(aTileEntity).startsWith("TileEntityCable")) {
    			if (GT_Utility.getPublicField(aTileEntity, "foamed").getByte(aTileEntity) == 1) {
    				if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    					GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
    		    		GT_Utility.callPublicMethod(aTileEntity, "changeFoam", (byte)2);
    	    			return true;
    				}
    			}
    			return false;
    		}
    	} catch(Throwable e) {
    		if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
    	}
    	
    	ItemStack tStack1 = GT_ModHandler.getIC2Item("constructionFoam", 1), tStack2 = GT_ModHandler.getIC2Item("constructionFoamWall", 1);
    	if (tStack1 != null && tStack1.isItemEqual(new ItemStack(aBlock)) && tStack2 != null && tStack2.getItem() != null && tStack2.getItem() instanceof ItemBlock) {
    		if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
    			GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
        		aWorld.setBlock(aX, aY, aZ, Block.getBlockFromItem(tStack2.getItem()), 7, 3);
    		}
    		return true;
    	}
    	
    	if (aTileEntity instanceof BaseMetaPipeEntity && (((BaseMetaPipeEntity)aTileEntity).mConnections & -64) == 64) {
			if (GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				GT_Utility.sendSoundToPlayers(aWorld, GregTech_API.sSoundList.get(102), 1.0F, -1, aX, aY, aZ);
				((BaseMetaPipeEntity)aTileEntity).mConnections = (byte)((((BaseMetaPipeEntity)aTileEntity).mConnections & ~64) | -128);
			}
			return true;
    	}
    	
    	return false;
    }
}