package it.unibo.alienenterprises.model.player;

import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.PlayerInfoLoaderImpl;
import it.unibo.alienenterprises.view.ShipInfoLoader;

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
            System.out.println(infoLoader.getShipSpriteFile(s).get());
        }
    }
    
}
