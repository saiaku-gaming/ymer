package com.valhallagame.ymer.message.trait;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.traitserviceclient.message.AttributeType;
import com.valhallagame.traitserviceclient.message.TraitType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SkillTraitParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  TraitType name;

  @NotNull
  AttributeType selectedAttribute;

  Integer position;
}
