package com.valhallagame.ymer.message.character;

import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetOwnedCharacterParameter {
  @CheckLowercase
  @NotBlank
  String characterName;
}
