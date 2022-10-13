package hu.petrik.bejegyzes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Bejegyzes> bejegyzesLista = new ArrayList<>();
    public static void main(String[] args) {
        Bejegyzes b1 = new Bejegyzes("Ronnie Coleman", "Yeah Buddy");
        bejegyzesLista.add(b1);
        Bejegyzes b2 = new Bejegyzes("Pepsi Béla", "Ez valamilyen nagyon menő bor");
        bejegyzesLista.add(b2);

        System.out.println(b1);
        System.out.println(bejegyzesLista);

        Scanner sc = new Scanner(System.in);
        System.out.println("Kérem adja meg hány bejegyzést szeretne felvenni: ");
        int db;
        try {
            db = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new Error("nem természetes számot adott meg");
        }
        String fajlnev = "bejegyzesek.csv";
        try {
            bejegyzesekFelveteleListabol(fajlnev);
        } catch (FileNotFoundException e) {
            System.out.println("Hiba! ");
        } catch (IOException e) {
            System.out.println("Ismeretlen hiba");
            System.out.println(e.getMessage());
        }

        for (int i = 0; i<db; i++){
            System.out.println("Adja meg a szerzőt: ");
            String szerzo = sc.nextLine();
            System.out.println("Adja meg a bejegyzést: ");
            String tartalom = sc.nextLine();
            bejegyzesLista.add( new Bejegyzes(szerzo, tartalom));
        }

        System.out.println(bejegyzesLista);

    }

    private static void bejegyzesekFelveteleListabol(String fajlnev) throws IOException {
        FileReader fr = new FileReader(fajlnev);
        BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null && !sor.isEmpty()){
                String[] adatok = sor.split(";");
                Bejegyzes bejegyzes = new Bejegyzes(adatok[1], adatok[0]);
                bejegyzesLista.add(bejegyzes);
                sor = br.readLine();
            }
        br.close();
        fr.close();
    }
}
