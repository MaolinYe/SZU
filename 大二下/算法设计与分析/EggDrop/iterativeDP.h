//
// Created by YEZI on 2023/5/21.
//

#ifndef MAIN_CPP_ITERATIVEDP_H
#define MAIN_CPP_ITERATIVEDP_H
#include<iostream>
namespace iterativeDP{
    int superEggDrop(int egg,int height){
        int **dp=new int*[egg+1];
        for(int i=1;i<=egg;i++){
            dp[i]=new int[height+1];
            dp[i][0]=0;
            dp[i][1]=1;
        }
        for(int i=1;i<=height;i++){
            dp[1][i]=i;
        }
        for(int i=2;i<=egg;i++){
            for(int j=2;j<=height;j++){
                dp[i][j]=height;
                for(int k=1;k<=j;k++){
                    dp[i][j]=std::min(dp[i][j],std::max(dp[i-1][k-1],dp[i][j-k])+1);
                }
            }
        }
        return dp[egg][height];
    }
}
#endif //MAIN_CPP_ITERATIVEDP_H
