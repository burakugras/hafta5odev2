package kodlama.io.hafta5odev2.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hafta5odev2.business.abstracts.TechnologyService;
import kodlama.io.hafta5odev2.business.requests.CreateTechnologyRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateTechnologyRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllTechnologyResponses;

@RestController
@RequestMapping("/api/technologies")
public class TechnologiesController {
    private TechnologyService technologyService;

    public TechnologiesController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }
    
    @PostMapping("/add")
    public void add(CreateTechnologyRequest createTechnologyRequest) throws Exception{
        technologyService.add(createTechnologyRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(int tecnologyId){
        technologyService.deleteTechnology(tecnologyId);
    }

    @PutMapping("/update")
    public void update(UpdateTechnologyRequest updateTechnologyRequest) throws Exception{
        technologyService.updateTechnology(updateTechnologyRequest);
    }

    @GetMapping("/getall")
    public List<GetAllTechnologyResponses> getAll(){
        return technologyService.getAllTechnologies();
    }
}
