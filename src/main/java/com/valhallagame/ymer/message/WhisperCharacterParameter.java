package com.valhallagame.ymer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperCharacterParameter {
	private String message;
	private String targetDisplayCharacterName;
}
