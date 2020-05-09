package me.hsgamer.bettergui.cosmeticstweak.property;

import java.util.Optional;
import me.hsgamer.bettergui.cosmeticstweak.ItemUtils;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BannerBaseColor extends ItemProperty<String, Optional<DyeColor>> {

  public BannerBaseColor(Icon icon) {
    super(icon);
  }

  @Override
  public Optional<DyeColor> getParsed(Player player) {
    return ItemUtils.parseDyeColor(parseFromString(getValue(), player));
  }

  @SuppressWarnings("deprecated")
  @Override
  public ItemStack parse(Player player, ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta instanceof BannerMeta) {
      BannerMeta bannerMeta = (BannerMeta) itemMeta;
      getParsed(player).ifPresent(bannerMeta::setBaseColor);
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  @SuppressWarnings("deprecated")
  @Override
  public boolean compareWithItemStack(Player player, ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    Optional<DyeColor> optional = getParsed(player);
    return optional.isPresent() && itemMeta instanceof BannerMeta && ((BannerMeta) itemMeta)
        .getBaseColor().equals(optional.get());
  }
}
