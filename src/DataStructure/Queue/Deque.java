package DataStructure.Queue;

public interface Deque<E> extends Queue<E> {

    // *** Deque method ***
    /**
     * 큐의 가장 처음 위치에 특정 요소를 추가합니다.
     * @param e 큐의 처음 위치에 추가할 특정 요소
     * @return 큐에 특정 요소가 추가되는 경우 {@code true}, 추가되지 않은 경우 {@code false}를 반환
     */
    boolean offerFirst(E e);

    /**
     * 큐의 가장 마지막 위치에 특정 요소를 추가합니다.
     * @param e 큐의 마지막 위치에 추가할 특정 요소
     * @return 큐에 특정 요소가 추가되는 경우 {@code true}, 추가되지 않은 경우 {@code false}를 반환
     */
    boolean offerLast(E e);

    /**
     * 큐의 가장 처음 요소를 제거하고 반환합니다.
     * @return 제거된 큐의 요소
     */
    E pollFirst();

    /**
     * 큐의 가장 마지막 요소를 제거하고 반환합니다.
     * @return 제거된 큐의 요소
     */
    E pollLast();

    /**
     * 큐의 가장 처음 요소를 반환합니다.
     * @return 큐의 최상단 요소
     */
    E peekFirst();

    /**
     * 큐의 가장 마지막 요소를 반환합니다.
     * @return 큐의 최하단 요소
     */
    E peekLast();
}