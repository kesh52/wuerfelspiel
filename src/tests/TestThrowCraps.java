package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.CrapsModel;
import model.DiceModel;

public class TestThrowCraps {

    @Test
    public void testThrowCrapsFunction() {
        List<DiceModel> dices = new ArrayList<DiceModel>();
        for (int i = 0; i <= 6; i++) {
            dices.add(i, new DiceModel(false, true));
        }
        CrapsModel.setDices(dices);
        CrapsModel.throwCraps();
        dices = CrapsModel.getDices();

        for (int i = 1; i <= 6; i++) {
            assertTrue((dices.get(1).getValue() >= 1) && (dices.get(1).getValue() <= 6));
        }
    }

}
