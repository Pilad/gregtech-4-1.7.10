package gregtechmod.common.tileentities;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class GT_MetaTileEntity_ThermalGenerator extends GT_MetaTileEntity_BasicTank {
	
	public GT_MetaTileEntity_ThermalGenerator(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_ThermalGenerator() {
		
	}
	
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return aIndex < 2;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetOutput() 						{return true;}
	@Override public boolean isOutputFacing(byte aSide)				{return true;}
	@Override public int maxEUOutput()								{return getBaseMetaTileEntity().isAllowedToWork()?24:0;}
	@Override public int maxEUStore()								{return 1000000;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 119);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_ThermalGenerator();
	}
	
	@Override public boolean doesFillContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean doesEmptyContainers()	{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeFilled()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean canTankBeEmptied()		{return getBaseMetaTileEntity().isAllowedToWork();}
	@Override public boolean displaysItemStack()	{return true;}
	@Override public boolean displaysStackSize()	{return false;}
	@Override public boolean isFluidInputAllowed(FluidStack aFluid) {return isValidFuel(aFluid);}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getTimer()%10==0) {
    		if (mFluid == null) {
    			if (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()))
    				mInventory[2] = null;
    			else
    				if (mInventory[2] == null)
    					mInventory[2] = new ItemStack(Blocks.fire, 1);
    		} else {
    			if (getFuelValue(mFluid) > 0) while (getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU()) && mFluid.amount > 0) {
    				if (getBaseMetaTileEntity().increaseStoredEnergyUnits(getFuelValue(mFluid), true)) mFluid.amount--;
    			}
    		}
    		if (mInventory[0] != null && getBaseMetaTileEntity().getUniversalEnergyStored() < (getBaseMetaTileEntity().getOutputVoltage() * 10 + getMinimumStoredEU())) {
    			for (int i = 0; i < GT_Recipe.sHotFuels.size(); i++) {
    				if (GT_Utility.areStacksEqual(mInventory[0], GT_Recipe.sHotFuels.get(i).mInput1)) {
    					if (GT_Utility.getFluidForFilledItem(mInventory[0]) == null) {
    						if (GT_Recipe.sHotFuels.get(i).mOutput1 != null) {
    							if (mInventory[1] == null) {
    								if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sHotFuels.get(i).mStartEU*800, true)) {
    									getBaseMetaTileEntity().decrStackSize(0, 1);
    									mInventory[1] = GT_Utility.copy(GT_Recipe.sHotFuels.get(i).mOutput1);
    								}
    							} else if (GT_Utility.areStacksEqual(mInventory[1], GT_Recipe.sHotFuels.get(i).mOutput1) && mInventory[1].stackSize + GT_Recipe.sHotFuels.get(i).mOutput1.stackSize <= mInventory[1].getMaxStackSize()) {
    								if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sHotFuels.get(i).mStartEU*800, true)) {
        								getBaseMetaTileEntity().decrStackSize(0, 1);
        	    						mInventory[1].stackSize += GT_Recipe.sHotFuels.get(i).mOutput1.stackSize;
    								}
    							}
    						} else {
    							if (getBaseMetaTileEntity().increaseStoredEnergyUnits(GT_Recipe.sHotFuels.get(i).mStartEU*800, true)) {
    								getBaseMetaTileEntity().decrStackSize(0, 1);
    							}
    						}
    					}
    					break;
    				}
    			}
    		}
    	}
    	
	    if (getBaseMetaTileEntity().isServerSide()) getBaseMetaTileEntity().setActive(getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getUniversalEnergyStored() >= getBaseMetaTileEntity().getOutputVoltage() + getMinimumStoredEU());
    }
    
    public boolean isValidFuel(FluidStack aFluid) {
    	return getFuelValue(aFluid)>0;
    }
    
    /**
     * @return EU per MB
     */
    public int getFuelValue(FluidStack aFluid) {
    	if (aFluid == null) return 0;
    	FluidStack tFluid;
    	for (int i = 0; i < GT_Recipe.sHotFuels.size(); i++) {
    		if ((tFluid = GT_Utility.getFluidForFilledItem(GT_Recipe.sHotFuels.get(i).mInput1)) != null) if (aFluid.isFluidEqual(tFluid)) return (GT_Recipe.sHotFuels.get(i).mStartEU * 4) / 5;
        }
    	return 0;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0)
    		return 38;
    	else if (aSide == 1)
    		return aActive?84:83;
    	else
    		return 36;
	}
	
	@Override
	public String getDescription() {
		return "Generates Energy from hot Stuff like Lava";
	}
	
	@Override
	public int getCapacity() {
		return 10000;
	}
	
	@Override
	public int getTankPressure() {
		return -100;
	}
}
