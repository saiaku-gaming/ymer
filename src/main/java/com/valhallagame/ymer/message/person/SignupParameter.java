package com.valhallagame.ymer.message.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SignupParameter {
    @NotNull
  String displayUsername;

    @NotNull
  String password;
}
