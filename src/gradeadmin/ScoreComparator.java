package gradeadmin;

import java.util.Comparator;

/**
 *
 * @author ishimizu
 */
public class ScoreComparator implements Comparator<ScoreData> {

    private final int order = -1;  // 1:昇順  -1:降順

    @Override
    public int compare(ScoreData o1, ScoreData o2) {
        int comp = compareString(o1.getID(), o2.getID());
        if (comp == 0) {
            comp = compareNumber(getScores(o1), getScores(o2));
        }
        return comp * order;
    }

    private Integer getScores(ScoreData sd) {
        String[] sc = sd.getScore();

        int sco = 0;
        for (String sc1 : sc) {
            if (sc1.equals("-")) {
            } else {
                sco = sco + Integer.parseInt(sc1);
            }
        }
        return (Integer)sco;

    }

    private int compareNumber(Integer i1, Integer i2) {
        return i1.compareTo(i2);
    }

    private int compareString(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        } else {
            return s1.compareTo(s2);
        }
    }

}
