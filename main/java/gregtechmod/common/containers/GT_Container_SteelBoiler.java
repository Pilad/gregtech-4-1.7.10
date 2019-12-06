package gregtechmod.common.containers;

import java.util.Iterator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.gui.GT_ContainerMetaTile_Machine;
import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.common.tileentities.GT_MetaTileEntity_Boiler_Steel;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class GT_Container_SteelBoiler extends GT_ContainerMetaTile_Machine {

	public GT_Container_SteelBoiler(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity, int aID) {
		super(aInventoryPlayer, aTileEntity, aID);
	}

    public void addSlots(InventoryPlayer aInventoryPlayer) {
        addSlotToContainer(new Slot(mTileEntity,  2, 116, 62));
        addSlotToContainer(new Slot(mTileEntity,  0,  44, 26));
        addSlotToContainer(new Slot(mTileEntity,  1,  44, 62));
        addSlotToContainer(new Slot(mTileEntity,  3, 116, 26));
    }
    
    public int getSlotCount() {
    	return 4;
    }
    
    public int getShiftClickSlotCount() {
    	return 1;
    }
    
    public int mTemperature = 2, mProcessingEnergy = 0, mSteamAmount = 0, mWaterAmount = 0;
    
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    	if (mTileEntity.isClientSide() || mTileEntity.getMetaTileEntity() == null) return;
    	
    	mTemperature = ((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mTemperature;
    	mProcessingEnergy = ((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mProcessingEnergy;
    	mSteamAmount = ((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mSteam == null?0:((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mSteam.amount;
    	mWaterAmount = ((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mFluid == null?0:((GT_MetaTileEntity_Boiler_Steel)mTileEntity.getMetaTileEntity()).mFluid.amount;
    	
    	mTemperature = Math.min(54, Math.max(0, (mTemperature * 54) / 990));
    	mSteamAmount = Math.min(54, Math.max(0, (mSteamAmount * 54) / 31900));
    	mWaterAmount = Math.min(54, Math.max(0, (mWaterAmount * 54) / 15900));
    	mProcessingEnergy = Math.min(14, Math.max(mProcessingEnergy>0?1:0, (mProcessingEnergy * 14) / 640));
    	
        Iterator var2 = this.crafters.iterator();
        while (var2.hasNext()) {
            ICrafting var1 = (ICrafting)var2.next();
            var1.sendProgressBarUpdate(this, 100, mTemperature);
            var1.sendProgressBarUpdate(this, 101, mProcessingEnergy);
            var1.sendProgressBarUpdate(this, 102, mSteamAmount);
            var1.sendProgressBarUpdate(this, 103, mWaterAmount);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
    	super.updateProgressBar(par1, par2);
    	switch (par1) {
    	case 100: mTemperature = par2; break;
    	case 101: mProcessingEnergy = par2; break;
    	case 102: mSteamAmount = par2; break;
    	case 103: mWaterAmount = par2; break;
    	}
    }
}
