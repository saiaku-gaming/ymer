package com.valhallagame.ymer.message.character;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EquippedItemParameter {
  @NotNull
  String itemSlot;

  @NotNull
  String armament;

  @NotNull
  String armor;
}
