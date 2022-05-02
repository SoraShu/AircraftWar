package edu.hitsz.application;

/**
 * @author SoraShu
 */

public enum Difficulty {

    /**
     * easy 难度
     */
    EASY("EASY", 1),
    /**
     * nomal 难度
     */
    NORMAL("NORMAL", 2),
    /**
     * difficult 难度
     */
    DIFFICULT("DIFFICULT", 3);

    private final String name;
    private final int index;

    Difficulty(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }


}
