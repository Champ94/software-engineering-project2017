package it.polimi.ingsw.client.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.client.lightmodel.DevelopmentCardLight;
import it.polimi.ingsw.client.lightmodel.ExcommunicationTileLight;
import it.polimi.ingsw.client.lightmodel.LeaderCardLight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserLightModel {
    private ParserSettingsClient parserSettingsClient;

    public ParserLightModel() {
        parserSettingsClient = new ParserSettingsClient();
    }

    public List<DevelopmentCardLight> parseDevelopmentCardsClient() throws IOException {
        JsonArray json;
        json = parserSettingsClient.extractJsonArray("DevelopmentCardsLight.json");
        return parseListDevelopmentCardLight(json);
    }

    private List<DevelopmentCardLight> parseListDevelopmentCardLight(JsonArray cards) {
        Gson gson = new Gson();
        JsonObject card;
        List<String> parsedCosts, parsedPermanentEffectDescription;
        List<DevelopmentCardLight> parsedCards = new ArrayList<>();
        for (int i=0; i < cards.size(); i++) {
            card = cards.get(i)
                        .getAsJsonObject();
            parsedCosts = gson.fromJson(card.get("cost").getAsJsonArray(), new TypeToken<ArrayList<String>>(){}.getType());
            parsedPermanentEffectDescription = gson.fromJson(card
                                                                .get("permanentEffectDescription")
                                                                .getAsJsonArray(),
                                                            new TypeToken<ArrayList<String>>(){}.getType());
            parsedCards.add(new DevelopmentCardLight(   card.get("name").getAsString(),
                    parsedCosts,
                    card.get("instantEffectDescription").getAsString(),
                    parsedPermanentEffectDescription));
        }
        return parsedCards;
    }

    public List<LeaderCardLight> parseLeaderCardClient() throws IOException {
        JsonArray json;
        json = parserSettingsClient.extractJsonArray("LeaderCardLight.json");
        return parseListLeaderCardLight(json);
    }

    private List<LeaderCardLight> parseListLeaderCardLight(JsonArray cards) {
        Gson gson = new Gson();
        JsonObject card;
        List<String> parsedRequirements;
        List<LeaderCardLight> parsedCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            card = cards.get(i)
                        .getAsJsonObject();
            parsedRequirements = gson.fromJson(card
                                                    .get("requirements")
                                                    .getAsJsonArray(),
                                                new TypeToken<ArrayList<String>>() {}.getType());
            parsedCards.add(new LeaderCardLight(card.get("name").getAsString(),
                                                card.get("effectDescription").getAsString(),
                                                parsedRequirements));
        }
        return parsedCards;
    }

    public List<ExcommunicationTileLight> parseExcommunicationTileClient() throws IOException {
        JsonArray json;
        json = parserSettingsClient.extractJsonArray("ExcommunicationTileLight.json");
        return parseListExcommunicationTileLight(json);
    }

    private List<ExcommunicationTileLight> parseListExcommunicationTileLight(JsonArray json) {
        JsonObject card;
        List<ExcommunicationTileLight> parsedCards = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            card = json.get(i)
                    .getAsJsonObject();
            parsedCards.add(new ExcommunicationTileLight(
                    card.get("name").getAsString(),
                    card.get("effectDescription").getAsString()));
        }
        return parsedCards;
    }

}