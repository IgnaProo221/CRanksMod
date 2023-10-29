package net.ignaproo.cranks.Ranks;

public class PlayerRankData {
    private String playerName;
    private String rank;

    public PlayerRankData(String playerName, String rank) {
        this.playerName = playerName;
        this.rank = rank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}