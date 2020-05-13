package me.hsgamer.bettergui.cosmeticstweak;

import me.hsgamer.bettergui.builder.PropertyBuilder;
import me.hsgamer.bettergui.cosmeticstweak.property.BannerBaseColor;
import me.hsgamer.bettergui.cosmeticstweak.property.BannerPattern;
import me.hsgamer.bettergui.cosmeticstweak.property.Firework;
import me.hsgamer.bettergui.cosmeticstweak.property.LeatherColor;
import me.hsgamer.bettergui.cosmeticstweak.property.Potion;
import me.hsgamer.bettergui.cosmeticstweak.property.PotionColor;
import me.hsgamer.bettergui.lib.xseries.XMaterial;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  @Override
  public void onEnable() {
    PropertyBuilder.registerItemProperty("potion", Potion.class);
    PropertyBuilder.registerItemProperty("effect", Potion.class);
    PropertyBuilder.registerItemProperty("firework", Firework.class);
    PropertyBuilder.registerItemProperty("color", LeatherColor.class);
    if (!XMaterial.supports(12)) {
      PropertyBuilder.registerItemProperty("banner-color", BannerBaseColor.class);
    }
    PropertyBuilder.registerItemProperty("banner-pattern", BannerPattern.class);
    PropertyBuilder.registerItemProperty("pattern", BannerPattern.class);
    if (XMaterial.supports(11)) {
      PropertyBuilder.registerItemProperty("potion-color", PotionColor.class);
    }
  }
}
