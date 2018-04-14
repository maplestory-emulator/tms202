package com.msemu.world.client.character;

import com.msemu.commons.network.packets.OutPacket;

/**
 * Created by Weber on 2018/4/13.
 */

public class ZeroInfo {
    private boolean isZeroBetaState;
    private int subHP;
    private int subMHP;
    private int subMP;
    private int subMMP;
    private int subSkin;
    private int subHair;
    private int subFace;
    private int dbCharZeroLinkCashPart;
    private int mixBaseHairColor;
    private int mixAddHairColor;
    private int mixHairBaseProb;

    public ZeroInfo deepCopy() {
        ZeroInfo zi = new ZeroInfo();
        zi.setZeroBetaState(isZeroBetaState());
        zi.setSubHP(getSubHP());
        zi.setSubMHP(getSubMHP());
        zi.setSubMP(getSubMP());
        zi.setSubMMP(getSubMMP());
        zi.setSubSkin(getSubSkin());
        zi.setSubHair(getSubHair());
        zi.setSubFace(getSubFace());
        zi.setDbCharZeroLinkCashPart(getDbCharZeroLinkCashPart());
        zi.setMixBaseHairColor(getMixBaseHairColor());
        zi.setMixAddHairColor(getMixAddHairColor());
        zi.setMixHairBaseProb(getMixHairBaseProb());
        return zi;
    }

    public boolean isZeroBetaState() {
        return isZeroBetaState;
    }

    public void setZeroBetaState(boolean zeroBetaState) {
        isZeroBetaState = zeroBetaState;
    }

    public int getSubHP() {
        return subHP;
    }

    public void setSubHP(int subHP) {
        this.subHP = subHP;
    }

    public int getSubMHP() {
        return subMHP;
    }

    public void setSubMHP(int subMHP) {
        this.subMHP = subMHP;
    }

    public int getSubMP() {
        return subMP;
    }

    public void setSubMP(int subMP) {
        this.subMP = subMP;
    }

    public int getSubMMP() {
        return subMMP;
    }

    public void setSubMMP(int subMMP) {
        this.subMMP = subMMP;
    }

    public int getSubSkin() {
        return subSkin;
    }

    public void setSubSkin(int subSkin) {
        this.subSkin = subSkin;
    }

    public int getSubHair() {
        return subHair;
    }

    public void setSubHair(int subHair) {
        this.subHair = subHair;
    }

    public int getSubFace() {
        return subFace;
    }

    public void setSubFace(int subFace) {
        this.subFace = subFace;
    }

    public int getDbCharZeroLinkCashPart() {
        return dbCharZeroLinkCashPart;
    }

    public void setDbCharZeroLinkCashPart(int dbCharZeroLinkCashPart) {
        this.dbCharZeroLinkCashPart = dbCharZeroLinkCashPart;
    }

    public int getMixBaseHairColor() {
        return mixBaseHairColor;
    }

    public void setMixBaseHairColor(int mixBaseHairColor) {
        this.mixBaseHairColor = mixBaseHairColor;
    }

    public int getMixAddHairColor() {
        return mixAddHairColor;
    }

    public void setMixAddHairColor(int mixAddHairColor) {
        this.mixAddHairColor = mixAddHairColor;
    }

    public int getMixHairBaseProb() {
        return mixHairBaseProb;
    }

    public void setMixHairBaseProb(int mixHairBaseProb) {
        this.mixHairBaseProb = mixHairBaseProb;
    }

    public void encode(OutPacket outPacket) {
        short mask = 0x1 | 0x2 | 0x4 | 0x40 | 0x80;
        outPacket.encodeShort(mask);
        if((mask & 0x1) != 0){
            outPacket.encodeByte(true);
        }
        if ((mask & 0x2) != 0) {
            outPacket.encodeInt(getSubHP() / 2);
        }
        if ((mask & 0x4) != 0) {
            outPacket.encodeInt(getSubMP() / 3);
        }
        if ((mask & 0x8) != 0) {
            outPacket.encodeByte(getSubSkin());
        }
        if ((mask & 0x10) != 0) {
            outPacket.encodeInt(getSubHair());
        }
        if ((mask & 0x20) != 0) {
            outPacket.encodeInt(getSubFace());
        }
        if ((mask & 0x40) != 0) {
            outPacket.encodeInt(getSubMHP());
        }
        if ((mask & 0x80) != 0) {
            outPacket.encodeInt(getSubMMP());
        }
        if ((mask & 0x100) != 0) {
            outPacket.encodeInt(getDbCharZeroLinkCashPart());
        }
        if ((mask & 0x200) != 0) {
            outPacket.encodeInt(getMixBaseHairColor());
            outPacket.encodeInt(getMixAddHairColor());
            outPacket.encodeInt(getMixHairBaseProb());
        }
    }
}
