package me.hsgamer.bettergui.cosmeticstweak.property;

import java.util.ArrayList;
import java.util.List;
import me.hsgamer.bettergui.cosmeticstweak.ItemUtils;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import me.hsgamer.bettergui.util.common.CommonUtils;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Firework extends ItemProperty<Object, List<FireworkEffect>> {

  public Firework(Icon icon) {
    super(icon);
  }

  @Override
  public List<FireworkEffect> getParsed(Player player) {
    List<FireworkEffect> fireworkEffects = new ArrayList<>();
    for (String s : CommonUtils.createStringListFromObject(getValue(), true)) {
      ItemUtils.parseFireworkEffect(s.trim()).ifPresent(fireworkEffects::add);
    }
    return fireworkEffects;
  }

  @Override
  public ItemStack parse(Player player, ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    List<FireworkEffect> fireworkEffects = getParsed(player);
    if (itemMeta instanceof FireworkMeta) {
      ((FireworkMeta) itemMeta).addEffects(fireworkEffects);
    } else if (itemMeta instanceof FireworkEffectMeta && !fireworkEffects.isEmpty()) {
      ((FireworkEffectMeta) itemMeta).setEffect(getParsed(player).get(0));
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  @Override
  public boolean compareWithItemStack(Player player, ItemStack itemStack) {
    List<FireworkEffect> list1 = getParsed(player);
    if (list1.isEmpty() && !itemStack.hasItemMeta()) {
      return true;
    }
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta instanceof FireworkEffectMeta) {
      return list1.contains(((FireworkEffectMeta) itemMeta).getEffect());
    } else if (itemMeta instanceof FireworkMeta) {
      List<FireworkEffect> list2 = ((FireworkMeta) itemMeta).getEffects();
      for (FireworkEffect fireworkEffect : list1) {
        if (!list2.contains(fireworkEffect)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
