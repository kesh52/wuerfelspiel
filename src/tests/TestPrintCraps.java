package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.CrapsModel;
import model.DiceModel;

public class TestPrintCraps {

    @Test
    public void testPrintCrapsFunction() {
        List<DiceModel> dices = new ArrayList<DiceModel>(
                Arrays.asList(
                        // First element is a fake element, because i count from 1 and not 0
                        new DiceModel(false, true, -100),
                        new DiceModel(true, true, 1),
                        new DiceModel(true, true, 5),
                        new DiceModel(false, true, 2),
                        new DiceModel(false, true, 3),
                        new DiceModel(false, true, 4),
                        new DiceModel(false, true, 5)
                        )
            );
        CrapsModel.setDices(dices);

        assertThat(CrapsModel.printCraps(), is("|1|, |5|, 2, 3, 4, 5"));
    }

}
