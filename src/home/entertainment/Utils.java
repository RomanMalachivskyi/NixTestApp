package home.entertainment;

import java.util.Random;

public class Utils {
    public static void printer(int size, int[][] gameGrid) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                System.out.print(gameGrid[i][j]);
            }
            System.out.println();
        }
    }

    public static void shuffle(int[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }

    public static Person createPerson(int[][] gameGrid, int size, int currentRow, int currentCol) {
        int startRow = (currentRow - 1 < 0) ? currentRow : currentRow - 1;
        int startCol = (currentCol - 1 < 0) ? currentCol : currentCol - 1;
        int endRow = (currentRow + 1 >= size) ? currentRow : currentRow + 1;
        int endCol = (currentCol + 1 >= size) ? currentCol : currentCol + 1;
        Person person = new Person();
        try {
            for (int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                   if (i == currentRow && j == currentCol) {
                       person.setAlive(gameGrid[i][j] == 1 ? true : false);
                       continue;
                   }
                   if (gameGrid[i][j] == 1) {
                       person.addLiveNeighbor();
                   } else {
                       person.addDeadNeighbor();
                   }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("blablabla");
        }
        return person;
    }

    public static boolean deciderToKill(Person person) {
        if (person.isAlive() && person.getLiveNeighbors() < 2) {
            System.out.println("Person with number: " + person.getNumber() + " will be dead due to underpopulation");
            return true;
        }
        if (person.isAlive() && (person.getLiveNeighbors() == 2 || person.getLiveNeighbors() == 3)) {
            System.out.println("Person with number: " + person.getNumber() + " will be live");
            return false;
        }
        if (person.isAlive() && person.getLiveNeighbors() > 3) {
            System.out.println("Person with number: " + person.getNumber() + " will be dead due to overcrowding");
            return true;
        }
        if (!person.isAlive() && person.getLiveNeighbors() == 3) {
            System.out.println("Person with number: " + person.getNumber() + " will be alive");
            return false;
        }
        return true;
    }
}
