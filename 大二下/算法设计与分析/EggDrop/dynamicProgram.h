//
// Created by YEZI on 2023/5/15.
//

#ifndef EGGDROP_DYNAMICPROGRAM_H
#define EGGDROP_DYNAMICPROGRAM_H
#include<iostream>
namespace dynamicProgram{
    int **dp;
    int Drop(int egg,int height){
        if(dp[egg][height]!=-1){
            return dp[egg][height];
        }
        if(egg==1||height<=2){
            dp[egg][height]=height;
            return height;
        }
        int minTimes=height;
        for(int i=1;i<=height;i++){
            minTimes=std::min(minTimes,std::max(Drop(egg,i-1), Drop(egg-1,height-i))+1);
        }
        dp[egg][height]=minTimes;
        return minTimes;
    }

    int superEggDrop(int egg,int height){
        dp=new int*[egg+1];
        for(int i=1;i<=egg;i++){
            dp[i]=new int[height+1];
        }
        for(int i=1;i<=egg;i++){
            for(int j=0;j<=height;j++){
                dp[i][j]=-1;
            }
        }
        return Drop(egg,height);
    }
}
#endif //EGGDROP_DYNAMICPROGRAM_H
