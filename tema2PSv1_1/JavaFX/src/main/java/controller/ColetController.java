package controller;

import model.Administrator;
import model.Colet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.AdministratorRepository;
import repositories.ColeteRepository;

import javax.persistence.Column;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/colete")
public class ColetController {

    @Autowired
    private ColeteRepository coleteRepository;

    @PostMapping
    public ResponseEntity<Colet> save(@RequestBody Colet colet){
        Colet colet2 = coleteRepository.save(colet);
        return new ResponseEntity<>(colet2,null, HttpStatus.OK);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Colet> update(@RequestBody Colet colet,@PathVariable Integer id){
        Colet colet1=coleteRepository.findById(id);
        colet1.setDest(colet.getDest());
        colet1.setGreutate(colet.getGreutate());
        colet1.setX(colet.getX());
        colet1.setY(colet.getY());
        colet1.setNumePostas(colet.getNumePostas());
        coleteRepository.save(colet1);
        return new ResponseEntity<>(colet1,null,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Colet>>  findAll() {//fara body       doar parametri pt filtrari
        List<Colet> list = coleteRepository.findAll();
        return new ResponseEntity<List<Colet>>(list,null,HttpStatus.OK);
    }

    @GetMapping(value = "/{numePostas}")
    public ResponseEntity<List<Colet>>  findAllByNumePostas(@PathVariable String numePostas) {//fara body       doar parametri pt filtrari
        List<Colet> list = coleteRepository.findAllByNumePostasContaining(numePostas);
        return new ResponseEntity<List<Colet>>(list,null,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){//@RequestBody Administrator administrator
        coleteRepository.delete(id);
        return new ResponseEntity<>(null,null,HttpStatus.OK);
    }

    /*public Colet save(@RequestBody Colet colet){
        coleteRepository.save(colet);
        return colet;
    }*/
}
