package com.msemu.world.client.character;

import com.msemu.commons.database.Schema;
import com.msemu.commons.network.packets.OutPacket;
import com.msemu.world.constants.MapleJob;

import javax.persistence.*;

/**
 * Created by Weber on 2018/3/29.
 */
@Schema
@Entity
@Table(name = "avatarData")
public class AvatarData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @JoinColumn(name = "characterStat")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CharacterStat characterStat;
    @JoinColumn(name = "avatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook avatarLook;
    @JoinColumn(name = "zeroAvatarLook")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarLook zeroAvatarLook;

    public AvatarLook getAvatarLook() {
        return avatarLook;
    }

    public CharacterStat getCharacterStat() {
        return characterStat;
    }

    public AvatarLook getZeroAvatarLook() {
        return zeroAvatarLook;
    }

    public void setZeroAvatarLook(AvatarLook zeroAvatarLook) {
        this.zeroAvatarLook = zeroAvatarLook;
    }

    public void encode(OutPacket outPacket) {
        characterStat.encode(outPacket);
        avatarLook.encode(outPacket);
        if(MapleJob.is神之子(getCharacterStat().getJob())) {
            zeroAvatarLook.encode(outPacket);
        }
    }

    public void setCharacterStat(CharacterStat characterStat) {
        this.characterStat = characterStat;
    }

    public void setAvatarLook(AvatarLook avatarLook) {
        this.avatarLook = avatarLook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AvatarLook getAvatarLook(boolean zeroBetaState) {
        return zeroBetaState ? getZeroAvatarLook() : getAvatarLook();
    }
}
