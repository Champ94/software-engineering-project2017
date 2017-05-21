package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.List;

public class Territory implements Collectible {
    private DevelopmentCard developmentCard; // Composite object attribute
    private int harvestActionValueRequired;
    private ExchangingGoods harvestResult;

    public Territory(DevelopmentCard developmentCard, int harvestActionValueRequired, ExchangingGoods harvestResult) {
        this.developmentCard = developmentCard;
        this.harvestActionValueRequired = harvestActionValueRequired;
        this.harvestResult = harvestResult;
    }

    @Override
    public CardInformation getCardInformation() {
        return developmentCard.getCardInformation();
    }

    @Override
    public List<Goods> getCosts() {
        return developmentCard.getCosts();
    }

    @Override
    public FlashEffect getInstantEffect() {
        return developmentCard.getInstantEffect();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    public int getHarvestActionValueRequired() {
        return harvestActionValueRequired;
    }

    public void setHarvestActionValueRequired(int harvestActionValueRequired) {
        this.harvestActionValueRequired = harvestActionValueRequired;
    }

    public ExchangingGoods getHarvestResult() {
        return harvestResult;
    }

    public void setHarvestResult(ExchangingGoods harvestResult) {
        this.harvestResult = harvestResult;
    }
}
