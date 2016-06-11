package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.CrapsModel;
import model.DiceModel;
import model.FalseDice;
import model.NoOnesOrFivesException;
import model.Player1;
import model.Player2;
import model.PlayerModel;

public class Game {
    public static int total = 0;
    public static PlayerModel curPlayer;
    public static List<PlayerModel> players = new ArrayList<PlayerModel>();

    public static void start() {
        PlayerModel player1 = new Player1("p1");
        PlayerModel player2 = new Player2("p2");
        players.add(player1);
        players.add(player2);

        // Player 1 has the first turn
        curPlayer = players.get(0);

        List<DiceModel> dices = new ArrayList<DiceModel>();
        for (int i = 0; i <= 6; i++) {
            dices.add(i, new DiceModel(false, true));
        }
        CrapsModel.setDices(dices);

        makeTurn();

        while (true) {

            ArrayList<Integer> indexesList = getUserInput();
            if (indexesList.contains(-42))
            {
                curPlayer.setTotal(total);
                System.out.println(curPlayer);
                System.out.println("=========================");
                curPlayer = getOppositePlayer(curPlayer);
                CrapsModel.emptyCraps();
                makeTurn();
                continue;
            }

            boolean run = true;

            try {
                total = CrapsModel.changeState(indexesList);
                System.out.println(total);
            } catch (NoOnesOrFivesException e) {
                e.printStackTrace();
                System.out.println(curPlayer);
                curPlayer = getOppositePlayer(curPlayer);
                CrapsModel.emptyCraps();
                makeTurn();

            } catch (FalseDice e) {
                e.printStackTrace();
                System.out.println(CrapsModel.printCraps());
                run = false;
            }

            if (run) {
                CrapsModel.throwCraps();
                System.out.println(CrapsModel.printCraps());
            }
        }
    }

    private static void makeTurn() {
        CrapsModel.throwCraps();
        System.out.println(curPlayer);
        System.out.println(CrapsModel.printCraps());
    }

    private static PlayerModel getOppositePlayer(PlayerModel player) {
        // return player 2
        if (player instanceof Player1) {
            return players.get(1);
        }

        // return player 1
        return players.get(0);
    }

    private static ArrayList<Integer> getUserInput() {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        String indexes = input.nextLine();
        List<String> tempList = new ArrayList<String>(Arrays.asList(indexes.split(",")));
        ArrayList<Integer> indexesList = new ArrayList<Integer>();
        for (String stringIdx : tempList) {
            indexesList.add(Integer.valueOf(stringIdx));
        }
        return indexesList;
    }
}
