package it.polimi.ingsw.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import it.polimi.ingsw.client.ClientInformation;
import it.polimi.ingsw.client.middleware.ClientSender;
import it.polimi.ingsw.client.middleware.ClientSenderHandler;
import it.polimi.ingsw.client.model.BoardLight;
import it.polimi.ingsw.client.model.Utils;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import it.polimi.ingsw.shared.model.PawnColor;
import it.polimi.ingsw.shared.model.actionsdescription.BasicAction;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;
import it.polimi.ingsw.shared.requests.clientserver.BaseInformation;
import it.polimi.ingsw.shared.requests.clientserver.ChosenConsumableAction;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CardActionController {
    private List<JFXRadioButton> greenCards;
    private List<JFXRadioButton> yellowCards;
    private List<JFXRadioButton> blueCards;
    private List<JFXRadioButton> purpleCards;

    private BoardLight boardLight;
    private BoardIdentifier selected;

    @FXML
    private JFXRadioButton t_g_4;
    @FXML
    private JFXRadioButton t_g_3;
    @FXML
    private JFXRadioButton t_g_2;
    @FXML
    private JFXRadioButton t_g_1;
    @FXML
    private JFXRadioButton t_y_4;
    @FXML
    private JFXRadioButton t_y_3;
    @FXML
    private JFXRadioButton t_y_2;
    @FXML
    private JFXRadioButton t_y_1;
    @FXML
    private JFXRadioButton t_b_4;
    @FXML
    private JFXRadioButton t_b_3;
    @FXML
    private JFXRadioButton t_b_2;
    @FXML
    private JFXRadioButton t_b_1;
    @FXML
    private JFXRadioButton t_p_4;
    @FXML
    private JFXRadioButton t_p_3;
    @FXML
    private JFXRadioButton t_p_2;
    @FXML
    private JFXRadioButton t_p_1;
    @FXML
    private JFXRadioButton harvest;
    @FXML
    private JFXRadioButton production;

    @FXML
    private ToggleGroup cardsToggleGroup;

    @FXML
    private JFXButton chosen;

    public void initialize() {
        List<ActionType> actionTypes = ClientInformation.getActionTypesForConsumableAction();
        boardLight = BoardLight.getInstance();
        setCardLists();
        for (ActionType actionType : actionTypes) {
            switch (actionType) {
                case GREEN_TOWER: {
                    setGreenCards();
                    break;
                }
                case YELLOW_TOWER: {
                    setYellowCards();
                    break;
                }
                case BLUE_TOWER: {
                    setBlueCards();
                    break;
                }
                case PURPLE_TOWER: {
                    setPurpleCards();
                    break;
                }
                case PRODUCTION: {
                    production.setText("Production");
                    break;
                }
                case HARVEST: {
                    harvest.setText("Harvest");
                    break;
                }
            }
        }
        getCard();
    }

    private void setCards(List<JFXRadioButton> buttonList, JFXRadioButton button1, JFXRadioButton button2,
                          JFXRadioButton button3, JFXRadioButton button4) {
        buttonList.add(button4);
        buttonList.add(button3);
        buttonList.add(button2);
        buttonList.add(button1);

    }

    private void setCardLists() {
        greenCards = new ArrayList<>();
        setCards(greenCards, t_g_4, t_g_3, t_g_2, t_g_1);

        yellowCards = new ArrayList<>();
        setCards(yellowCards, t_y_4, t_y_3, t_y_2, t_y_1);

        blueCards = new ArrayList<>();
        setCards(blueCards, t_b_4, t_b_3, t_b_2, t_b_1);

        purpleCards = new ArrayList<>();
        setCards(purpleCards, t_p_4, t_p_3, t_p_2, t_p_1);
    }

    private void setGreenCards() {
        for (int i = 0; i < boardLight.getGreenTower().size(); i++) {
            if (!boardLight.getGreenTower().get(i).getCard().getName().isEmpty()) {
                greenCards.get(i).setText(boardLight.getGreenTower().get(i).getCard().getName());
            }
        }
    }

    private void setYellowCards() {
        for (int i = 0; i < boardLight.getYellowTower().size(); i++) {
            if (!boardLight.getYellowTower().get(i).getCard().getName().isEmpty()) {
                yellowCards.get(i).setText(boardLight.getYellowTower().get(i).getCard().getName());
            }
        }
    }

    public void setBlueCards() {
        for (int i = 0; i < boardLight.getBlueTower().size(); i++) {
            if (!boardLight.getBlueTower().get(i).getCard().getName().isEmpty()) {
                blueCards.get(i).setText(boardLight.getBlueTower().get(i).getCard().getName());
            }
        }
    }

    public void setPurpleCards() {
        for (int i = 0; i < boardLight.getPurpleTower().size(); i++) {
            if (!boardLight.getPurpleTower().get(i).getCard().getName().isEmpty()) {
                purpleCards.get(i).setText(boardLight.getPurpleTower().get(i).getCard().getName());
            }
        }
    }

    @FXML
    public void getCard() {
        if (cardsToggleGroup.getSelectedToggle() != null) {
            for (int i = 0; i < boardLight.getGreenTower().size(); i++) {
                if (boardLight.getGreenTower().get(i).getSlotLight().getBoardIdentifier().toString().equalsIgnoreCase(
                        ((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId())) {
                    selected = boardLight.getGreenTower().get(i).getSlotLight().getBoardIdentifier();
                }
            }
            for (int i = 0; i < boardLight.getYellowTower().size(); i++) {
                if (boardLight.getYellowTower().get(i).getSlotLight().getBoardIdentifier().toString()
                        .equalsIgnoreCase(((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId())) {
                    selected = boardLight.getYellowTower().get(i).getSlotLight().getBoardIdentifier();
                }
            }
            for (int i = 0; i < boardLight.getBlueTower().size(); i++) {
                if (boardLight.getBlueTower().get(i).getSlotLight().getBoardIdentifier().toString()
                        .equalsIgnoreCase(((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId())) {
                    selected = boardLight.getBlueTower().get(i).getSlotLight().getBoardIdentifier();
                }
            }
            for (int i = 0; i < boardLight.getPurpleTower().size(); i++) {
                if (boardLight.getPurpleTower().get(i).getSlotLight().getBoardIdentifier().toString()
                        .equalsIgnoreCase(((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId())) {
                    selected = boardLight.getPurpleTower().get(i).getSlotLight().getBoardIdentifier();
                }
            }
            if (((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId().equalsIgnoreCase("harvest")) {
                if (boardLight.getHarvest().get(0).getPawnLight().isPresent()) {
                    selected = BoardIdentifier.HARVEST_2;
                } else {
                    selected = BoardIdentifier.HARVEST_1;
                }
            }
            if (((JFXRadioButton) cardsToggleGroup.getSelectedToggle()).getId().equalsIgnoreCase("production")) {
                if (boardLight.getProduction().get(0).getPawnLight().isPresent()) {
                    selected = BoardIdentifier.PRODUCTION_2;
                } else {
                    selected = BoardIdentifier.PRODUCTION_1;
                }
            }
            BasicAction basicAction = new BasicAction(Utils.getActionTypeByBoardIdentifier(selected), selected, 0);
            BoardAction boardAction = new BoardAction(basicAction, PawnColor.UNCOLORED);
            ClientSender clientSender = new ClientSenderHandler();
            clientSender.sendToServer(new ChosenConsumableAction(
                    new BaseInformation(ClientInformation.getCurrentGameId(), ClientInformation.getPlayerName()),
                    boardAction, ClientInformation.getNameOfCardGivingConsumableAction()));
            Stage stage = (Stage)chosen.getScene().getWindow();
            stage.close();
        }
    }
}
