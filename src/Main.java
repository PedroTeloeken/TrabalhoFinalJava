import java.util.Scanner;

public class Main {

    private Main() {
        jogarJogo();
    }

    private void jogarJogo() {
        Scanner teclado = new Scanner(System.in);

        final int lines = 6;
        final int column = 7;
        char corPlayer;
        char corRobo;
        boolean playAgain;
        boolean fimDoJogo = false;

        char[][] vetBoard = new char[lines][column];

        do {
            novoJogo(vetBoard);
            corPlayer = escolherCorPlayer(teclado);

            corRobo = getCorRobo(corPlayer);

            fimDoJogo = endGame(teclado, corPlayer, corRobo, fimDoJogo, vetBoard);
            char resposta;

            do {
                System.out.println("Deseja iniciar uma nova partida? (S/N)");
                resposta = teclado.next().toUpperCase().charAt(0);
                playAgain = (resposta == 'S');
                if (resposta == 'S' || resposta == 'N') {
                    if (playAgain) {
                        fimDoJogo = false;
                    } else {
                        System.out.println("Fim de Jogo");
                    }
                } else {
                    System.out.println("Resposta Invalida");
                }
            } while (resposta != 'S' && resposta != 'N');

        } while (playAgain);
    }

    private static char getCorRobo(char corPlayer) {
        char corRobo;
        if (corPlayer == 'V') {
            corRobo = 'A';
        } else {
            corRobo = 'V';
        }
        return corRobo;
    }

    private boolean endGame(Scanner teclado, char corPlayer, char corRobo, boolean fimDoJogo, char[][] vetBoard) {
        int cont = 0;
        while (!fimDoJogo) {
            fimDoJogo = atribuiJogadaPlayer(teclado, vetBoard, corPlayer);
            if (!fimDoJogo) {
                fimDoJogo = atribuiJogadaRobo(vetBoard, corRobo);
            }
            cont = cont + 2;
            if (cont == 42) {
                System.out.println("Empate");
                return true;
            }
        }
        return false;
    }

    // Método para randomizar a jogada do robô
    private boolean atribuiJogadaRobo(char[][] vetBoard, char corRobo) {
        boolean jogarNovamente;
        do {
            jogarNovamente = false;
            int min = 0; // Valor mínimo do intervalo
            int max = 6; // Valor máximo do intervalo
            boolean casaValida = true;

            // Gerar um número aleatório dentro do intervalo
            int jogada = (int) (min + (Math.random() * (max - min + 1)));

            int linha = 5;
            while (casaValida) {
                if (linha < 0) {
                    jogarNovamente = true;
                    break;
                } else {
                    if (vetBoard[linha][jogada] == 'B') {
                        vetBoard[linha][jogada] = corRobo;
                        casaValida = false;
                        if (verificaVitoria(vetBoard, linha, jogada, corRobo)) {
                            System.out.println("Jogada do robô: ");
                            printTabuleiro(vetBoard);
                            System.out.println("\u001b[1;34m" + "!!! Robô VENCEU !!!" + "\u001b[0m" + " ");
                            return true;
                        }
                    }
                    linha--;
                }
            }
        } while (jogarNovamente);

        System.out.println("Jogada do robô: ");
        printTabuleiro(vetBoard);
        return false;
    }

    // Método para escolher a cor do player
    private char escolherCorPlayer(Scanner teclado) {
        char cor;
        do {
            System.out.println("Informe com qual cor você deseja jogar, V (Vermelho) ou A (Azul)");
            cor = teclado.next().toUpperCase().charAt(0);
        } while ((cor != 'V' && cor != 'A'));
        return cor;
    }

    private boolean atribuiJogadaPlayer(Scanner teclado, char[][] vetBoard, char corPlayer) {
        boolean jogarNovamente;
        do {
            jogarNovamente = false;
            System.out.println("Informe a coluna que você vai querer jogar:");
            boolean casaValida = true;
            int jogada = teclado.nextInt() - 1;
            boolean validaCasa = true;

            while (validaCasa) {
                if (jogada >= 0 && jogada <= 6) {
                    validaCasa = false;
                } else {
                    System.out.println("\u001b[1;31m" + "!!! Informe um número válido !!!" + "\u001b[0m");
                    jogada = teclado.nextInt() - 1;
                }
            }

            int linha = 5;
            while (casaValida) {
                if (linha < 0) {
                    System.out.println("Não tem mais linhas");
                    jogarNovamente = true;
                    break;
                } else {
                    if (vetBoard[linha][jogada] == 'B') {
                        vetBoard[linha][jogada] = corPlayer;
                        printTabuleiro(vetBoard);
                        casaValida = false;
                        if (verificaVitoria(vetBoard, linha, jogada, corPlayer)) {
                            System.out.println("\u001b[1;36m" + "!!! VOCÊ VENCEU !!!" + "\u001b[0m" + " ");
                            return true;
                        }
                    }
                    linha--;
                }
            }
        } while (jogarNovamente);
        return false;
    }

    // Método para iniciar/zerar um jogo
    private void novoJogo(char[][] vetBoard) {
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

    // Método para mostrar o tabuleiro
    private void printTabuleiro(char[][] vetBoard) {
        System.out.println();
        System.out.println("   | 1 || 2 || 3 || 4 || 5 || 6 || 7 |");
        for (int l = 0; l < vetBoard.length; l++) {
            System.out.print(" " + (l + 1) + " ");
            for (int c = 0; c < 7; c++) {
                if (vetBoard[l][c] == 'V') {
                    System.out.print("| " + "\u001b[1;31m" + vetBoard[l][c] + "\u001b[0m" + " |");
                } else if (vetBoard[l][c] == 'A') {
                    System.out.print("| " + "\u001b[1;34m" + vetBoard[l][c] + "\u001b[0m" + " |");
                } else {
                    System.out.print("| " + vetBoard[l][c] + " |");
                }
            }
            System.out.println();
        }
    }

    // Método para verificar a condição de vitória
    private boolean verificaVitoria(char[][] vetBoard, int linha, int coluna, char cor) {
        // Verificar horizontalmente
        int count = 0;
        for (int c = 0; c < 7; c++) {
            if (vetBoard[linha][c] == cor) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Verificar verticalmente
        count = 0;
        for (int l = 0; l < 6; l++) {
            if (vetBoard[l][coluna] == cor) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Verificar diagonalmente para a direita (/)
        int startRow = linha - Math.min(linha, coluna);
        int startCol = coluna - Math.min(linha, coluna);
        count = 0;
        int r = startRow;
        int c = startCol;
        while (r < 6 && c < 7) {
            if (vetBoard[r][c] == cor) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            r++;
            c++;
        }

        // Verificar diagonalmente para a esquerda (\)
        startRow = linha - Math.min(linha, 6 - coluna);
        startCol = coluna + Math.min(linha, 6 - coluna);
        count = 0;
        r = startRow;
        c = startCol;
        while (r < 6 && c >= 0) {
            if (vetBoard[r][c] == cor) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
            r++;
            c--;
        }
        return false;
    }

    public static void main(String[] args) {
        new Main();
    }
}