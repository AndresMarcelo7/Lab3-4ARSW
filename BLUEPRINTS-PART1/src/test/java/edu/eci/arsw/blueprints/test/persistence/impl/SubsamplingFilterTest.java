package edu.eci.arsw.blueprints.test.persistence.impl;
import edu.eci.arsw.blueprints.filter.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SubsamplingFilterTest {
    @Test
    public void subsamplingFiltering(){
        SubsamplingFilter prueba = new SubsamplingFilter();
        Point puntos[] = {new Point(0,0), new Point(1,1), new Point(2,2), new Point(3,3), new Point(4,4)};

        Blueprint bp = new Blueprint("marcelo","ubate",puntos);
        bp = prueba.filterBlueprint(bp);

        List<Point> puntosN = new ArrayList<>();
        puntosN.add(new Point(0,0));
        puntosN.add(new Point(2,2));
        puntosN.add(new Point(4,4));

        assertTrue(bp.getPoints().size() == puntosN.size());

        List<Point> res = bp.getPoints();
        for (int i = 0; i < res.size(); i++){
            assertEquals(puntosN.get(i),res.get(i));
        }
    }

}
