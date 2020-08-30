package me.hsgamer.bettergui.cosmeticstweak.property;

import java.util.ArrayList;
import java.util.List;
import me.hsgamer.bettergui.cosmeticstweak.ItemUtils;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import me.hsgamer.bettergui.util.common.CommonUtils;
import org.bukkit.block.banner.Pattern;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BannerPattern extends ItemProperty<Object, List<Pattern>> {

  public BannerPattern(Icon icon) {
    super(icon);
  }

  @Override
  public List<Pattern> getParsed(Player player) {
    List<Pattern> list = new ArrayList<>();
    for (String s : CommonUtils.createStringListFromObject(getValue(), true)) {
      ItemUtils.parsePattern(parseFromString(s, player)).ifPresent(list::add);
    }
    return list;
  }

  @Override
  public ItemStack parse(Player player, ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (itemMeta instanceof BannerMeta) {
      BannerMeta bannerMeta = (BannerMeta) itemMeta;
      bannerMeta.setPatterns(getParsed(player));
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  @Override
  public boolean compareWithItemStack(Player player, ItemStack itemStack) {
    List<Pattern> list1 = getParsed(player);
    if (list1.isEmpty() && (!itemStack.hasItemMeta() || !(itemStack
        .getItemMeta() instanceof BannerMeta))) {
      return true;
    }

    List<Pattern> list2 = ((BannerMeta) itemStack.getItemMeta()).getPatterns();
    if (list1.size() != list2.size()) {
      return false;
    }
    for (Pattern pattern : list1) {
      if (!list2.contains(pattern)) {
        return false;
      }
    }
    return true;
  }
}
