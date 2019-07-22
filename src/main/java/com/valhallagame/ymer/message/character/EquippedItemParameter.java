package com.valhallagame.ymer.message.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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

    String metaData;
}
