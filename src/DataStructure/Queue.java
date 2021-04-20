package DataStructure;

public interface Queue<E> {
    /**
     * 큐의 가장 처음 위치에 특정 요소를 추가합니다.
     * @param e 큐의 처음 위치에 추가할 특정 요소.
     * @return 큐에 특정 요소가 추가된 경우 {@code true}, 추가되지 않은 경우 {@code false}를 반환
     */
    boolean offer(E e);

    /**
     * 큐의 가장 처음 요소를 제거하고 반환합니다.
     * @return 제거된 큐의 요소
     */
    E poll();

    /**
     * 큐의 가장 처음 요소를 반환합니다.
     * @return 큐의 최상단 요소
     */
    E peek();
}
