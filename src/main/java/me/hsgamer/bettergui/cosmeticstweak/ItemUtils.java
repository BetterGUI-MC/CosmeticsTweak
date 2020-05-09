package me.hsgamer.bettergui.cosmeticstweak;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.hsgamer.bettergui.util.Validate;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;

public class ItemUtils {

  private ItemUtils() {
    // IGNORED
  }

  public static Optional<Color> parseColor(String input) {
    Optional<DyeColor> optional = parseDyeColor(input);
    if (optional.isPresent()) {
      return optional.map(DyeColor::getColor);
    }

    String[] split = input.trim().split(":", 3);

    int red = Validate.getNumber(split[0]).orElse(BigDecimal.ZERO).intValue();
    int green =
        split.length > 1 ? Validate.getNumber(split[1]).orElse(BigDecimal.ZERO).intValue() : 0;
    int blue =
        split.length > 2 ? Validate.getNumber(split[2]).orElse(BigDecimal.ZERO).intValue() : 0;

    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      return Optional.empty();
    }

    return Optional.of(Color.fromRGB(red, green, blue));
  }

  public static Optional<DyeColor> parseDyeColor(String input) {
    try {
      return Optional.of(DyeColor.valueOf(input.trim().toUpperCase()));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }

  public static Optional<Pattern> parsePattern(String input) {
      String[] split = input.trim().split(":", 2);
      if (split.length != 2) {
        return Optional.empty();
      }
      try {
        Optional<DyeColor> optional = parseDyeColor(split[1].trim());
        if (optional.isPresent()) {
          return Optional.of(new Pattern(optional.get(), PatternType.valueOf(split[0].trim().toUpperCase())));
        }
      } catch (IllegalArgumentException e) {
        // IGNORED
      }
      return Optional.empty();
  }

  public static Optional<FireworkEffect> parseFireworkEffect(String input) {
    String[] data = input.trim().split(" ", 5);
    FireworkEffect.Builder builder = FireworkEffect.builder();

    try {
      builder.with(FireworkEffect.Type.valueOf(data[0].toUpperCase()));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }

    List<Color> color = new ArrayList<>();
    for (String colorString : data[1].split("-")) {
      parseColor(colorString).ifPresent(color::add);
    }
    if (!color.isEmpty()) {
      builder.withColor(color);
    }

    List<Color> fade = new ArrayList<>();
    for (String fadeString : data[2].split("-")) {
      parseColor(fadeString).ifPresent(fade::add);
    }
    if (!fade.isEmpty()) {
      builder.withFade(fade);
    }

    builder.flicker(Boolean.parseBoolean(data[3]));
    builder.trail(Boolean.parseBoolean(data[4]));

    return Optional.of(builder.build());
  }
}
