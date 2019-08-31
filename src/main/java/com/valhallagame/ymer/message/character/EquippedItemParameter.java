package com.valhallagame.ymer.message.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EquippedItemParameter {
  @NotBlank
  String itemSlot;

  @NotBlank
  String armament;

  @NotBlank
  String armor;

  String metaData;
}
