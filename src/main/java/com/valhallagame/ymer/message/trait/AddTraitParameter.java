package com.valhallagame.ymer.message.trait;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.traitserviceclient.message.TraitType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddTraitParameter {
  @NotNull
  @CheckLowercase
  String characterName;

  @NotNull
  TraitType name;
}
