package gregtechmod.loaders.postload;

import gregtechmod.GT_Mod;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_ModHandler;
import gregtechmod.api.util.GT_OreDictUnificator;
import gregtechmod.common.items.GT_MetaItem_Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_ItemIterator implements Runnable {
	@Override
	public void run() {
		Item tItem;
		String tName;
        ItemStack tCharmL = null, tCharmLL = null, tCharmI = null, tCharmII = null, tCharmIII = null, tStack, tStack2, tStack3;
        
        GT_Log.out.println("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotBronze", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze" , 8), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("plateBronze", 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2}))) {
			GT_OreDictUnificator.registerOre("craftingRawMachineTier00", tStack);
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustBronze" , 8), null, 0, false);
			GT_ModHandler.addSmeltingRecipe(tStack, GT_OreDictUnificator.get("ingotBronze", 8));
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotIron", 1), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get("ingotGold", 1), tStack3, tStack2, tStack3, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustIron", 4), GT_OreDictUnificator.get("dustGold", 1), 0, false);
		}
		if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2 = GT_OreDictUnificator.get("ingotSteel", 1), tStack3 = new ItemStack(Blocks.glass, 1, 0), tStack2, tStack3, GT_OreDictUnificator.get("ingotGold", 1), tStack3, tStack2, tStack3, tStack2}))) {
			GT_ModHandler.addPulverisationRecipe(tStack, GT_OreDictUnificator.get("dustSteel", 4), GT_OreDictUnificator.get("dustGold", 1), 0, false);
		}
		
		GT_Log.out.println("GT_Mod: Registering various Tools to be usable on GregTech Machines");
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[] {null, new ItemStack(Items.iron_ingot, 1), null, new ItemStack(Items.stick, 1)}));
		GregTech_API.registerScrewdriver(GT_ModHandler.getRecipeOutput(new ItemStack[] {new ItemStack(Items.iron_ingot, 1), null, null, null, new ItemStack(Items.stick, 1)}));
		
        GT_Log.out.println("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.rotten_flesh, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 2), GT_ModHandler.getIC2Item("filledTinCan", 2, 1), null, 200, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.spider_eye, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.poisonous_potato, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 1), GT_ModHandler.getIC2Item("filledTinCan", 1, 1), null, 100, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
        GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.cake, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 6), GT_ModHandler.getIC2Item("filledTinCan", 6, 0), null, 600, 1);
		GregTech_API.sRecipeAdder.addCannerRecipe(new ItemStack(Items.mushroom_stew, 1, GregTech_API.ITEM_WILDCARD_DAMAGE), GT_ModHandler.getIC2Item("tinCan", 3), GT_ModHandler.getIC2Item("filledTinCan", 3, 0), new ItemStack(Items.bowl, 1), 300, 1);
		
		GT_Log.out.println("GT_Mod: Scanning ItemList.");
	 
		
        
        GT_Log.out.println("GT_Mod: Getting Storage Blocks of Redpower for the OreDictUnification.");
        if (GT_ModHandler.mNikolite != null) {
        	GT_OreDictUnificator.set("dustNikolite", GT_ModHandler.mNikolite, GT_Mod.sUnificatorRP);
        	tStack2 = GT_ModHandler.mRuby;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(32, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockRuby", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mSapphire;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(33, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSapphire", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mGreenSapphire;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(34, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockGreenSapphire", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mSilver;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(17, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockSilver", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mCopper;
        	tStack3 = GT_ModHandler.getIC2Item("copperIngot", 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockCopper", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mTin;
        	tStack3 = GT_ModHandler.getIC2Item("tinIngot", 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockTin", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        	tStack2 = GT_ModHandler.mBrass;
        	tStack3 = GT_MetaItem_Material.instance.getUnunifiedStack(25, 1);
	        if (tStack2 != null) {
	        	GT_ModHandler.removeRecipe(new ItemStack[] {tStack2,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3,tStack3});
	        	if (null != (tStack = GT_ModHandler.getRecipeOutput(new ItemStack[] {tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2,tStack2}))) {
		        	GT_OreDictUnificator.set("blockBrass", tStack, GT_Mod.sUnificatorRP);
	        	}
	        }
        }
	}
}