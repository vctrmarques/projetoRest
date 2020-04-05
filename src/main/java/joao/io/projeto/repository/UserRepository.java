package joao.io.projeto.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import joao.io.projeto.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findByName(String nome);
	
	User findByEmail(String email);
	
	Optional<User> findByCpf(String cpf);
	
	User findBySexo(String sexo);
	
	User findByNaturalidade(String naturalidade);
	
	User findByNacionalidade(String nacionalidade);
}
