package com.valhallagame.ymer.message.trait;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.traitserviceclient.message.TraitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SpecializeTraitParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  TraitType name;

  @NotNull
  @Min(0)
  Integer specialization;

  @NotNull
  Integer position;
}
