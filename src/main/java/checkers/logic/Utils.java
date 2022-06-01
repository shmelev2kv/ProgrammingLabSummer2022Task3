package checkers.logic;

import checkers.ui.*;

import static checkers.logic.GameSituation.activePlayer;
import static checkers.logic.Turner.activeCheckerChoosed;
import static checkers.logic.Turner.resultOfLastMove;
import static checkers.ui.Constants.SIDES;

public class Utils {

    public static final InfoCenter infoCenter = Turner.infoCenter;

    public static void changePlayerTurn() {
        if (activePlayer.equals(SIDES.black)) {
            activePlayer = SIDES.white;
        } else {
            activePlayer = SIDES.black;
        }
        activeCheckerChoosed = false;
        resultOfLastMove = 0;

        String out = activePlayer.toString().substring(0, 1).toUpperCase() + activePlayer.toString().substring(1);

        infoCenter.updateMessage(out + "'s turn");
    }

    public static boolean isWhiteTurn() {
        return activePlayer.equals(SIDES.white);
    }

    public static void highlight(int row, int col) {
        CheckersBoard.Checker checker = Turner.checkers[row][col];
        checker.highlight();
    }

    public static void unHighlight(int row, int col) {
        CheckersBoard.Checker checker = Turner.checkers[row][col];
        checker.removeHighlight();
    }

    public static void delete(int row, int col) {
        CheckersBoard.Checker checker = Turner.checkers[row][col];
        checker.side = Constants.SIDES.no;
        checker.canEat = false;
        checker.isKing = false;
        checker.clearGraphic();
    }

    public static void makeAKing(int row, int col) {
        CheckersBoard.Checker checker = Turner.checkers[row][col];
        checker.isKing = true;
        checker.makeKingGraphic();
    }


    public static boolean checkerSideFitsPlayerSide(SIDES activeSide) {
        return activePlayer.equals(SIDES.black) && activeSide.equals(SIDES.black) ||
                activePlayer.equals(SIDES.white) && activeSide.equals(SIDES.white);
    }
}
