import java.util.Scanner;

public class Main {

    private Main() {
        Scanner scanner = new Scanner(System.in);

        final int lines = 6;
        final int column = 7;

        char[][] vetBoard = new char[lines][column];

        newGame(vetBoard);

        vetBoard[4][5] = 'a';

        atribuiJogada(scanner, vetBoard);

    }

    private void atribuiJogada(Scanner scanner, char[][] vetBoard) {
        while (true) {
            System.out.println("Informe a coluna que você vai querer jogar");
            int jogada = scanner.nextInt();
            vetBoard[5][jogada - 1] = 'W';
            printTabuleiro(vetBoard);
        }
    }

    private void newGame(char[][] vetBoard) {
        System.out.println("   | 1 || 2 || 3 || 4 || 5 || 6 || 7 |");
        for (int l = 0; l < vetBoard.length; l++) {
            System.out.print(" " + (l + 1) + " ");
            for (int c = 0; c < 7; c++) {
                vetBoard[l][c] = 'B';
                vetBoard[4][5] = 'a';
                System.out.print("| " + vetBoard[l][c] + " |");
            }
            System.out.println();
        }
    }

    private void printTabuleiro(char[][] vetBoard) {
        System.out.println("   | 1 || 2 || 3 || 4 || 5 || 6 || 7 |");
        for (int l = 0; l < vetBoard.length; l++) {
            System.out.print(" " + (l + 1) + " ");
            for (int c = 0; c < 7; c++) {
                System.out.print("| " + vetBoard[l][c] + " |");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}