package com.valhallagame.ymer.message.trait;

import com.valhallagame.traitserviceclient.message.TraitType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SaveTraitBarIndexParameter {
  @NotNull
  TraitType name;

  int barIndex;
}
