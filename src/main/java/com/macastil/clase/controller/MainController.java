package com.macastil.clase.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.macastil.clase.bean.db.User;
import com.macastil.clase.bean.input.Input;
import com.macastil.clase.bean.output.Respuesta;
import com.macastil.clase.bean.output.RespuestaCalc;
import com.macastil.clase.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping(value = "/add2", consumes = "application/json", produces = "application/json")
	public @ResponseBody User addNewUser2(@RequestBody User user) {
		logger.debug("Consultando usuario");
		return userRepository.save(user);
	}	
	
	@GetMapping(value = "/find", consumes = "application/json", produces = "application/json")
	public @ResponseBody Optional<User> getUser(@RequestBody User user) {
        logger.error("Consultando usuario, id: "+ user.getId());
		return userRepository.findById(user.getId());
	}

	@PutMapping(path = "/add") 
	public @ResponseBody Respuesta addNewUser(@RequestParam String name, @RequestParam(required = false) String email,
			@RequestParam String apellido, @RequestParam Integer cedula) {

		Respuesta respuesta = new Respuesta();

		if (email == null) {
			respuesta.setResultado("Error en agregar usuario");
			respuesta.setError("Email vacio");
			return respuesta;
		}
		System.out.println("consultando usuario");

		User userNuevo = new User();
		userNuevo.setName(name);
		try {
			userNuevo.setEmail(email);
		} catch (Exception e1) {
			respuesta.setResultado("Error en agregar usuario");
			respuesta.setError(e1.getMessage());
			return respuesta;
		}
		userNuevo.setApellido(apellido);
		userNuevo.setCedula(cedula);

		try {
			userRepository.save(userNuevo);
			respuesta.setResultado("Usuario salvado");
		} catch (Exception e) {
			// escribo lo que pasa cuando ocurre la exception
			respuesta.setResultado("Error en agregar usuario");
			respuesta.setError(e.getMessage());
		}
		return respuesta;
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	
	@GetMapping(value = "/consultaU", consumes = "application/json", produces = "application/json")
	public @ResponseBody Respuesta consutarUserCalc(@RequestBody Input input) {
		// buscar persna
		Optional<User> users = userRepository.findById(Integer.parseInt(input.getId_user()));
	    	
		Respuesta res = new Respuesta();
		
	    if(!users.isEmpty()) {
	    	User user = users.get();
	    	// armar la respuesta
	    	res.setNombrePersona(user.getName());
	    }
		// invocar el micro de calculadora
		
	    
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8090/" + input.getOperacion() + "/" + input.getUno() + "/" + input.getDos();
	    ResponseEntity<RespuestaCalc> resCalculadora = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){ });
	    
		// armar la respuesta
		res.setNumero(Double.valueOf(resCalculadora.getBody().getResultado()));
		
		return res;
	}
	
	


	// cossultar un usuario GET

	// borrar usuario DELETE

	// instertar varios usuario PUT

	// actualizar usuario POST

}