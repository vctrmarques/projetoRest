package joao.io.projetoRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import joao.io.projeto.entity.User;

public class UserJsonTeste {

	@Test
	public void deveVerificarPrimeiroNivel() {
		given()
		.when()
			.get("http://localhost:8081/usuario/")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name", containsString("Silva"));
		
	}
	
	@Test
	public void salvarUsuarioUsandoMap() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "Usuario da Silva Teste");
		params.put("cpf", "385.389.930-70");
		params.put("sexo", "masculino");
		params.put("email", "teste@teste.com.br");
		params.put("nacionalidade", "masculino");
		params.put("naturalidade", "masculino");
		params.put("dataNascimento", "2020-06-04");
		
		given()
			.log().all()
			.contentType("application/json")
			.body(params)
		.when()
			.post("http://localhost:8081/usuario/")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Usuario da Silva Teste"))
			.body("cpf", is("385.389.930-70"));

	}
	
	@Test
	public void deserealizarObejtoSalvarUsuario() {

		User user = new User();
	  
	  User usuarioInserido =  given() 
	  .log().all() 
	  .contentType("application/json")
	  .body(user) .when() .post("http://localhost:8081/usuario") .then()
	  .log().all() .statusCode(201) .body("id", is(notNullValue()))
	  .extract().body().as(User.class);
	 
	  System.out.println(usuarioInserido);
	  Assert.assertThat(usuarioInserido.getId(),notNullValue());
	  Assert.assertEquals("Usuario da Silva Teste", usuarioInserido.getName());
	  }
	 
	
	public void salvarUsuarioViaXMLUsandoObejto() {
		User user = new User();
		
		given()
			.log().all()
			.contentType(ContentType.XML)
			.body(user)
		.when()
			.post("http://localhost:8081/usuario")
		.then()
			.log().all()
			.statusCode(201)
			.body("user.id", is(notNullValue()))
			.body("user.name", is("Usuario da Silva Teste"))
			.body("user.cpf", is("385.389.930-70"));
		
	}
}
