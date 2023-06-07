package it.unibo.alienenterprises.model;

import java.util.*;

import it.unibo.alienenterprises.model.api.UserAccount;

public class UserAccountImpl implements UserAccount{

    private int money;
    //private String nickname;
    //private int highscore;
    private Map<Integer,Integer> inventory =  new HashMap<>(); //contiene l'ID e il corrispondente livello di acquisto

    public UserAccountImpl(int money) {
        this.money =  money;
    }

    @Override
    public void updateInventory(int ID) {
        if(!inventory.containsKey(ID)) {
            inventory.put(ID, 1);
        } else {
            this.inventory.computeIfPresent(ID, (k,v) -> v+1);
        }
        System.out.print(this.inventory.size());
        
    }

    @Override
    public void save() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public int getCurrLevel(int ID) {
        return (inventory.containsKey(ID)) ? this.inventory.get(ID) : 0;
    }

    @Override
    public void setMoney(int changeMoney) {
        this.money +=changeMoney;
    }

    @Override
    public Set<Integer> getInventoryID() {
        return inventory.keySet();

    }
    
}
