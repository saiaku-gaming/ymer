package com.valhallagame.ymer.message.feat;

import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetFeatsParameter {
  @NotBlank
  @CheckLowercase
  String characterName;
}
