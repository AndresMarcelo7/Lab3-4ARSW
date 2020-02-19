/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    @Autowired
    @Qualifier("bpServices")
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> manejadorBlueprints()  {
        //obtener datos que se enviarán a través del API
        try {
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("No hay registros de blueprints!",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value="/{author}")
    public ResponseEntity<?> manejadorBlueprintsByAuthor(@PathVariable String author)  {
        //obtener datos que se enviarán a través del API
        try {
            return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("No existe el autor!",HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value="/{author}/{name}")
    public ResponseEntity<?> manejadorBlueprint(@PathVariable String author,@PathVariable String name)  {
        //obtener datos que se enviarán a través del API
        try {
            return new ResponseEntity<>(bps.getBlueprint(author, name), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            return new ResponseEntity<>("No existe este Blueprint !",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> manejadorPostBlueprint(@RequestBody Blueprint o){
        try {
            bps.addNewBlueprint(o);
            return new ResponseEntity<>("Tu registro fue exitoso",HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
        //curl -i -X POST -HContent-Type:application/json -HAccept:application/json http://localhost:8080 -d '{"author":"Pepe","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"P1"}'
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{author}/{name}")
    public ResponseEntity<?> manejadorPutBlueprint(@PathVariable String author, @PathVariable String name, @RequestBody Blueprint bp){
        try {
            bps.updateBlueprint(bp, author, name);
            return new ResponseEntity<>("Actualizacion exitosa",HttpStatus.CREATED);
        }
        catch(BlueprintNotFoundException ex){
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
}
