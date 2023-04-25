package practice_b_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 역량테스트 B형 대비 - 오타교정
 * 
 * 주어지는 값 - 사용자 수
 * 
 * 사용자가 오타 입력시 교정된 단어를 보여주는 기능을 제공
 * 오타교정 시스템은 사용자들의 검색 정보를 바탕으로 실시간으로 오타 교정 DB를 갱신하고, 그 결과를 반환
 * 이 시스템의 사용자들은 오타를 검색했다고 판단하는 경우, 10초 이내에 다시 검색을 시도
 * 10초 이내에 다시 검색된 단어가 이 시스템에서 정의한 오타 유형에서 하나에만 해당하는 경우에
 * 오타 교정 DB에 등록 가능
 * 오타 유형
 * 1. 추가, 삭제 -> 길이 차이 비교, 차이 후에 다른 부분을 찾고, 그 부분을 제외하고 일치하는지 확인
 * 2. 치환 -> 같은 길이를 갖고 있는지 비교, 다른 부분이 있는지 확인
 * 같은 오타, 정타 쌍이 3명의 다른 사용자에게 나타나면 오타 교정 DB에 등록
 * 그 이후 오타 교정 DB에 등록된 단어가 검색되면 교정된 정타를 반환
 * 
 * 하나의 오타에 대해 두 개 이상의 정타가 있을 수도 있다.
 * 
 * 만들어야 되는 것
 * 1. 사용자 Class -> UID, 이전 검색 단어, 이후 검색 단어, 이전 시간, 이후 시간
 * 2. 교정 후보 HashMap (교정 후보 단어와  "UID" : "정타 단어")
 * 3. 교정된 단어 HashMap (교정된 단어와 정타 단어 Set)
 * 
*/

class UserSolution {

    // The below commented methods are for your reference. If you want
    // to use it, uncomment these methods.
    // 두 글자가 일치하는지 확인, 일치하지 않는 곳이 발견되면 해당 위치 리턴
    static int mstrcmp(char[] a, char[] b) {
        int i;
        for (i = 0; a[i] != '\0'; i++)
            if (a[i] != b[i])
                return i;
        return -1;
    }

    // 해당 길이까지 일치하는지 확인, 일치하지 않는 곳이 발견되면 해당 위치 리턴
    static int mstrncmp(char[] a, char[] b, int len) {
        for (int i = 0; i < len; i++)
            if (a[i] != b[i])
                return i;
        return -1;
    }

    /*
     * //두 글자가 일치하는지 확인
     * int mstrcmp(char[] a, char[] b) {
     * int i;
     * for (i = 0; a[i] != '\0'; i++) {
     * if (a[i] != b[i]) return a[i] - b[i];
     * }
     * return a[i] - b[i];
     * }
     */

    // 해당 길이까지 일치하는지 확인 일치하지 않는 곳이 발견되면 값 리턴
    /*
     * int mstrncmp(char[] a, char[] b, int len) {
     * for (int i = 0; i < len; i++) if (a[i] != b[i]) return a[i] - b[i];
     * return 0;
     * }
     */

    // 글자 길이를 리턴하는 함수
    static int mstrlen(char[] a) {
        int len = 0;
        while (a[len] != '\0')
            len++;

        return len;
    }

    // 글자 복사
    void mstrcpy(char[] dest, char[] src) {
        int i = 0;
        while (src[i] != '\0') {
            dest[i] = src[i];
            i++;
        }
        dest[i] = src[i];
    }

    // 글자 복사
    void removeBlank(char[] dest) {
        char[] temp = Arrays.copyOf(dest, dest.length);

        for (int i = 0, idx = 0; i < dest.length; i++)
            if (temp[i] != '\0')
                dest[idx++] = temp[i];
    }

    // index부터 해당 길이까지 복사
    static void mstrncpy(char[] dest, char[] src, int index, int len) {
        if (len < 0)
            return;
        for (int i = 0; i < len; i++)
            dest[i] = src[index++];
        dest[len] = '\0';
    }

    // 사용자 객체
    static class User {
        int UID, prevTime;
        char[] prevWord;

        public User(int uID) {
            UID = uID;
        }
    }

    // 오타와 정타를 묶어야 함
    static Map<char[], Map<char[], Set<Integer>>> candidateTable = new HashMap<>();
    static Map<char[], Set<char[]>> fixTable = new HashMap<>();
    static List<User> userList = new ArrayList<>();

    void init(int n) {
        candidateTable.clear();
        fixTable.clear();
        userList.clear();
        userList.add(null); // 0번째는 비워두기
        // 사용자 리스트 만들기
        for (int i = 1; i <= n; i++)
            userList.add(new User(i));
    }

    int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
        Set<char[]> result = fixTable.get(searchWord);
        int idx = 0;

