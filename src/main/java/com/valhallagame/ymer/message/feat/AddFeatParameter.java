package com.valhallagame.ymer.message.feat;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.featserviceclient.message.FeatName;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddFeatParameter {
  @NotNull
  @CheckLowercase
  String characterName;

  @NotNull
  FeatName name;
}
