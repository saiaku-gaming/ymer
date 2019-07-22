package com.valhallagame.ymer.message.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SteamLoginParameter {
    @NotEmpty
    String authSessionTicket;
}
