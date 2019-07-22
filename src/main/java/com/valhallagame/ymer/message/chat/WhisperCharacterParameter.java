package com.valhallagame.ymer.message.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class WhisperCharacterParameter {
  @NotNull
  String message;

    @NotNull
  String targetDisplayCharacterName;
}
