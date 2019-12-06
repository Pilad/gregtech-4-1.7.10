package gregtechmod.common.tileentities;

import java.util.ArrayList;

import gregtechmod.api.interfaces.IGregTechTileEntity;
import gregtechmod.api.metatileentity.BaseTileEntity;
import gregtechmod.api.metatileentity.MetaTileEntity;
import gregtechmod.api.metatileentity.implementations.GT_MetaTileEntity_BasicTank;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkPosition;
import net.minecraftforge.fluids.IFluidBlock;

public class GT_MetaTileEntity_AdvancedPump extends GT_MetaTileEntity_BasicTank {
	
	public ArrayList<ChunkPosition> mPumpList = new ArrayList<ChunkPosition>();
	
	public Block mPumpedBlockID1 = null, mPumpedBlockID2 = null;
	
	public GT_MetaTileEntity_AdvancedPump(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_AdvancedPump() {
		
	}
	
	@Override public boolean isTransformerUpgradable()				{return true;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return true;}
	@Override public boolean isSimpleMachine()						{return false;}
	@Override public boolean isValidSlot(int aIndex)				{return true;}
	@Override public boolean isFacingValid(byte aFacing)			{return false;}
	@Override public boolean isEnetInput() 							{return true;}
	@Override public boolean isInputFacing(byte aSide)				{return true;}
	@Override public int maxEUInput()								{return 128;}
    @Override public int maxEUStore()								{return 100000;}
    @Override public int maxMJStore()								{return maxEUStore();}
    @Override public int maxSteamStore()							{return maxEUStore();}
	@Override public int getInvSize()								{return 3;}
	@Override public void onRightclick(EntityPlayer aPlayer)		{getBaseMetaTileEntity().openGUI(aPlayer, 130);}
	@Override public boolean isAccessAllowed(EntityPlayer aPlayer)	{return true;}
    
	@Override
	public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_AdvancedPump();
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		//aNBT.setInteger("mPumpedBlockID1", mPumpedBlockID1);
		//aNBT.setInteger("mPumpedBlockID2", mPumpedBlockID2);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
    	//mPumpedBlockID1 = aNBT.getInteger("mPumpedBlockID1");
    	//mPumpedBlockID2 = aNBT.getInteger("mPumpedBlockID2");
	}
	
	@Override public boolean doesFillContainers()	{return true;}
	@Override public boolean doesEmptyContainers()	{return false;}
	@Override public boolean canTankBeFilled()		{return false;}
	@Override public boolean canTankBeEmptied()		{return true;}
	@Override public boolean displaysItemStack()	{return false;}
	@Override public boolean displaysStackSize()	{return false;}
	
	@Override public int getInputSlot() {return 1;}
	@Override public int getOutputSlot() {return 2;}
	
    @Override
    public void onPostTick() {
    	if (getBaseMetaTileEntity().isServerSide() && getBaseMetaTileEntity().getTimer()%10==0) {
    		
    		if (getBaseMetaTileEntity() instanceof BaseTileEntity) {
    			((BaseTileEntity)getBaseMetaTileEntity()).ignoreUnloadedChunks = false;
    		}
    		
    		if (getBaseMetaTileEntity().isAllowedToWork() && getBaseMetaTileEntity().getUniversalEnergyStored() > 10000 && (mFluid == null || mFluid.amount + 1000 <= getCapacity())) {
        		boolean tMovedOneDown = false;
        		
        		if (getBaseMetaTileEntity().getTimer()%100==0) {
        			tMovedOneDown = moveOneDown();
        		}
        		
        		if (mPumpedBlockID1 != null || mPumpedBlockID2 != null) {
            		getFluidAt(getBaseMetaTileEntity().getXCoord(), getYOfPumpHead() - 1, getBaseMetaTileEntity().getZCoord());
            		if (mPumpedBlockID1 != null || mPumpedBlockID2 != null) {
            			getFluidAt(getBaseMetaTileEntity().getXCoord(), getYOfPumpHead(), getBaseMetaTileEntity().getZCoord() + 1);
            		}
            		if (mPumpedBlockID1 != null || mPumpedBlockID2 != null) {
            			getFluidAt(getBaseMetaTileEntity().getXCoord(), getYOfPumpHead(), getBaseMetaTileEntity().getZCoord() - 1);
            		}
            		if (mPumpedBlockID1 != null || mPumpedBlockID2 != null) {
            			getFluidAt(getBaseMetaTileEntity().getXCoord() + 1, getYOfPumpHead(), getBaseMetaTileEntity().getZCoord());
            		}
            		if (mPumpedBlockID1 != null || mPumpedBlockID2 != null) {
            			getFluidAt(getBaseMetaTileEntity().getXCoord() - 1, getYOfPumpHead(), getBaseMetaTileEntity().getZCoord());
            		}
                } else {
                	if (getYOfPumpHead() < getBaseMetaTileEntity().getYCoord()) {
    		        	if (tMovedOneDown || (mPumpList.isEmpty() && getBaseMetaTileEntity().getTimer() % 200 == 100) || getBaseMetaTileEntity().getTimer() % 72000 == 100) {
    		        		mPumpList.clear();
    		        		for (int y = getBaseMetaTileEntity().getYCoord() - 1, yHead = getYOfPumpHead(); mPumpList.isEmpty() && y >= yHead; y--) {
    		        			scanForFluid(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord(), mPumpList, getBaseMetaTileEntity().getXCoord(), getBaseMetaTileEntity().getZCoord(), 64);
    		        		}
    		        	}
    		        	if (!tMovedOneDown && !mPumpList.isEmpty()) {
    			        	consumeFluid(mPumpList.get(mPumpList.size()-1).chunkPosX, mPumpList.get(mPumpList.size()-1).chunkPosY, mPumpList.get(mPumpList.size()-1).chunkPosZ);
    			        	mPumpList.remove(mPumpList.size()-1);
    		        	}
                	}
        		}
    		}
    		getBaseMetaTileEntity().setActive(!mPumpList.isEmpty());
    	}
    }
    
