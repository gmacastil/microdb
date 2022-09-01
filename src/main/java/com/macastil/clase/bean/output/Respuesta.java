package com.macastil.clase.bean.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {
	private String resultado;
	private String error;
	private String nombrePersona;
	private Double numero;
}
