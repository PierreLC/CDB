package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

}
