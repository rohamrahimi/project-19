package model.Cards;

import model.Buff.Buff;
import model.Cell;

import java.util.ArrayList;

public abstract class SoldierCard extends Card {
    private int ap;
    private int hp;
    private SoldierTypes type;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private int flagNumber;
    private int attackRange;
    private boolean isAttackedThisTurn;
    private boolean isMovedThisTurn;
    public SoldierCard() {
        super();
    }

    public SoldierCard(int cardId, String name, int price, int mana, int ap, int hp, SoldierTypes type, int attackRange, String description) {
        super(cardId, name, price, mana, description);
        this.ap = ap;
        this.hp = hp;
        this.type = type;
        this.attackRange = attackRange;
    }

    public SoldierCard(SoldierCard soldierCard) {
        super(soldierCard);
        this.ap = soldierCard.ap;
        this.hp = soldierCard.hp;
        this.type = soldierCard.type;
        this.attackRange = soldierCard.attackRange;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getHp() {
        return hp;
    }

    public void decreaseHP(int amount) {
        this.hp -= amount;
    }

    public SoldierTypes getType() {
        return type;
    }

    public void setType(SoldierTypes type) {
        this.type = type;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getFlagNumber() {
        return flagNumber;
    }

    public void setFlagNumber(int flagNumber) {
        this.flagNumber = flagNumber;
    }

    public void changeAp(int number) {
        this.ap += number;
    }

    public void changeHp(int number) {
        this.hp += number;
    }

    public void attack(Card opponentCard) {
    }

    public boolean isAttackedThisTurn() {
        return isAttackedThisTurn;
    }

    public SoldierCard setAttackedThisTurn(boolean attackedThisTurn) {
        isAttackedThisTurn = attackedThisTurn;
        return this;
    }

    public boolean isMovedThisTurn() {
        return isMovedThisTurn;
    }

    public SoldierCard setMovedThisTurn(boolean movedThisTurn) {
        isMovedThisTurn = movedThisTurn;
        return this;
    }

    public void counterAttack(SoldierCard target) {
        target.decreaseHP(this.getAp());
    }
    public void attack (SoldierCard terget){
        terget.decreaseHP(this.getAp());
        // TODO: 2019-05-05 cast buff
    }
    public String toBattleFormat(int x, int y) {
        return getInBattleCardId() + " : "
                + getName() + ", "
                + "health : " + getHp() + ", "
                + "location : " + "(" + x + ", " + y + "), "
                + "Power : " + getAp();
    }

    public abstract String toInfoString();
    public boolean targetIsInRange(Cell attackerCell,Cell targetCell){
        switch (this.getType()){
            case MELEE:
                return (Math.abs(attackerCell.getYCoordinate()-targetCell.getYCoordinate()) <= 1 &&
                Math.abs(attackerCell.getXCoordinate() - targetCell.getXCoordinate()) <= 1 );
            case RANGED:
                if(Math.abs(attackerCell.getYCoordinate()-targetCell.getYCoordinate()) <= 1 &&
                        Math.abs(attackerCell.getXCoordinate() - targetCell.getXCoordinate()) <= 1)
                    return false;
                else
                    return (Math.abs(attackerCell.getYCoordinate()-targetCell.getYCoordinate()) <= this.getAttackRange() &&
                        Math.abs(attackerCell.getXCoordinate() - targetCell.getXCoordinate()) <= this.getAttackRange());
            case HYBRID:
                return (Math.abs(attackerCell.getYCoordinate()-targetCell.getYCoordinate()) <= this.getAttackRange() &&
                        Math.abs(attackerCell.getXCoordinate() - targetCell.getXCoordinate()) <= this.getAttackRange());
        }
        return false;
    }
}
