package me.hsgamer.bettergui.cosmeticstweak.property;

import java.util.Optional;
import me.hsgamer.bettergui.cosmeticstweak.ItemUtils;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherColor extends ItemProperty<String, Optional<Color>> {

  public LeatherColor(Icon icon) {
    super(icon);
  }

  @Override
  public Optional<Color> getParsed(Player player) {
    return ItemUtils.parseColor(parseFromString(getValue(), player));
  }

  @Override
  public ItemStack parse(Player player, ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta instanceof LeatherArmorMeta) {
      getParsed(player).ifPresent(((LeatherArmorMeta) itemMeta)::setColor);
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  @Override
  public boolean compareWithItemStack(Player player, ItemStack itemStack) {
    Optional<Color> optional = getParsed(player);
    if (optional.isPresent() && itemStack.hasItemMeta()) {
      ItemMeta itemMeta = itemStack.getItemMeta();
      if (itemMeta instanceof LeatherArmorMeta) {
        return ((LeatherArmorMeta) itemMeta).getColor().equals(optional.get());
      }
    }
    return true;
  }
}
