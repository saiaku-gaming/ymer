package com.valhallagame.ymer.message.party;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class InviteCharacterParameter {
    @NotNull
  String displayCharacterName;
}
