package it.unibo.alienenterprises.model.player;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.PlayerClassLoaderImpl;

public class PlayerLoaderTest {

    @Test
    public void loadingTest(){
        new PlayerClassLoaderImpl().loadStandardPlayer();
    }
    
}
