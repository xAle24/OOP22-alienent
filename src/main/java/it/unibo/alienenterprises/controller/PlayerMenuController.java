package it.unibo.alienenterprises.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.Statistic;

public interface PlayerMenuController {

    Set<String> getPlayerIds();

    Optional<Map<Statistic, Integer>> getStats(String id);

    Optional<String> getName(String id);

    Optional<String> getDescription(String id);

    Optional<String> getSpritePath(String id);

    void select(String id);

    void confirmSelection();
}