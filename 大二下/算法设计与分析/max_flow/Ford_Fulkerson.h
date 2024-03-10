//
// Created by YEZI on 2023/6/20.
//

#ifndef MAX_FLOW_FORD_FULKERSON_H
#define MAX_FLOW_FORD_FULKERSON_H

#include<iostream>
#include<fstream>

using namespace std;
namespace Ford_Fulkerson {
    class Map {
        int **map;
        bool *visited;
        int edgeNumber;
        int vertexNumber;
        int max_flow = 0;
        int sink;

        int DFS(const int current, int min_flow) {
            if (current == sink) {
                return min_flow;
            }
            visited[current] = true;
            for (int i = 0; i < vertexNumber; i++) {
                if (map[current][i] && !visited[i]) {
                    int next_flow = DFS(i, min(map[current][i], min_flow));
                    if (next_flow) {
                        map[current][i] -= next_flow;
                        map[i][current] += next_flow;
                        return next_flow;
                    }
                }
            }
            return 0;
        }

    public:
        void find_max_flow() {
            while (true) {
                fill(visited, visited + vertexNumber, false);
                int flow = DFS(0, 1314520);
                if (flow == 0)
                    break;
                max_flow += flow;
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
        Map(){
            fstream file(R"(C:\Users\Yezi\Desktop\C++\max_flow\data.txt)");
            if (!file.is_open()) {
                cout << "File Open Error!" << endl;
                return;
            }
            file >> vertexNumber >> edgeNumber;
            map = new int *[vertexNumber];
            visited = new bool[vertexNumber];
            for (int i = 0; i < vertexNumber; i++) {
                map[i] = new int[vertexNumber];
                fill(map[i], map[i] + vertexNumber, 0);
            }
            sink = vertexNumber - 1;
            int head, tail, capacity;
            while (!file.eof()) {
                file >> head >> tail >> capacity;
                map[head][tail] = capacity;
            }
        }
        Map(const int doctor_num,const int holiday_num,const int day_num,const int capacity) {
            vertexNumber=1+doctor_num+doctor_num*holiday_num+holiday_num*day_num+1;
            map = new int *[vertexNumber];
            visited = new bool[vertexNumber];
            for (int i = 0; i < vertexNumber; i++) {
                map[i] = new int[vertexNumber];
                fill(map[i], map[i] + vertexNumber, 0);
            }
            sink = vertexNumber - 1;
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
#endif //MAX_FLOW_FORD_FULKERSON_H
