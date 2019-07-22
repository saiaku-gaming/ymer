package com.valhallagame.ymer.message.friend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SendCharacterInviteParameter {
    @NotNull
  String displayCharacterName;
}
