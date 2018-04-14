package com.msemu.world.client.character;

import com.msemu.commons.network.packets.OutPacket;

/**
 * Created by Weber on 2018/4/13.
 */
public class ExpConsumeItem {
    private int itemId;
    private int minLev;
    private int maxLev;
    private long remainingExp;

    public ExpConsumeItem(int itemId, int minLev, int maxLev, long remainingExp) {
        this.itemId = itemId;
        this.minLev = minLev;
        this.maxLev = maxLev;
        this.remainingExp = remainingExp;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMinLev() {
        return minLev;
    }

    public void setMinLev(int minLev) {
        this.minLev = minLev;
    }

    public int getMaxLev() {
        return maxLev;
    }

    public void setMaxLev(int maxLev) {
        this.maxLev = maxLev;
    }

    public long getRemainingExp() {
        return remainingExp;
    }

    public void setRemainingExp(long remainingExp) {
        this.remainingExp = remainingExp;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getItemId());
        outPacket.encodeInt(getMinLev());
        outPacket.encodeInt(getMaxLev());
        outPacket.encodeLong(getRemainingExp());
    }
}
