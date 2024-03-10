#include <iostream>
#include<chrono>
#include"Ford_Fulkerson.h"
#include "Edmonds_karp.h"
#include "Dinic.h"

using namespace std;

int main() {
//    Ford_Fulkerson::Map map(500,20,7,10);
//    Edmonds_Karp::Map map(500,20,7,10);
    Dinic::Map map(400,20,7,10);
    auto start = chrono::high_resolution_clock::now();
    map.find_max_flow();
    auto end = chrono::high_resolution_clock::now();
    auto consume = chrono::duration_cast<chrono::milliseconds>(end - start);
//    map.show();
    cout << consume.count() << "ms" << endl;
    return 0;
}