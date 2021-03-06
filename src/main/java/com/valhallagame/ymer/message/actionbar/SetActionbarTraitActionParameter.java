package com.valhallagame.ymer.message.actionbar;

import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SetActionbarTraitActionParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  Integer index;

  String traitName;
}
