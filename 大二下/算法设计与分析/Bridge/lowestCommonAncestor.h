//
// Created by YEZI on 2023/5/31.
//

#ifndef BRIDGE_LOWESTCOMMONANCESTOR_H
#define BRIDGE_LOWESTCOMMONANCESTOR_H

#include<iostream>
#include<vector>

using namespace std;
namespace LCA {
    class Map {
        vector<vector<int>> map;
        vector<bool> visited;
        vector<pair<int, int>> edges;
        vector<pair<int, int>> notTreeEdges;
        vector<bool> notLoopEdges;
        vector<int> depth;
        vector<int> father;
        int vertexNumber;
    public:
        Map(int edgeNumber, int vertexNumber) :  vertexNumber(vertexNumber) {
            map.resize(vertexNumber);
            depth.resize(vertexNumber);
            notLoopEdges.assign(vertexNumber, false);
            visited.assign(vertexNumber, false);
            father.resize(vertexNumber);
            for (int i = 0; i < vertexNumber; i++) {
                father[i] = i;
            }
        }

        void buildTree(int &current, int deep, int &currentFather) {
            depth[current] = deep;
            father[current] = currentFather;
            visited[current] = true;
            for (auto &son: map[current]) {
                if (!visited[son]) {
                    notLoopEdges[son] = true;
                    buildTree(son, deep + 1, current);
                }
            }
        }

        void createTree() {
            for (int i = 0; i < vertexNumber; i++) {
                if (!visited[i]) {
                    buildTree(i, 0, i);
                }
            }
        }

        void addEdge(int head, int tail, bool init = false) {
            map[head].push_back(tail);
            map[tail].push_back(head);
            if (init) {
                edges.emplace_back(head, tail);
            }
        }

        void findNotTreeEdge() {
            for (auto &edge: edges) {
                if (father[edge.first] != edge.second && father[edge.second] != edge.first) {
                    notTreeEdges.emplace_back(edge.first, edge.second);
                }
            }
        }

        void compressPath(int current,int ancestor){
            while(father[current]!=ancestor){
                int next=father[current];
                father[current]=ancestor;
                depth[current]=depth[ancestor]+1;
                current=next;
            }
        }

        void findLoopEdge(pair<int, int> &edge) {
            int u=edge.first;
            int v=edge.second;
            while(true){
                if(depth[u]>depth[v]){
                    notLoopEdges[u]=false;
                    u=father[u];
                }else if(depth[u]<depth[v]){
                    notLoopEdges[v]=false;
                    v=father[v];
                }else if(u!=v){
                    notLoopEdges[u]=false;
                    u=father[u];
                    notLoopEdges[v]=false;
                    v=father[v];
                }else{
                    compressPath(edge.first,father[u]);
                    compressPath(edge.second,father[u]);
                    break;
                }
            }
        }

        void findBridge() {
            createTree();
            findNotTreeEdge();
            for (auto &edge: notTreeEdges) {
                findLoopEdge(edge);
            }
        }

        void showBridge() {
            for(int i=0;i<vertexNumber;i++){
                if(notLoopEdges[i]){
                    cout<<i<<'-'<<father[i]<<endl;
                }
            }
        }
    };
}
#endif //BRIDGE_LOWESTCOMMONANCESTOR_H
