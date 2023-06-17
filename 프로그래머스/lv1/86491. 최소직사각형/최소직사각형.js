function solution(sizes) {
    let wid = Number.MIN_VALUE;
    let hei = Number.MIN_VALUE;
    
    sizes.forEach(item => {
        const targetWid = item[0];
        const targetHei = item[1];
        if(targetWid <= targetHei){
            wid = Math.max(targetHei, wid);
            hei = Math.max(targetWid, hei);
        } else {
            wid = Math.max(targetWid, wid);
            hei = Math.max(targetHei, hei);
        } 
    });
    const answer = wid * hei;
    return answer;
}