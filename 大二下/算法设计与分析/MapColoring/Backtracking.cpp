#include<iostream>
#include<fstream>
#include<chrono>
#include<sstream>

using namespace std;
void fillColor(const int);
int map[450][450] = {0};
int color[450] = {0};
const int colorNumber = 4;
int vertexNumber = 0;
int edgeNumber = 0;
int solution = 0;
int done=0;
fstream file("C:\\Users\\Yezi\\Desktop\\C++\\MapColoring\\Map\\le9_4.txt");
string line, word;

int main() {
    if (!file.is_open()) {
        cout << "File error.\n";
        return 1;
    }
    getline(file, line);
    istringstream iss(line);
    iss >> word >> word >> vertexNumber >> edgeNumber;
    int head, tail;
    for (int i = 0; i < edgeNumber; i++) {
        getline(file, line);
        istringstream ISS(line);
        ISS >> word >> head >> tail;
        map[tail - 1][head - 1] = map[head - 1][tail - 1] = 1;
    }
    auto start = chrono::high_resolution_clock::now();
    fillColor(0);
    auto end = chrono::high_resolution_clock::now();
    auto consume = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "There is " << solution << " solutions.\n" << "The time consumed is " << consume.count()
         << " ms.\n";
    return 0;
}

bool conflict(const int &vertex) {
    for (int i = 0; i < vertexNumber; i++) {
        if (map[vertex][i] && color[vertex] == color[i])
            return true;
    }
    return false;
}

void fillColor(const int vertex) {
    if(done==vertexNumber){
        solution++;
        for(int i=0;i<vertexNumber;i++)
            cout<<color[i]<<' ';
        cout<<endl;
        return;
    }
    for(int i=1;i<=colorNumber;i++){
        color[vertex]=i;
        if(!conflict(vertex)){
            done++;
            fillColor(vertex + 1);
            done--;
        }
        color[vertex]=0;
    }
}
