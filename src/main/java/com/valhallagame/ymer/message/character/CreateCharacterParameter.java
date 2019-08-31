package com.valhallagame.ymer.message.character;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CreateCharacterParameter {
  @NotBlank
  String displayCharacterName;

  @NotNull
  String startingClass;
}
