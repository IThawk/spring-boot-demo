package com.ithawk.learn.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
//    TODO
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            int c;
            int d;
            for(int i = 0; i <= len - 4; i++){
                if(i > 0 && nums[i] == nums[i-1]){
                    continue;
                }
                for(int j = i + 1; j <= len - 3; j++){
                    if(j > i + 1 && nums[j] == nums[j-1]){
                        continue;
                    }
                    c = j + 1;
                    d = len - 1;
                    while(c < d){
                        if(c > j + 1 && nums[c] == nums[c-1]){
                            c++;
                            continue;
                        }
                        if(nums[i]+nums[j]+nums[c]+nums[d] > target){
                            d--;
                        }else if(nums[i]+nums[j]+nums[c]+nums[d] < target){
                            c++;
                        }else{
                            res.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[j],nums[c],nums[d])));
                            c++;
                            d--;
                        }
                    }
                }
            }


            return res;
        }
    }
}
