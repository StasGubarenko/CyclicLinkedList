import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CyclicLinkedList<T> {

    private Node<T> head;
    private int size;

    @Override
    public String toString() {
        Node<T> current = head;
        String result = "";

        if (current == null) {
            return "List is empty";
        } else {

            result = result + current.toString();

            while (current.next != head) {

                current = current.next;
                result = result + current.toString();

            }
            return result;

        }
    }

    public void add(T data) {

        Node<T> newNode = new Node<T>(data, head);

        if (head == null) {

            head = newNode;
            head.next = head;

        } else {

            Node<T> current = head;

            while (current.next != head) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    public void add(int index, T element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index : " + index + " size : " + size);
        }

        if (index == 0 && size == 0) {
            add(element);
            return;
        }

        if (index == 0 && size != 0) {
            Node<T> newNode = new Node<>(element, null);

            Node<T> current = head;

            do {
                current = current.next;
            } while (current.next != head);

            Node<T> temp = head;
            current.next = newNode;
            head = newNode;
            newNode.next = temp;

            size++;
            return;
        }

        //В конец списка
        if (index == size) {
            add(element);
            return;
        }

        Node<T> newNode = new Node<T>(element, null);

        Node<T> current = head;
        int count = 0;

        do {

            if (index - 1 == count) {
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return;
            }

            current = current.next;
            count++;
        } while (current.next != head);

    }

    public boolean addAll(int index, CyclicLinkedList<T> cyclicLinkedList) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index : " + index + " size: " + size);
        }

        if (size == 0) {
            return false;
        }

        if (index == 0 && this != cyclicLinkedList) {
            Node<T> current = cyclicLinkedList.head;
            int i = 0;

            do {
                add(i, current.data);
                i++;
                current = current.next;
            } while (current.next != cyclicLinkedList.head);

            add(i, current.data);
            return true;
        }

        if (index == size) {

            Node<T> current = cyclicLinkedList.head;

            int newIndex = size();
            int borderSize = cyclicLinkedList.size;

            for (int i = 0; i < borderSize; i++) {

                add(newIndex, current.data);
                current = current.next;
                newIndex++;
            }

            return true;
        }

        Node<T> current = head;

        if (this == cyclicLinkedList) {

            if (size > 1) {
                CyclicLinkedList<T> list = new CyclicLinkedList<>();

                //Добавляем в лист элементы из исходного листа
                for (int i = 0; i < size(); i++) {
                    list.add(i, current.data);
                    current = current.next;
                }

                int sizeInnerList = size();
                for (int i = 0; i < sizeInnerList; i++) {
                    add(index, list.get(i));
                    index++;
                }
                return true;
            } else {
                add(index, current.data);
                return true;
            }

        } else {
            Node<T> cur = cyclicLinkedList.head;

            int i = index;

            do {
                add(i, cur.data);
                i++;
                cur = cur.next;
            } while (cur.next != cyclicLinkedList.head);

            add(i, cur.data);
            return true;
        }
    }

    public T getLastElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            return current.data;
        }
    }

    public T getFirstElement() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        } else {
            return head.data;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public T get(int index) {

        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index: " + index + " size " + size);
        }

        Node<T> current = head;
        int iteration = 0;


        do {
            if (index == iteration) {
                return current.data;
            }
            current = current.next;
            iteration++;

        } while (current.next != head);

        return current.data;
    }

    public void sort(Comparator<? super T> nameComparator) {
        //Если список ЛИБО пуст, ЛИБО содержит 1 элемент, то нет необходимости его сортировать
        if (size == 0 || size == 1) {
            return;
        }

        //Создаем массив. Поскольку лист не предоставляет рандомный доступ к его элементам
        T[] array = (T[]) new Object[size()];


        //В массив добавляем значение из листа
        Node<T> current = head;
        int i = 0;

        do {
            array[i] = current.data;
            current = current.next;
            i++;
        } while (current.next != head);

        array[i] = current.data;

        //Отсортировали массив
        Arrays.sort(array, nameComparator);

        Node<T> cur = head;
        int j = 0;
        do{

            cur.data = array[j];
            cur = cur.next;
            j++;

        }while (cur.next != head);

        cur.data = array[j];
    }

    public boolean hasNext() {
        Node<T> current = head;

        if (current.next != head) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public boolean contains(T element) {
        if (size == 0) {
            return false;
        }
        Node<T> current = head;

        do {
            if (current.data == element) {
                return true;
            }

            current = current.next;
        } while (current.next != head);

        if (current.data == element) {
            return true;
        }
        return false;
    }

    public T remove(int index) {
        checkElementInIndex(index);

        if (index == 0) {
            T value = head.data;

            head = head.next;

            size--;

            return value;

        }

        if (index == size - 1) {
            Node<T> current = head;
            T dataLastNode = current.data;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
                dataLastNode = current.next.data;
            }

            current.next = head;
            size--;
            return dataLastNode;
        }

        Node<T> current = head;
        T dataMiddleNode = current.data;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            dataMiddleNode = current.next.data;
        }

        current.next = current.next.next;

        size--;
        return dataMiddleNode;

    }


    private void checkElementInIndex(int index) {
        if (!isElementInIndex(index)) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + size);
        }
    }

    private boolean isElementInIndex(int index) {
        return index >= 0 && index < size;
    }

    public T set(int index, T element) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index : " + index + " size: " + size);
        }

        if (index == 0) {

            T dataOldNode = head.data;
            head.data = element;
            return dataOldNode;

        }

        if (index == size - 1) {

            Node<T> current = head;

            while (current.next != head) {
                current = current.next;
            }

            T dataOldNode = current.data;
            current.data = element;
            return dataOldNode;

        }

        Node<T> current = head;
        int count = 0;
        T dataOldNode = current.data;

        for (int i = 0; i < index; i++) {
            current = current.next;
            count++;
            if (count == index) {
                current.data = element;
            }
        }
        return dataOldNode;
    }

    public CyclicLinkedList<T> subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size());


        CyclicLinkedList<T> cyclicLinkedList = new CyclicLinkedList<T>();

        if (fromIndex == toIndex) {
            return cyclicLinkedList;
        }

        Node<T> current = head;

        if (fromIndex == 0) {
            for (int i = fromIndex; i < toIndex; i++) {
                cyclicLinkedList.add(current.data);
                current = current.next;
            }
        } else {

            for (int i = 0; i < size; i++) {
                if (i == fromIndex) {
                    break;
                }
                current = current.next;
            }

            for (int i = fromIndex; i < toIndex - 1; i++) {
                cyclicLinkedList.add(current.data);
                current = current.next;
            }
            cyclicLinkedList.add(current.data);
            return cyclicLinkedList;
        }

        if (fromIndex == toIndex) {
            return cyclicLinkedList;
        }


        return cyclicLinkedList;
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex " + toIndex);
        }

        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex " + fromIndex + " toIndex " + toIndex);
        }
    }

    private static class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }


        @Override
        public String toString() {
            return "Node{" + "data=" + data + '}';
        }


    }

}
