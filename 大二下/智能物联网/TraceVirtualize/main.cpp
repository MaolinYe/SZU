#include <iostream>
#include<fstream>
#include<vector>
#include<algorithm>
#include<iomanip>

using namespace std;


struct Point {
    int ID;
    long long time;
    float latitude;
    float longitude;

    static bool cmp(Point a, Point b) {
        if (a.ID != b.ID)
            return a.ID < b.ID;
        return a.time < b.time;
    }
};


int main() {
    vector<Point> point;
    fstream file("C:\\Users\\Yezi\\Desktop\\C++\\TraceVirtualize\\TraceData.txt");
    if (!file.is_open()) {
        cout << "File error.\n";
        return 1;
    }
    int ID;
    long long time;
    float latitude;
    float longitude;
    string line;
    for (int i = 0; i < 294; i++) {
        getline(file, line);
        istringstream ISS(line);
        ISS >> ID >> time >> latitude >> longitude;
        point.push_back({ID, time, latitude, longitude});
    }
    sort(point.begin(), point.end(), Point::cmp);
    for (int j = 0; j < 3; j++) {
        for (int i = 0; i < 98; i++) {
            cout << "'p" << i << "':[" << fixed << setprecision(7) << point[i + j * 98].longitude << ','
                 << setprecision(8) << point[i + j * 98].latitude << "]," << endl;
        }
        cout << endl;
    }
    return 0;
}