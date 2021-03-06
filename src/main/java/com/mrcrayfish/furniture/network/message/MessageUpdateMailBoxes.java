package com.mrcrayfish.furniture.network.message;

import com.mrcrayfish.furniture.FurnitureMod;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class MessageUpdateMailBoxes implements IMessage<MessageUpdateMailBoxes>
{
    private CompoundNBT compound;

    public MessageUpdateMailBoxes() {}

    public MessageUpdateMailBoxes(CompoundNBT compound)
    {
        this.compound = compound;
    }

    @Override
    public void encode(MessageUpdateMailBoxes message, PacketBuffer buffer)
    {
        buffer.writeCompoundTag(message.compound);
    }

    @Override
    public MessageUpdateMailBoxes decode(PacketBuffer buffer)
    {
        CompoundNBT compound = buffer.readCompoundTag();
        return new MessageUpdateMailBoxes(compound);
    }

    @Override
    public void handle(MessageUpdateMailBoxes message, Supplier<NetworkEvent.Context> supplier)
    {
        supplier.get().enqueueWork(() -> FurnitureMod.PROXY.updateMailBoxes(message.compound));
        supplier.get().setPacketHandled(true);
    }
}
