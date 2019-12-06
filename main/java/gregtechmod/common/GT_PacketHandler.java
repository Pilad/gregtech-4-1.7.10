package gregtechmod.common;

import java.io.IOException;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;


public class GT_PacketHandler extends Packet {
	 
	//public void onPacketData(INetworkManager aManager, Packet250CustomPayload aPacket, EntityPlayer aPlayer) {
		/*
		try {
			if (aPacket.channel.equals(GregTech_API.TILEENTITY_PACKET_CHANNEL)) {
				ByteArrayDataInput tIn = ByteStreams.newDataInput(aPacket.data);
				int tX = tIn.readInt(), tY = tIn.readShort(), tZ = tIn.readInt();
				if (aPlayer != null && aPlayer instanceof EntityPlayer) {
					if (GregTech_API.DEBUG_MODE && (((EntityPlayer)aPlayer).username.equals("Player") || ((EntityPlayer)aPlayer).username.equals("GregoriusT"))) GT_Log.out.println("Received initial MetaTileEntity Data: " + aPacket.length + " Bytes @ (" + tX + ";" + tY + ";" + tZ + ") during Tick: " + GregTech_API.sClientTickCounter);
					TileEntity tTileEntity = ((EntityPlayer)aPlayer).worldObj.getTileEntity(tX, tY, tZ);
					if (tTileEntity != null) {
							 if (tTileEntity instanceof BaseMetaTileEntity) ((BaseMetaTileEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
						else if (tTileEntity instanceof BaseMetaPipeEntity) ((BaseMetaPipeEntity)tTileEntity).receiveMetaTileEntityData(tIn.readShort(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readInt(), tIn.readByte(), tIn.readByte(), tIn.readByte(), tIn.readByte());
					}
				}
			} else if (aPacket.channel.equals(GregTech_API.SOUND_PACKET_CHANNEL)) {
				ByteArrayDataInput tIn = ByteStreams.newDataInput(aPacket.data);
				int tX = tIn.readInt(), tY = tIn.readShort(), tZ = tIn.readInt();
				if (aPlayer != null && aPlayer instanceof EntityPlayer) {
					GT_Utility.doSoundAtClient(tIn.readUTF(), tIn.readFloat(), tIn.readFloat(), tX, tY, tZ);
				}
			} else if (aPacket.channel.equals(GregTech_API.GENERIC_CHANNEL)) {
				if (GregTech_API.DEBUG_MODE) GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.channel + " -> " + aPacket.data.length + " Bytes");
			} else {
				 GT_Log.out.println("Tick " + GregTech_API.sClientTickCounter + " @ " + aPacket.channel + " -> " + aPacket.data.length + " Bytes");
			}
		} catch(Throwable e) {
			e.printStackTrace(GT_Log.err);
		}
		*/
	//}

	@Override
	public void readPacketData(PacketBuffer p_148837_1_) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writePacketData(PacketBuffer p_148840_1_) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processPacket(INetHandler p_148833_1_) {
		// TODO Auto-generated method stub
		
	}
}