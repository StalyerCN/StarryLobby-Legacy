package cn.starry.hub.api.enums;

public enum GameOwned {
    BEDWARS(0, " " + "&f起床战争" + " "),
    THEPIT(1, " " + "&f天坑乱斗" + " "),
    SKYPVP(2, " " + "&fSkyPvP" + " "),
    DUEL(3, " " + "&f竞技场" + " "),
    SKYWARS(3, " " + "&f空岛战争" + " "),
    ARCADE(3, " " + "&f街机游戏" + " "),
    SURVIVAL(3, " " + "&f生存" + " "),
    RPG(3, " " + "&f羁旅" + " ");

    private final int id;
    private final String displayName;

    private GameOwned(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
