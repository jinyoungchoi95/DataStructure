package InterFace;

public interface List<E> {
    /**
     * 리스트의 크기를 반환합니다.
     *
     * @return 리스의 크기를 반환
     */
    int size();

    /**
     * 리스트가 비어있는지를 확인합니다.
     *
     * @return 리스트에 요소가 있을 경우 {@code true}, 리스트에 요소가 없을 경우 {@code false}를 반환
     */
    boolean isEmpty();

    /**
     * 리스트에 특정 요소가 포함되어 있는지를 확인합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에 요소가 있을 경우 {@code true}, 리스트에 요소가 없을 경우 {@code false}를 반환
     */
    boolean contains(Object o);

    /**
     * 리스트를 아직 생성되지 않은 배열에 배열의 형태로 반환합니다.
     *
     * @return 리스트의 모든 요소를 포함한 배열을 반환
     */
    Object[] toArray();

    /**
     * 생성되어 있는 배열에 리스트의 값을 반환합니다.
     *
     * @param a 리스트의 값을 복사해 넣을 배열
     * @return 배열에 리스트의 값을 반환합니다.
     */
    <T> T[] toArray(T[] a);

    /**
     * 리스트에 특정 요소를 추가합니다.
     *
     * @param e 리스트에 추가할 특정 요소
     * @return  리스트에 중복을 허용하지 않는 경우, 리스트에 중복되는 요소가 있는 경우 {@code true}, 리스트에 중복되는 요소가 없는 경우 {@code false}를 반환
     */
    boolean add(E e);

    /**
     * 리스트에 특정 요소를 특정 위치에 추가합니다.
     * 특정 위치를 기준으로 존재하는 요소들은 뒤로 이동합니다.
     *
     * @param index 리스트에 특정 요소를 추가할 특정 위치
     * @param element 리스트에 추가할 특정 요소
     */
    void add(int index, E element);

    /**
     * 리스트에 특정 요소를 제거합니다.
     *
     * @param o 리스트에서 제거할 특정 요소
     * @return 리스트에 삭제할 특정 요소를 찾지 못했거나 삭제에 실패했을 경우 {@code false}, 리스트에 특정 요소를 삭제했을 경우 {@code true}를 반환
     */
    boolean remove(Object o);

    /**
     * 리스트에 특정 위치에 있는 요소를 삭제합니다.
     *
     * @param index 리스트에서 제거할 특정 요소가 있는 위치
     * @return 삭제된 요소를 반환
     */
    E remove(int index);

    /**
     * 리스트의 모든 요소를 제거합니다.
     */
    void clear();

    /**
     * 리스트의 특정 위치에 있는 요소를 가져옵니다.
     *
     * @param index 리스트에서 요소를 가져올 특정 위치
     * @return 리스트의 특정 위치에 있는 요소를 반환
     */
    E get(int index);

    /**
     * 리스트의 특정 위치에 있는 요소를 새로운 요소로 교체합니다.
     *
     * @param index 리스트에서 변환할 요소의 위치
     * @param element 변환할 새로운 요소
     * @return 위치에 원래 존재하던 요소를 반환
     */
    E set(int index, E element);

    /**
     * 리스트에 특정 요소가 담긴 위치를 반환합니다.
     * 리스트의 가장 처음부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return  리스트에서 가장 먼저 나온 요소를 반환, 요소가 포함되지 않은 경우 -1을 반환
     */
    int indexOf(Object o);

    /**
     * 리스트에서 특정 요소가 담긴 위치를 반환합니다.
     * 리스트의 가장 마지막부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return  리스트에서 가장 먼저 나온 요소를 반환, 요소가 포함되지 않은 경우 -1을 반환
     */
    int lastIndexOf(Object o);
}