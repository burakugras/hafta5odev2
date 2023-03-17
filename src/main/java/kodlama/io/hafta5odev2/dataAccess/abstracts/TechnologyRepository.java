package kodlama.io.hafta5odev2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hafta5odev2.entities.concretes.Technology;

public interface TechnologyRepository extends JpaRepository<Technology,Integer>{
    boolean existsByTechnologyName(String technologyName);
}
