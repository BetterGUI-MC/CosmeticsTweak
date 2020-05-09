package me.hsgamer.bettergui.cosmeticstweak;

import me.hsgamer.bettergui.builder.PropertyBuilder;
import me.hsgamer.bettergui.cosmeticstweak.property.Potion;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  @Override
  public void onEnable() {
    PropertyBuilder.registerItemProperty("potion", Potion.class);
    PropertyBuilder.registerItemProperty("effect", Potion.class);
  }
}
