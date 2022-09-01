package com.macastil.clase.bean.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaCalc {
	private int a;
	private int b;
	private int resultado;
	private String error = "NO";
}