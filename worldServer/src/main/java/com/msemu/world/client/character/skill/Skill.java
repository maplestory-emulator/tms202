/*
 * MIT License
 *
 * Copyright (c) 2018 msemu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.msemu.world.client.character.skill;

import com.msemu.commons.database.Schema;
import com.msemu.commons.utils.types.FileTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Weber on 2018/4/13.
 */
@Schema
@Entity
@Table(name = "skills")
@Getter
@Setter
public class Skill {

    @JoinColumn(name = "dateExpire")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    protected FileTime dateExpire = FileTime.getFileTimeFromType(FileTime.Type.PERMANENT);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "charId")
    private int charId;
    @Column(name = "skillId")
    private int skillId;
    @Column(name = "rootId")
    private int rootId;
    @Column(name = "maxLevel")
    private int maxLevel;
    @Column(name = "currentLevel")
    private int currentLevel;
    @Column(name = "masterLevel")
    private int masterLevel;

    @Override
    public String toString() {
        return "id = " + getSkillId() + ", cur = " + getCurrentLevel() + ", master = " + getMasterLevel();
    }
}