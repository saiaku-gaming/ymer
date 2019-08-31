package com.valhallagame.ymer.message.person;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SignupParameter {
  @NotNull
  String displayUsername;

  @NotNull
  String password;
}
