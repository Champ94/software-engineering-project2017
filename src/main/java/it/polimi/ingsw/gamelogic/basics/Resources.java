package it.polimi.ingsw.gamelogic.basics;

import it.polimi.ingsw.gamelogic.Util;

import java.util.Objects;

public class Resources {
    private int woods;
    private int stones;
    private int servants;
    private int coins;

    public Resources() {
        woods = 0;
        stones = 0;
        servants = 0;
        coins = 0;
    }

    public Resources(int woods, int stones, int servants, int coins) {
        this.woods = woods;
        this.stones = stones;
        this.servants = servants;
        this.coins = coins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resources resources = (Resources) o;
        return getWoods() == resources.getWoods() &&
                getStones() == resources.getStones() &&
                getServants() == resources.getServants() &&
                getCoins() == resources.getCoins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWoods(), getStones(), getServants(), getCoins());
    }

    public void add(Resources resourcesToAdd) {
        woods += resourcesToAdd.getWoods();
        stones += resourcesToAdd.getStones();
        servants += resourcesToAdd.getServants();
        coins += resourcesToAdd.getCoins();
    }

    /*
    TODO: delete subtractOrSetToZero
     */
    public void subtract(Resources resourcesToSubtract) {
        woods = Util.subtractOrSetToZero(woods, resourcesToSubtract.getWoods());
        stones = Util.subtractOrSetToZero(stones, resourcesToSubtract.getStones());
        servants = Util.subtractOrSetToZero(servants, resourcesToSubtract.getServants());
        coins = Util.subtractOrSetToZero(coins, resourcesToSubtract.getCoins());
    }

    public boolean isMoreThan(Resources resourcesToConfront) {
        /*
        TODO: method
         */
        return false;
    }

    public boolean isEmpty() {
        return woods == 0 &&
                stones == 0 &&
                servants == 0 &&
                coins == 0;
    }

    public int getWoods() {
        return woods;
    }

    public void setWoods(int woods) {
        this.woods = woods;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getServants() {
        return servants;
    }

    public void setServants(int servants) {
        this.servants = servants;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}