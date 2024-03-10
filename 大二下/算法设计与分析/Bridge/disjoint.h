#ifndef BRIDGE_DISJOINT_H
#define BRIDGE_DISJOINT_H

#include<iostream>
#include<vector>

using namespace std;

namespace Disjoint {
    class Map {
        vector<pair<int, int>> bridges;
        vector<pair<int, int>> edges;
        vector<pair<int,int>>edgesTemp;
        vector<int> root;
        int edgeNumber;
        int vertexNumber;
        int connectedComponent;

    public:
        Map(int edgeNumber, int vertexNumber) : edgeNumber(edgeNumber), vertexNumber(vertexNumber) {
            root.resize(vertexNumber);
        }

        void addEdge(int head, int tail, bool init = false) {
            if (init) {
                edges.emplace_back(head, tail);
            }else{
                edgesTemp.emplace_back(head,tail);
            }
        }

        int countComponent() {
            int component = 0;
            for (int i = 0; i < vertexNumber; i++) {
                root[i] = i;
            }
            for(auto&edge:edgesTemp){
                merge(edge.first,edge.second);
            }
            for(int i=0;i<vertexNumber;i++){
                if(root[i]==i){
                    component++;
                }
            }
            return component;
        }

        int findRoot(int&vertex){
            if(root[vertex]==vertex){
                return vertex;
            }
            return root[vertex]= findRoot(root[vertex]);
        }

        void merge(int&u,int&v){
            int uRoot= findRoot(u);
            int vRoot= findRoot(v);
            if(uRoot!=vRoot){
                root[vRoot]=uRoot;
            }
        }

        void removeEdge(pair<int,int>edge){
            for(auto it=edgesTemp.begin();it!=edgesTemp.end();it++){
                if(*it==edge){
                    edgesTemp.erase(it);
                    break;
                }
            }
        }


        void findBridge() {
            edgesTemp=edges;
            connectedComponent=countComponent();
            for(auto&edge:edges){
                removeEdge(edge);
                if(connectedComponent<countComponent()){
                    bridges.emplace_back(edge.first,edge.second);
                }
                addEdge(edge.first,edge.second);
            }
        }

        void showBridge() {
            for (auto &bridge : bridges) {
                cout << bridge.first << '-' << bridge.second << endl;
            }
        }
    };
}

#endif //BRIDGE_DISJOINT_H
