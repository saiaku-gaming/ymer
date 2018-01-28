package com.valhallagame.ymer.message.party;

import java.lang.Integer;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetPartyByIdParameter {
  @NotNull
  Integer partyId;
}
