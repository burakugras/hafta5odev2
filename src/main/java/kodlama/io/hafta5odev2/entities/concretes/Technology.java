package kodlama.io.hafta5odev2.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="technology")
@Entity
public class Technology {
    @Id
    @Column(name="technologyId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int technologyId;

    @Column(name="technologyName")
    private String technologyName;  

    @ManyToOne
    @JoinColumn(name="languageId")
    private Language language;
}
