package com.macastil.clase.bean.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
	private String id_user;
	private String operacion;
	private Integer uno;
	private Integer dos;
	
}
