package gregtechmod.api.metatileentity.implementations;

import java.util.ArrayList;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This is the main construct for my Basic Machines such as the Automatic Extractor
 * Extend this class to make a simple Machine
 */
public abstract class GT_MetaTileEntity_BasicMachine_Bronze extends GT_MetaTileEntity_BasicMachine {
	public GT_MetaTileEntity_BasicMachine_Bronze(int aID, String mName, String mNameRegional) {
		super(aID, mName, mNameRegional);
	}
	
	public GT_MetaTileEntity_BasicMachine_Bronze() {
		
	}
	
	public boolean mNeedsSteamVenting = false;
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		super.saveNBTData(aNBT);
		aNBT.setBoolean("mNeedsSteamVenting", mNeedsSteamVenting);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		super.loadNBTData(aNBT);
		mNeedsSteamVenting = aNBT.getBoolean("mNeedsSteamVenting");
	}
	
	@Override public boolean isSteampowered()						{return true;}
	@Override public boolean isElectric()							{return false;}
	@Override public boolean isPneumatic()							{return false;}
	@Override public boolean isTransformerUpgradable()				{return false;}
	@Override public boolean isOverclockerUpgradable()				{return false;}
	@Override public boolean isBatteryUpgradable()					{return false;}
	@Override public boolean isEnetInput() 							{return false;}
	@Override public boolean isEnetOutput() 						{return false;}
	@Override public boolean isInputFacing(byte aSide)				{return false;}
	@Override public boolean isOutputFacing(byte aSide)				{return false;}
	@Override public boolean isTeleporterCompatible()				{return false;}
	@Override public boolean isFacingValid(byte aFacing)			{return super.isFacingValid(aFacing) && aFacing != mMainFacing;}
	@Override public int getMinimumStoredEU()						{return 1000;}
	@Override public int maxEUInput()								{return 0;}
    @Override public int maxEUOutput()								{return 0;}
    @Override public int maxEUStore()								{return 0;}
    @Override public int maxMJStore()								{return 0;}
    @Override public int maxSteamStore()							{return 2000;}
	@Override public int dechargerSlotCount()						{return 0;}
	@Override public boolean isLiquidInput (byte aSide)				{return aSide != mMainFacing;}
	@Override public boolean isLiquidOutput(byte aSide)				{return aSide != mMainFacing;}
	
	@Override public int getTopFacingInactive()						{return 314;}
	@Override public int getTopFacingPipe()							{return 344;}
	@Override public int getBottomFacingInactive()					{return 315;}
	@Override public int getBottomFacingPipe()						{return 345;}
	@Override public int getSideFacingInactive()					{return 316;}
	@Override public int getSideFacingPipe()						{return 346;}
	
	@Override
	public boolean doesAutoOutput() {
		return false;
	}
	
	@Override
	public boolean allowToCheckRecipe() {
		if (mNeedsSteamVenting 
				&& getBaseMetaTileEntity().getCoverIDAtSide(getBaseMetaTileEntity().getFrontFacing()) == null
				&& !GT_Utility.hasBlockHitBox(getBaseMetaTileEntity().getWorld(),
											  getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1),
											  getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1), 
											  getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1))
			) 
		{
		
			sendSound((byte)9);
			mNeedsSteamVenting = false;
			try {
				for (EntityLivingBase tLiving : (ArrayList<EntityLivingBase>)getBaseMetaTileEntity().getWorld().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1), getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1), getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1), getBaseMetaTileEntity().getOffsetX(getBaseMetaTileEntity().getFrontFacing(), 1)+1, getBaseMetaTileEntity().getOffsetY(getBaseMetaTileEntity().getFrontFacing(), 1)+1, getBaseMetaTileEntity().getOffsetZ(getBaseMetaTileEntity().getFrontFacing(), 1)+1))) {
					tLiving.attackEntityFrom(DamageSource.generic, getSteamDamage());
				}
			} catch(Throwable e) {
				if (GregTech_API.DEBUG_MODE) e.printStackTrace(GT_Log.err);
			}
		}
		return !mNeedsSteamVenting;
	}
	
	@Override
	public void endProcess() {
		if (isSteampowered()) mNeedsSteamVenting = true;
	}
	
	@Override
    public void doSound(byte aIndex, double aX, double aY, double aZ) {
		super.doSound(aIndex, aX, aY, aZ);
		if (aIndex == 9) {
			GT_Utility.doSoundAtClient(GregTech_API.sSoundList.get(4), 1.0F, aX, aY, aZ);
	        for (int l = 0; l < 8; ++l) getBaseMetaTileEntity().getWorld().spawnParticle("largesmoke", aX - 0.5 + Math.random(), aY - 0.5 + Math.random(), aZ - 0.5 + Math.random(), ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetX / 5.0, ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetY / 5.0, ForgeDirection.getOrientation(getBaseMetaTileEntity().getFrontFacing()).offsetZ / 5.0);
		}
	}
	
	@Override
	public boolean isGivingInformation() {
		return false;
	}
	
	@Override
	public boolean allowCoverOnSide(byte aSide, Item aCoverID) {
		return false;
		//return GregTech_API.getCoverBehavior(new ItemStack(aCoverID)).isSimpleCover() && super.allowCoverOnSide(aSide, aCoverID);
	}
	
	public float getSteamDamage() {
		return 6.0F;
	}
}