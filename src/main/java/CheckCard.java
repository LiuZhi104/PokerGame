import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckCard {

    public static String getWinner(Player player1, Player player2) {
        int cardLevel = player1.getCardLevel();
        if (cardLevel == player2.getCardLevel()) {
            if (cardLevel == 0) {
                return checkSameLevel(player1, player2);
            }
            if (cardLevel == 1) {
                return compareSameLevel(player1, player2);
            }
            if (cardLevel == 2) {
                return compareBothTwoPair(player1, player2);
            }
            if (cardLevel == 3) {
                return compareSameLevel(player1, player2);
            }
            if (cardLevel == 4 || cardLevel == 8) {
                return compareBothStraight(player1, player2);
            }
            if (cardLevel == 5) {
                return compareBothFlush(player1, player2);
            }
            if (cardLevel == 6) {
                return compateBothFullHouse(player1, player2);
            }
            if (cardLevel == 7) {
                return compateBothFourOfAKind(player1, player2);
            }
        }
        if (cardLevel > player2.getCardLevel()) {
            return "palyer1";
        }
        return "palyer2";
    }

    private static String compateBothFourOfAKind(Player player1, Player player2) {
        Integer maxFourOfAKindValue1 = getMaxFourOfAKindValue(player1);
        Integer maxFourOfAKindValue2 = getMaxFourOfAKindValue(player2);
        return getResult(maxFourOfAKindValue1, maxFourOfAKindValue2);
    }

    private static String getResult(Integer maxFourOfAKindValue1, Integer maxFourOfAKindValue2) {
        if (maxFourOfAKindValue1 > maxFourOfAKindValue2) {
            return "palyer1";
        } else {
            return "palyer2";
        }
    }

    private static Integer getMaxFourOfAKindValue(Player player) {
        List<Integer> playList = Arrays.asList(TransformUtil.getSortValues(player));
        Integer maxValue = 2;
        Integer play1Value = playList.get(0);
        playList = playList.stream().filter(num -> !num.equals(play1Value)).collect(Collectors.toList());
        if (playList.size() == 1) {
            maxValue = play1Value;
        } else {
            maxValue = playList.get(0);
        }
        return maxValue;
    }

    private static String compateBothFullHouse(Player player1, Player player2) {
        Integer player1MaxValue = getFullHouseMaxValue(player1);
        Integer player2MaxValue = getFullHouseMaxValue(player2);
        return getResult(player1MaxValue, player2MaxValue);
    }

    private static Integer getFullHouseMaxValue(Player player) {
        List<Integer> playList = Arrays.asList(TransformUtil.getSortValues(player));
        Integer maxValue = 2;
        Integer play1Value = playList.get(0);
        playList = playList.stream().filter(num -> !num.equals(play1Value)).collect(Collectors.toList());
        if (playList.size() == 2) {
            maxValue = play1Value;
        } else {
            maxValue = playList.get(0);
        }
        return maxValue;
    }

    private static String compareBothFlush(Player player1, Player player2) {
        return checkSameLevel(player1, player2);
    }

    private static String compareBothStraight(Player player1, Player player2) {
        List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(player1));
        List<Integer> play2List = Arrays.asList(TransformUtil.getSortValues(player2));
        if (play1List.get(4) == play2List.get(4)) {
            return "Equal value";
        }
        return getResult(play1List.get(4), play2List.get(4));
    }

    private static String compareBothTwoPair(Player player1, Player player2) {
        Integer pairValue1Min = TransformUtil.findPairValue(player1);
        List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(player1));
        play1List = play1List.stream().filter(num -> !num.equals(pairValue1Min)).collect(Collectors.toList());
        Integer pairValue1Max = TransformUtil.findPairValueMax(play1List);
        play1List = play1List.stream().filter(num -> !num.equals(pairValue1Max)).collect(Collectors.toList());


        Integer pairValue2Min = TransformUtil.findPairValue(player2);
        List<Integer> play2List = Arrays.asList(TransformUtil.getSortValues(player2));
        play2List = play2List.stream().filter(num -> !num.equals(pairValue2Min)).collect(Collectors.toList());
        Integer pairValue2Max = TransformUtil.findPairValueMax(play2List);
        play2List = play2List.stream().filter(num -> !num.equals(pairValue2Max)).collect(Collectors.toList());

        if (pairValue1Max > pairValue2Max) {
            return "palyer1";
        }
        if (pairValue1Max < pairValue2Max) {
            return "palyer2";
        } else {
            if (pairValue1Min > pairValue2Min) {
                return "palyer1";
            }
            if (pairValue1Min < pairValue2Min) {
                return "palyer2";
            } else {
                return TransformUtil.compareCardValues(play1List.toArray(new Integer[play1List.size()]), play2List.toArray(new Integer[play2List.size()]));
            }
        }
    }

    private static String compareSameLevel(Player player1, Player player2) {
        Integer pairValue1 = TransformUtil.findPairValue(player1);
        List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(player1));
        play1List = play1List.stream().filter(num -> !num.equals(pairValue1)).collect(Collectors.toList());

        Integer pairValue2 = TransformUtil.findPairValue(player2);
        List<Integer> play2List = Arrays.asList(TransformUtil.getSortValues(player2));
        play2List = play2List.stream().filter(num -> !num.equals(pairValue2)).collect(Collectors.toList());
        if (pairValue1 > pairValue2) {
            return "palyer1";
        }
        if (pairValue1 < pairValue2) {
            return "palyer2";
        }
        return TransformUtil.compareCardValues(play1List.toArray(new Integer[play1List.size()]), play2List.toArray(new Integer[play2List.size()]));
    }

    private static String checkSameLevel(Player player1, Player player2) {
        Integer[] palyer1Values = TransformUtil.getSortValues(player1);
        Integer[] palyer2Values = TransformUtil.getSortValues(player2);
        return TransformUtil.compareCardValues(palyer1Values, palyer2Values);
    }
}
