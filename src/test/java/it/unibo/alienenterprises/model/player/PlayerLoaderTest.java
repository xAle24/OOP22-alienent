package it.unibo.alienenterprises.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.PlayerClassLoaderImpl;

public class PlayerLoaderTest {

    @Test
    public void loadingTest(){
        var p = new PlayerClassLoaderImpl().loadStandardPlayer();
    }
    
}
