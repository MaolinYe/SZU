/*
 *  邻接矩阵建图
 *  适合稠密图（点少边多）
 */
#include<iostream>
#include<cstring>
using namespace std;
const int maxn = 1e5+5; // 图中点的最大数量
int g[maxn][maxn]; // 邻接矩阵 g[u][v]表示u->v的边的数量
int main(){
    int n,m;  // 点数n 边数m
    memset(g,0,sizeof(g));  // 清空矩阵
    cout<<"请输入图的点数和边数"<<endl;
    cin>>n>>m;
    cout<<"请输入每一条边（示例：1 2表示1到2有一条边）"<<endl;
    for(int i=0,v,u;i<m;++i){
        cin>>v>>u;
        ++g[v][u];
        // g[u][v]=1;  // 若建无向图则要建立双向边
    }
}
