import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckCard {

    public static String getWinner(Player player1, Player player2) {
        if(player1.getCardLevel()==player2.getCardLevel()){
            if(player1.getCardLevel() == 0){
                return checkSameLevel(player1,player2);
            }else if(player1.getCardLevel()==1){
                return checkSameLevel(player1,player2);
            }else if(player1.getCardLevel()==2){
                Integer pairValue1 = TransformUtil.findPairValue(player1);
                List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(player1));
                play1List = play1List.stream().filter(num -> num != pairValue1).collect(Collectors.toList());

                Integer pairValue2 = TransformUtil.findPairValue(player2);
                List<Integer> play2List = Arrays.asList(TransformUtil.getSortValues(player2));
                play2List = play2List.stream().filter(num -> num != pairValue2).collect(Collectors.toList());
                if(pairValue1>pairValue2){
                    return "palyer1";
                }else if(pairValue1 < pairValue2){
                    return "palyer2";
                }
                return TransformUtil.compareCardValuesList(play1List,play2List);
            }else if(player1.getCardLevel()==3){
                Integer pairValue1Min = TransformUtil.findPairValue(player1);
                List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(player1));
                play1List = play1List.stream().filter(num -> num != pairValue1Min).collect(Collectors.toList());
                Integer pairValue1Max = TransformUtil.findPairValueMax(play1List);
                play1List = play1List.stream().filter(num -> num != pairValue1Max).collect(Collectors.toList());


                Integer pairValue2Min = TransformUtil.findPairValue(player2);
                List<Integer> play2List = Arrays.asList(TransformUtil.getSortValues(player2));
                play2List = play2List.stream().filter(num -> num != pairValue2Min).collect(Collectors.toList());
                Integer pairValue2Max = TransformUtil.findPairValueMax(play2List);
                play2List = play2List.stream().filter(num -> num != pairValue2Max).collect(Collectors.toList());

                if(pairValue1Max>pairValue1Max){
                    return "palyer1";
                }else if(pairValue1Max < pairValue1Max){
                    return "palyer2";
                }else{
                    if(pairValue1Min>pairValue2Min){
                        return "palyer1";
                    }else if (pairValue1Min < pairValue2Min){
                        return "palyer2";
                    }else{
                        return TransformUtil.compareCardValuesList(play1List,play2List);
                    }
                }
            }
        }
        if(player1.getCardLevel()>player2.getCardLevel()){
            return "palyer1";
        }
        return "palyer2";
    }

    public static  String checkSameLevel(Player player1, Player player2) {
        Integer[] palyer1Values = TransformUtil.getSortValues(player1);
        Integer[] palyer2Values = TransformUtil.getSortValues(player2);
        return TransformUtil.compareCardValues(palyer1Values, palyer2Values);
    }
}
