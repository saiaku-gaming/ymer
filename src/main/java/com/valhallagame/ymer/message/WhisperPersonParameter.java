package com.valhallagame.ymer.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhisperPersonParameter {
	@NotNull
	private String message;
	@NotNull
	private String targetDisplayUsername;
}
