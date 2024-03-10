//
// Created by YEZI on 2023/5/21.
//

#ifndef MAIN_CPP_DICHOTOMY_H
#define MAIN_CPP_DICHOTOMY_H

#include<iostream>

namespace dichotomy {
    int superEggDrop(int egg, int height) {
        int **dp = new int *[egg + 1];
        for (int i = 1; i <= egg; i++) {
            dp[i] = new int[height + 1];
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        for (int i = 1; i <= height; i++) {
            dp[1][i] = i;
        }
        for (int i = 2; i <= egg; i++) {
            for (int j = 2; j <= height; j++) {
                int low = 1;
                int high = j;
                while (low < high) {
                    int mid = (low + high + 1) / 2;
                    if (dp[i - 1][mid - 1] <= dp[i][j - mid]) {
                        low = mid;
                    } else {
                        high = mid - 1;
                    }
                }
                dp[i][j] = std::max(dp[i - 1][low - 1], dp[i][j - low]) + 1;
            }
        }
        return dp[egg][height];
    }
}
#endif //MAIN_CPP_DICHOTOMY_H
