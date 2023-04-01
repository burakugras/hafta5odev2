package kodlama.io.hafta5odev2.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hafta5odev2.business.abstracts.TechnologyService;
import kodlama.io.hafta5odev2.business.requests.CreateTechnologyRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateTechnologyRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllTechnologyResponses;
import kodlama.io.hafta5odev2.business.responses.GetByIdTechnologyResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/technologies")
@AllArgsConstructor
public class TechnologiesController {
    private TechnologyService technologyService;
    
    @PostMapping()
    @ResponseStatus(code=HttpStatus.CREATED)
    public void add(@RequestBody CreateTechnologyRequest createTechnologyRequest) throws Exception{
        technologyService.add(createTechnologyRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int tecnologyId){
        technologyService.deleteTechnology(tecnologyId);
    }

    @PutMapping
    public void update(@RequestBody UpdateTechnologyRequest updateTechnologyRequest) throws Exception{
        technologyService.updateTechnology(updateTechnologyRequest);
    }

    @GetMapping()
    public List<GetAllTechnologyResponses> getAll(){
        return technologyService.getAllTechnologies();
    }

    @GetMapping("/{id}")
    public GetByIdTechnologyResponse getByIdTechnologyResponse(@PathVariable int id){
        return this.technologyService.getByIdTechnologyResponse(id);
    }
}
