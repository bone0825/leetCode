// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
//         int len = prerequisites.length;
//         for(int i = 0; i < len; i ++){ // 배열의 길이 내에서
//             if(!getResult(i, prerequisites[i][0], prerequisites[i][1], prerequisites)) return false; //현재 위치, course, precourse, 전체 배열을 바탕으로 결과값 재귀함수 실행
//         }
//         return true;
//     }

//     public boolean getResult(int indx, int main ,int sub, int[][] prerequisites){
//         for(int i = 0; i < prerequisites.length; i++){
//             if(prerequisites[i][0] == prerequisites[i][1]) return false; // precourse와 course가 같으면 순환 발생...
//             if(i == indx) continue;
//             if(prerequisites[i][0] == sub){ //탐색한 course가 이전의 precourse와 같다면
//                 if (prerequisites[i][1] == main || prerequisites[i][1] == prerequisites[indx][0]){ //탐색한 precourse의 값이 이전의 course값과 같다면
//                     return false; // 순환 발생
//                 } else{
//                     return getResult(indx, prerequisites[i][0], prerequisites[i][1],prerequisites); //다음 course를 찾아 탐색
//                 }
                
//             }
//         }
//         return true;
//     }
// }
class Solution {
    boolean[] visited, cycle;
    ArrayList<Integer> arr[];
    boolean result = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        cycle = new boolean[numCourses];
        arr = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++){ //numCourse 개의
            arr[i] = new ArrayList<>(); // arrayList 선언
        }

        for(int i = 0; i < prerequisites.length; i++){
            int cor = prerequisites[i][0]; //수강강의
            int pre = prerequisites[i][1]; //선행조건
            arr[pre].add(cor); // 선행조건 -> (선행조건) -> 수행조건
        }
        
        for(int i = 0; i < numCourses; i++){
            dfs(i);
            if(!result) break;
        }
        return result;        
    }

    void dfs(int numCourse){
        if(visited[numCourse]){
            if(cycle[numCourse]){
                result = false;
            }
            return;
        }
        visited[numCourse] = true; // 방문 표기
        cycle[numCourse] = true; // 분기 표기

        for(int i = 0; i < arr[numCourse].size(); i++){
            dfs(arr[numCourse].get(i));
        }

        cycle[numCourse] = false; // 하나의 사이클이 다 돌아가면 초기화
    }
}