//
// Created by YEZI on 2023/5/21.
//

#ifndef MAIN_CPP_BACKWARD_H
#define MAIN_CPP_BACKWARD_H
namespace backward{
    int superEggDrop(int egg,int height){
        int*high=new int[egg+1];
        for(int i=0;i<=egg;i++){
            high[i]=0;
        }
        int times=0;
        while(high[egg]<height){
            times++;
            for(int i=egg;i>0;i--){
                high[i]=high[i]+high[i-1]+1;
            }
        }
        return times;
    }
}
#endif //MAIN_CPP_BACKWARD_H
