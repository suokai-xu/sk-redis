package com.sk.primary;
/**
* 2019年4月11日 上午10:50:44
* @HXing xu
* sk-redis
* 
**/

public class DemoList {
	
	public static void main(String[] args) {
//		mp();
		map();
		
	}
	

	public static void map() {
		int srcArray[] = {3,5,11,17,21,23,28,30,32,50,64,78,81,95,101}; 
		//int i = binSearch(srcArray, 0, srcArray.length - 1, 81);
		int i = binSearch(srcArray, 3);
        System.out.println(i); 
	}
	 // 二分查找递归实现   
    public static int binSearch(int srcArray[], int start, int end, int key) {   
        int mid = (end - start) / 2 + start; 
        int k_Id =srcArray[mid]; 
        if ( k_Id== key) {   
            return mid;   
        }   
        if (start >= end) {   
            return -1;   
        } else if (key > srcArray[mid]) {   
            return binSearch(srcArray, mid + 1, end, key);   
        } else if (key < srcArray[mid]) {   
            return binSearch(srcArray, start, mid - 1, key);   
        }   
        return -1;   
    } 

    // 二分查找普通循环实现   
    public static int binSearch(int srcArray[], int key) {   
        int mid = srcArray.length / 2;   
        if (key == srcArray[mid]) {   
            return mid;   
        }   

        int start = 0;   
        int end = srcArray.length - 1;   
        while (start <= end) {   
            mid = (end - start) / 2 + start;   
            if (key < srcArray[mid]) {   
               end = mid - 1;   
            } else if (key > srcArray[mid]) {   
                start = mid + 1;   
            } else {   
                return mid;   
            }   
        }   
        return -1;   
    } 
	
	public static void mp() {
		
		int[] is = {3,2,1};
		
		System.out.println("排序前数组为：");
	    for(int num:is){
	    	System.out.print(num+" ");
		}
	    
		for(int i = 0 ; i<is.length-1; i++) {
			
			for(int j = 0; j<is.length-1-i;j++) {
				if(is[j] > is[j+1]) {
					int temp=is[j];
					is[j]=is[j+1];
					is[j+1]=temp;
				}
			}
		}
		
		System.out.println();
		System.out.println("排序后的数组为：");
		for(int num:is){
			System.out.print(num+ " ");
		}
		
	}

}
