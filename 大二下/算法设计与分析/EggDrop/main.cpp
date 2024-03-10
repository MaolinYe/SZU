#include <iostream>
#include<chrono>
#include"brute.h"
#include"dynamicProgram.h"
#include"backward.h"
#include"iterativeDP.h"
#include"dichotomy.h"
using namespace std;
int main() {
    int egg=100000;
//    int height=100000;
    auto start=chrono::high_resolution_clock::now();
    auto end=chrono::high_resolution_clock::now();
    auto consume=chrono::duration_cast<chrono::microseconds>(end-start);
    for(int height=1000000;height<5000001;height=height+1000000){
        cout<<"egg: "<<egg<<" height: "<<height<<endl;

        start=chrono::high_resolution_clock::now();
        brute::superEggDrop(egg,height);
        end=chrono::high_resolution_clock::now();
        consume=chrono::duration_cast<chrono::microseconds>(end-start);
        cout<<"brute: "<<consume.count()<<endl;

        start=chrono::high_resolution_clock::now();
        dynamicProgram::superEggDrop(egg,height);
        end=chrono::high_resolution_clock::now();
        consume=chrono::duration_cast<chrono::microseconds>(end-start);
        cout<<"dp: "<<consume.count()<<endl;

        start=chrono::high_resolution_clock::now();
        iterativeDP::superEggDrop(egg,height);
        end=chrono::high_resolution_clock::now();
        consume=chrono::duration_cast<chrono::microseconds>(end-start);
        cout<<"iterativeDP: "<<consume.count()<<endl;

        start=chrono::high_resolution_clock::now();
        dichotomy::superEggDrop(egg,height);
        end=chrono::high_resolution_clock::now();
        consume=chrono::duration_cast<chrono::microseconds>(end-start);
        cout<<"dichotomy: "<<consume.count()<<endl;

        start=chrono::high_resolution_clock::now();
        backward::superEggDrop(egg,height);
        end=chrono::high_resolution_clock::now();
        consume=chrono::duration_cast<chrono::microseconds>(end-start);
        cout<<"backward: "<<consume.count()<<endl;

        cout<<endl;
    }
    return 0;
}
