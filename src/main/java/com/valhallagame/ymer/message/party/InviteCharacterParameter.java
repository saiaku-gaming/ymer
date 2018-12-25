package com.valhallagame.ymer.message.party;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class InviteCharacterParameter {
  @NotBlank
  String displayCharacterName;
}
