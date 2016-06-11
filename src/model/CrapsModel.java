package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrapsModel {
    private static List<DiceModel> dices;
    public static final int NUMBER_OF_DICES = 6;
    public static final int FIFTY_POINTS = 50;
    public static final int ONE_H_POINTS = 100;
    public static final int FIVE_H_POINTS = 500;
    public static final int ONE_T_POINTS = 1000;
    public static int total = 0;

    public static List<Integer> onesJackpot;
    public static List<Integer> fivesJackpot;

    static {
        dices = new ArrayList<DiceModel>();
        onesJackpot = new ArrayList<Integer>();
        fivesJackpot = new ArrayList<Integer>();
        for (int i = 0; i <= NUMBER_OF_DICES; i++) {
            dices.add(i, new DiceModel(false, true));
        }
    }

    public static List<DiceModel> getDices() {
        return dices;
    }

    public static void setDices(List<DiceModel> dices) {
        CrapsModel.dices = dices;
    }

    public static void throwCraps() {
        Random rand = new Random();
        for (int i = 1; i <= NUMBER_OF_DICES; i++) {
            DiceModel dice = dices.get(i);
            boolean fixed = dice.isFixed();
            if (!fixed) {
                int randomNum = rand.nextInt((6 - 1) + 1) + 1;
                dice.setValue(randomNum);
            }
        }
    }

    public static int changeState(ArrayList<Integer> indexes) throws NoOnesOrFivesException, FalseDice {
        // TODO set fixed elements
        boolean containsFreeOneOrFive = containsFreeOneOrFive();

        if (!containsFreeOneOrFive) {
            throw new NoOnesOrFivesException("No one or five");
        }

        // Fix the dices
        for (Integer idx : indexes) {
            DiceModel dice = dices.get(idx);
            int curValue = dice.getValue();

            if (dice.isFixed()) {
                System.out.println("Dice with the index " + idx + " is already fixed");
                continue;
            }

            if (curValue == 1 || curValue == 5) {
                dice.setFixed(true);
            } else {
                throw new FalseDice("You can't fix this dice");
            }

            if (onesJackpot.size() <= 3 && curValue == 1) {
                onesJackpot.add(idx);
            }

            if (fivesJackpot.size() <= 3 && curValue == 5) {
                fivesJackpot.add(idx);
            }
        }

        for (int i = 1; i <= NUMBER_OF_DICES; i++) {
            DiceModel curDice = dices.get(i);
            boolean canUse = curDice.canUse();

            // TODO compute last three of kind turn
            if (!canUse && curDice.getValue() == 1) {
                total = ONE_T_POINTS;
            }

            if (!canUse && curDice.getValue() == 5) {
                total = FIVE_H_POINTS;
            }
        }

        // onesJackpot contains indexes of ones in this run
        if (onesJackpot.size() == 3) {
            total = ONE_T_POINTS;

            for (Integer idx : onesJackpot) {
                dices.get(idx).setCanUse(false);
            }

            for (int i = 1; i <= NUMBER_OF_DICES; i++) {
                /*if (!onesJackpot.contains(i)) {
                    DiceModel curDice = dices.get(i);
                    boolean fixed = curDice.isFixed();
                    if (fixed) {
                        int curValue = curDice.getValue();
                        switch (curValue) {
                        case 1:
                            total += ONE_H_POINTS;
                            break;
                        case 5:
                            total += FIFTY_POINTS;
                            break;
                        }
                    }
                }*/
            }
        }

        if (fivesJackpot.size() == 3) {
            total = FIVE_H_POINTS;

            for (Integer idx : fivesJackpot) {
                dices.get(idx).setCanUse(false);
            }

            for (int i = 1; i <= NUMBER_OF_DICES; i++) {
                /*if (!fivesJackpot.contains(i)) {
                    DiceModel curDice = dices.get(i);
                    boolean fixed = curDice.isFixed();
                    if (fixed) {
                        int curValue = curDice.getValue();
                        switch (curValue) {
                        case 1:
                            total += ONE_H_POINTS;
                            break;
                        case 5:
                            total += FIFTY_POINTS;
                            break;
                        }
                    }
                }*/
            }
        }

        // TODO remove jackpot dices
        for (int i = 1; i <= NUMBER_OF_DICES; i++) {
            DiceModel curDice = dices.get(i);
            boolean fixed = curDice.isFixed();
            boolean canUse = curDice.canUse();
            if (fixed && canUse) {
                int curValue = curDice.getValue();
                switch (curValue) {
                case 1:
                    total += ONE_H_POINTS;
                    break;
                case 5:
                    total += FIFTY_POINTS;
                    break;
                }
            }
        }

        // int total = dices.stream().mapToInt(i -> i.getValue()).sum();

        // TODO reset all values for the next throw
        onesJackpot = new ArrayList<Integer>();
        fivesJackpot = new ArrayList<Integer>();
        int returnTotal = total;
        total = 0;

        return returnTotal;
    }

    public static boolean containsFreeOneOrFive() {
        for (int i = 1; i <= NUMBER_OF_DICES; i++) {
            DiceModel dice = dices.get(i);
            boolean oneFound = dice.getValue() == 1;
            boolean fiveFound = dice.getValue() == 5;
            boolean fixed = dice.isFixed();

            if ((oneFound || fiveFound) && !fixed) {
                return true;
            }
        }

        return false;
    }

    public static String printCraps() {
        List<String> output = new ArrayList<String>();
        for (int i = 1; i <= NUMBER_OF_DICES; i++) {
            output.add(dices.get(i).toString());
        }

        return String.join(", ", output);
    }

    public static void emptyCraps() {
        dices = new ArrayList<DiceModel>();
        onesJackpot = new ArrayList<Integer>();
        fivesJackpot = new ArrayList<Integer>();
        for (int i = 0; i <= NUMBER_OF_DICES; i++) {
            dices.add(i, new DiceModel(false, true));
        }
    }
}
