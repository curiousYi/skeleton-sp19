package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * TO-DO
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    public void checkEnergy() {
        if(energy < 0) {
            energy = 0;
        }
    }

    public void move() {
        energy -= 0.03;
        this.checkEnergy();
    }

    public void stay() {
        energy -= 0.01;
        this.checkEnergy();
    }

    public Clorus replicate() {
        double halved = energy / 2;
        energy = halved;

        return new Clorus(halved);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for( Direction dir :neighbors.keySet()) {
            Occupant occupant = neighbors.get(dir);
            if(occupant.name() == "empty") {
                emptyNeighbors.add(dir);
            } else if(occupant.name() == "plip") {
                anyPlip = true;
                plipNeighbors.add(dir);
            }
        }
        if(emptyNeighbors.isEmpty() == true ) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlip) {
            Integer numberOfPlipNeighbors = plipNeighbors.size();
            Integer random = (new Random()).nextInt(numberOfPlipNeighbors + 1);
            Direction prey = plipNeighbors.pop();

            //Do this jankiness because the linter was complaining
            for(int i = 1; i < numberOfPlipNeighbors; i++) {
                if(i == random) {
                    prey = plipNeighbors.pop();
                } else {
                    plipNeighbors.pop();
                }
            }

            return new Action(Action.ActionType.ATTACK, prey);
        } else if (this.energy() >= 1.0) {
            //Rule 3 (replicate)
            Direction randomEmptyDirection = HugLifeUtils.randomEntry(emptyNeighbors);

            return new Action(Action.ActionType.REPLICATE, randomEmptyDirection);
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry((emptyNeighbors)));
    }

}
