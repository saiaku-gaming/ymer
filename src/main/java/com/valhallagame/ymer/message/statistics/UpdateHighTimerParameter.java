package com.valhallagame.ymer.message.statistics;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.statisticsserviceclient.message.StatisticsKey;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateHighTimerParameter {
  @NotNull
  @CheckLowercase
  String characterName;

  @NotNull
  StatisticsKey key;

  @NotNull
  float timer;
}
