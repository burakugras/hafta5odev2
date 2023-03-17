package kodlama.io.hafta5odev2.entities.concretes;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="language")
@Entity
public class Language {
    
    @Id
    @Column(name="technologyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;

    @Column(name="languageName")
    private String languageName;

    @OneToMany(mappedBy="language",cascade = CascadeType.ALL)//cascade ile ilişkili olan alt teknolojileri de siliyor sadece tek satır kod ile :)
    private List<Technology> technologies;
}
