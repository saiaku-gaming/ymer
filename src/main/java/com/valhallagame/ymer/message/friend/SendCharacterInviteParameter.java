package com.valhallagame.ymer.message.friend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SendCharacterInviteParameter {
    @NotBlank
  String displayCharacterName;
}
