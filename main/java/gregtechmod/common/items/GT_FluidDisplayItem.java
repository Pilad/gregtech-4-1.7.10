package gregtechmod.common.items;

import gregtechmod.api.GregTech_API;
import gregtechmod.api.items.GT_Generic_Item;
import gregtechmod.api.util.GT_Utility;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class GT_FluidDisplayItem extends GT_Generic_Item {
	public GT_FluidDisplayItem(int aID, String aName) {
		super(aID, aName, null);
	}
	
	 
	
	@Override
    public IIcon getIconFromDamage(int aMeta) {
    	Fluid tFluid = FluidRegistry.getFluid(aMeta);
    	if (tFluid != null) {
    		return tFluid.getStillIcon();
    	}
    	return GregTech_API.FAIL_ICON;
    }
    
	@Override
    public int getSpriteNumber() {
        return 0;
    }
	
	@Override
    public String getUnlocalizedName(ItemStack aStack) {
	    if (aStack != null) {
		    Fluid tFluid = FluidRegistry.getFluid(aStack.getItemDamage());
		    if (tFluid != null) {
		    	return GT_Utility.capitalizeString(tFluid.getUnlocalizedName().replaceAll("fluid.", "").replaceAll("tile.", ""));
		    }
	    }
        return "";
    }
	
	@Override
    public String getItemStackDisplayName(ItemStack aStack) {
        return getUnlocalizedName(aStack);
    }
 
}