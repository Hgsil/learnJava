/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class ComparePokers {

    public static void main(String[] args) {
        PokerOne[] allPoker = new PokerOne[52];
        PokerOne[] firstPokerman = new PokerOne[7];
        PokerOne[] secondPokerman = new PokerOne[7];
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
        ComparePokers.sortPokers(firstPokerman);    //排序
        ComparePokers.sortPokers(secondPokerman);   //排序

        if (ComparePokers.getbest(firstPokerman)>ComparePokers.getbest(secondPokerman)){
            System.out.println("A比B大。");
        }
        else if (ComparePokers.getbest(firstPokerman)<ComparePokers.getbest(secondPokerman)){
            System.out.println("B比A大。");
        }
        else {
            System.out.println("A和B一样大。");
        }
    }


    public static PokerOne[] sortPokers(PokerOne[] p) {     //排序
        for (int i = p.length; i > 0; i--) {
            for (int j = p.length; j > 0; j--) {
                if (p[j].getNumber() > p[j - 1].getNumber())
                    p[j].changePokers(p[j - 1]);
            }

        }
        return p;
    }



    public static Long getbest(PokerOne[] pokerOnes){
        PokerOne[][] allkindPocker =new PokerOne[21][5];
        int[] flag =new int[21];
        Integer[] level =new Integer[21];
        Long[] finalValue = new Long[21];
        String finalNumber[][] =new String[21][6];
        String finaltoLong = new String();
        Long bestValue =new Long(0);
        int bestLoction =0;
        for (int i = 0; i < 21 ; i++) {
            for (int j = 0; j < 6 ; j++) {
                finalNumber[i][j] =new String();
            }
        }
        GiveValue.intGet(flag);
        GiveValue.intergerGet(level);
        GiveValue.LongGet(finalValue);

        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5 ; j++) {
                allkindPocker[i][j] =new PokerOne();
            }
        }
        //把7张牌所有可能拿出来
        for (int a=0 ;a<3 ; a++)
            for (int b = a+1; b <4 ; b++)
                for (int c = b+1; c <5 ; c++)
                    for (int d = c+1; d <6 ; d++)
                        for (int e = d+1,m =0,n =0; e <7 ; e++,m++) {
                            pokerOnes[a].getPokers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[b].getPokers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[c].getPokers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[d].getPokers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[e].getPokers(allkindPocker[m][n]);
                            n=0;
                        }

        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5; j++){
                for (int k = j+1; k <5 ; k++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[j][k].getNumber())
                        flag[i]++;
                }
            }
            switch (flag[i]){
                case 6:
                    level[i] = 7;
                case 4:
                    level[i] = 6;
                case 3:
                    level[i] = 3;
                case 2:
                    level[i] = 2;
                case 1:
                    level[i] = 1;
                case 0:
                    level[i] = 0;

            }
            flag[i] =0;
            for (int j = 0; j <4 ; j++) {
                if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()+1)
                    flag[i]++;
            }
            if (flag[i]==4){
                level[i] = 4 ;

            }
            flag[i] = 0 ;
            for (int j = 0; j <4 ; j++) {
                if (allkindPocker[i][j].getColour().equals(allkindPocker[i][j+1].getColour()))
                    flag[i]++;
            }


            if (flag[i]==4){
                if (level[i]==4){
                    level[i] = 8;

                    if (allkindPocker[i][0].getNumber()==14)
                        level[i] = 9;
                }
                else{
                    level[i] = 5;
                }

            }
            //将一对重新排序
            if (level[i]==1){
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        allkindPocker[i][j].changePokers(allkindPocker[i][0]);
                        allkindPocker[i][j+1].changePokers(allkindPocker[i][1]);
                    }
                }
            }
            //将两对重新排序
            if (level[i]==2){
                int[] showLocation =new int[2];
                int l =0;
                GiveValue.intGet(showLocation);
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        showLocation[l] =j;
                        l++;
                    }
                }
                allkindPocker[i][showLocation[0]].changePokers(allkindPocker[i][0]);
                allkindPocker[i][showLocation[0]+1].changePokers(allkindPocker[i][1]);
                allkindPocker[i][showLocation[1]].changePokers(allkindPocker[i][2]);
                allkindPocker[i][showLocation[1]+1].changePokers(allkindPocker[i][3]);

            }

            //将三条重新排序
            if(level[i]==3){
                int location =0;
                for (int j = 0; j <4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        location = j;
                        break;
                    }
                }
                allkindPocker[i][location].changePokers(allkindPocker[i][0]);
                allkindPocker[i][location+1].changePokers(allkindPocker[i][1]);
                allkindPocker[i][location+2].changePokers(allkindPocker[i][2]);
            }
            //将葫芦重新排序
            if (level[i]==6){
                if (allkindPocker[i][0].getNumber()!=allkindPocker[i][2].getNumber()){
                    allkindPocker[i][0].changePokers(allkindPocker[i][3]);
                    allkindPocker[i][1].changePokers(allkindPocker[i][4]);
                }
            }
            //将四条重新排序
            if (level[i]==7){
                if (allkindPocker[i][0].getNumber()!=allkindPocker[i][1].getNumber()){
                    allkindPocker[i][0].changePokers(allkindPocker[i][4]);
                }
            }
            finalNumber[i][0] =  level[i].toString();
            for (int j = 1,k =0; j <finalNumber.length ; j++,k++) {
                if (allkindPocker[i][k].getNumber()>9)
                finalNumber[i][j] = allkindPocker[i][k].getNumber().toString();
                else
                    finalNumber[i][j] = "0"+allkindPocker[i][k].getNumber().toString();
            }

            for (int j = 0; j < finalNumber.length; j++) {
                finaltoLong += finalNumber[j];
            }
            finalValue[i] = Long.valueOf(finaltoLong);
        }
        bestValue = finalValue[0];

        for (int i = 1; i <21 ; i++) {
            if (finalValue[i]>bestValue){
                bestValue = finalValue[i];
                bestLoction = i;
            }
        }
        for (int i = 0; i <5 ; i++) {
            System.out.print(allkindPocker[bestLoction][i].getNumber());
            System.out.print(allkindPocker[bestLoction][i].getColour());
            System.out.println();
        }
        return bestValue;
    }
}