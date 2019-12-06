package gregtechmod.common.items;

import java.util.Set;

import net.minecraft.block.Block;

public class GT_Vanilla_Axe extends GT_Vanilla_Tool {
 
	public static Set<Block> setBlock;
	
	public GT_Vanilla_Axe(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+3.0F, setBlock);
		setHarvestLevel("axe", mHarvestLevel);
		 
	}
	 
}