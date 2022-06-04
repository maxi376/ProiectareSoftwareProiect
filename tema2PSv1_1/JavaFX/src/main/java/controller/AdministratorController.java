package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.AdministratorRepository;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/administrator")
public class AdministratorController {

    //ArrayList<Administrator> list=new ArrayList<Administrator>();

    @Autowired
    private AdministratorRepository administratorRepository;

    @PostMapping
    public ResponseEntity<Administrator> save(@RequestBody Administrator administrator){
        Administrator administrator2= administratorRepository.save(administrator);
        return new ResponseEntity<>(administrator2,null,HttpStatus.OK);
    }

    //@PutMapping



    @GetMapping
    public ResponseEntity<List<Administrator>>  findAll() {//fara body       doar parametri pt filtrari
        List<Administrator> list = administratorRepository.findAll();
        return new ResponseEntity<List<Administrator>>(list,null,HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){//@RequestBody Administrator administrator
        administratorRepository.delete(id);
        return new ResponseEntity<>(null,null,HttpStatus.OK);
    }
}
