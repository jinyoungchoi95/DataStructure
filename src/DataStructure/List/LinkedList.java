package DataStructure.List;

import DataStructure.Queue.Deque;

import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E>, Deque<E> {

    transient Node<E> first;
    transient Node<E> last;
    private int size = 0;

    /**
     * 먼저 입력받을 값이 없기 때문에 List를 전부 Null로 초기화한다.
     */
    public LinkedList() {
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
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
     * @return 리스트에 요소가 있는 경우 true, 리스트가 비어있는 경우 false를 반환
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 리스트에 특정 요소가 있는지를 확인합니다.
     * 특정 요소 o가 null인 경우 따로 검사합니다. (o==null? e==null : o.equals(e)).
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에 특정 요소가 있는 경우 true, 리스트에 특정 요소가 없는 경우 false를 반환
     */
    @Override
    public boolean contains(Object o) {
        return (indexOf(o) >= 0) ? true : false;
    }

    /**
     * 리스트를 배열의 형태로 반환합니다.
     *
     * @return 리스트의 모든 요소를 포함한 배열을 반환
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[index++] = x.item;
        }

        return result;
    }

    /**
     * 리스트를 기존 배열에 맞게 반환합니다.
     *
     * @param a 리스트의 값을 복사해 넣을 배열
     * @return 기존의 배열 내부에 리스트의 값들을 반환, 리스트의 크기가 배열보다 큰 경우 리스트를 그대로 반환, 작을 경우 리스트의 요소를 기존 배열에 대입, 배열[size] 값에 구분을 하기 위해 null 값 대입
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) copy(size, a);
        }
        int index = 0;
        Object[] result = a;
        for (Node<E> x = first; x != null; x = x.next) {
            result[index++] = x.item;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    private Object[] copy(int len, Object[] old) {
        Object[] copy = new Object[len];
        for (int i = 0; i < len; i++) {
            copy[i] = old[i];
        }
        return copy;
    }

    /**
     * 리스트에 특정 요소를 추가합니다.
     *
     * @param e 리스트에 추가할 특정 요소
     * @return 리스트에 중복을 허용하지 않는 경, 리스트에 중복된 요소가 있는 경우
     */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 리스트의 가장 처음에 특정요소를 추가합니다.
     * @param e 리스트에 추가할 특정 요소
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 리스트의 가장 마지막에 특정요소를 추가합니다.
     *
     * @param e 리스트에 추가할 특정 요소
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 리스트의 특정 위치에 특정 요소를 추가합니다.
     * @param index 리스트에 특정 요소를 추가할 특정 위치
     * @param element 리스트에 추가할 특정 요소
     */
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        if (index == size) {
            linkLast(element);
        } else {
            linkInIndex(element, node(index));
        }
    }

    private void linkInIndex(E e, Node<E> input) {
        final Node<E> prev = input.prev;
        final Node<E> newNode = new Node(prev, e, input);
        input.prev = newNode;
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 리스트에서 특정 요소를 제거합니다.
     *
     * @param o 리스트에서 제거할 특정 요소
     * @return 리스트에 삭제할 특정 요소를 찾지 못했거나 삭제에 실패했을 경우 false, 리스트에 특정 요소를 삭제했을 경우 true를 반환
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 리스트의 특정 위치에 있는 특정 요소를 제거합니다.
     * @param index 리스트에서 제거할 특정 요소가 있는 위치
     * @return 삭제된 요소의 item 값을 반환
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        return unlink(node(index));
    }

    /**
     * 리스트의 가장 처음 위치에 있는 요소를 제거합니다.
     * @return 제거한 요소의 item 값을 반환
     */
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst();
    }

    /**
     * 리스트의 가장 마지막 위치에 있는 요소를 제거합니다.
     * @return 제거한 요소의 item 값을 반환
     */
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        return unlinkLast();
    }

    private E unlink(Node<E> x) {
        final E item = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return item;
    }

    private E unlinkFirst() {
        final E item = first.item;
        final Node<E> next = first.next;

        first = next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return item;
    }

    private E unlinkLast() {
        final E item = last.item;
        final Node<E> prev = last.prev;

        last = prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    /**
     * 리스트의 모든 요소를 지우고 size 변수를 0으로 초기화합니다.
     */
    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
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
        return node(index).item;
    }

    /**
     * 리스트의 특정 위치에 있는 요소를 새로운 요소로 반환합니다.
     *
     * @param index   리스트에서 변환할 요소의 위치
     * @param element 변환할 새로운 요소
     * @return 변환한 위치에 있던 오래된 요소
     */
    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        Node<E> x = node(index);
        E oldValue = x.item;
        x.item = element;
        return oldValue;
    }

    /**
     * 리스트에 특정 요소가 존재하는 위치를 반환합니다.
     * 리스트의 가장 처음부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에서 가장 먼저 나온 요소의 위치를 반환, 포함되지 않은 경우 -1을 반환
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next, index++) {
                if (x.item == null) return index;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next, index++) {
                if (o.equals(x.item)) return index;
            }
        }
        return -1;
    }

    /**
     * 리스트에 특정 요소가 존재하는 위치를 반환합니다.
     * 리스트의 가장 처음부터 검사합니다.
     *
     * @param o 리스트에서 찾을 특정 요소
     * @return 리스트에서 가장 먼저 나온 요소의 위치를 반환, 포함되지 않은 경우 -1을 반환
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if (o == null) {
            for (Node<E> x = last; x != null; x = x.prev, index--) {
                if (x.item == null) return index;
            }
        } else {
            for (Node<E> x = last; x != null; x = x.prev, index--) {
                if (o.equals(x.item)) return index;
            }
        }
        return -1;
    }

    /**
     * 큐의 가장 처음 위치에 특정 요소를 추가합니다.
     * @param e 큐의 처음 위치에 추가할 특정 요소.
     * @return 큐에 특정 요소가 추가된 경우 {@code true}, 추가되지 않은 경우 {@code false}를 반환
     */
    public boolean offer(E e) {
        return add(e);
    }

    /**
     * 큐의 가장 처음 요소를 반환합니다.
     * @return 큐의 최상단 요소
     */
    public E peek() {
        Node<E> node = first;
        return node == null ? null : node.item;
    }

    /**
     * 큐의 가장 처음 요소를 제거하고 반환합니다.
     * @return 제거된 큐의 요소
     */
    public E poll() {
        Node<E> node = first;
        return node == null ? null : unlinkFirst();
    }


    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E pollFirst() {
        Node<E> node = first;
        return node == null ? null : unlinkFirst();
    }

    @Override
    public E pollLast() {
        Node<E> node = last;
        return node == null ? null : unlinkLast();
    }

    @Override
    public E peekFirst() {
        Node<E> node = first;
        return node == null ? null : node.item;
    }

    @Override
    public E peekLast() {
        Node<E> node = last;
        return node == null ? null : node.item;
    }


    /**
     * 특정 위치에 존재하는 node를 반환합니다.
     * 특정 위치가 size의 1/2보다 작을 경우 List의 처음부터, 클 경우 List의 마지막부터 검사합니다.
     *
     * @param index 리스트에서 원하는 요소의 특정 위치
     * @return 특정 위치에 존재하는 요소
     */
    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private void rangeCheck(int index) {
        if (index < 0 || this.size <= index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || this.size < index) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }
}
