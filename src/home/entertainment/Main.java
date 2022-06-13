package home.entertainment;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        int size = 25;

        Scanner in = new Scanner(System.in);

        System.out.println("Hello dear interviewers, lets play a Game of Life.");
        System.out.println("Please enter number of live persons, for random please use rnd:");

        String s = in.nextLine();
        int count;
        if (s.equals("rnd")) {
            count = new Random().nextInt(size*size-0);
        } else {
            count = Integer.parseInt(s);
        }
        System.out.println("We will start the game with " + count + " live persons.");

        int[][] gameGrid = new int [size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                if (count > 0) {
                    gameGrid[i][j] = 1;
                    count--;
                } else {
                    gameGrid[i][j] = 0;
                }
            }
        }

        System.out.println("Printing raw data");
        Utils.printer(size, gameGrid);


        Utils.shuffle(gameGrid);

        System.out.println("Printing shuffled data");
        Utils.printer(size, gameGrid);

        boolean markedForGameInProgress = true;
        while (markedForGameInProgress) {
            List<Person> persons = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Person person =  Utils.createPerson(gameGrid, size, i, j);
                    String number = i + ":" + j;
                    person.setNumber(number);
                    persons.add(person);
                    count++;
                }
            }

            persons.forEach(p -> {
                if (Utils.deciderToKill(p)) {
                    p.suicide();
                } else {
                    p.resurrect();
                }
            });

            Map<String, Person> personMap = persons.stream().collect(Collectors.toMap(Person::getNumber, Function.identity()));

            long countOfDeads = persons.stream().filter(person -> person.getAliveAsInt() == 0).count();
            System.out.println("How many russian orks were killed?");
            System.out.println(countOfDeads);

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String number = i + ":" + j;
                    gameGrid[i][j] = personMap.get(number).getAliveAsInt();
                }
            }
            System.out.println("Printing after generation data");
            Utils.printer(size, gameGrid);

            System.out.println("To exit the game just enter 'exit', to continue just press enter...");
            String command = in.nextLine();
            if (command.equals("exit") || countOfDeads == size * size) {
                System.out.println("End of game, if everyone already dead or someone stopped the game!");
                markedForGameInProgress = false;}

        }

    }

}
