package dk.sdu.mmmi.cbse.commonasteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;

public interface AsteroidSplitterSPI {
    Entity[] splitAsteroid(double x, double y, double radius, double radians, double[] polygonCoordinates);
}
