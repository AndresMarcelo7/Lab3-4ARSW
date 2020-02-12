package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.FilterBlueprints;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

@Component
@Qualifier("subsampplingFilter")
public class SubsamplingFilter implements FilterBlueprints {

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        ArrayList<Point> aux = new ArrayList<Point>();

        for (int i=0; i<bp.getPoints().size(); i++) {
            if (i%2==0){
                aux.add(bp.getPoints().get(i));
            }
        }
        Point[] rta = toStaticList(aux);
        return new Blueprint(bp.getAuthor(),bp.getName(),rta);

    }

    private Point[] toStaticList(ArrayList<Point> arreglo){
        Point[] rta = new Point[arreglo.size()];
        for(int i=0; i<arreglo.size(); i++ ){
            rta[i] = arreglo.get(i);
        }
        return rta;
    }

}
