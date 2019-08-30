package ru.yammi.eventsystem.events;

import ru.yammi.eventsystem.Event;
import net.minecraft.network.Packet;

public class PacketEvent
        extends Event {

    private Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }
}
