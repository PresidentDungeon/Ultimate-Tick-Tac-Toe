/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import field.Field;
import field.IField;

/**
 *
 * @author ander
 */
public class GameState implements IGameState{
    
    private IField field;
    private int moveNumber = 0;
    private int roundNumber = 0;
    

    public GameState() {
        this.field = new Field();
    }

    @Override
    public IField getField() {
        return field;
    }

    @Override
    public int getMoveNumber() {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }
    
}
