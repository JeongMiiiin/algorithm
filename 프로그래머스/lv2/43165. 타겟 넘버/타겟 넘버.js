let answer;

function solution(numbers, target) {
    answer = 0;
    dfs(numbers, 0, target, 0, answer);
    return answer;
}

function dfs(numbers, depth, target, sum){
    if(depth == numbers.length){ // 마지막 노드 까지 탐색한 경우
        if(target == sum){
          answer++;
        } 
    } else {
        dfs(numbers, depth + 1, target, sum + numbers[depth]); // 해당 노드의 값을 더하고 다음 깊이 탐색
        dfs(numbers, depth + 1, target, sum - numbers[depth]); // 해당 노드의 값을 빼고 다음 깊이 탐색
    }
}