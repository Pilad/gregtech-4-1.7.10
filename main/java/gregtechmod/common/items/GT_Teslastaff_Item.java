package gregtechmod.common.items;

import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_ModHandler;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;

public class GT_Teslastaff_Item extends ItemTool implements IElectricItem {
	private static Set<Block> blocks;
	public int mCharge, mTransfer, mTier;
	 
    public GT_Teslastaff_Item(String aName) {
        super(0, ToolMaterial.GOLD, blocks );
        
        blocks.add(Blocks.air);
        
		setCreativeTab(GregTech_API.TAB_GREGTECH);
		setMaxStackSize(1);
		setMaxDamage(100);
		setNoRepair();
		setUnlocalizedName(aName);
        mCharge = 100000000;
        mTransfer = 8192;
        mTier = 4;
        GameRegistry.registerItem(this, aName);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(GregTech_API.TEXTURE_PATH_ITEM + getUnlocalizedName());
    }
    
	@Override
    public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		aList.add("No warranty!");
    }
	
    @Override
    public boolean hitEntity(ItemStack aStack, EntityLivingBase aTarget, EntityLivingBase aPlayer) {
        
    	if (aTarget instanceof EntityPlayer && aPlayer instanceof EntityPlayer && ElectricItem.manager.canUse(aStack, 9000000)) {
            EntityPlayer tTarget = (EntityPlayer)aTarget, tPlayer = (EntityPlayer)aPlayer;
            GT_ModHandler.useElectricItem(aStack, 9000000, tPlayer);
            for (int i = 0; i < 4; i++) {
            	if (tTarget.inventory.armorInventory[i] != null) {
            		if (tTarget.inventory.armorInventory[i].getItem() instanceof IElectricItem) {
            			tTarget.inventory.armorInventory[i] = null;
            		}
            	}
            }
            aPlayer.attackEntityFrom(DamageSource.magic, 19);
            aTarget.attackEntityFrom(DamageSource.magic, 19);
        }
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int var1, CreativeTabs var2, List var3) {
        ItemStack tCharged = new ItemStack(this, 1), tUncharged = new ItemStack(this, 1, getMaxDamage());
        ElectricItem.manager.charge(tCharged, Integer.MAX_VALUE, Integer.MAX_VALUE, true, false);
        var3.add(tCharged);
        var3.add(tUncharged);
    }
    
	@Override
    public int getItemEnchantability() {
        return 0;
    }
	
	@Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }
	
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }
	
    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean getShareTag() {
        return true;
    }
    
	@Override
	public boolean canProvideEnergy(ItemStack aStack) {
		return false;
	}
	 
	 
	@Override
	public int getTier(ItemStack aStack) {
		return mTier;
	}
	
	 

	@Override
	public Item getChargedItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getEmptyItem(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMaxCharge(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return mCharge;
	}

	@Override
	public double getTransferLimit(ItemStack itemStack) {
		// TODO Auto-generated method stub
		return mTransfer;
	}
}
