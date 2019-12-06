package gregtechmod.common.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class GT_Vanilla_Pickaxe extends GT_Vanilla_Tool {
	
	static Set<Block> mineble;
	
	public GT_Vanilla_Pickaxe(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+2.0F, mineble);
		setHarvestLevel("pickaxe", mHarvestLevel);
	}
	
    public boolean canHarvestBlock(Block aBlock) {
        return aBlock == Blocks.obsidian ? mHarvestLevel >= 3 : 
        	(aBlock != Blocks.diamond_block && aBlock != Blocks.diamond_ore ? 
        			(aBlock != Blocks.emerald_ore && aBlock != Blocks.emerald_block ? 
        					(aBlock != Blocks.gold_ore && aBlock != Blocks.gold_block ? 
        							(aBlock != Blocks.iron_ore && aBlock != Blocks.iron_block ? 
        									(aBlock != Blocks.lapis_block && aBlock != Blocks.lapis_ore 
        									? (aBlock != Blocks.redstone_block && aBlock != Blocks.redstone_ore 
        									? (aBlock.getMaterial() == Material.rock ? true : (aBlock.getMaterial() == Material.iron
    										? true : aBlock.getMaterial() == Material.anvil)) :
    											mHarvestLevel >= 2) :
    												mHarvestLevel >= 1) :
    													mHarvestLevel >= 1) :
    														mHarvestLevel >= 2) :
    															mHarvestLevel >= 2) :
    																mHarvestLevel >= 2);
    }
    
    
}