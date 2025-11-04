package se.ifmo.models;

import lombok.Getter;

import java.util.Comparator;

@Getter
public enum DragonType {
    UNDERGROUND(1),
    AIR(2),
    WATER(3),
    FIRE(4);

    private final int priority;

    DragonType(int priority) {
        this.priority = priority;
    }

    public static final Comparator<DragonType> COMPARATOR = Comparator.comparingInt(DragonType::getPriority);
}
