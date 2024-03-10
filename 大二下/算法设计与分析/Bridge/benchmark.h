//
// Created by YEZI on 2023/5/29.
//

#ifndef BRIDGE_BENCHMARK_H
#define BRIDGE_BENCHMARK_H

#include<iostream>
#include<vector>

using namespace std;
namespace Benchmark {
    class Map {
        vector<pair<int, int>> bridges;
        vector<vector<int>> map;
        vector<bool> visited;
        vector<pair<int, int>> edges;
        int edgeNumber;
        int vertexNumber;
        int connectedComponent;
        int count;
    public:
        Map(int edgeNumber, int vertexNumber) : edgeNumber(edgeNumber), vertexNumber(vertexNumber) {
            map.resize(vertexNumber);
        }

        void addEdge(int head, int tail, bool init = false) {
            map[head].push_back(tail);
            map[tail].push_back(head);
            if (init) {
                edges.emplace_back(head, tail);
            }
        }

        void removeEdge(int head, int tail) {
            for (auto it = map[head].begin(); it != map[head].end(); it++) {
                if (*it == tail) {
                    map[head].erase(it);
                    break;
                }
            }
            for (auto it = map[tail].begin(); it != map[tail].end(); it++) {
                if (*it == head) {
                    map[tail].erase(it);
                    break;
                }
            }
        }

        void DFS(int &current) {
            if (visited[current])
                return;
            visited[current] = true;
            count++;
            for (auto next: map[current]) {
                DFS(next);
            }
        }

        int countComponent() {
            int component = 0;
            visited.assign(vertexNumber, false);
            for (int i = 0; i < vertexNumber; i++) {
                count = 0;
                DFS(i);
                if (count) {
                    component++;
                }
            }
            return component;
        }

        void findBridge() {
            connectedComponent = countComponent();
            for (auto &edge: edges) {
                removeEdge(edge.first, edge.second);
                if (connectedComponent < countComponent()) {
                    bridges.emplace_back(edge.first, edge.second);
                }
                addEdge(edge.first, edge.second);
            }
        }

        void showBridge() {
            for (auto &bridge: bridges) {
                cout << bridge.first << '-' << bridge.second << endl;
            }
        }
    };
}
#endif //BRIDGE_BENCHMARK_H