    private boolean moveOneDown() {
    	if (mInventory[0] == null || mInventory[0].stackSize < 1 || !GT_Utility.areStacksEqual(mInventory[0], GT_ModHandler.getIC2Item("miningPipe", 1))) return false;
    	int yHead = getYOfPumpHead();
    	if (yHead <= 0) return false;
    	if (!consumeFluid(getBaseMetaTileEntity().getXCoord(), yHead - 1, getBaseMetaTileEntity().getZCoord()) && !getBaseMetaTileEntity().getAir(getBaseMetaTileEntity().getXCoord(), yHead - 1, getBaseMetaTileEntity().getZCoord())) return false;
    	if (!getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getXCoord(), yHead - 1, getBaseMetaTileEntity().getZCoord(), Block.getBlockFromItem( GT_ModHandler.getIC2Item("miningPipeTip", 1).getItem()))) return false;
    	if (yHead != getBaseMetaTileEntity().getYCoord()) getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getXCoord(), yHead, getBaseMetaTileEntity().getZCoord(), Block.getBlockFromItem( GT_ModHandler.getIC2Item("miningPipeTip", 1).getItem()));
    	getBaseMetaTileEntity().decrStackSize(0, 1);
    	return true;
    }
    
    private int getYOfPumpHead() {
    	int y = getBaseMetaTileEntity().getYCoord() - 1;
    	while (getBaseMetaTileEntity().getWorld().getBlock(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord()) == Block.getBlockFromItem(GT_ModHandler.getIC2Item("miningPipe", 1).getItem())) y--;
    	if (y == getBaseMetaTileEntity().getYCoord() - 1) {
    		if (getBaseMetaTileEntity().getWorld().getBlock(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord()) != Block.getBlockFromItem(GT_ModHandler.getIC2Item("miningPipeTip", 1).getItem())) {
        		return y + 1;
    		}
    	} else {
    		if (getBaseMetaTileEntity().getWorld().getBlock(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord()) != Block.getBlockFromItem( GT_ModHandler.getIC2Item("miningPipeTip", 1).getItem())) {
    			getBaseMetaTileEntity().getWorld().setBlock(getBaseMetaTileEntity().getXCoord(), y, getBaseMetaTileEntity().getZCoord(), Block.getBlockFromItem( GT_ModHandler.getIC2Item("miningPipeTip", 1).getItem()));
    		}
    	}
    	return y;
    }
    
    private void scanForFluid(int aX, int aY, int aZ, ArrayList<ChunkPosition> aList, int mX, int mZ, int mDist) {
    	boolean pX = addIfFluidAndNotAlreadyAdded(aX + 1, aY, aZ, aList),
    			nX = addIfFluidAndNotAlreadyAdded(aX - 1, aY, aZ, aList),
    			pZ = addIfFluidAndNotAlreadyAdded(aX, aY, aZ + 1, aList),
    			nZ = addIfFluidAndNotAlreadyAdded(aX, aY, aZ - 1, aList);
    	
    	if (pX && aX < mX + mDist) {
    		scanForFluid(aX + 1, aY, aZ, aList, mX, mZ, mDist);
    	}
    	if (nX && aX > mX - mDist) {
    		scanForFluid(aX - 1, aY, aZ, aList, mX, mZ, mDist);
    	}
    	if (pZ && aZ < mZ + mDist) {
    		scanForFluid(aX, aY, aZ + 1, aList, mX, mZ, mDist);
    	}
    	if (nZ && aZ > mZ - mDist) {
    		scanForFluid(aX, aY, aZ - 1, aList, mX, mZ, mDist);
    	}
    	if (addIfFluidAndNotAlreadyAdded(aX, aY + 1, aZ, aList) || (aX == mX && aZ == mZ && aY < getBaseMetaTileEntity().getYCoord())) {
    		scanForFluid(aX, aY + 1, aZ, aList, mX, mZ, mDist);
    	}
    }
    
    private boolean addIfFluidAndNotAlreadyAdded(int aX, int aY, int aZ, ArrayList<ChunkPosition> aList) {
    	ChunkPosition tCoordinate = new ChunkPosition(aX, aY, aZ);
    	if (!aList.contains(tCoordinate)) {
    		Block tID = getBaseMetaTileEntity().getWorld().getBlock(aX, aY, aZ);
    		if (mPumpedBlockID1 == tID || mPumpedBlockID2 == tID) {
    			aList.add(tCoordinate);
    			return true;
    		}
    	}
    	return false;
    }
    
    private void getFluidAt(int aX, int aY, int aZ) {
    	Block aID = getBaseMetaTileEntity().getWorld().getBlock(aX, aY, aZ);
    	if (aID != null) {
			if (aID == Blocks.lava || aID == Blocks.flowing_lava) {
				mPumpedBlockID1 = Blocks.lava;
				mPumpedBlockID2 = Blocks.flowing_lava;
				return;
			}
			
			if (aID == Blocks.water || aID == Blocks.flowing_water) {
				mPumpedBlockID1 = Blocks.water;
				mPumpedBlockID2 = Blocks.flowing_water;
				return;
			}
			if (aID instanceof IFluidBlock) {
				mPumpedBlockID1 = aID;
				mPumpedBlockID2 = aID;
				return;
			}
    	}
		mPumpedBlockID1 = null;
		mPumpedBlockID2 = null;
    }
    
    private boolean consumeFluid(int aX, int aY, int aZ) {
    	Block tID = getBaseMetaTileEntity().getWorld().getBlock(aX, aY, aZ);
    	int tMeta = getBaseMetaTileEntity().getWorld().getBlockMetadata(aX, aY, aZ);
    	
    	if (mPumpedBlockID1 == tID || mPumpedBlockID2 == tID) {
    		
    		if (tID == Blocks.water || tID == Blocks.flowing_water) {
	    		if (tMeta == 0) {
	    			if (mFluid == null) {
		        		getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
		    			mFluid = GT_ModHandler.getWater(1000);
		    		} else if (GT_ModHandler.isWater(mFluid)) {
		        		getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
		    			mFluid.amount += 1000;
		    		} else {
		    			return false;
		    		}
	    		} else {
	        		getBaseMetaTileEntity().decreaseStoredEnergyUnits( 250, true);
	    		}
    		}
    		
    		if (tID == Blocks.lava || tID == Blocks.flowing_lava) {
	    		if (tMeta == 0) {
		    		if (mFluid == null) {
		        		getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
		    			mFluid = GT_ModHandler.getLava(1000);
		    		} else if (GT_ModHandler.isLava(mFluid)) {
		        		getBaseMetaTileEntity().decreaseStoredEnergyUnits(1000, true);
		    			mFluid.amount += 1000;
		    		} else {
		    			return false;
		    		}
	    		} else {
	        		getBaseMetaTileEntity().decreaseStoredEnergyUnits( 250, true);
	    		}
    		}
    		
			if (tID instanceof IFluidBlock) {
	    		if (mFluid == null) {
	    			mFluid = ((IFluidBlock)tID).drain(getBaseMetaTileEntity().getWorld(), aX, aY, aZ, true);
	        		getBaseMetaTileEntity().decreaseStoredEnergyUnits(mFluid==null?1000:mFluid.amount, true);
	    		} else {
	    			return false;
	    		}
			}
			
    		getBaseMetaTileEntity().getWorld().setBlock(aX, aY, aZ, Blocks.air, 0, 0);
    		return true;
    	}
    	return false;
    }
    
	@Override
	public int getTextureIndex(byte aSide, byte aFacing, boolean aActive, boolean aRedstone) {
    	if (aSide == 0) return 110;
    	if (aSide == 1) return aActive?69:68;
    	return 109;
	}
	
	@Override
	public String getDescription() {
		return "The best way of emptying Oceans!";
	}
	
	@Override
	public int getCapacity() {
		return 16000;
	}
	
	@Override
	public int getTankPressure() {
		return 100;
	}
	
	@Override
	public boolean allowPullStack(int aIndex, byte aSide, ItemStack aStack) {
		return aIndex == 2;
	}
	
	@Override
	public boolean allowPutStack(int aIndex, byte aSide, ItemStack aStack) {
		return GT_Utility.areStacksEqual(aStack, GT_ModHandler.getIC2Item("miningPipe", 1))?aIndex==0:aIndex==1;
	}
}