        // 교정 DB에 없을 때
        if (result != null) {
            // 교정 DB에 있을 때
            Iterator iter = result.iterator();

            while (iter.hasNext())
                correctWord[idx++] = (char[]) iter.next();
        }

        User u = userList.get(mId); // 검색한 사용자 가지고 오기
        // 오타검사를 해야할 때
        if (u.prevTime > 0 && // 과거 검색시간이 있음
                searchTimestamp - u.prevTime <= 10 && // 과거 검색시간과 현재 시간의 차이가 10초 이내
                compare(u.prevWord, searchWord) == 1) { // 오타 유형에 하나만 해당할 때
            // 오타검사 진행
            Map<char[], Set<Integer>> candidates = candidateTable.get(u.prevWord);

            // 최초등록인 경우
            if (candidates == null) {
                Set<Integer> initSet = new HashSet<>();
                initSet.add(u.UID);
                Map<char[], Set<Integer>> initCandidate = new HashMap<>();
                initCandidate.put(searchWord, initSet);
                candidateTable.put(u.prevWord, initCandidate);
            } else {
                // 이미 해당 오타에 등록되어 있는 경우
                Set<Integer> candidateSet = candidates.get(searchWord) != null ? candidates.get(searchWord)
                        : new HashSet<>();
                candidateSet.add(u.UID);

                // 오류 보고가 3번 이상이어서 오류 DB에 등록해야 할 때
                if (candidateSet.size() > 2)
                    insertFixTable(u.prevWord, searchWord);
                else
                    candidates.put(searchWord, candidateSet);
            }

            u.prevTime = 0;

        } // 오타 검사를 하지 않아도 될때 - 교정DB만 확인하고 리턴
        u.prevTime = searchTimestamp;
        u.prevWord = searchWord;

        return idx;
    }

    static char[] test = new char[] { 's', 'h', 'a', 'a', 'e', ' ', ' ', ' ', ' ', ' ', ' ' };

    // 후보테이블에서 오류 테이블로 옮기기
    private void insertFixTable(char[] wrongWord, char[] correctWord) {
        // if (mstrcmp(wrongWord, test) == -1) {
        // System.out.println(-1);
        // }

        Set<char[]> fixList = fixTable.get(wrongWord) != null ? fixTable.get(wrongWord) : new HashSet<>();
        fixList.add(correctWord);
        // candidateTable.get(wrongWord).remove(correctWord);
        fixTable.put(wrongWord, fixList);
    }

    private static int compare(char[] word1, char[] word2) {
        int result = 0;

        int len, diff = 0;
        char[] afterWord1, afterWord2;
        int standard = Math.abs(mstrlen(word1) - mstrlen(word2));

        if (standard == 1) {
            // 둘의 길이가 다를 때 -> 추가/삭제 비교

            // true인 경우 word2가 더 큼
            boolean flag = mstrlen(word1) < mstrlen(word2);

            len = mstrlen(word2);
            if (flag)
                len = mstrlen(word1);

            diff = mstrncmp(word1, word2, len);

            if (diff > -1) { // 달라진 값이 더 큰 값에서 해당 이후의 값을 복사 후 비교
                len -= diff;
                afterWord1 = new char[len + 1];
                afterWord2 = new char[len + 1];
                // 변경된 문자 뒤로 비교하기 위한 복사
                if (flag) {
                    mstrncpy(afterWord1, word1, diff, len);
                    mstrncpy(afterWord2, word2, diff + 1, len);
                } else {
                    mstrncpy(afterWord1, word1, diff + 1, len);
                    mstrncpy(afterWord2, word2, diff, len);
                }
                // 추가, 삭제 및 변경까지 일어난 경우
                if (mstrcmp(afterWord1, afterWord2) > -1)
                    result++;
            }

            result++;

        } else if (standard == 0) {
            // 둘의 길이가 같을 때 -> 치환 비교
            len = mstrlen(word1);
            // 둘의 값이 일치하는지 확인
            diff = mstrcmp(word1, word2);
            if (diff > -1) {
                // 뒤에값만 비교하면 됨 (가장 먼저 만나는 다른 위치를 리턴하기 때문에)
                afterWord1 = new char[len - diff];
                afterWord2 = new char[len - diff];

                // 변경된 문자 뒤로 비교하기 위한 복사
                mstrncpy(afterWord1, word1, diff + 1, len - diff - 1);
                mstrncpy(afterWord2, word2, diff + 1, len - diff - 1);
                // 뒤로 바뀐 문자가 없을 때
                if (afterWord1.length > 0 && afterWord2.length > 0 && mstrcmp(afterWord1, afterWord2) == -1)
                    result++;
            }
        }

        return result;
    }
}