package controller;

import model.CoordonatorActivitate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.CoordonatorRepository;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/coordonatoractivitate")
public class CoordonatorActivitateController {

    @Autowired
    private CoordonatorRepository coordonatorRepository;

    @PostMapping
    public ResponseEntity<CoordonatorActivitate> save(@RequestBody CoordonatorActivitate coordonatorActivitate){
        coordonatorRepository.save(coordonatorActivitate);
        CoordonatorActivitate coordonatorActivitate2=coordonatorRepository.save(coordonatorActivitate);
        return new ResponseEntity<>(coordonatorActivitate2,null, HttpStatus.OK);
    }


    @PutMapping (value = "/{id}")
    public ResponseEntity<CoordonatorActivitate> update(@RequestBody CoordonatorActivitate coordonatorActivitate,@PathVariable Integer id){
        CoordonatorActivitate coordonatorActivitate1=coordonatorRepository.findById(id);
        coordonatorActivitate1.setNumeUtilizator(coordonatorActivitate.getNumeUtilizator());
        coordonatorActivitate1.setParola(coordonatorActivitate.getParola());
        coordonatorRepository.save(coordonatorActivitate1);
        return new ResponseEntity<>(coordonatorActivitate1,null,HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<CoordonatorActivitate>> findAll(){//fara body      doar parametri pentru filtrari
        List<CoordonatorActivitate> list=coordonatorRepository.findAll();
        return new ResponseEntity<List<CoordonatorActivitate>>(list,null,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {//@RequestBody CoordonatorActivitate
        coordonatorRepository.delete(id);
        return new ResponseEntity<>(null,null,HttpStatus.OK);
    }


}
