#include<iostream>
#include<fstream>
#include<chrono>
#include<sstream>

using namespace std;
struct Vertex{
    int degree=0;
    int place;
}vertex[450];
void fillColor(const int);
int map[450][450] = {0};
int color[450] = {0};
const int colorNumber = 15;
int vertexNumber = 0;
int edgeNumber = 0;
int solution = 0;
int done=0;
fstream file("C:\\Users\\Yezi\\Desktop\\C++\\MapColoring\\Map\\le450_15b.txt");
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
    for(int i=0;i<vertexNumber;i++){
        vertex[i].place=i;
        for(int j=0;j<vertexNumber;j++){
            vertex[i].degree+=map[i][j];
        }
    }
    sort(vertex,vertex+vertexNumber,[](const Vertex&a,const Vertex&b){return a.degree>b.degree;});
    auto start = chrono::high_resolution_clock::now();
    fillColor(0);
    auto end = chrono::high_resolution_clock::now();
    auto consume = chrono::duration_cast<chrono::milliseconds>(end - start);
    cout << "We had found " << solution << " solutions.\n" << "The time consumed is " << consume.count()
         << " ms.\n";
    return 0;
}

bool conflict(const int &vertexIndex) {
    for (int i = 0; i < vertexNumber; i++) {
        if (map[vertexIndex][i] && color[vertexIndex] == color[i])
            return true;
    }
    return false;
}

void fillColor(const int vertexIndex) {
    if(done==vertexNumber){
        solution++;
        return;
    }
    for(int i=1;i<=colorNumber;i++){
        color[vertex[vertexIndex].place]=i;
        if(!conflict(vertex[vertexIndex].place)){
            done++;
            fillColor(vertexIndex + 1);
            if(solution>0)
                return;
            done--;
        }
        color[vertex[vertexIndex].place]=0;
    }
}
