/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author d3m0n
 */
public class GoFishGame extends Game {
    private GroupOfCards deck;
    private ArrayList<GoFishPlayer> players;
    private int currentPlayerIndex;

    public GoFishGame(String name) {
        super(name);
        deck = new GroupOfCards(52);
        players = new ArrayList<>();
        currentPlayerIndex = 0;
    }
    
    @Override
    public void play() {
        // create and shuffle the deck of cards
        for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
            for (String rank : new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"}) {
                GoFishCard card = new GoFishCard(suit, rank);
                deck.getCards().add(card);
            }
        }
        deck.shuffle();

        // deal the cards to the players
        for (int i = 0; i < 5; i++) {
            for (GoFishPlayer player : players) {
                GoFishCard card = (GoFishCard) deck.getCards().remove(0);
                player.addCardToHand(card);
            }
        }

        // start the game
        while (true) {
            // print the player's hand
            GoFishPlayer currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s hand: " + currentPlayer.getHand());

            // ask another player for a card
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ask " + currentPlayer.getName() + ", who do you want to ask for a card? ");
            String playerName = scanner.nextLine();
            GoFishPlayer otherPlayer = getPlayerByName(playerName);
            if (otherPlayer == null) {
                System.out.println("Invalid player name.");
                continue;
            }
            System.out.print("Ask " + currentPlayer.getName() + ", what rank do you want to ask for? ");
            String rank = scanner.nextLine();

            // check if the other player has the requested card
            boolean cardReceived = otherPlayer.askPlayer(currentPlayer, rank);
            if (cardReceived) {
                System.out.println(otherPlayer.getName() + " gave " + currentPlayer.getName() + " a " + rank + ".");
            } else {
                System.out.println(otherPlayer.getName() + " does not have any " + rank + "s.");
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }

            // check for matches in the player's hand
            ArrayList<GoFishCard> hand = currentPlayer.getHand();
            ArrayList<GoFishCard> matches = new ArrayList<>();
            for (int i = 0; i < hand.size(); i++) {
                GoFishCard card = hand.get(i);
                if (countRankInHand(card.getRank(), hand) == 4) {
                    matches.add(card);
                }
            }
            if (!matches.isEmpty()) {
                for (GoFishCard card : matches) {
                    hand.remove(card);
                }
                System.out.println(currentPlayer.getName() + " matched " + matches.size() + " " + matches.get(0).getRank() + "s!");
            }

            // check if the game is over
            if (deck.getCards().isEmpty() && currentPlayer.getHand().isEmpty()) {
                break;
            }
        }
    }
    
    @Override
    public void declareWinner() {
        // find the player with the most matches
        GoFishPlayer winner = null;
        int maxMatches = 0;
        for (GoFishPlayer player : players) {
            int matches = countMatches(player.getHand());
            if (matches > maxMatches) {
                winner = player;
                maxMatches = matches;
            }
        }

        // print the winner and their matches
        System.out.println("The winner is " + winner.getName()
                + " with " + maxMatches + " matches!");
    }

    public void addPlayer(GoFishPlayer player) {
        players.add(player);
    }

    private GoFishPlayer getPlayerByName(String name) {
        for (GoFishPlayer player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    private int countRankInHand(String rank, ArrayList<GoFishCard> hand) {
        int count = 0;
        for (GoFishCard card : hand) {
            if (card.getRank().equals(rank)) {
                count++;
            }
        }
        return count;
    }

    private int countMatches(ArrayList<GoFishCard> hand) {
        int count = 0;
        for (int i = 0; i < hand.size(); i++) {
            GoFishCard card1 = hand.get(i);
            for (int j = i + 1; j < hand.size(); j++) {
                GoFishCard card2 = hand.get(j);
                if (card1.getRank().equals(card2.getRank())) {
                    count++;
                }
            }
        }
        return count;
    }
}