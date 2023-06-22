package cn.starry.hub.api.enums;

public enum GameType {
    SPORTS(0, "&7(竞技类)"),
    LEISURE(1, "&7(休闲类)"),
    SURVIVAL(2, "&7(生存类)"),
    RPG(3, "&7(RPG类)");

    private final int id;
    private final String displayName;

    private GameType(int id, String displayName) {
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
