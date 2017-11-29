package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    private final static int DEFAULT_KOKO = 5,
            DEFAULT_KASVATUS = 5;
    private int kasvatuskoko;     // Joukon kasvatus koko.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // joukon alkioiden varsinainen lkm ilman loppunollia. 

    public IntJoukko() {
        joukko = new int[DEFAULT_KOKO];
        alkioidenLkm = 0;
        this.kasvatuskoko = DEFAULT_KASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasitteetti < 0");
        }
        joukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = DEFAULT_KASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasitteetti < 0");
        } else if (kasvatuskoko <= 0) {
            throw new IllegalArgumentException("kasvatuskoko <= 0");
        }
        joukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    // oliokohtaiset
    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        joukko[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm % joukko.length == 0) {
            kasvata();
        }
        return true;
    }

    private void kasvata() {
        int[] kasvatettu = new int[alkioidenLkm + kasvatuskoko];
        System.arraycopy(joukko, 0, kasvatettu, 0, joukko.length);
        joukko = kasvatettu;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukko[i]) {
                for (int j = i; j < alkioidenLkm - 1; j++) {
                    joukko[j] = joukko[j + 1];
                }
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }    

    //staattiset joukko-operaatiot
    private static int[] yhdisteTaulu(final IntJoukko a, final IntJoukko b) {
        int[] c = new int[a.alkioidenLkm + b.alkioidenLkm];
        System.arraycopy(a.joukko, 0, c, 0, a.alkioidenLkm);
        System.arraycopy(b.joukko, 0, c, a.alkioidenLkm, b.alkioidenLkm);
        return c;
    }
    
    public static IntJoukko getYhdisteJoukko(final IntJoukko a, final IntJoukko b) {
        int[] c = yhdisteTaulu(a, b);
        IntJoukko x = new IntJoukko();
        for (int i = 0; i < c.length; i++) {
            x.lisaa(c[i]);
        }
        return x;
    }

    public static IntJoukko getLeikkausJoukko(final IntJoukko a, final IntJoukko b) {
        int[] aTaulu = a.getJoukkoTaulukkona();
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            if (b.kuuluu(aTaulu[i])) {
                y.lisaa(aTaulu[i]);
            }
        }
        return y;
    }

    public static IntJoukko erotus(final IntJoukko a, final IntJoukko b) {
        int[] aTaulu = a.getJoukkoTaulukkona();
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            if (b.kuuluu(aTaulu[i]) == false) {
                y.lisaa(aTaulu[i]);
            }
        }
        return y;
    }

    //get ja toString
    public int[] getJoukkoTaulukkona() {
        return Arrays.copyOf(joukko, alkioidenLkm);
    }
    
    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        if (alkioidenLkm != 0) {
            tuotos += joukko[0];
            for (int i = 1; i < alkioidenLkm; i++) {
                tuotos += ", ";
                tuotos += joukko[i];
            }
        }
        return tuotos + "}";
    }
}
