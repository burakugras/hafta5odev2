package kodlama.io.hafta5odev2.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTechnologyResponses {
    private int technologyId;
    private String technologyName;
}
