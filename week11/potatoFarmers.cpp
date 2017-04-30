#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
struct Farmer {
    int iq, weight;
};

int n;
vector<Farmer> farmers;
void lis(int testCase) {
    
    sort(farmers.begin(), farmers.end(), [](Farmer a, Farmer b) { return a.iq == b.iq ? b.weight < a.weight : a.iq < b.iq; });
    
    int* currSum = new int[farmers.size()];
    currSum[0] = 1;
    int maxSum = 1;
    
    int size = farmers.size();
    
    for(int x = 1; x < size; x++) {
        currSum[x] = 1;
        
        for (int y = 0; y < x; y++) {
            if (farmers[x].weight < farmers[y].weight && currSum[x] < currSum[y] + 1) {
                currSum[x] = currSum[y] + 1;
                
                if (currSum[x] > maxSum) {
                    maxSum = currSum[x];
                    break;
                }
            }
        }
    }
    
    cout << "Case #" << testCase << ": " << maxSum << endl;
}



int main(int argc, const char * argv[]) {    
    int t;
    
    cin >> t;
    
    for (int i = 1; i <= t; i++) {
        cin >> n;
        farmers.resize(n);
        
        for (int j = 0; j < n; j++) {
            farmers[j] = Farmer();
            cin >> farmers[j].iq;
            cin >> farmers[j].weight;
        }
        
        lis(i);
    }
    
    return 0;
}