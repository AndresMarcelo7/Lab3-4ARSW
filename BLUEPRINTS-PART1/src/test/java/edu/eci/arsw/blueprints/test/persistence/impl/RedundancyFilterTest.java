package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filter.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RedundancyFilterTest {
    @Test
    public void filterRepeated() {
        List<Point> puntosPrueba = new ArrayList<>();
        puntosPrueba.add(new Point(0, 0));
        puntosPrueba.add(new Point(10, 10));
        puntosPrueba.add(new Point(-2, -2));
        RedundancyFilter prueba = new RedundancyFilter();
        Point puntos[] = {new Point(0, 0), new Point(0, 0), new Point(10, 10), new Point(10, 10), new Point(-2, -2), new Point(-2, -2)};
        Blueprint bp = new Blueprint("johan", "elArea", puntos);
        bp = prueba.filterBlueprint(bp);
        assertTrue(bp.getPoints().size() == puntosPrueba.size());
        List<Point> res = bp.getPoints();
        for (int i = 0; i < res.size(); i++){
            assertEquals(puntosPrueba.get(i),res.get(i));
        }

    }
}