/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Component
@Qualifier("inMemoryPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("Pepe", "P1",pts);
        Blueprint bp2=new Blueprint("Pepe", "P2",pts);
        Blueprint bp3=new Blueprint("Juan", "P3",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
    }    
    //Si se daña un pixel significa que circuito generador de matrices dejo de funcionar
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint bp = blueprints.get(new Tuple<>(author, bprintname));

        if (bp == null){
            throw new BlueprintNotFoundException("No existe este Blueprint");
        }
        return bp;
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> bpsrta = new HashSet<Blueprint>();
        for (Tuple<String,String> bps : blueprints.keySet()){
            if (bps.o1.equals(author)){
                bpsrta.add(blueprints.get(bps));
            }
        }
        if (bpsrta.isEmpty()){
            throw new BlueprintNotFoundException("No existe este autor");
        }
        return bpsrta;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        if (blueprints.isEmpty()){
            throw new BlueprintNotFoundException("No Hay Datos");
        }
        return new HashSet<Blueprint>(blueprints.values());
    }

    @Override
    public void updateBlueprint(Blueprint bp, String author, String name) throws BlueprintNotFoundException {
        if (bp == null){
            throw new BlueprintNotFoundException("No existe este Blueprint");
        }
        Blueprint old = getBlueprint(author,name);
        old.setPoints(bp.getPoints());

    }


}
