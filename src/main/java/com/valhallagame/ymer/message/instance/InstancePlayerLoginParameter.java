package com.valhallagame.ymer.message.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class InstancePlayerLoginParameter {
  @NotBlank
  String token;

  @NotBlank
  String gameSessionId;
}
