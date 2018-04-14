package com.msemu.world.client.character;

import com.msemu.commons.network.packets.OutPacket;
import com.msemu.commons.utils.types.FileTime;

/**
 * Created by Weber on 2018/4/14.
 */
public class RunnerGameRecord {
    private FileTime lastPlayed = new FileTime();
    private int characterID;
    private int lastScore;
    private int highScore;
    private int runnerPoint;
    private int totalLeft;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCharacterID());
        outPacket.encodeInt(getLastScore());
        outPacket.encodeInt(getHighScore());
        outPacket.encodeInt(getRunnerPoint());
        outPacket.encodeFT(getLastPlayed());
        outPacket.encodeInt(getTotalLeft());
    }

    public FileTime getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(FileTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getRunnerPoint() {
        return runnerPoint;
    }

    public void setRunnerPoint(int runnerPoint) {
        this.runnerPoint = runnerPoint;
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public void setTotalLeft(int totalLeft) {
        this.totalLeft = totalLeft;
    }
}
