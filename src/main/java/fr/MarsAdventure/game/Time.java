package fr.MarsAdventure.game;

public class Time {

    private final int updatesSinceStart;

    public Time(){
        this.updatesSinceStart = 0;
    }

    public int getUpdatesFromSeconds(int seconds) {
        return seconds * GameLoop.UPDATES_PER_SECOND;
    }

    public int getUpdatesSinceStart() {
        return updatesSinceStart;
    }
}
