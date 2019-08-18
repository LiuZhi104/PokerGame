import java.util.*;
import java.util.stream.Collectors;

public class Player {
    private final int HIGH_CARD = 0;
    private final int PAIR = 1;
    private final int TWO_PAIR = 2;
    private final int THREE_OF_A_KIND = 3;
    private final int STRAIGHT = 4;
    private final int FLUSH = 5;
    private final int FULL_HOUSE = 6;
    private final int FOUR_OF_A_KIND = 7;
    private final int STARIGHT_FLUSH = 8;
    private String name;
    private List<Poker> cardGroup = new ArrayList<>();
    private int cardLevel = HIGH_CARD;

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Poker> getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(List<Poker> cardGroup) {
        this.cardGroup = cardGroup;
    }

    public void computeCardLevel() {
        Integer[] palyerValues = TransformUtil.playerGetSortValues(this.cardGroup);
        Set<Integer> noRepetValues = new HashSet<>(Arrays.asList(palyerValues));
        if (noRepetValues.size() == 5) {
            List<String> allCardColor = TransformUtil.getAllCardColor(this);
            String sameColor = allCardColor.get(0);
            allCardColor = allCardColor.stream().filter(color -> color != sameColor).collect(Collectors.toList());
            if (allCardColor.size() == 0) {
                if (palyerValues[4] - palyerValues[0] == 4) {
                    this.cardLevel = STARIGHT_FLUSH;
                } else {
                    this.cardLevel = FLUSH;
                }

            } else {
                if (palyerValues[4] - palyerValues[0] == 4) {
                    this.cardLevel = STRAIGHT;
                }
            }
        } else if (noRepetValues.size() == 4) {
            this.cardLevel = PAIR;
        } else if (noRepetValues.size() == 3) {
            List<Integer> playList = getFilterList();
            if (playList.size() == 2) {
                this.cardLevel = THREE_OF_A_KIND;
            } else {
                this.cardLevel = TWO_PAIR;
            }
        } else if (noRepetValues.size() == 2) {
            List<Integer> playList = getFilterList();
            if (playList.size() == 1) {
                this.cardLevel = FOUR_OF_A_KIND;
            } else {
                this.cardLevel = FULL_HOUSE;
            }
        }
    }

    private List<Integer> getFilterList() {
        Integer pairValue = TransformUtil.findPairValue(this);
        List<Integer> playList = Arrays.asList(TransformUtil.getSortValues(this));
        playList = playList.stream().filter(num -> num != pairValue).collect(Collectors.toList());
        return playList;
    }
}
