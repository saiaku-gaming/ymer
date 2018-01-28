package com.valhallagame.ymer.message.feat;

import com.valhallagame.common.validation.CheckLowercase;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetFeatsParameter {
  @NotNull
  @CheckLowercase
  String characterName;
}
