package com.valhallagame.ymer.message.recipe;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.currencyserviceclient.model.CurrencyType;
import com.valhallagame.wardrobeserviceclient.message.WardrobeItem;
import java.util.Map;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ClaimRecipeParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  WardrobeItem recipe;

  @NotNull
  Map<CurrencyType, Integer> currencies;
}
