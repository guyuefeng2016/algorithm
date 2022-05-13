package com.fei;

import java.util.Random;

/**
 * @description:
 * 实现indexOf
 * @author: guyuefeng
 * @create: 2022-05-09 10:35
 **/
public class IndexOfString {

    /**
     *
     * @param origin 原字符串
     * @param match 待匹配字符串
     * @return
     */
    public static int indexOfStr(String origin, String match){
        if (origin == null || match == null || origin.length() < match.length()){
            return -1;
        }
        if (match.length() == 0){
            return 0;
        }

        char[] originArr = origin.toCharArray();
        char[] matchArr = match.toCharArray();

        int originLength = originArr.length;
        int matchLength = matchArr.length;

        int[] next = getNext(matchArr);

        int x = 0;
        int y = 0;

        while (x < originLength && y < matchLength){
            if (originArr[x] == matchArr[y]){
                //原字符串比较位置的数  == 待匹配的字符串位置的数，就继续往后匹配
                x++;
                y++;
            } else if (y == 0){
                //如果待匹配的字符串位置y已经到了0位置，表明待匹配字符串之前的next数每一个都无法和原字符串的当前x位置的数匹配上，直接匹配x的后面的数
                x++;
            } else {
                //如果待匹配的字符串位置y还没有到0位置，表明 y之前的位置所有数可能还有前后相等的，就跳到前一个开始比较
                /**
                 * 如果原数和匹配的数当前位置的数不等，为什么让原数组保留在x位置不变，而让匹配的数组跳到y的前一个next 进行匹配？ 本质就是快速匹配，匹配数组跳到y位置，相当于原数组跳到 x-y的位置
                 * 然后将y位置的数和x当前位置进行比较。 另外一个本质是原数组不能跳到 x-y 之前的位置进行匹配，如果x-y之前的位置也能匹配到，那相当于y位置的next算错了，因为如果x-y位置之前也能匹配到，
                 * 那说明y位置的next其实还有一个更大的数，而不是现在计算的next
                 */
                y = next[y];
            }
        }

        return y == matchLength ? x-y : -1;
    }

    /**
     * 返回数组中每一个位置前面所有数 左边==右边 最多的数
     * 算的每一个位置l，都是不包括它本身 [0,l-1]位置
     * 例如：
     * (1) ababc , c位置的next所有数就是 abab ， ab = ab，所以getNext = 2
     * (2) acdfgacdm , m位置的next所有数就是 acdfgacd , acd == acd ，所以getNext = 3
     * @param matchArr
     * @return
     */
    public static int[] getNext(char[] matchArr){
        int length = matchArr.length;
        int[] next = new int[length];

        //0位置前面没有数，规定为-1
        next[0] = -1;

        if (length == 1){
            return next;
        }

        //1位置前面只有一个数，没有左边和右边之分，规定为0
        next[1] = 0;

        //从x开始算next
        int x = 2;
        //n用来和 x-1进行比较的，n就是 next[x] 的值
        int n = 0;

        while (x < length){
            if (matchArr[n] == matchArr[x-1]){
                //当前要计算x位置的next，比如 ababc ,第三个位置的数b，前面的所有数为 aba，a==a，所以3位置上的b的next=1
                //这个时候到4位置的c，那么它前面所有的数为abab，它只需要判断 1位置上的数 是否等于 3位置上的数，如果相等，那么4位置的c的next等于3位置的next+1
                /**
                 * 这一步要解决的就是知道 x-1位置前面的next，为什么 x-1位置的数==n位置数，那x的next= x-1的 next+1 ，而不是其他的
                 * 假设已经求出来 x-1位置左边相等的最多的数是n个，即 next[x-1] = n ，也就是说 x-1位置的前面的数 [0,n-1]之间的数 等于 [x-1-n, x-2]之间的数
                 *
                 * (例如 abcdabcf , 6位置的c之前的数只有[0,1]之间的数"ab" 等于 [4,5]之间的数"ab", n此时等于2，如果要算f位置的next，那么只有当n=2位置的c等于6位置的c，
                 * 此时f的next等于6位置的c的next+1 = 2+1 = 3， 那为什么不是等于4，或者其他的数，一定是6位置的c的next+1 ?
                 * 假设f的next等于4 >  3 = next+1 ，也就是去掉f本身它之前所有的数为 abcdabc , 因为6位置的c的next=2，即去掉自己之前所有的数，如果加上自己，那么next=4，
                 * 这是绝对不可能的！因为如果加上自己next=4，表明6位置的c往前推3个数他会和[0,3]位置的4个数全部相等，那么这个时候把6位置的c干掉，那6位置的c往前推的3个数，
                 * 当然也会和[0,2]的三个数相等，这显然矛盾！因为我们假设的是6位置的c的next=2，而这个时候会推出6位置的c的next=3)
                 */
                next[x++] = ++n;
            } else if (n == 0){
                //如果n位置的数和x-1位置数不等，并且n==0，表明x-1那个位置next=0，那么当前x肯定next=0
                x++;
            } else {
                //如果n位置的数和x-1位置数不等，并且n不等于0，表明x-1那个位置next>0，那么继续找n的下一个next位置进行比较
                //往前推next，本质就是最快匹配，因为 [0,n-1]位置的数全部等于 [x-1-n, x-2]位置的数， 如果此时新的n位置的数等于 x-1位置，
                // 那就相当于 [0,n]位置的数全部和 [x-1-n,x-1]位置的数相等，那么当前x的next = n-0+1 = n+1
                n = next[n];
            }
        }

        return next;
    }

    public static char randomNextChar(Random random){
        int charCode = -1;
        while (charCode < 97 || charCode > 122){
            charCode = random.nextInt(123);
        }
        return (char)charCode;
    }

    public static void main1(String[] args) {
        double SYSP_01_01=-20d;
        double SYSP_01_02=-18d;
        double SYSP_01_03=0d;

       double totalpoint=(0.25*SYSP_01_03*0.5)+(0.35*(SYSP_01_01+50)/150*100*0.5)+(0.4*(SYSP_01_02+50)/150*100*0.5);
        System.out.println(totalpoint);
    }

    public static void main(String[] args) {
        int count = 5;

        Random random = new Random();

        long startTime = System.currentTimeMillis();

        for (int i=0; i<count; i++){
            int arrLen = random.nextInt(26)+1;

            StringBuilder origin = new StringBuilder();
            StringBuilder match = new StringBuilder();
            for (int j=0; j<arrLen; j++){
                char c = randomNextChar(random);
                origin.append(c);
                if (random.nextInt(3) == 0){
                    match.append(c);
                }
            }

            String s = origin.toString();
            String m = match.toString();
//            String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//            String m = "aaaaaaaaaaaaaaaaaaaaaaaaab";

            int index = s.indexOf(m);
            int index1 = indexOfStr(s, m);

            if (index != index1){
                System.out.println("error ! origin: "+s+" , match: "+m+" , index: "+index+" , index1: "+index1);
                return;
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("spend time : "+ (endTime - startTime));
        System.out.println("Nice !");

    }

}
