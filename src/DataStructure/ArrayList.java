package DataStructure;

import InterFace.List;

public class ArrayList<E> implements List<E> {

    /**
     * ArrayList capacity 의 기본
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 빈 배
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 요소를 담을 배열
     * 배열의 직렬화를 방지하기 위해서 transient 지시자 사용
     */
    transient Object[] elementData;
    /**
     * ArrayList의 크기
     */
    private int size;

    /**
     * Constructor.
     * intialCapacity가 0보다 큰 경우 크기만큼 Object 배열을 만든다.
     * initalCapacity가 0인 경우 빈 배열 EMPTY_ELEMENTDATA로 초기화한다.
     * 0보다 작은 경우 예외처리한다.
     */
    public ArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * 리스트의 요소의 개수를 반환합니다.
     *
     * @return 리스트 요소의 개수를 반환
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 리스트가 비어있는지를 확인합니다.
     *
     * @return 리스트에 요소가 있는 경우 {@code true}, 리스트가 비어있는 경 {@code false}를 반환
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 리스트에 특정 요소가 있는지를 확인합니다.
     * 특정 요소 o가 null인 경우 따로 검사합니다. (o==null ? e==null : o.equals(e)).
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에 특정 요소가 있는 경우 {@code true}, 리스트에 특정 요소가 없는 경우 {@code false}를 반
     */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) return true;
            }
        }
        return false;
    }

    /**
     * 리스트를 배열의 형태로 반환합니다.
     *
     * @return 리스트의 모든 요소를 포함한 배열을 반환
     */
    @Override
    public Object[] toArray() {
        return copy();
    }

    private Object[] copy() {
        Object[] copy = new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = elementData[i];
        }
        return copy;
    }

    /**
     * 리스트에 특정 요소를 추가합니다.
     *
     * @param e 리스트에 추가할 특정 요소
     * @return 리스트에 중복을 허용하지 않는 경우, 리스트에 중복되는 요소가 있는 경우 {@code true}, 리스트에 중복되는 요소가 없는 경우 {@code false}를 반환
     */
    @Override
    public boolean add(E e) {
        resizeCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * 리스트의 특정 위치에 특정 요소를 추가합니다.
     *
     * @param index   리스트에 특정 요소를 추가할 특정 위치
     * @param element 리스트에 추가할 특정 요소
     */
    @Override
    public void add(int index, E element) {
        resizeCapacity(size + 1);
        fastAdd(index);
        elementData[index] = element;
        size++;
    }

    private void resizeCapacity(int minCapacity) {
        growUpCapacity(calculateCapacity(elementData, minCapacity));
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {  // 빈 배열이라면
            return Math.max(DEFAULT_CAPACITY, minCapacity); // 최소한 용량의 default값 이상으로 크기를 정한다.
        }
        return minCapacity;
    }

    private void growUpCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            elementData = grow(elementData, minCapacity);
        }
    }

    private Object[] grow(Object[] elementData, int minCapacity) {
        Object[] copy = new Object[minCapacity];
        for (int i = 0; i < elementData.length; i++) {
            copy[i] = elementData[i];
        }
        return copy;
    }

    private void fastAdd(int index) {
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
    }

    /**
     * 리스트에서 특정 요소를 제거합니다.
     *
     * @param o 리스트에서 제거할 특정 요소
     * @return 리스트에 삭제할 특정 요소를 찾지 못했거나 삭제에 실패했을 경우 {@code false}, 리스트에 특정 요소를 삭제했을 경우 {@code true}를 반환
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 리스트의 특정 위치에 있는 요소를 제거합니다.
     *
     * @param index 리스트에서 제거할 특정 요소가 있는 위치
     * @return 삭제된 요소를 반환
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        E oldValue = (E) elementData[index];
        fastRemove(index);

        return oldValue;
    }

    private void fastRemove(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

        elementData[--size] = null;
    }

    /**
     * 리스트의 모든 요소를 지우고 size 변수를 0으로 초기화합니다.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }

        size = 0;
    }

    /**
     * 리스트의 특정 위치에 있는 요소를 반환합니다.
     *
     * @param index 리스트에서 요소를 가져올 특정 위치
     * @return 리스트의 특정 위치에 존재하는 요소
     */
    @Override
    public E get(int index) {
        rangeCheck(index);

        return (E) elementData[index];
    }

    /**
     * 리스트의 특정 위치에 있는 요소를 새로운 요소로 변환합니다.
     *
     * @param index   리스트에서 변환할 요소의 위치
     * @param element 변환할 새로운 요소
     * @return 변환한 위치에 있던 오래된 요소
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 리스트에 특정 요소가 존재하는 위치를 반환합니다.
     * 리스트의 가장 처음부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에서 가장 먼저 나온 요소를 반환, 요소가 포함되지 않은 경우 -1을 반환
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) return i;
            }
        }
        return -1;
    }

    /**
     * 리스트에 특정 요소가 존재하는 위치를 반환합니다.
     * 리스트의 가장 마지막부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에서 가장 먼저 나온 요소를 반환, 요소가 포함되지 않은 경우 -1을 반환
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) return i;
            }
        }
        return -1;
    }

    /**
     * 특정 요소가 리스트 범위를 벗어나는지 체크한다.
     *
     * @param index 리스트 범위에 있는지 체크할 특정 요소
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }
}
