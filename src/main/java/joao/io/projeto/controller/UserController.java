package joao.io.projeto.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joao.io.projeto.entity.User;
import joao.io.projeto.service.UserService;
import joao.io.projeto.repository.UserRepository;


@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private UserRepository userService;

	@GetMapping(value = "/")
	public List<User> getAllStudents() {
		return userService.findAll();
	}

	@GetMapping(value = "/{cpf}")
	public ResponseEntity<User> getByCpf(@PathVariable(value = "cpf") String cpf) {
		
		Optional<User> user = userService.findByCpf(cpf);
        if(user.isPresent())
        	return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping(value = "/")
    public User save(@Valid @RequestBody User user) {
        return userService.save(user);
    }
	
	@PutMapping(value = "/{cpf}")
    public ResponseEntity<User> alterar(@PathVariable(value = "cpf") String cpf, @Valid @RequestBody User newUser)
    {
		Optional<User> oldUser = userService.findByCpf(cpf);
        if(oldUser.isPresent()){
            User user = oldUser.get();
            
            user.setName(newUser.getName());
            user.setCpf(newUser.getCpf());
            user.setEmail(newUser.getEmail());
            user.setSexo(newUser.getSexo());
            user.setNacionalidade(newUser.getNacionalidade());
            user.setNaturalidade(newUser.getNaturalidade());
            user.setDataNascimento(newUser.getDataNascimento());
            
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<Object> deleteStudent(@PathVariable String cpf) {
    	
    	Optional<User> user = userService.findByCpf(cpf);
         if(user.isPresent()){
             userService.delete(user.get());
             return new ResponseEntity<Object>(HttpStatus.OK);
         }
         else
        	 return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
     }
    	
}
