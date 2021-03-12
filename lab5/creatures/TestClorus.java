package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testAttacks() {
        Clorus c = new Clorus(2);
        Plip prey = new Plip(1);
        assertEquals(2, c.energy(),0.01);
        c.attack(prey);
        assertEquals(2.5, c.energy(),0.01);
    }

    @Test
    public void testReplicates() {
        double originalEnergy = 2;
        Clorus c = new Clorus(originalEnergy);
        Clorus offspring = c.replicate();
        assertEquals(originalEnergy / 2.0 , c.energy(), 0.01);
        assertEquals(originalEnergy / 2.0, offspring.energy(), 0.01);
        assertNotEquals(c, offspring);

        //same as plip
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip(1.2));
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        //If empty space and plips then one will be attacked randomly
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Plip(1.2));
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmptyNoPlip = new HashMap<Direction, Occupant>();
        topEmptyNoPlip.put(Direction.TOP, new Empty());
        topEmptyNoPlip.put(Direction.BOTTOM, new Impassible());
        topEmptyNoPlip.put(Direction.LEFT, new Impassible());
        topEmptyNoPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmptyNoPlip);
        Action unexpected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
        c = new Clorus(0.8);
        HashMap<Direction, Occupant> topEmptyNoPlip2 = new HashMap<Direction, Occupant>();
        topEmptyNoPlip2.put(Direction.TOP, new Empty());
        topEmptyNoPlip2.put(Direction.BOTTOM, new Impassible());
        topEmptyNoPlip2.put(Direction.LEFT, new Impassible());
        topEmptyNoPlip2.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmptyNoPlip2);
        unexpected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertNotEquals(unexpected, actual);


        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}
