package it.unibo.alienenterprises.model.player;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.view.PlayerClassInfoLoader;
import it.unibo.alienenterprises.view.PlayerClassInfoLoaderImpl;

public class PlayerInfoLoaderTest {

    private final PlayerClassInfoLoader infoLoader;
    private final List<String> list = List.of("standard", "tank", "sniper");

    public PlayerInfoLoaderTest(){
        this.infoLoader = new PlayerClassInfoLoaderImpl(list);
        this.infoLoader.load();
    }

    @Test
    public void infoLoaderTest(){
        for(final var s : list){
            var info = this.infoLoader.getPlayerClassInfo(s);
            assertTrue(info.isPresent(), "the name is not loaded");
            System.out.println(info);
        }
    }
    
}
