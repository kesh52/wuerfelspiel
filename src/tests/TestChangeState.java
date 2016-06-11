package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.CrapsModel;
import model.DiceModel;
import model.FalseDice;
import model.NoOnesOrFivesException;

@RunWith(Parameterized.class)
public class TestChangeState {
    private List<DiceModel> data = null;
    ArrayList<Integer> indexesToFix = null;
    private int expected;

    public TestChangeState(ArrayList<DiceModel> data, ArrayList<Integer> indexesToFix, int expected) {
        this.data = data;
        this.indexesToFix = indexesToFix;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        List<DiceModel> test1 = new ArrayList<DiceModel>(
                    Arrays.asList(
                            // First element is a fake element, because i count from 1 and not 0
                            new DiceModel(false, true, -100),
                            new DiceModel(false, true, 1),
                            new DiceModel(false, true, 1),
                            new DiceModel(false, true, 1),
                            new DiceModel(false, true, 1),
                            new DiceModel(false, true, 1),
                            new DiceModel(false, true, 1)
                            )
                );

        List<DiceModel> test2 = new ArrayList<DiceModel>(
                Arrays.asList(
                        // First element is a fake element, because i count from 1 and not 0
                        new DiceModel(false, true, -100),
                        new DiceModel(false, true, 1),
                        new DiceModel(false, true, 5),
                        new DiceModel(false, true, 2),
                        new DiceModel(false, true, 3),
                        new DiceModel(false, true, 4),
                        new DiceModel(false, true, 5)
                        )
            );

        return Arrays
                .asList(new Object[][] { {test1, new ArrayList<Integer>(Arrays.asList(1, 2, 3)), 1000}, {test2, new ArrayList<Integer>(Arrays.asList(1)), 100} });

    }

    @Test
    public void testChange() {
        CrapsModel.setDices(data);
        try {
            assertEquals(expected, CrapsModel.changeState(new ArrayList<Integer>(indexesToFix)));
        } catch (NoOnesOrFivesException | FalseDice e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
