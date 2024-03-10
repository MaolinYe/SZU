#include <iostream>
#include<fstream>
#include"benchmark.h"
#include"disjoint.h"
#include"lowestCommonAncestor.h"
#include<chrono>
using namespace std;
int main() {

//    fstream file(R"(C:\Users\Yezi\Desktop\C++\Bridge\small.txt)");
//    fstream file(R"(C:\Users\Yezi\Desktop\C++\Bridge\mediumDG.txt)");
    fstream file(R"(C:\Users\Yezi\Desktop\C++\Bridge\largeG.txt)");
    if(!file.is_open()){
        cout<<"File Open Error!"<<endl;
        return 0;
    }
    int edgeNumber;
    int vertexNumber;
    int head;
    int tail;
    file>>vertexNumber>>edgeNumber;
//    Benchmark::Map map(edgeNumber,vertexNumber);
//    Disjoint::Map map(edgeNumber,vertexNumber);
    LCA::Map map(edgeNumber,vertexNumber);
    while(!file.eof()){
        file>>head>>tail;
        map.addEdge(head,tail, true);
    }
    auto start=chrono::high_resolution_clock::now();
    map.findBridge();
    auto end=chrono::high_resolution_clock::now();
    auto consume=chrono::duration_cast<chrono::microseconds>(end-start);
    cout<<consume.count()<<"microseconds"<<endl;
    map.showBridge();
    return 0;
}
