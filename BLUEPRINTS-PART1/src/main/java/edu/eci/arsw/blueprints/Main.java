package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("SpringContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        Point[] ejemplo = {new Point(1,2),new Point(1,2),new Point(1,2), new Point(3,4),new Point(1,2)};
        System.out.println(bps.getBlueprintsByAuthor("Anfemaru"));
        Blueprint rta = bps.filterBlueprint(new Blueprint("Anfemaru","miblueprint",ejemplo));
        System.out.println(rta.getPoints());
    }
}
