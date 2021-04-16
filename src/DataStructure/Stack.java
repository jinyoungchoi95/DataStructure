package DataStructure;

import java.util.EmptyStackException;

public class Stack<E> extends ArrayList<E> {

    public Stack() {
    }

    /**
     * 스택의 최상단에 특정 요소를 추가합니다.
     * @param item 스택에 추가할 특정 요소
     * @return 스택에 추가한 특정 요소
     */
    public E push(E item) {
        add(item);

        return item;
    }

    /**
     * 스택의 최상단을 제거하고 반환합니다.
     * @return 제거된 스택의 요소
     */
    public E pop() {
        E object = peek();
        int len = size();

        remove(len - 1);

        return object;
    }

    /**
     * 스택의 최상단을 반환합니다.
     * @return 스택의 최상단 요소
     */
    public E peek() {
        int len = size();

        return get(len - 1);
    }

    /**
     * 스택에 특정 요소의 위치를 반환합니다.
     * 스택의 최상단부터 특정 요소가 있는 위치를 반환합니다.
     * 최상단 index : 1
     * 최상단 아래 index : 2
     * ...
     * 가장 아래 index : stack.size()
     * @param o 스택에서 찾을 특정 요소
     * @return 스택에서 가장 먼저 나온 요소를 반환, 요소가 포함되지 않은 경우 -1을 반환
     */
    public int search(Object o) {
        int index = lastIndexOf(o);

        if (index >= 0) {
            return size() - index;
        }

        return -1;
    }

    /**
     * 스택이 비어있는지를 확인합니다.
     *
     * @return 스택에 요소가 있는 경우 true, 리스트가 비어있는 경우 false를 반환
     */
    public boolean empty() {
        return size() == 0;
    }
}
