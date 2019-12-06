package gregtech.api.interfaces.tileentity;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public interface ITexturedTileEntity {
    /**
     * @return the Textures rendered by the GT Rendering
     */
    public IIcon getTexture(Block aBlock, byte aSide);
}