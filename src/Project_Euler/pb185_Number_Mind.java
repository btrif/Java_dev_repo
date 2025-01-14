package Project_Euler;

//  Created by BTrif Trif on 30-05-2020 , 12:21 PM.


import java.util.ArrayList;

public class pb185_Number_Mind {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Guess[] guesses = new Guess[] {
                new Guess("5616185650518293", 2),
                new Guess("3847439647293047", 1),
                new Guess("5855462940810587", 3),
                new Guess("9742855507068353", 3),
                new Guess("4296849643607543", 3),
                new Guess("3174248439465858", 1),
                new Guess("4513559094146117", 2),
                new Guess("7890971548908067", 3),
                new Guess("8157356344118483", 1),
                new Guess("2615250744386899", 2),
                new Guess("8690095851526254", 3),
                new Guess("6375711915077050", 1),
                new Guess("6913859173121360", 1),
                new Guess("6442889055042768", 2),
                new Guess("2321386104303845", 0),
                new Guess("2326509471271448", 2),
                new Guess("5251583379644322", 2),
                new Guess("1748270476758276", 3),
                new Guess("4895722652190306", 1),
                new Guess("3041631117224635", 3),
                new Guess("1841236454324589", 3),
                new Guess("2659862637316867", 2) };

        System.out.print("Answer: ");
        solve(guesses);

        long endTime = System.nanoTime();
        System.out.printf("Total Time: %.6f seconds\n",
                ((endTime - startTime)/1000000000.0));
    }

    public static void solve(Guess[] guesses) {
        int guessLen = guesses[0].guess.length();
        int numGuesses = guesses.length;
        guessChars = new char[numGuesses][];
        numCorrect = new int[numGuesses];
        for (int i = 0; i < numGuesses; i++) {
            guessChars[i] = guesses[i].guess.toCharArray();
            numCorrect[i] = guesses[i].numCorrect;
        }

        comb = new int[guessLen + 1][4];
        for (int n = 0; n < guessLen + 1; n++)
            for (int m = 0; m < 4; m++)
                comb[n][m] = comb(n, m);

        solveRec(new char[guessLen], new int[guessLen], 0);
    }

    public static char[][] guessChars;
    public static int[] numCorrect;
    public static int[][] comb;

    public static void solveRec(char[] mustBe, int[] cannotBeBits,
                                int usedPositions) {
        if ((1 << numCorrect.length) - 1 == usedPositions) {
            for (int i = 0; i < mustBe.length; i++)
                if (mustBe[i] == 0)
                    for (int j = 0; j <= 9; j++)
                        if ((cannotBeBits[i] & 1 << j) == 0) {
                            mustBe[i] = (char) ('0' + j);
                            break;
                        }

            System.out.println(new String(mustBe));
            return;
        }

        int pos = nextPos(mustBe, cannotBeBits, usedPositions);
        if (pos < 0)
            return;

        char[] gChars = guessChars[pos];
        int numCorr = numCorrect[pos];
        ArrayList<Integer> freePos = new ArrayList<Integer>();
        for (int i = 0; i < gChars.length; i++)
            if (mustBe[i] == gChars[i])
                numCorr--;
            else if (mustBe[i] == 0
                    && ((cannotBeBits[i] & (1 << (gChars[i] - '0'))) == 0))
                freePos.add(i);

        if (numCorr < 0 || freePos.size() < numCorr)
            return;

        //Some tricks to only use one set of loops
        int a = numCorr - 3;
        int[] newCannotBeBits = new int[cannotBeBits.length];
        System.arraycopy(cannotBeBits, 0, newCannotBeBits, 0,
                cannotBeBits.length);
        for (; a < freePos.size(); a = numCorr >= 3 ? a + 1 : freePos.size()) {
            int aPos = a >= 0 ? freePos.get(a) : -1;
            for (int b = a + 1; b < freePos.size(); b = numCorr >= 2 ? b + 1
                    : freePos.size()) {
                int bPos = b >= 0 ? freePos.get(b) : -1;
                for (int c = b + 1; c < freePos.size(); c = numCorr >= 1 ? c + 1
                        : freePos.size()) {
                    int cPos = c >= 0 ? freePos.get(c) : -1;
                    if (aPos >= 0)
                        mustBe[aPos] = gChars[aPos];
                    if (bPos >= 0)
                        mustBe[bPos] = gChars[bPos];
                    if (cPos >= 0)
                        mustBe[cPos] = gChars[cPos];
                    try {
                        for (int i = 0; i < freePos.size(); i++) {
                            if (i == a || i == b || i == c)
                                continue;
                            int iPos = freePos.get(i);
                            newCannotBeBits[iPos] |= 1 << (gChars[iPos] - '0');
                            if (newCannotBeBits[iPos] == 0x3ff)
                                return;
                        }
                        solveRec(mustBe, newCannotBeBits, usedPositions
                                | (1 << pos));
                        for (int i = 0; i < freePos.size(); i++) {
                            int iPos = freePos.get(i);
                            newCannotBeBits[iPos] = cannotBeBits[iPos];
                        }
                    } finally {
                        if (aPos >= 0)
                            mustBe[aPos] = 0;
                        if (bPos >= 0)
                            mustBe[bPos] = 0;
                        if (cPos >= 0)
                            mustBe[cPos] = 0;
                    }
                }
            }
        }
    }

    //Determine next constraint to use
    static int nextPos(char[] mustBe, int[] cannotBeBits, int usedPositions) {
        int bestPos = -1;
        int lowestCount = Integer.MAX_VALUE;

        for (int p = 0; p < guessChars.length; p++) {
            if ((usedPositions & (1 << p)) != 0)
                continue;

            char[] gChars = guessChars[p];
            int numCorr = numCorrect[p], freePos = 0;

            for (int i = 0; i < gChars.length; i++) {
                char mb = mustBe[i];
                if (mb == gChars[i]) {
                    if (--numCorr < 0)
                        return -1;
                } else if (mb == 0
                        && ((cannotBeBits[i] & (1 << (gChars[i] - '0'))) == 0))
                    freePos++;
            }
            if (freePos < numCorr)
                return -1;
            int count = comb[freePos][numCorr];
            if (count == 0)
                return p;//this must be the best
            if (count < lowestCount) {
                lowestCount = count;
                bestPos = p;
            }
        }
        return bestPos;
    }

    static int comb(int n, int m) {
        if (m == 0 || m > n)
            return 0;
        if (n == m)
            return 1;
        int res = 1;
        for (int i = 1; i <= m; i++)
            res = (res * (n - i + 1))/i;
        return res;
    }

    static class Guess {
        public String guess;
        public int numCorrect;

        public Guess(String g, int nc) {
            this.guess = g;
            this.numCorrect = nc;
        }
    }

}
