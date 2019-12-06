package gregtechmod.api.items;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * This is just a basic Tool, which has normal durability and could break Blocks.
 */
public class GT_Tool_Item extends GT_Generic_Item {
	public final List<String> mEffectiveAgainstList = new ArrayList<String>();
	//public final List<Integer> mEffectiveBlocksList = new ArrayList<Integer>();
	public final List<String> mEffectiveOreDictList = new ArrayList<String>();
	public final List<Material> mEffectiveMaterialsList = new ArrayList<Material>();
	
	protected final int mEntityDamage, mChargedGTID, mDisChargedGTID, mToolQuality;
	protected final float mToolStrength;
	
	private short mDamagePerContainerCraft = 10, mDamagePerWeaponUse = 3, mDamagePerBlockBreak = 1;
	private int mTier = 1, mEUperBrokenBlock = 1000, mSilklevel = 0, mFortunelevel = 0, mLootinglevel = 0;
	private String mToolClass1, mToolClass2, mToolClass3, mCraftingSound, mBreakingSound, mBlockBreakSound, mEntityHitSound;
	private float mCraftingSoundStrength = 1, mBreakingSoundStrength = 1, mBlockBreakSoundStrength = 1, mEntityHitSoundStrength = 1;
	
	public GT_Tool_Item(int aID, String aName, String aTooltip, int aMaxDamage, int aEntityDamage) {
		this(aID, aName, aTooltip, aMaxDamage, aEntityDamage, -1, -1);
	}
	
	public GT_Tool_Item(int aID, String aName, String aTooltip, int aMaxDamage, int aEntityDamage, int aChargedGTID, int aDisChargedGTID) {
		this(aID, aName, aTooltip, aMaxDamage, aEntityDamage, aChargedGTID, aDisChargedGTID, 0, 0.0F);
	}
	
	public GT_Tool_Item(int aID, String aName, String aTooltip, int aMaxDamage, int aEntityDamage, int aChargedGTID, int aDisChargedGTID, int aToolQuality, float aToolStrength) {
		super(aID, aName, aTooltip, aTooltip != null && !aTooltip.equals("Doesn't work as intended, this is a Bug"));
		mEntityDamage = aEntityDamage;
		mDisChargedGTID = aDisChargedGTID;
		mChargedGTID = aChargedGTID;
		mToolQuality = Math.max(aToolQuality, 0);
		mToolStrength = aToolStrength;
		setMaxDamage(aMaxDamage);
		setMaxStackSize(1);
		setNoRepair();
		setFull3D();
		GT_ModHandler.registerBoxableItemToToolBox(this);
	}
	
	public final GT_Tool_Item addToEffectiveList(String aEntityClassName) {
		mEffectiveAgainstList.add(aEntityClassName.substring(aEntityClassName.lastIndexOf(".")+1));
		return this;
	}
	
	public final GT_Tool_Item addToOreDictList(String aOreName) {
		mEffectiveOreDictList.add(aOreName);
		return this;
	}
	
	public final GT_Tool_Item addToBlockList(ItemStack aBlock) {
		//if (aBlock != null && aBlock.getItem() != null && aBlock.getItem() instanceof ItemBlock) mEffectiveBlocksList.add();
		return this;
	}
	
	public final GT_Tool_Item addToBlockList(Block aBlock) {
		//if (aBlock != null) mEffectiveBlocksList.add(aBlock);
		return this;
	}

	public final GT_Tool_Item addToBlockList(Integer aBlock) {
		//if (aBlock > 0) mEffectiveBlocksList.add(aBlock);
		return this;
	}
	
	public final GT_Tool_Item addToMaterialList(Material aMaterial) {
		if (aMaterial != null) mEffectiveMaterialsList.add(aMaterial);
		return this;
	}
	
	public final GT_Tool_Item setElectricTier(int aTier) {
		mTier = Math.max(1, aTier);
		return this;
	}
	
	public final GT_Tool_Item setElectricConsumptionPerBrokenBlock(int aEU) {
		mEUperBrokenBlock = Math.max(0, aEU);
		return this;
	}

	public final GT_Tool_Item setPrimaryToolClass(String aClass) {
		mToolClass1 = aClass;
		return this;
	}
	
	public final GT_Tool_Item setSecondaryToolClass(String aClass) {
		mToolClass2 = aClass;
		return this;
	}

	public final GT_Tool_Item setTertiaryToolClass(String aClass) {
		mToolClass3 = aClass;
		return this;
	}

