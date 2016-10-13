/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class ComparePokers {

    public static void main(String[] args) {
        PokerOne[] allPoker = new PokerOne[52];
        PokerOne[] firstPokerman = new PokerOne[7];
        PokerOne[] secondPokerman = new PokerOne[7];
        long bestfirstPokerman = 0;
        long bestsecondPokerman = 0;

        //赋初值
        for (int i = 0, j = 0; j < ShufflePoker.numbers.length; i++, j++) {
            allPoker[j] = new PokerOne();
            allPoker[j].setNumber(ShufflePoker.numbers[j]);
            allPoker[j].setColour(ShufflePoker.colours[i]);
            if (i == 3) i = -1;

        }
        //洗牌
        for (int i = 0; i < 3; i++) {
            ShufflePoker.firstKind(allPoker);
        }

        ShufflePoker.secondKind(allPoker);
        ShufflePoker.thirdKind(allPoker);
        //发2张底牌
        for (int i = 0; i < firstPokerman.length - 5; i++) {
            firstPokerman[i] = new PokerOne();

            allPoker[i].getPokers(firstPokerman[i]);
        }

        for (int i = 0, j = firstPokerman.length - 5;
             i < secondPokerman.length - 5; i++, j++) {
            secondPokerman[i] = new PokerOne();
            allPoker[j].getPokers(secondPokerman[i]);


        }
        //发额外的5张牌
        for (int i = firstPokerman.length - 5, j = 2 * (firstPokerman.length - 5);
             i < firstPokerman.length; i++, j++) {
            firstPokerman[i] = new PokerOne();
            secondPokerman[i] = new PokerOne();
            allPoker[j].getPokers(firstPokerman[i]);
            j += 1;
            allPoker[j].getPokers(secondPokerman[i]);
        }

        //分别测出两份牌的等级
        bestfirstPokerman = ComparePokers.getbest(firstPokerman);
        bestsecondPokerman = ComparePokers.getbest(secondPokerman);


        if (bestfirstPokerman>bestsecondPokerman){
            System.out.println("A比B大。");
        }
        else if (bestfirstPokerman<bestsecondPokerman){
            System.out.println("B比A大。");
        }
        else {
            System.out.println("A和B一样大。");
        }
    }






    public static long getbest(PokerOne[] pokerOnes){
        PokerOne[][] allkindPoker =new PokerOne[21][5];
        int[] flag1 =new int[21];
        int[] flag2 =new int[21];
        int[] flag3 =new int[21];
        int[] level =new int[21];
        long[] finalValue = new long[21];
        long finalNumber[][] =new long[21][6];
        int m =0;

        long bestValue = 0;
        int bestLoction =0;
        for (int i = 0; i < 21 ; i++) {
            for (int j = 0; j < 6 ; j++) {
                finalNumber[i][j] =0;
            }
        }
        GiveValue.intGet(level);
        GiveValue.longGet(finalValue);

        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5 ; j++) {
                allkindPoker[i][j] =new PokerOne();
            }
        }
        //把7张牌所有可能拿出来
        for (int a=0 ;a<3 ; a++) {
            for (int b = a + 1; b < 4; b++) {
                for (int c = b + 1; c < 5; c++) {
                    for (int d = c + 1; d < 6; d++) {
                        for (int e = d + 1, n = 0; e < 7; e++, m++) {
                            pokerOnes[a].getPokers(allkindPoker[m][n]);
                            n++;
                            pokerOnes[b].getPokers(allkindPoker[m][n]);
                            n++;
                            pokerOnes[c].getPokers(allkindPoker[m][n]);
                            n++;
                            pokerOnes[d].getPokers(allkindPoker[m][n]);
                            n++;
                            pokerOnes[e].getPokers(allkindPoker[m][n]);
                            n = 0;
                        }
                    }
                }
            }
        }
        //排序
        for (int k = 0; k < 21; k++) {
            for (int i = 0; i <5; i++) {
                for (int j = 4; j > 0; j--) {
                    if (allkindPoker[k][j].getNumber() > allkindPoker[k][j - 1].getNumber())
                        allkindPoker[k][j].changePokers(allkindPoker[k][j - 1]);
                }

            }

        }
        //测有多少对子
        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if (allkindPoker[i][j].getNumber() == allkindPoker[i][k].getNumber())
                        flag1[i]++;
                }
            }


            if (flag1[i]==0)    level[i] =0 ;
            else if (flag1[i]==1)    level[i] =1 ;
            else if (flag1[i]==2)    level[i] =2 ;
            else if (flag1[i]==3)    level[i] =3 ;
            else if (flag1[i]==6)    level[i] =6 ;
            else if (flag1[i]==7)    level[i] =7 ;


            for (int j = 0; j <4 ; j++) {
                if (allkindPoker[i][j].getNumber()==(allkindPoker[i][j+1].getNumber()+1))
                    flag2[i]++;
            }
            if (flag2[i]==4){
                level[i] = 4 ;

            }
            for (int j = 0; j <4 ; j++) {
                    if (allkindPoker[i][j].getColour().equals(allkindPoker[i][j+1].getColour()))
                    flag3[i]++;
            }


            if (flag3[i]==4){
                if (level[i]==4){
                    level[i] = 8;

                    if (allkindPoker[i][0].getNumber()==14)
                        level[i] = 9;
                }
                else{
                    level[i] = 5;
                }

            }
            //将一对重新排序
            if (level[i]==1){
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPoker[i][j].getNumber()== allkindPoker[i][j+1].getNumber()) {
                        allkindPoker[i][j].changePokers(allkindPoker[i][0]);
                        allkindPoker[i][j+1].changePokers(allkindPoker[i][1]);
                    }
                }
            }
            //将两对重新排序
            if (level[i]==2){
                int[] showLocation =new int[2];
                int l =0;
                GiveValue.intGet(showLocation);
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPoker[i][j].getNumber()== allkindPoker[i][j+1].getNumber()) {
                        showLocation[l] =j;
                        l++;
                    }
                }
                allkindPoker[i][showLocation[0]].changePokers(allkindPoker[i][0]);
                allkindPoker[i][showLocation[0]+1].changePokers(allkindPoker[i][1]);
                allkindPoker[i][showLocation[1]].changePokers(allkindPoker[i][2]);
                allkindPoker[i][showLocation[1]+1].changePokers(allkindPoker[i][3]);

            }

            //将三条重新排序
            if(level[i]==3){
                int location =0;
                for (int j = 0; j <4 ; j++) {
                    if (allkindPoker[i][j].getNumber()== allkindPoker[i][j+1].getNumber()) {
                        location = j;
                        break;
                    }
                }
                allkindPoker[i][location].changePokers(allkindPoker[i][0]);
                allkindPoker[i][location+1].changePokers(allkindPoker[i][1]);
                allkindPoker[i][location+2].changePokers(allkindPoker[i][2]);
            }
            //将葫芦重新排序
            if (level[i]==6){
                if (allkindPoker[i][0].getNumber()!= allkindPoker[i][2].getNumber()){
                    allkindPoker[i][0].changePokers(allkindPoker[i][3]);
                    allkindPoker[i][1].changePokers(allkindPoker[i][4]);
                }
            }
            //将四条重新排序
            if (level[i]==7){
                if (allkindPoker[i][0].getNumber()!= allkindPoker[i][1].getNumber()){
                    allkindPoker[i][0].changePokers(allkindPoker[i][4]);
                }
            }
            finalNumber[i][0] =  level[i]+10;
            for (int j = 1,k =0; j < 5 ; j++,k++) {
                if (allkindPoker[i][k].getNumber()!=null)
                finalNumber[i][j] = (long)(allkindPoker[i][k].getNumber()+10);

            }

            for (int j = 0; j < 6; j++) {
                for (int k = 10-j*2; k >0 ; k--) {
                    finalNumber[i][j]*=10;
                }
                finalValue[i]+=finalNumber[i][j];
            }
        }
        bestValue = finalValue[0];

        for (int i = 1; i <21 ; i++) {
            if (finalValue[i]>bestValue){
                bestValue = finalValue[i];
                bestLoction = i;
            }
        }
        for (int i = 0; i <5 ; i++) {
            System.out.print(allkindPoker[bestLoction][i].getNumber());
            System.out.print("  ");
            System.out.print(allkindPoker[bestLoction][i].getColour());
            System.out.print("  ");
        }
        System.out.println();
        return bestValue;
    }
}