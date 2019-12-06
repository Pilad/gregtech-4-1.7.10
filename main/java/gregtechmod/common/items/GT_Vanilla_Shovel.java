package gregtechmod.common.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class GT_Vanilla_Shovel extends GT_Vanilla_Tool {
	static Set<Block> setBlock;

	public GT_Vanilla_Shovel(int aID, String aUnlocalizedName, String aMaterialName, int aHarvestLevel, int aEnchantability, int aMaxDamage, float aEfficiency, float aEntityDamage) {
		super(aUnlocalizedName, aMaterialName, aHarvestLevel, aEnchantability, aMaxDamage, aEfficiency, aEntityDamage+1.0F, setBlock);
		setHarvestLevel( "shovel", mHarvestLevel);
	}
	
    public boolean canHarvestBlock(Block aBlock) {
        return aBlock == Blocks.snow ? true : aBlock == Blocks.snow_layer;
    }
}