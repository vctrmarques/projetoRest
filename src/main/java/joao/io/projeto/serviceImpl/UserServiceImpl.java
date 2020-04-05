package joao.io.projeto.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import joao.io.projeto.entity.User;
import joao.io.projeto.repository.UserRepository;
import joao.io.projeto.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public Optional<User> findByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}
	
}
