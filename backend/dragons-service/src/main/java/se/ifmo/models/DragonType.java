package se.ifmo.models;

import java.util.Comparator;
import lombok.Getter;

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

  public static final Comparator<DragonType> COMPARATOR =
      Comparator.comparingInt(DragonType::getPriority);
}
