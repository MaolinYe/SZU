//
// Created by YEZI on 2023/5/15.
//

#ifndef EGGDROP_BRUTE_H
#define EGGDROP_BRUTE_H
#include<iostream>
namespace brute{
    int superEggDrop(int egg,int height){
        if(egg==1||height<=2)
            return height;
        int minTimes=height;
        for(int i=1;i<=height;i++){
            minTimes=std::min(minTimes,std::max(superEggDrop(egg,i-1), superEggDrop(egg-1,height-i))+1);
        }
        return minTimes;
    }
}
#endif //EGGDROP_BRUTE_H
