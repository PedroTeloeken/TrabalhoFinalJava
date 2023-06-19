import java.util.Scanner;

public class Main {

    private Main() {
        Scanner teclado = new Scanner(System.in);

        final int lines = 6;
        final int column = 7;
        char corPlayer = 'a';
        char corRobo;
        boolean playAgain = false;

        char[][] vetBoard = new char[lines][column];


        do {
            newGame(vetBoard);
            corPlayer = escolherCorPlayer(teclado, corPlayer);

            if (corPlayer == 'V') {
                corRobo = 'A';
            } else {
                corRobo = 'V';
            }

            while (true) {
                atribuiJogadaPlayer(teclado, vetBoard, corPlayer);
                atribuiJogadaRobo(vetBoard, corRobo);
            }


            //Por algum motivo o sistema tá se batendo com essas duas linhas de código, mas a ideia era essa
            //System.out.println("Deseja iniciar uma nova partida?");
            //jogarNovamente = teclado.nextBoolean();

        } while (true);

    }

    //metodo para randomizar a jogada do robo
    private void atribuiJogadaRobo(char[][] vetBoard, char corRobo) {

        int min = 0; // Valor mínimo do intervalo
        int max = 6; // Valor máximo do intervalo
        boolean casaValida = true;

        // Gerar um número aleatório dentro do intervalo
        int jogada = (int) (min + (Math.random() * (max - min + 1)));

        int linha = 5;
        while (casaValida) {

            if (linha < 0) {
                System.out.println("Não tem mais linhas");
            } else {
                if (vetBoard[linha][jogada] == 'B') {
                    vetBoard[linha][jogada] = corRobo;
                    casaValida = false;
                }
                linha--;
            }
        }

        System.out.println("Jogada do robô: ");
        System.out.println();
        printTabuleiro(vetBoard);

    }

    private void atribuiJogadaPlayer(Scanner teclado, char[][] vetBoard, char corPlayer) {

        System.out.println("Informe a coluna que você vai querer jogar:");

        boolean casaValida = true;
        int jogada = teclado.nextInt() - 1;

        int linha = 5;
        while (casaValida) {

            if (linha < 0) {
                System.out.println("Não tem mais linhas");

            } else {
                if (vetBoard[linha][jogada] == 'B') {
                    vetBoard[linha][jogada] = corPlayer;
                    printTabuleiro(vetBoard);
                    casaValida = false;
                }
                linha--;
            }
        }
    }

    //metodo para escolher a cor do player
    private char escolherCorPlayer(Scanner teclado, char cor) {

        do {
            System.out.println("Informe com qual cor você deseja jogar, V (Vermelho) ou A (Azul)");
            cor = teclado.next().toUpperCase().charAt(0);

        } while ((cor != 'V' && cor != 'A'));

        return cor;
    }

    //metodo para escolher a cor do robo que será a contraria do player,
    //não tem validação porque aqui só serão aceitos valores passados corretos da validação do player

    private char escolherCorRobo(char corPlayer) {

        char corRobo = 'a';

        switch (corPlayer) {
            case 'V':
                corRobo = 'A';
                break;
            case 'A':
                corRobo = 'V';
                break;
        }

        return corRobo;
    }


    //método para atribuir jogada do player

    //método para iniciar/ zerar um jogo
    private void newGame(char[][] vetBoard) {
        System.out.println("   | 1 || 2 || 3 || 4 || 5 || 6 || 7 |");
        for (int l = 0; l < vetBoard.length; l++) {
            System.out.print(" " + (l + 1) + " ");
            for (int c = 0; c < 7; c++) {
                vetBoard[l][c] = 'B';
                System.out.print("| " + vetBoard[l][c] + " |");
            }
            System.out.println();
        }
    }

    //metodo para mostrar o tabuleiro
    private void printTabuleiro(char[][] vetBoard) {
        System.out.println();
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