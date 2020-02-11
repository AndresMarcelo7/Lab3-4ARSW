package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.FilterBlueprints;
import edu.eci.arsw.blueprints.model.Blueprint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Qualifier("subsampplingFilter")
public class SubsamplingFilter implements FilterBlueprints {

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        return null;
    }
}
