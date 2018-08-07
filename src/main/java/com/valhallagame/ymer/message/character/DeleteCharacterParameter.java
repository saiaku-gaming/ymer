package com.valhallagame.ymer.message.character;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class DeleteCharacterParameter {
  @NotBlank
  String displayCharacterName;
}