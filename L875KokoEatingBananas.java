// Lesson: Whenever you know that a solution is between two numbers but don't know where it sits between them. Think of binary search.
class L875KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = piles[0];
        for(int i= 1; i< n; i++ ){
            max = Math.max(max, piles[i]);
        }
        //return findKBruteForce(piles, max, h);
        return findKBinarySearch(piles, h, max);
    }

    private int findKBruteForce(int[] piles, int h, int max){
        for(int k = 1; k<= max; k++){
            int hours = 0;
            for(int i=0; i<piles.length; i++){
                hours += (int) Math.ceil((double)piles[i]/k);
                if(hours > h){
                    break;
                }
            }
            if(hours <= h){
                return k;
            }
        }
        return -1;
    }

    private int findKBinarySearch(int[] piles, int h, int max){
        int left = 1;
        int right = max;
        int solution = max;
        while (left <= right){
            int middle = (left + right) / 2;
            if(checkK(piles, h, middle)){
                solution = middle;
                right = middle -1;
            } else {
                left = middle + 1;
            }
        }
        return solution;
    }

    private boolean checkK(int[] piles, int h,int k){
        
        int hours = 0;
        for(int i=0; i< piles.length; i++){
            hours +=(int) Math.ceil((double)piles[i]/k);
            if(hours > h){
                return false;
            }
        }
        return true;
    }

}