	public final GT_Tool_Item setCraftingSound(String aSound) {
		mCraftingSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setBreakingSound(String aSound) {
		mBreakingSound = aSound;
		return this;
	}

	public final GT_Tool_Item setBlockBreakSound(String aSound) {
		mBlockBreakSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setEntityHitSound(String aSound) {
		mEntityHitSound = aSound;
		return this;
	}
	
	public final GT_Tool_Item setUsageAmounts(int aDamagePerContainerCraft, int aDamagePerWeaponUse, int aDamagePerBlockBreak) {
		mDamagePerContainerCraft = (short)aDamagePerContainerCraft;
		mDamagePerWeaponUse = (short)aDamagePerWeaponUse;
		mDamagePerBlockBreak = (short)aDamagePerBlockBreak;
		return this;
	}
	
	public final GT_Tool_Item setSilkyness(int aLevel) {
		mSilklevel = aLevel;
		return this;
	}

	public final GT_Tool_Item setLuckyness(int aLevel) {
		mFortunelevel = aLevel;
		return this;
	}
	
	public final GT_Tool_Item setLootyness(int aLevel) {
		mLootinglevel = aLevel;
		return this;
	}
	
	public final GT_Tool_Item registerAtOreDict(String aName) {
		GT_OreDictUnificator.registerOre(aName, new ItemStack(this, 1, GregTech_API.ITEM_WILDCARD_DAMAGE));
		return this;
	}
	
	public void setMode(ItemStack aStack, int aMode) {
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
		checkEnchantmentEffects(aStack);
        tNBT.setInteger("mMode", aMode);
	}
	
	public int getMode(ItemStack aStack) {
		if (aStack == null) return 0;
        NBTTagCompound tNBT = aStack.getTagCompound();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.setTagCompound(tNBT);
        }
		checkEnchantmentEffects(aStack);
        return tNBT.getInteger("mMode");
	}
	
	public final short getDamagePerContainerItemCraft() {
		return mDamagePerContainerCraft;
	}
	
	public final short getDamagePerWeaponUse() {
		return mDamagePerWeaponUse;
	}
	
	public final short getDamagePerBlockBreak() {
		return mDamagePerBlockBreak;
	}
	
	public void onHitEntity(Entity aEntity) {
		
	}
	
	public void checkEnchantmentEffects(ItemStack aStack) {
		if (aStack != null) {
			
			//if (aStack.isItemEnchanted()) aStack.stackTagCompound.getTags().remove(aStack.stackTagCompound.getTag("ench"));
			
			if (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, mEUperBrokenBlock)) {
				if (mSilklevel		> 0) aStack.addEnchantment(Enchantment.silkTouch	, mSilklevel);
				if (mFortunelevel	> 0) aStack.addEnchantment(Enchantment.fortune		, mFortunelevel);
				if (mLootinglevel	> 0) aStack.addEnchantment(Enchantment.looting		, mLootinglevel);
			}
		}
	}

	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister) {
		super.registerIcons(aIconRegister);
		if (mChargedGTID >= 0)		((GT_Generic_Item)GregTech_API.sItemList[mChargedGTID])		.mIcon = mIcon;
		if (mDisChargedGTID >= 0)	((GT_Generic_Item)GregTech_API.sItemList[mDisChargedGTID])	.mIcon = mIcon;
    }
	
	@Override
    public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
    	GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
		checkEnchantmentEffects(aStack);
        return aStack;
    }
    
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		GT_ModHandler.useElectricItem(aStack, 0, aPlayer);
		checkEnchantmentEffects(aStack);
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List aList) {
		if (GT_ModHandler.isElectricItem(new ItemStack(this, 1))) {
	        ItemStack tCharged = new ItemStack(this, 1);
	        GT_ModHandler.chargeElectricItem(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
	        aList.add(tCharged);
	        return;
		}
		aList.add(new ItemStack(this, 1));
    }
	
	public boolean shouldPassSneakingClickToBlock(World aWorld, int aX, int aY, int aZ) {
        return true;
    }
	
	@Override
    public int getItemEnchantability() {
        return 0;
    }
	
	@Override
    public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {
        return false;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {
        return false;
    }
	
	public final ItemStack getContainerItemStack(ItemStack aStack) {
		checkEnchantmentEffects(aStack);
		aStack = GT_Utility.copy(aStack);
		if (aStack.getItemDamage() > aStack.getMaxDamage()) {
			GT_Utility.doSoundAtClient(mBreakingSound, mBreakingSoundStrength);
			//ItemStack tStack = getEmptyItem(aStack);
			//if (tStack != null) return tStack;
		} else {
			GT_Utility.doSoundAtClient(mCraftingSound, mCraftingSoundStrength);
			GT_ModHandler.damageOrDechargeItem(aStack, getDamagePerContainerItemCraft(), getDamagePerContainerItemCraft()*1000, null);
		}
		checkEnchantmentEffects(aStack);
		return aStack;
    }
	
	@Override
    public boolean hasContainerItem() {
        return true;
    }
	
	@Override
    public boolean hasEffect(ItemStack aStack) {
		checkEnchantmentEffects(aStack);
        return false;
    }
	
	@Override
    public final boolean hitEntity(ItemStack aStack, EntityLivingBase aEntity, EntityLivingBase aPlayer) {
		GT_Utility.sendSoundToPlayers(aEntity.worldObj, mEntityHitSound, mEntityHitSoundStrength, -1, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ);
		checkEnchantmentEffects(aStack);
		if (mEntityDamage > 1) {
			GT_ModHandler.damageOrDechargeItem(aStack, getDamagePerWeaponUse(), getDamagePerWeaponUse() * 1000, aPlayer);
			if (aEntity != null) onHitEntity(aEntity);
			checkEnchantmentEffects(aStack);
			return true;
		}
        return false;
    }
	
	@Override
    public Multimap getItemAttributeModifiers() {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", mEntityDamage>0?mEntityDamage:1, 0));
        return multimap;
    }
	
	public final float getDamageVsEntity(Entity aAttackedEntity, ItemStack aStack) {
		checkEnchantmentEffects(aStack);
		int tDamage = mEntityDamage;
		if (mEffectiveAgainstList.contains(GT_Utility.getClassName(aAttackedEntity))) tDamage *= 2;
		if (tDamage > 1) {
			if (GT_ModHandler.isElectricItem(aStack)) {
				if (GT_ModHandler.canUseElectricItem(aStack, getDamagePerWeaponUse() * 1000)) {
					return tDamage;
				} else {
					return 1;
				}
			} else {
				return tDamage;
			}
		}
        return 1;
    }
	
 
	private final boolean isEffectiveAgainst(Block aBlock, int aMeta) {
		if (mToolStrength > 1 && aBlock != null) {
			if (getPrimaryToolClass() != null) {
				int tHarvestLevel = aBlock.getHarvestLevel(aMeta);
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (getSecondaryToolClass() != null) {
				int tHarvestLevel = aBlock.getHarvestLevel(aMeta);
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (getTertiaryToolClass() != null) {
				int tHarvestLevel = aBlock.getHarvestLevel(aMeta);
				if (tHarvestLevel >= 0) return tHarvestLevel <= mToolQuality;
			}
			if (mEffectiveMaterialsList.contains(aBlock.getMaterial())) return true;
			
			//if (mEffectiveBlocksList.contains(aBlock)) return true;
			
			for (String tString : mEffectiveOreDictList) {
				if (GT_OreDictUnificator.isItemStackInstanceOf(new ItemStack(aBlock, 1, aMeta==-1?GregTech_API.ITEM_WILDCARD_DAMAGE:aMeta), tString)) return true;
			}
		}
		return false;
	}
	
    public final String getPrimaryToolClass() {
        return mToolClass1;
    }
    
    public final String getSecondaryToolClass() {
        return mToolClass2;
    }
    
    public final String getTertiaryToolClass() {
        return mToolClass3;
    }
    
	@Override
    public final boolean canHarvestBlock(Block aBlock, ItemStack aStack) {
		return canHarvestBlock(aBlock) && (!GT_ModHandler.isElectricItem(aStack) || GT_ModHandler.canUseElectricItem(aStack, mEUperBrokenBlock));
    }
	 
    public final boolean canHarvestBlock(Block aBlock) {
		return mToolStrength > 1 && isEffectiveAgainst(aBlock, -1);
    }
	   
    @Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x,int y, int z, EntityLivingBase player) {
    	GT_Utility.sendSoundToPlayers(world, mBlockBreakSound, mBlockBreakSoundStrength, -1, x, y, z);
		checkEnchantmentEffects(stack);
		if (mToolStrength > 1 && block.getBlockHardness(world, x, y, z) != 0.0D) {
        	GT_ModHandler.damageOrDechargeItem(stack, getDamagePerBlockBreak(), getDamagePerBlockBreak()*mEUperBrokenBlock, player);
    		checkEnchantmentEffects(stack);
        }
        return true;
	}
    
	 
    
	@Override
    public final boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {
        return false;
    }
    
	public final boolean canProvideEnergy(ItemStack aStack) {
		return false;
	}
	 
	 
	
	@Override
	public final int getTier(ItemStack aStack) {
		return mTier;
	}
	
	 

	 
	public Item getChargedItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Item getEmptyItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public double getMaxCharge(ItemStack itemStack) {
		return getMaxDamage() * 1000;
	}

	 
	public double getTransferLimit(ItemStack itemStack) {
		return (int)(Math.pow(2, mTier) * 128);
	}
}