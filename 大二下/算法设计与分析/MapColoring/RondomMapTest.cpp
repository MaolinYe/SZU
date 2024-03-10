#include<iostream>
#include<chrono>
#include<vector>
#include<random>
using namespace std;
void fillColor(const int);
const int vertexNumber = 100;
const int edgeNumber = 1000;
int map[vertexNumber][vertexNumber] = {0};
int color[vertexNumber] = {0};
const int colorNumber = 4;
bool colorAccess[vertexNumber][colorNumber + 1];
int degree[vertexNumber]={0};
int colorAccessNumber[vertexNumber];
int solution = 0;
int done=0;

int main() {
    random_device randomDevice;
    mt19937 mt(randomDevice());
    uniform_int_distribution<int> intDistribution_one(1,vertexNumber/4);
    uniform_int_distribution<int> intDistribution_two(vertexNumber/4+1,vertexNumber/2);
    uniform_int_distribution<int> intDistribution_three(vertexNumber/2+1,vertexNumber/4*3);
    uniform_int_distribution<int> intDistribution_four(vertexNumber/4*3+1,vertexNumber);
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_four(mt);
        int tail=intDistribution_one(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
    }
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_four(mt);
        int tail=intDistribution_two(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
    }
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_four(mt);
        int tail=intDistribution_three(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
    }
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_three(mt);
        int tail=intDistribution_one(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
    }
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_three(mt);
        int tail=intDistribution_two(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
    }
    for(int i=0;i<edgeNumber/6;i++){
        int head=intDistribution_two(mt);
        int tail=intDistribution_one(mt);
        if(head==tail||map[head][tail]){
            i--;
            continue;
        }
        map[head][tail]=map[tail][head]=1;
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
    auto consume = chrono::duration_cast<chrono::microseconds>(end - start);
    cout << "There is " << solution << " solutions.\n" << "The time consumed is " << consume.count()
         << " microseconds.\n";
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
        if(minColor>colorAccessNumber[i]&&color[i]==0||minColor==colorAccessNumber[i]&&degree[next]<degree[i]&&color[i]==0){
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
