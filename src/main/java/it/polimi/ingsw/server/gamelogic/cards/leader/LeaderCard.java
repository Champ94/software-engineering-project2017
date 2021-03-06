package it.polimi.ingsw.server.gamelogic.cards.leader;

import it.polimi.ingsw.shared.model.LeaderCategory;

import java.util.List;
import java.util.Objects;

/**
 * Class that describes the Leader cards in general, with basic information, a cost defined as Leader Cost,
 * if the card has been placed on the board and if it can be played or not.
 */
public class LeaderCard {
    private LeaderInformation leaderInformation;
    private List<LeaderCost> leaderCosts;
    private boolean placedOnBoard;
    private boolean playable;

    public LeaderCard(LeaderInformation leaderInformation, List<LeaderCost> leaderCosts) {
        this.leaderInformation = leaderInformation;
        this.leaderCosts = leaderCosts;
        placedOnBoard = false;
        playable = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderCard that = (LeaderCard) o;
        return isPlacedOnBoard() == that.isPlacedOnBoard() &&
                isPlayable() == that.isPlayable() &&
                Objects.equals(getLeaderInformation(), that.getLeaderInformation()) &&
                Objects.equals(getLeaderCosts(), that.getLeaderCosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeaderInformation(), getLeaderCosts(), isPlacedOnBoard(), isPlayable());
    }

    public String getLeaderName() {
        return leaderInformation.getName();
    }

    public LeaderCategory getLeaderCategory() {
        return leaderInformation.getLeaderCategory();
    }

    public LeaderInformation getLeaderInformation() {
        return leaderInformation;
    }

    public void setLeaderInformation(LeaderInformation leaderInformation) {
        this.leaderInformation = leaderInformation;
    }

    public List<LeaderCost> getLeaderCosts() {
        return leaderCosts;
    }

    public void setLeaderCosts(List<LeaderCost> leaderCosts) {
        this.leaderCosts = leaderCosts;
    }

    public boolean isPlacedOnBoard() {
        return placedOnBoard;
    }

    public void setPlacedOnBoard(boolean placedOnBoard) {
        this.placedOnBoard = placedOnBoard;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
