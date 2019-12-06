package gregtechmod.api.metatileentity;

import gregtechmod.api.interfaces.IIC2TileEntity;
import ic2.api.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * 
 * This file contains all the needed 'implements' of the Interfaces for the Industrial Craft Stuff.
 */
public class BaseMetaTileEntityICUE extends BaseMetaTileEntityUE implements IIC2TileEntity {
	public BaseMetaTileEntityICUE() {
		super();
	}
	
	public int injectEnergy(Direction aDirection, int aAmount) {return injectEnergyUnits((byte)aDirection.toSideValue(), aAmount, 1)?0:aAmount;}
	public boolean isTeleporterCompatible(Direction aSide) {return hasValidMetaTileEntity() && mMetaTileEntity.isTeleporterCompatible();}
	public boolean acceptsEnergyFrom(TileEntity aReceiver, Direction aDirection) {return inputEnergyFrom((byte)aDirection.toSideValue());}
    public boolean emitsEnergyTo(TileEntity aReceiver, Direction aDirection) {return outputsEnergyTo((byte)aDirection.toSideValue());}

	@Override
	public double getDemandedEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSinkTier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSourceTier() {
		// TODO Auto-generated method stub
		return 0;
	}
}