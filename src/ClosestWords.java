/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class ClosestWords {
    LinkedList<String> closestWords = null;

    int closestDistance = -1;


    int partDist(String w1, String w2, int w1len, int w2len) {
        int[][] d = new int[w1len + 1][w2len + 1];
        for (int i = 0; i <= w1len; i++) {
            for (int j = 0; j <= w2len; j++) {
                if (i == 0)
                    d[i][j] = j;
                else if (j == 0)
                    d[i][j] = i;
                else {
                    int cost = w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1; // 0 if the characters are the same, 1 otherwise
                    d[i][j] = Math.min(d[i - 1][j - 1] + cost, d[i - 1][j] + 1); // replace or add
                    d[i][j] = Math.min(d[i][j], d[i][j - 1] + 1); // delete
                }
            }
        }
        return d[w1len][w2len];
    }

    int distance(String w1, String w2) {
        return partDist(w1, w2, w1.length(), w2.length());
    }

    public ClosestWords(String w, List<String> wordList) {
        for (String s : wordList) {
            int dist = distance(w, s);
            // System.out.println("d(" + w + "," + s + ")=" + dist);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords = new LinkedList<String>();
                closestWords.add(s);
            } else if (dist == closestDistance) {
                closestWords.add(s);
            }
        }
    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }
}
