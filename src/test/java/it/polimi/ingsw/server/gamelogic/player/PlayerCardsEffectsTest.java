package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.CardFlashAction;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.DoubleServants;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.NoBonusGoodsOnTower;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.DoubleRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.shared.model.ActionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerCardsEffectsTest {
    private PlayerCardsEffects playerCardsEffects;

    @BeforeEach
    void setUp() {
        playerCardsEffects = new PlayerCardsEffects();
    }


    @Test
    void testEqualsTrue1() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        assertTrue(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsTrue2() {
        PlayerCardsEffects playerCardsEffectsToConfront = playerCardsEffects;
        assertTrue(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsFalse1() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        playerCardsEffectsToConfront.setCardFlashAction(new CardFlashAction("", ActionType.BLUE_TOWER, 2));
        assertFalse(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsFalse2() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        playerCardsEffectsToConfront.setPlayerOrderWeight(2);
        assertFalse(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsFalse3() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        playerCardsEffectsToConfront.setChurchSustainBonus(new Goods(new Resources(1,2,3,4)));
        assertFalse(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsFalse4() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        playerCardsEffectsToConfront.addRequirementsModifier(new DoubleServants(
                new AvailableActions(ActionType.BLUE_TOWER)));
        assertFalse(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsFalse5() {
        PlayerCardsEffects playerCardsEffectsToConfront = new PlayerCardsEffects();
        playerCardsEffectsToConfront.addRewardsModifier(new DoubleRewards(new AvailableActions()));
        assertFalse(playerCardsEffects.equals(playerCardsEffectsToConfront));
    }

    @Test
    void testEqualsDifferent1() {
        String different = "";
        assertFalse(playerCardsEffects.equals(different));
    }

    @Test
    void testEqualsDifferent2() {
        assertFalse(playerCardsEffects.equals(null));
    }

    @Test
    void testAddRequirementsModifier() {
        RequirementsModifier requirementsModifierToConfront = new NoBonusGoodsOnTower(new AvailableActions(ActionType.BLUE_TOWER));
        playerCardsEffects.addRequirementsModifier(requirementsModifierToConfront);
        assertEquals(requirementsModifierToConfront, playerCardsEffects.getRequirementsModifiers().get(0));
    }

    @Test
    void testAddRewardsModifier() {
        RewardsModifier rewardsModifierToConfront = new DoubleRewards(new AvailableActions(
                new ArrayList<ActionType>(){{add(ActionType.BLUE_TOWER); add(ActionType.HARVEST);}}));
        playerCardsEffects.addRewardsModifier(rewardsModifierToConfront);
        assertEquals(rewardsModifierToConfront, playerCardsEffects.getRewardsModifiers().get(0));
    }

    @Test
    void testGetCardFlashAction() {
        CardFlashAction cardFlashActionToConfront = new CardFlashAction("", ActionType.BLUE_TOWER, 3);
        playerCardsEffects.setCardFlashAction(cardFlashActionToConfront);
        Optional<CardFlashAction> result = playerCardsEffects.getCardFlashAction();
        assertTrue(result.isPresent());
        if (result.isPresent()) {
            CardFlashAction realResult;
            realResult = result.get();
            assertEquals(cardFlashActionToConfront, realResult);
        }
    }

    @Test
    void testGetPlayerOrderWeight() {
        playerCardsEffects.setPlayerOrderWeight(3);
        assertEquals(3, playerCardsEffects.getPlayerOrderWeight());
    }

    @Test
    void testGetRequirementsModifiers() {
        RequirementsModifier requirementsModifier;
        List<RequirementsModifier> requirementsModifiersToConfront;
        requirementsModifier = new NoBonusGoodsOnTower(new AvailableActions(ActionType.BLUE_TOWER));
        requirementsModifiersToConfront = new ArrayList<RequirementsModifier>(){{
            add(requirementsModifier);}};
        playerCardsEffects.addRequirementsModifier(requirementsModifier);
        assertEquals(requirementsModifiersToConfront, playerCardsEffects.getRequirementsModifiers());
    }

    @Test
    void testGetRewardsModifiers() {
        RewardsModifier rewardsModifier;
        List<RewardsModifier> rewardsModifiersToConfront;
        rewardsModifier = new DoubleRewards(new AvailableActions(
                new ArrayList<ActionType>(){{add(ActionType.BLUE_TOWER); add(ActionType.HARVEST);}}));
        rewardsModifiersToConfront = new ArrayList<RewardsModifier>(){{
            add(rewardsModifier);}};
        playerCardsEffects.addRewardsModifier(rewardsModifier);
        assertEquals(rewardsModifiersToConfront, playerCardsEffects.getRewardsModifiers());
    }
}