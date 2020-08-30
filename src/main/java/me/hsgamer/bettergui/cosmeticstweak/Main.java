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
    PropertyBuilder.registerItemProperty(Potion::new, "potion", "effect");
    PropertyBuilder.registerItemProperty(Firework::new, "firework");
    PropertyBuilder.registerItemProperty(LeatherColor::new, "color");
    if (!XMaterial.supports(12)) {
      PropertyBuilder.registerItemProperty(BannerBaseColor::new, "banner-color");
    }
    PropertyBuilder.registerItemProperty(BannerPattern::new, "banner-pattern", "pattern");
    if (XMaterial.supports(11)) {
      PropertyBuilder.registerItemProperty(PotionColor::new, "potion-color");
    }
  }
}
