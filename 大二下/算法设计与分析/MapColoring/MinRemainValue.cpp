#include<iostream>
#include<fstream>
#include<chrono>
#include<sstream>
#include<vector>
using namespace std;
void fillColor(const int);
int map[450][450] = {0};
int color[450] = {0};
const int colorNumber = 25;
bool colorAccess[450][colorNumber+1];
int degree[450]={0};
int colorAccessNumber[450];
int vertexNumber = 0;
int edgeNumber = 0;
int solution = 0;
int done=0;
fstream file("C:\\Users\\Yezi\\Desktop\\C++\\MapColoring\\Map\\le450_25a.txt");
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
        for(int j=1;j<=colorNumber;j++)
            colorAccess[i][j]= true;
        for(int j=0;j<vertexNumber;j++)
            degree[i]+=map[i][j];
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
int MRV(const int vertex,vector<int>&MRVRecover){
    for(int i=0;i<vertexNumber;i++){
        if(map[vertex][i]){
            if(colorAccess[i][color[vertex]]){
                MRVRecover.push_back(i);
                colorAccess[i][color[vertex]]= false;
            }
        }
    }
    int next=0;
    int minColor=colorNumber;
    for(int i=0;i<vertexNumber;i++){
        colorAccessNumber[i]=0;
        for(int j=1;j<=colorNumber;j++){
            if(colorAccess[i][j])
                colorAccessNumber[i]++;
        }
        if(minColor>colorAccessNumber[i]&&color[i]==0){
            minColor=colorAccessNumber[i];
            next=i;
        }
    }
    return next;
}
void MRV_Recover(const int vertex,vector<int>&MRVRecover){
    for(auto&it:MRVRecover){
        colorAccess[it][color[vertex]]= true;
    }
}
void fillColor(const int vertex) {
    if(done==vertexNumber){
        solution++;
//        for(int i=0;i<vertexNumber;i++)
//            cout<<color[i]<<' ';
//        cout<<endl;
        return;
    }
    for(int i=1;i<=colorNumber;i++){
        color[vertex]=i;
        if(!conflict(vertex)){
            done++;
            vector<int>MRVRecover;
            int next= MRV(vertex,MRVRecover);
            fillColor(next);
            if(solution>0)
                return;
            MRV_Recover(vertex,MRVRecover);
            done--;
        }
        color[vertex]=0;
    }
}
