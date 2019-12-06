package gregtechmod.common.gui;

import org.lwjgl.opengl.GL11;

import gregtechmod.api.gui.GT_GUIContainer;
import gregtechmod.common.containers.GT_ContainerMetaID_Machine;
import gregtechmod.common.tileentities.GT_TileEntityMetaID_Machine;
import net.minecraft.entity.player.InventoryPlayer;

public class GT_GUIContainerMetaID_Machine extends GT_GUIContainer {

	public GT_TileEntityMetaID_Machine mTileEntity;
	public GT_ContainerMetaID_Machine  mContainer;
	public int mID;
	
	public GT_GUIContainerMetaID_Machine(GT_ContainerMetaID_Machine aContainer, GT_TileEntityMetaID_Machine aTileEntity, int aID, String aGUIbackground) {
		super(aContainer, aGUIbackground);
		mID = aID;
        mTileEntity = aTileEntity;
        mContainer  = aContainer;
	}
	
    public GT_GUIContainerMetaID_Machine(InventoryPlayer aInventoryPlayer, GT_TileEntityMetaID_Machine aTileEntity, int aID, String aGUIbackground) {
        this(new GT_ContainerMetaID_Machine(aInventoryPlayer, aTileEntity, aID), aTileEntity, aID, aGUIbackground);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
    	super.drawGuiContainerBackgroundLayer(par1, par2, par3);
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}