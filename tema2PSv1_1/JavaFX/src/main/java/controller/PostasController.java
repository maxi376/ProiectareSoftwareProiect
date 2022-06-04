package controller;

import model.Postas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.PostasRepository;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/postas")
public class PostasController {

    @Autowired
    private PostasRepository postasRepository;

    @PostMapping
    public ResponseEntity<Postas> save(@RequestBody Postas postas){
        Postas postas2= postasRepository.save(postas);
        return new ResponseEntity<>(postas2,null, HttpStatus.OK);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Postas> update(@RequestBody Postas postas,@PathVariable Integer id){
        Postas postas1=postasRepository.findById(id);
        postas1.setNumeUtilizator(postas.getNumeUtilizator());
        postas1.setParola(postas.getParola());
        postasRepository.save(postas1);
        return new ResponseEntity<>(postas1,null,HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<Postas>>  findAll() {//fara body       doar parametri pt filtrari
        List<Postas> list = postasRepository.findAll();
        return new ResponseEntity<List<Postas>>(list,null,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){//@RequestBody Administrator administrator
        postasRepository.delete(id);
        return new ResponseEntity<>(null,null,HttpStatus.OK);
    }

    @GetMapping     (value="/{nume}")
    public ResponseEntity<List<Postas>> getPostasiByNumeUtilizator(@PathVariable String nume){
        List<Postas> postasi=postasRepository.findAllByNumeUtilizatorContaining(nume);
        return new ResponseEntity<>(postasi,null,HttpStatus.OK);
    }
}
