package com.valhallagame.ymer.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperCharacterParameter {
	@NotNull
	private String message;
	@NotNull
	private String targetDisplayCharacterName;
}
