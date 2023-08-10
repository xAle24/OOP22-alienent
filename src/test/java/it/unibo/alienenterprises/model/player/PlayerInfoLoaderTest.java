package it.unibo.alienenterprises.model.player;

import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.view.ShipInfoLoader;
import it.unibo.alienenterprises.view.javafx.PlayerInfoLoaderImpl;

public class PlayerInfoLoaderTest {

    private final ShipInfoLoader infoLoader;
    private final Set<String> list = Set.of("standard", "tank", "sniper");

    public PlayerInfoLoaderTest(){
        this.infoLoader = new PlayerInfoLoaderImpl(list);
        this.infoLoader.load();
    }

    @Test
    public void spriteLoadTest(){
        for(final var s : list){
            System.out.println(infoLoader.getShipImage(s).get());
        }
    }
    
}
