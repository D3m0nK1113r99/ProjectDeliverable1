/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author d3m0n
 */
public class GoFishPlayer extends Player {
    private ArrayList<GoFishCard> hand;

    public GoFishPlayer(String name) {
        super(name);
        hand = new ArrayList<>();
    }

    public ArrayList<GoFishCard> getHand() {
        return hand;
    }

    public void addCardToHand(GoFishCard card) {
        hand.add(card);
    }

    public boolean askPlayer(GoFishPlayer player, String rank) {
        boolean cardReceived = false;
        ArrayList<GoFishCard> playerHand = player.getHand();
        for (int i = 0; i < playerHand.size(); i++) {
            if (playerHand.get(i).getRank().equals(rank)) {
                GoFishCard card = playerHand.remove(i);
                hand.add(card);
                cardReceived = true;
                break;
            }
        }
        return cardReceived;
    }

    @Override
    public void play() {
        // not needed for Go Fish, but must be implemented because of abstract method in Player class
    }
}
