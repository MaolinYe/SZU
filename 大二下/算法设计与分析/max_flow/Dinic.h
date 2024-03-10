//
// Created by YEZI on 2023/6/20.
//

#ifndef MAX_FLOW_DINIC_H
#define MAX_FLOW_DINIC_H

#include<iostream>
#include<fstream>
#include<queue>

using namespace std;
namespace Dinic {
    class Map {
        int **map;
        int *pre;
        int *cur;
        int *flow;
        int *dis;
        int vertexNumber;
        int edgeNumber;
        int max_flow = 0;
        int source;
        int sink;

        bool BFS() {
            queue<int> q;
            fill(dis, dis + vertexNumber, -1);
            flow[source] = INT_MAX;
            dis[source] = 0;
            q.push(source);

            while (!q.empty()) {
                int current = q.front();
                q.pop();
                for (int i = 0; i < vertexNumber; i++) {
                    if (dis[i] == -1 && map[current][i]) {
                        flow[i] = min(flow[current], map[current][i]);
                        pre[i] = current;
                        dis[i] = dis[current] + 1;
                        if (i == sink)
                            return true;
                        q.push(i);
                    }
                }
            }
            return false;
        }

        int DFS(int u, int limit) {
            if (u == sink || !limit)
                return limit;
            int totalFlow = 0;
            for (int &i = cur[u]; i < vertexNumber; i++) {
                if (dis[i] == dis[u] + 1 && map[u][i]) {
                    int d = DFS(i, min(limit, map[u][i]));
                    if (!d) {
                        dis[i] = -1;
                    } else {
                        limit -= d;
                        totalFlow += d;
                        map[u][i] -= d;
                        map[i][u] += d;
                        if (!limit)
                            break;
                    }
                }
            }
            return totalFlow;
        }

    public:
        void find_max_flow() {
            while (BFS()) {
                memcpy(cur, map[source], sizeof(int) * vertexNumber);
                max_flow += DFS(source, INT_MAX);
            }
        }

        void show() {
            cout << "Max flow is " << max_flow << endl;
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                    if (map[i][j] && i < j)
                        cout << '<' << i << ',' << j << "> is abandoned." << endl;
                }
            }
        }

        Map() {
            fstream file(R"(C:\Users\Yezi\Desktop\C++\max_flow\data.txt)");
            if (!file.is_open()) {
                cout << "File Open Error!" << endl;
                return;
            }
            file >> vertexNumber >> edgeNumber;
            map = new int *[vertexNumber];
            pre = new int[vertexNumber];
            cur = new int[vertexNumber];
            flow = new int[vertexNumber];
            dis = new int[vertexNumber];
            for (int i = 0; i < vertexNumber; i++) {
                map[i] = new int[vertexNumber];
                fill(map[i], map[i] + vertexNumber, 0);
            }
            int head, tail, capacity;
            while (!file.eof()) {
                file >> head >> tail >> capacity;
                map[head][tail] = capacity;
            }
            source = 0;
            sink = vertexNumber - 1;
        }

        Map(const int doctor_num,const int holiday_num,const int day_num,const int capacity) {
            vertexNumber=1+doctor_num+doctor_num*holiday_num+holiday_num*day_num+1;
            map = new int *[vertexNumber];
            pre = new int[vertexNumber];
            cur = new int[vertexNumber];
            flow = new int[vertexNumber];
            dis = new int[vertexNumber];
            for (int i = 0; i < vertexNumber; i++) {
                map[i] = new int[vertexNumber];
                fill(map[i], map[i] + vertexNumber, 0);
            }
            sink = vertexNumber - 1;
            source = 0;
            for(int doctor=1;doctor<=doctor_num;doctor++){
                map[0][doctor]=capacity;
            }
            for(int doctor=1;doctor<=doctor_num;doctor++){
                for(int holiday=0;holiday<holiday_num;holiday++){
                    int tail=1+doctor_num+doctor_num*holiday+doctor-1;
                    map[doctor][tail]=1;
                    int head=tail;
                    bool select[day_num];
                    fill(select,select+day_num,false);
                    for(int day=0;day<day_num/2+1;day++){
                        int index=rand()%day_num;
                        if(select[index]){
                            day--;
                            continue;
                        }
                        select[index]= true;
                        tail=1+doctor_num+doctor_num*holiday_num+day_num*holiday+index;
                        map[head][tail]=1;
                    }
                }
            }
            for(int day=0;day<holiday_num*day_num;day++){
                map[1+doctor_num+doctor_num*holiday_num+day][sink]=1;
            }
        }
    };
}

#endif //MAX_FLOW_DINIC_H
