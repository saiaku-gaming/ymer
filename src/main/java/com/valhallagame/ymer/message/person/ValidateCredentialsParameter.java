package com.valhallagame.ymer.message.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ValidateCredentialsParameter {
  @NotBlank
  String displayUsername;

  @NotBlank
  String password;
}
