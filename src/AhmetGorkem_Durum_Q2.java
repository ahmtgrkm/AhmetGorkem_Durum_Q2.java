
public class AhmetGorkem_Durum_Q2 {


    public static void main(String[] args) {

        int[] WNgreedy = {3,4,2,6,8};
        int[][] WEtest = {{0,1,5,0,0},{0,0,0,6,2},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        int greed=greedy(WNgreedy,WEtest) ;
        int rectest = recursive(WNgreedy, WEtest, 4);
        int[] dparr=new int[5];
        int dptest=dynamic(WNgreedy, WEtest, 0, dparr);

        System.out.println("Minimum total weight of greedy = " +greed);
        System.out.println("Minimum total weight of recursive = " +rectest);
        System.out.println("Minimum total weight of dynamic = " +dptest);


        System.out.println();
        System.out.println();
        System.out.println();

        int sizeOfArrays=5;
        int[] WN = new int[sizeOfArrays];
        int[][] WE = new int[sizeOfArrays][sizeOfArrays];
        int[] dp = new int[WN.length];
        generate_complete_binary_tree(sizeOfArrays,WN,WE);
        int rec=0;
        int dyn=0;
        double startTime;
        double finishtime;
        double workingtime;
        for (int i=0;i<3;i++) {
            sizeOfArrays=(int)Math.random()*300;
            generate_complete_binary_tree(sizeOfArrays,WN,WE);
            startTime = System.nanoTime();
            rec = recursive(WN, WE, sizeOfArrays);
            finishtime = System.nanoTime();
            workingtime=finishtime-startTime;
            System.out.println("Working time of recursive " +(i+1)+". turn is: "+workingtime+" .And minimum total weight is : "+rec);
            startTime = System.nanoTime();
            dyn = dynamic(WN, WE, 0, dp);
            finishtime = System.nanoTime();
            workingtime=finishtime-startTime;
            System.out.println("Working time of dynamic " +(i+1)+". turn is: "+workingtime+" .And minimum total weight is : "+dyn);
        }





    }

     static void generate_complete_binary_tree(int size,final int[] WN,final int[][] WE) {
        int temp = 1;
        for (int i = 0; i < size; i++) {
            WN[i] = ((int) ((Math.random() * 19)+1));
        }
        for (int i = 0; i < (size / 2); i++) {
            for (int j = 0; j < 2; j++) {
                try {
                    WE[i][temp] = ((int) ((Math.random() * 19)+1));
                    temp += 1;
                } catch (ArrayIndexOutOfBoundsException a) {

                }
            }
        }

    }

    static int greedy(int[] WN, int[][] WE) {
        int temp = Integer.MAX_VALUE;
        int n = WN.length;
        int numOfLeaves = (int) Math.ceil((n/2));
        int cost=WN[0];
        int choose;
        int right;
        int left;
        int node =0;
        for (int i=0;i<numOfLeaves;i++){

                left = (WN[2 * node + 1] + WE[node][2 * node + 1]);
                right = (WN[2 * node + 2] + WE[node][2 * node + 2]);

                if (left <= right) {
                    node = (2 * node + 1);
                    choose = left;
                } else {
                    node = (2 * node + 2);
                    choose = right;
                }

            cost+=choose;

                if (node>=numOfLeaves){
                    break;
                }


        }
        return cost;
        }

        //O(n)
    static int recursive(int[] WN, int[][] WE, double node){
        double n= WN.length;
        double numOfLeaves=Math.ceil(n/2.0);
        double parent=Math.floor((node-1)/2.0);
        if (node>=(n-numOfLeaves)){
            return WN[(int)node]+WE[(int) parent][(int) node];
        }
        else{
            if (node==0){
                return (WN[(int) node]+ Math.min(recursive(WN,WE,(2*node+1)), recursive(WN,WE,(2*node+2))));
            }
            else {
            return (WN[(int) node]+WE[(int) parent][(int) node])+ Math.min(recursive(WN,WE,(2*node+1)), recursive(WN,WE,(2*node+2)));
        }}
    }
     //O(n)
    static int dynamic(int[] WN, int[][] WE, double node, int[] dp) {
        double n = WN.length;
        double numOfLeaves = Math.ceil(n / 2.0);
        double parent = Math.floor((node - 1) / 2.0);
        if (node >= (n - numOfLeaves)) {
            dp[(int) node] = WN[(int) node] + WE[(int) parent][(int) node];
            return dp[(int) node];
        } else {
            if (node == 0) {
                if (dp[(int) (2 * node + 1)] == 0) {
                    dp[(int) (2 * node + 1)] = dynamic(WN, WE, 2 * node + 1, dp);
                }
                if (dp[(int) (2 * node + 2)] == 0) {
                    dp[(int) (2 * node + 2)] = dynamic(WN, WE, 2 * node + 2, dp);
                }
                dp[(int) node] = WN[(int) node] + Math.min(dp[(int) (2 * node + 1)], dp[(int) (2 * node + 2)]);
                return dp[(int) node];
            } else {
                if (dp[(int) (2 * node + 1)] == 0) {
                    dp[(int) (2 * node + 1)] = dynamic(WN, WE, 2 * node + 1, dp);
                }
                if (dp[(int) (2 * node + 2)] == 0) {
                    dp[(int) (2 * node + 2)] = dynamic(WN, WE, 2 * node + 2, dp);
                }
                dp[(int) node] = WN[(int) node] + WE[(int) parent][(int) node] + Math.min(dp[(int) (2 * node + 1)], dp[(int) (2 * node + 2)]);
                return dp[(int) node];
            }
        }
    }



}