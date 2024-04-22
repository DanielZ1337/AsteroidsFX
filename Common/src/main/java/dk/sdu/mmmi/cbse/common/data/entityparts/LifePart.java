package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public class LifePart implements EntityPart {

    private int life;
    private boolean isHit = false;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    @Override
    public void process(GameData gameData, Entity entity, World world) {
        if (isHit) {
            life--;
            isHit = false;
        }

        if (life <= 0) {
            world.removeEntity(entity);
        }
    }
}