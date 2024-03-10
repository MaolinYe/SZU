/*
 *  使用vector实现邻接表
 *  邻接表建图适合稀疏图（点多边少）
 */
#include<bits/stdc++.h>
using namespace std;
class Edge{
public:
    int from;   // 始点
    int to;     // 终点
    Edge(){}
    Edge(int _from,int _to):from(_from),to(_to) {}
};
class Graph{
    int point_nums;  // 点的数量
    int edge_nums;   // 边的数量
    vector<vector<Edge> > G;
public:
    Graph(int n,int m):point_nums(n), edge_nums(m),G(point_nums) {}
    int add_edge(int from,int to){   // 增加一条从from点到to点的单向边
        G[from].push_back(Edge(from,to));   // 尾插法在点from的出边表插入边
        return 0;
    }
};
int main(){
    int n,m;  // 点数n 边数m
    cout<<"请输入图的点数和边数"<<endl;
    cin>>n>>m;
    Graph g(n,m);
    cout<<"请输入每一条边（示例：1 2表示1到2有一条边）"<<endl;
    for(int i=0,v,u;i<m;++i){
        cin>>v>>u;
        g.add_edge(v,u);
        // g.add_edge(u,v);  // 若建无向图则要建立双向边
    }
}
