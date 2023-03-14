/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author d3m0n
 */
public class Main {
    public static void main(String[] args) {
        GoFishGame game = new GoFishGame("Go Fish");
        game.addPlayer(new GoFishPlayer("Player 1"));
        game.addPlayer(new GoFishPlayer("Player 2"));
        game.play();
        game.declareWinner();
    }
}