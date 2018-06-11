package com.valhallagame.ymer.message.feat;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.featserviceclient.message.FeatName;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RemoveFeatParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  FeatName name;
}
