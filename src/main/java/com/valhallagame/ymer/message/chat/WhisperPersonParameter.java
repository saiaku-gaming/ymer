package com.valhallagame.ymer.message.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class WhisperPersonParameter {
  @NotNull
  String message;

    @NotNull
  String targetDisplayUsername;
}
