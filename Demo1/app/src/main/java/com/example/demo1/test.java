package com.example.demo1;
public class test {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] runningSum = runningSum(nums);
        for(int i=0;i<=nums.length;i++){
            System.out.print(runningSum[i]);
        }
    }
    public static int[] runningSum(int[] nums) {
        int[] runningSum = new int[nums.length];
        runningSum[0] = nums[0];
        for(int i = 1;i<=nums.length;i++){
            runningSum[i] = nums[i] + nums[i+1];
        }
        return runningSum;
    }
}
