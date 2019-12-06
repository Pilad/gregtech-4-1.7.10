package gregtechmod.api.world;

import java.util.ArrayList;
import java.util.Collection;

import gregtechmod.api.GregTech_API;
import net.minecraft.block.Block;

public abstract class GT_Worldgen_Ore extends GT_Worldgen {
	public final int mBlockMeta,  mAmount, mSize, mMinY, mMaxY, mProbability, mDimensionType;
	Block mBlockID;
	public final Collection<String> mBiomeList = new ArrayList<String>();
	public final boolean mAllowToGenerateinVoid;
	
	public GT_Worldgen_Ore(String aName, boolean aDefault, Block aBlockID, int aBlockMeta, int aDimensionType, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid) {
		super(aName, aDefault);
		mBlockID		= aBlockID;
		mBlockMeta		= Math.min(Math.max(aBlockMeta, 0), 15);
		mDimensionType	= aDimensionType;
		mProbability	= GregTech_API.sConfiguration.addAdvConfig("worldgen."+mWorldGenName, "Probability"	, aProbability);
		mAmount			= GregTech_API.sConfiguration.addAdvConfig("worldgen."+mWorldGenName, "Amount"		, aAmount);
		mSize			= GregTech_API.sConfiguration.addAdvConfig("worldgen."+mWorldGenName, "Size"		, aSize);
		mMinY			= GregTech_API.sConfiguration.addAdvConfig("worldgen."+mWorldGenName, "MinHeight"	, aMinY);
		mMaxY			= GregTech_API.sConfiguration.addAdvConfig("worldgen."+mWorldGenName, "MaxHeight"	, aMaxY);
		if (aBiomeList != null) mBiomeList.addAll(aBiomeList);
		mAllowToGenerateinVoid = aAllowToGenerateinVoid;
	}
}