package it.polimi.ingsw.gamelogic.player;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that describes the Player's personal Board
 */
public class PlayerBoard {
    private BonusTiles bonusTiles;
    private Goods goods;

    private Deck deck;
    private List<Pawn> pawns;

    public PlayerBoard(BonusTiles bonusTiles, Goods goods) {
        this.bonusTiles = bonusTiles;
        this.goods = goods;
        deck = new Deck();
        pawns = new ArrayList<>();
    }

    public Goods getProductionBonus() {
        return bonusTiles.getProductionBonus();
    }

    public Goods getHarvestBonus() {
        return bonusTiles.getHarvestBonus();
    }

    public int getNumberOfTerritories() {
        return deck.getNumberOfTerritories();
    }

    public int getNumberOfBuildings () {
        return deck.getNumberOfBuildings();
    }

    public int getNumberOfCharacters () {
        return deck.getNumberOfCharacters();
    }

    public int getNumberOfVentures () {
        return deck.getNumberOfVentures();
    }

    /**
     * Checks the value of the Pawn of a given color
     * @param pawnColor color of the Pawn that needs to be checked
     * @return the value of the Pawn if it is present
     */
    public int getPawnValueGivenColor(PawnColor pawnColor) {
        Optional<Pawn> pawn = getPawnGivenColor(pawnColor);
        if (pawn.isPresent())
            return pawn.get().getValue();
        else
            throw new IllegalStateException("No pawn found");
    }

    /**
     * Checks if there is the Pawn of the given color
     * @param pawnColor color to check
     * @return true if there is the pawn
     */
    public Optional<Pawn> getPawnGivenColor(PawnColor pawnColor) {
        return pawns.stream()
                .filter(predicate -> predicate.getPawnColor() == pawnColor)
                .reduce((a, b) -> { throw new IllegalStateException("More than one Pawn found"); });
    }

    public BonusTiles getBonusTiles() {
        return bonusTiles;
    }

    public void setBonusTiles(BonusTiles bonusTiles) {
        this.bonusTiles = bonusTiles;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(List<Pawn> pawns) {
        this.pawns = pawns;
    }
}
