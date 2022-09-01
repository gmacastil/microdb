package com.macastil.clase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.macastil.clase.bean.output.Respuesta;
import com.macastil.clase.controller.MainController;

@SpringBootTest
class MicrodbApplicationTests {

	@Autowired
	private MainController main;

	@Test
	void adduser() {
		Respuesta res = main.addNewUser("javier", "a@a.com", "paez", 142452345);
		assertThat(res.getError()).isNotNull();
	}

}
