package it.unibo.alienenterprises.model.player;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.view.PlayerClassInfoLoader;
import it.unibo.alienenterprises.view.PlayerClassInfoLoaderImpl;

public class PlayerInfoLoaderTest {

    private final PlayerClassInfoLoader infoLoader;

    public PlayerInfoLoaderTest(){
        this.infoLoader = new PlayerClassInfoLoaderImpl(List.of("standard"));
        this.infoLoader.load();
    }

    @Test
    public void infoLoaderTest(){
        var info = this.infoLoader.getPlayerClassInfo("standard");
        assertTrue(info.isPresent(), "the name is not loaded");
        System.out.println(info);
    }
    
}
