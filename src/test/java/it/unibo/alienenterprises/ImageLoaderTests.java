package it.unibo.alienenterprises;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ImageLoader;
import it.unibo.alienenterprises.controller.ImageLoaderImpl;

public class ImageLoaderTests {

    private final ImageLoader imageLoader;
    
    public ImageLoaderTests(){
        this.imageLoader = new ImageLoaderImpl();
    }

    @Test
    public void loadingTest(){
        final var list = List.of("sniper","standard","tank");
        for(final var id : list){
            final var sprite = this.imageLoader.getSpriteFilePathOf(id);
            assertTrue(sprite.isPresent(),"sprite relative to " + id + " is not preset");
        }
    }

    @Test
    public void idNotPresentTest(){
        final var list = List.of("snr","standadarddd","taank");
        for(final var id : list){
            final var sprite = this.imageLoader.getSpriteFilePathOf(id);
            assertTrue(sprite.isEmpty(),"something has been loaded");
        }
    }
}
