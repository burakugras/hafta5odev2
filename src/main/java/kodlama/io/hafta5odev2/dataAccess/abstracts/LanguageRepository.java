package kodlama.io.hafta5odev2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hafta5odev2.entities.concretes.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer>{
    boolean existsByLanguageName(String languageName);
}
