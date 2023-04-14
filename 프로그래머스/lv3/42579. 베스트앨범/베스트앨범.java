import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

/*
 * 프로그래머스 - 베스트앨범
 * 
 * 주어지는 값
 * genres -> 노래의 장르
 * plays -> 노래별 재생 횟수
 * 
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범 출시
 * 노래는 고유번호로 구분.
 * 노래를 수록하는 기준
 * 1. 속한 노래가 많이 재생된 장르를 먼저 수록
 * 2. 장르 내에서 많이 재생된 노래를 먼저 수록
 * 3. 장르 내에서 재생횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록
 * 
 * TreeMap -> 장르와 Queue 담기
 * PriorityQueue의 사이즈를 계속 재서 2개를 유지
 * 
*/

class Solution {
    static class Genre implements Comparable<Genre>{
		String title;
		int maxPlay;
		
		public Genre(String title, int maxPlay) {
			this.title = title;
			this.maxPlay = maxPlay;
		}
		@Override
		public int compareTo(Genre o) {
			return o.maxPlay - this.maxPlay;
		}
		@Override
		public boolean equals(Object o) {
			if(this == o) return true;
			
			if(!(o instanceof Genre)) return false;
			Genre genre = (Genre) o;
			return Objects.equals(this.title, genre.title); 
		}
		@Override
		public int hashCode() {
			return Objects.hash(this.title);
		}
	}
	
	static class Song implements Comparable<Song>{
		int no, cnt;

		public Song(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Song o) {
			return this.cnt != o.cnt ? o.cnt - this.cnt : this.no - o.no;
		}
	}
    
    static Map<String, Genre> genreList = new HashMap<>();
	static Map<Genre, List<Song>> initAlbum = new HashMap<>();
	static TreeMap<Genre, List<Song>> bestAlbum = new TreeMap<>();
    public int[] solution(String[] genres, int[] plays) {
        //초기화
		genreList.clear();
		initAlbum.clear();
		bestAlbum.clear();
		
		for(int i=0; i < genres.length; i++) {
			//최대 재생수 설정
			String name = genres[i];
			Genre genre = genreList.get(name);
			if(genre == null) genre = new Genre(name, 0);
			genre.maxPlay += plays[i];
			genreList.put(name, genre);
			Song song = new Song(i, plays[i]);
			if(initAlbum.get(genre) == null) { //생성이 되어있지 않은 장르일 때
				List<Song> list = new ArrayList<>();
				list.add(song);
				initAlbum.put(genre, list);		
			}
			//생성이 되어있는 장르일 때
			else initAlbum.get(genre).add(song);
		}
		
		bestAlbum = new TreeMap<>(initAlbum);
		
		
		//초기에 두개로 만들기
        int[] ans = new int[bestAlbum.size() * 2];
        
        int idx = 0;
        for(Entry<Genre, List<Song>> pair : bestAlbum.entrySet()) {
        	List<Song> list = pair.getValue();
        	Collections.sort(list);
        	
        	ans[idx++] = list.get(0).no;
        	
        	//두개 이상이 수록되어 있으면 두개
        	if(list.size() > 1) ans[idx++] = list.get(1).no; 
        		
        }
        
        ans = Arrays.copyOf(ans, idx);
        
        
        return ans;
    }
}