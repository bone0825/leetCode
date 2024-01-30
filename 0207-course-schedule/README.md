<h2><a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a></h2><h3>Medium</h3><hr><div><p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
</ul>
</div>

<hr>
<div>
	
# Intuition

<!-- Describe your first thoughts on how to solve this problem. -->

**문제 분석:**
A를 듣기위해 B를 들어야하는 강좌가 있을 때 전체 강좌를 수강할 수 있느냐를 묻는 문제였다.

**False의 경우:**
A를 듣기 위해 B를 들어야 하지만, B를 듣기 위해 A를 들어야 하는 경우.
즉, 하나의 경우 안에서 순환이 발생하는 경우 전체 강좌를 수강할 수 없다.

</div>
<div>
	
# Approach
<!-- Describe your approach to solving the problem. -->
A,B -> B,C -> C,D를 구현하기 위해 재귀함수를 생각했고, 이를 기반으로 코드를 구현함.

결과 : 시간초과.
분석 : A,B -> B,C로 넘어갈 때 전체 리스트를 다시 탐색해야 하는 경우가 발생하기 때문에 for의 반복이 급격하게 늘어나는 것으로 분석됨.

문제해결 : dfs알고리즘을 이용해 방문한 Node에 대해 체크를 진행하여 문제 해결.

</div>

# Code

```
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
```
https://leetcode.com/problems/course-schedule/solutions/4646437/leetcourse-207-dfs
