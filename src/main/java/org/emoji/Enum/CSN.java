package org.emoji.Enum;

public enum CSN {
        EMOJI,
        INVENTORY,
        SIZE,
        MATERIAL,
        MAIN,
        ITEMS,
        NAME,
        DESCRIPTION,
        DATA,
        AMOUNT,
        SLOT,
        MYTHIC,
        COMMAND,
        COOL_TIME,
        SPAWN,
        BLOCK,
        ITEM,
        SUB,
        PLAYER,
        DEATH,
        ;

        public String getLower(){
                return this.name().toLowerCase();
        }
}
