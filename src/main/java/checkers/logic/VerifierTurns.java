package checkers.logic;

import checkers.ui.*;

import static java.lang.Math.abs;

public class VerifierTurns {
    private final CheckersBoard.Checker[][] checkers = CheckersBoard.checkers;
    private int activeCheckerRow;
    private int activeCheckerCol;
    private String activeCheckerColor;
    private boolean activeCheckerKing;
    private String enemyCheckerColor;
    private int eatenCheckerRow;
    private int eatenCheckerCol;
    private final DiagonalChecker diagonalChecker = new DiagonalChecker();



    public VerifierTurns() {
    }



    public void init(int activeCheckerRow, int activeCheckerCol) {
        this.activeCheckerRow = activeCheckerRow;
        this.activeCheckerCol = activeCheckerCol;
        this.activeCheckerColor = checkers[activeCheckerRow][activeCheckerCol].color;
        this.activeCheckerKing = checkers[activeCheckerRow][activeCheckerCol].isKing;

        initEnemyColor(activeCheckerColor);
    }



    private void initEnemyColor(String activeCheckerColor) {
        if (activeCheckerColor.equals("White")) enemyCheckerColor = "Black";
        else enemyCheckerColor = "White";
    }



    public int checkTurn(int selectedCellRow, int selectedCellCol) {
        int difRow = abs(selectedCellRow - activeCheckerRow);
        int difCol = abs(selectedCellCol - activeCheckerCol);
        String selectedCellColor = checkers[selectedCellRow][selectedCellCol].color;

        if (selectedCellColor.equals("No") && difRow == difCol) {
            int dif = difRow;
            difRow = (selectedCellRow - activeCheckerRow) / difRow;
            difCol = (selectedCellCol - activeCheckerCol) / difCol;
            int i = activeCheckerRow + difRow;
            int j = activeCheckerCol + difCol;
            if (activeCheckerKing || selectedCellRow < activeCheckerRow && activeCheckerColor.equals("White") ||
                    selectedCellRow > activeCheckerRow && activeCheckerColor.equals("Black")) {
                switch (dif) {
                    case (1) -> {
                        return 1;
                    }
                    case (2) -> {
                        if (checkers[i][j].color.equals(enemyCheckerColor)) {
                            eatenCheckerRow = i;
                            eatenCheckerCol = j;
                            return 2;
                        }
                    }
                }
            }
        }

        return 0; //This turn is impossible
    }




    public int checkAllTurns() {
        diagonalChecker.init(activeCheckerRow, activeCheckerCol);
        diagonalChecker.repeatRightUp(this);
        diagonalChecker.repeatLeftUp(this);
        diagonalChecker.repeatLeftDown(this);
        diagonalChecker.repeatRightDown(this);
        return diagonalChecker.getResult();
    }

    public boolean checkForTurns() {
        return checkAllTurns() != 0;
    }

    public boolean checkForTakes() {
        return checkAllTurns() == 2;
    }

    public int getEatenRow() {
        return eatenCheckerRow;
    }

    public int getEatenCol() {
        return eatenCheckerCol;
    }
}
