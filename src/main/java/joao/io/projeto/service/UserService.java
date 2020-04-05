package joao.io.projeto.service;

import java.util.List;
import java.util.Optional;

import joao.io.projeto.entity.User;

public interface UserService {

	public List<User> findAll();
	
	public Optional<User> findByCpf(String cpf);

	public void save(User user);
	
	public void deleteUser(User user);
	
	public User saveOrUpdateUser(User user);
}
