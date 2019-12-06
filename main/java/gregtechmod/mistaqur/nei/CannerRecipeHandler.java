package gregtechmod.mistaqur.nei;

import java.awt.Rectangle;
import java.util.ArrayList;

import codechicken.nei.PositionedStack;
import gregtechmod.api.GregTech_API;
import gregtechmod.api.util.GT_Log;
import gregtechmod.api.util.GT_Recipe;
import gregtechmod.common.gui.GT_GUIContainer_BasicMachine_Canner;
import net.minecraft.client.gui.inventory.GuiContainer;

public class CannerRecipeHandler extends GT_RecipeHandler {
	
	public class CachedCannerRecipe extends CachedGT_Recipe {
		public int mDuration, mEUt;

		public CachedCannerRecipe(GT_Recipe aRecipe) {
			resources = new ArrayList<PositionedStack>();
			if (aRecipe.mInput1 != null)
				resources.add(new PositionedStack(aRecipe.mInput1, 35 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.mInput2 != null)
				resources.add(new PositionedStack(aRecipe.mInput2, 53 - sOffsetX, 25 - sOffsetY));

			products = new ArrayList<PositionedStack>();
			if (aRecipe.mOutput1 != null)
				products.add(new PositionedStack(aRecipe.mOutput1, 107 - sOffsetX, 25 - sOffsetY));
			if (aRecipe.mOutput2 != null)
				products.add(new PositionedStack(aRecipe.mOutput2, 125 - sOffsetX, 25 - sOffsetY));
			
			mDuration = aRecipe.mDuration;
			mEUt = aRecipe.mEUt;
		}
	}
	
	@Override
	public void loadTransferRects() {
		try {
		transferRects.add(new RecipeTransferRect(new Rectangle(70-sOffsetX, 24-sOffsetY, 36, 18), getRecipeId()));
		
		ArrayList<Class<? extends GuiContainer>> guis = new ArrayList<Class<? extends GuiContainer>>();
		ArrayList<RecipeTransferRect> transferRects2 = new ArrayList<RecipeTransferRect>();
		guis.add(GT_GUIContainer_BasicMachine_Canner.class);
		transferRects2.add(new RecipeTransferRect(new Rectangle(70-5, 24-11, 36, 18), getRecipeId(), new Object[0]));
		RecipeTransferRectHandler.registerRectsToGuis(guis, transferRects2);
		} catch(Throwable e) {e.printStackTrace(GT_Log.out);}
	}
	
	@Override
	public String getRecipeName() {
		return "Automatic Canning Machine";
	}
	
	@Override
	public String getRecipeId() {
		return "gregtech.Canner";
	}
	
	@Override
	public String getGuiTexture() {
		return GregTech_API.GUI_PATH + "NEICanner.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "gregtech.Canner";
	}
	
	@Override
	public ArrayList<GT_Recipe> getRecipeList() {
		return GT_Recipe.sCannerRecipes;
	}
	
	@Override
	public CachedGT_Recipe getRecipe(GT_Recipe irecipe) {
		return new CachedCannerRecipe(irecipe);
	}
	
	@Override
	public void drawExtras(int recipe) {
		Integer time = ((CachedCannerRecipe)arecipes.get(recipe)).mDuration;
		drawText(30, 80, new StringBuilder().append("EU: ").append(toNumber(time*((CachedCannerRecipe)arecipes.get(recipe)).mEUt)).toString(), 0xFF000000, false);
		drawText(30, 90, new StringBuilder().append("Time: ").append(toNumber(time/20)).append(" secs").toString(), 0xFF000000, false);
		drawText(30,100, new StringBuilder().append("MaxEnergy: ").append(toNumber(((CachedCannerRecipe)arecipes.get(recipe)).mEUt)).append(" EU/t").toString(), 0xFF000000, false);
	}
}