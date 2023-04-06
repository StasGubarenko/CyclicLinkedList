import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CyclicLinkedListTest {


    @Test
    @DisplayName("Проверка добавления элемента в конец списка")
    public void addElementTest() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);

        int[] expectedArray = {1, 2, 3, 4};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка получение элемента по отрицательному индексу")
    public void checkGetElementByNegativeIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.get(-1));

        assertEquals("index: -1 size 0", actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка получение элемента по некорректному индексу")
    public void checkGetElementByIncorrectIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.get(0));

        assertEquals("index: 0 size 0", actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка получение элемента по некорректному индексу")
    public void checkGetElementByIncorrectIndexMore() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(0);
        cyclicLinkedList.add(0);
        cyclicLinkedList.add(0);

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.get(4));

        assertEquals("index: 4 size 3", actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка добавление элемента в начало списка")
    public void addElementInTheBeginningInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(0, 0);

        int[] expectedArray = {0, 1, 2};

        for (int i = 0; i < expectedArray.length; i++) {
            Assertions.assertEquals(expectedArray[i], cyclicLinkedList.get(i));
        }

        Assertions.assertEquals(3,cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка добавление элемента в середину списка")
    public void addElementInTheMiddleOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(2, 5);

        int[] expectedArray = {1, 2, 5, 3, 4};

        for (int i = 0; i < expectedArray.length; i++) {
            Assertions.assertEquals(expectedArray[i], cyclicLinkedList.get(i));
        }

        Assertions.assertEquals(5, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка добавление элемента в конец списка")
    public void addElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);

        cyclicLinkedList.add(4, 99);

        int[] expectedArray = {1, 2, 3, 4, 99};

        for (int i = 0; i < expectedArray.length; i++) {
            Assertions.assertEquals(expectedArray[i], cyclicLinkedList.get(i));
        }

        Assertions.assertEquals(5, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка добавление элемента в пустой список")
    public void addElementInTheEmptyList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(0, 99);

        int[] expectedArray = {99};

        for (int i = 0; i < expectedArray.length; i++) {
            Assertions.assertEquals(expectedArray[i], cyclicLinkedList.get(i));
        }
        Assertions.assertEquals(1, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка срабатывания исключения при невалидном индексе")
    public void throwsException() throws IndexOutOfBoundsException {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.add(-1,6));

        assertEquals("index : -1 size : 2", actualException.getMessage());

    }

    @Test
    @DisplayName("Проверка срабатывания исключения при индексе больше чем размер списка")
    public void throwsExceptionWhenIndexIsGreaterThenSizeOfList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.add(3,3));
        assertEquals("index : 3 size : 2", actualException.getMessage());
    }

    @Test
    @DisplayName("Проверка добавления списка 2 в начало списка 1")
    public void addSecondListToFirstList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);

        CyclicLinkedList<Integer> cyclicLinkedList1 = new CyclicLinkedList<>();
        cyclicLinkedList1.add(3);
        cyclicLinkedList1.add(4);

        cyclicLinkedList.addAll(0, cyclicLinkedList1);

        int[] arrays = {3, 4, 1, 2};

        for(int i = 0; i < arrays.length; i++){
            if(cyclicLinkedList.get(i) != arrays[i]){
                throw new RuntimeException();
            }
        }

        Assertions.assertEquals(4, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка добавления списка 1 в начало списка 1")
    public void addFirstListToFirstList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);

        cyclicLinkedList.addAll(0, cyclicLinkedList);

        int[] arrays = {1, 2, 1, 2};

        for(int i = 0; i < arrays.length; i++){
            if(cyclicLinkedList.get(i) != arrays[i]){
                throw new RuntimeException();
            }
        }

        Assertions.assertEquals(4, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка срабатывания исключения при попытке получить последний элемент из списка")
    public void throwsNoSuchElementException() throws NoSuchElementException {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        try {
            cyclicLinkedList.getLastElement();
        } catch (NoSuchElementException thrown) {
            assertEquals("List is empty", thrown.getMessage());
        }
    }

    @Test
    @DisplayName("Получение первого элемента из списка")
    public void getFirstElementFromList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);

        Assertions.assertEquals(3, cyclicLinkedList.getFirstElement());
    }

    @Test
    @DisplayName("Проверка возврата true при использовании метода isEmpty в пустом листе")
    public void checkIsEmpty() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        assertTrue(cyclicLinkedList.isEmpty());
    }

    @Test
    @DisplayName("Проверка возврата false при использовании метода isEmpty заполненном списке")
    public void checkIsEmptyReturnFalse() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        assertFalse(cyclicLinkedList.isEmpty());
    }

    @Test
    @DisplayName("Проверка удаления элементов в списке")
    public void checkClearElementsInList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(6);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(30);

        cyclicLinkedList.clear();

        Assertions.assertEquals(0, cyclicLinkedList.size());

    }

    @Test
    @DisplayName("Проверка получение элемента по невалидному индексу")
    public void checkGetElementByInvalidIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);

        IndexOutOfBoundsException actualException = Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
                cyclicLinkedList.get(3));
        assertEquals("index : 3 size : 3", actualException.getMessage());

    }

    @Test
    @DisplayName("Проверка наличия первого элемента в списке")
    public void checkContainsFirstElement() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);

        assertTrue(cyclicLinkedList.contains(43));
    }

    @Test
    @DisplayName("Проверка наличия последнего элемента в списке")
    public void checkContainsLastElement() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);

        assertTrue(cyclicLinkedList.contains(9));
    }

    @Test
    @DisplayName("Проверка наличия рандомного элемента в списке")
    public void checkContainsRandomElement() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);

        assertTrue(cyclicLinkedList.contains(1));
        assertTrue(cyclicLinkedList.contains(5));
    }

    @Test
    @DisplayName("Проверка возврата false при пустом списке")
    public void checkReturnFalseEmptyList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        assertFalse(cyclicLinkedList.contains(8));
    }

    @Test
    @DisplayName("Проверка возврата false при отсутствии элемента в списке")
    public void checkReturnFalseByAbsentElementInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);

        assertFalse(cyclicLinkedList.contains(8));
    }

    @Test
    @DisplayName("Проверка удаления первого элемента в списке")
    public void removeFirstElementInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);

        Assertions.assertEquals(1, cyclicLinkedList.remove(0));

        int[] expectedArray = {2, 3, 4};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

        Assertions.assertEquals(3, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка удаления последнего элемента в списке")
    public void removeLastElementInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);

        Assertions.assertEquals(4, cyclicLinkedList.remove(3));

        int[] expectedArray = {1, 2, 3};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

        Assertions.assertEquals(3, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка удаления  рандомного элемента в списке")
    public void removeRandomElementInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);

        Assertions.assertEquals(3, cyclicLinkedList.remove(2));

        int[] expectedArray = {1, 2, 4};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

        Assertions.assertEquals(3, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка замены элемента в начале списка")
    public void checkChangingElementInTheBeggingOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(0, 1);


        int[] expectedArray = {1, 20, 30};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

    }

    @Test
    @DisplayName("Проверка замены элемента в конце списка")
    public void checkChangingElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(2, 1);

        int[] expectedArray = {10, 20, 1};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка замены элемента в рандом месте списка")
    public void checkChangingElementInTheRandomIndexOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(1, 1);

        int[] expectedArray = {10, 1, 30};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка возврата корректного подлиста. fromIndex нулевой индекс")
    public void checkReturnCorrectSublist() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(6);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(8);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(10);

        CyclicLinkedList<Integer> actual = cyclicLinkedList.subList(0, 3);

        int[] expectedArray = {1, 2, 3};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != actual.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

        assertThrows(IndexOutOfBoundsException.class, () -> actual.get(3));


    }

    @Test
    @DisplayName("Проверка возврата корректного подлиста. fromIndex НЕ нулевой индекс и toIndex не последний списка ")
    public void checkReturnCorrectSublist2() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(6);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(8);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(10);

        CyclicLinkedList<Integer> actual = cyclicLinkedList.subList(7, 9);

        int[] expectedArray = {8, 9};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != actual.get(i)) {
                    throw new RuntimeException();
                }
            }
        });


        assertThrows(IndexOutOfBoundsException.class, () -> actual.get(2));

    }

    @Test
    @DisplayName("Проверка наличия в подлисте последнего элемента листа")
    public void checkReturnCorrectSublist3() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(6);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(8);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(10);

        CyclicLinkedList<Integer> actual = cyclicLinkedList.subList(7, 10);

        int[] expectedArray = {8, 9, 10};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != actual.get(i)) {
                    throw new RuntimeException();
                }
            }
        });


        assertThrows(IndexOutOfBoundsException.class, () -> actual.get(3));

    }

    @Test
    @DisplayName("Индексы начало и конца списка совпадают")
    public void checkReturnCorrectSublist4() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(6);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(8);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(10);

        CyclicLinkedList<Integer> actual = cyclicLinkedList.subList(3, 3);

        Assertions.assertEquals("List is empty", actual.toString());

    }

    @Test
    @DisplayName("Проверка выбрасывания исключения при отрицательном fromIndex")
    public void thrownIndexOutOfBoundsException() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> cyclicLinkedList.subList(-1, 2));
    }

    @Test
    @DisplayName("Проверка выбрасывания исключения при условии, что toIndex больше size")
    public void thrownIndexOutOfBoundsExceptionWhenToIndexIsGreaterThenSize() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> cyclicLinkedList.subList(0, 4));
    }

    @Test
    @DisplayName("Проверка выбрасывания исключения при условии, что fromIndex больше toIndex")
    public void thrownIndexOutOfBoundsExceptionWhenFromIndexIsGreaterThenToIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        assertThrows(IndexOutOfBoundsException.class, () -> cyclicLinkedList.subList(4, 2));
    }

    @Test
    @DisplayName("Проверка добавления списка 2 в начало списка 1")
    public void testAddingInTheBeggingListTwoInTheListOne() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        CyclicLinkedList<Integer> cyclicLinkedList1 = new CyclicLinkedList<>();

        cyclicLinkedList1.add(4);
        cyclicLinkedList1.add(5);
        cyclicLinkedList1.add(6);

        cyclicLinkedList.addAll(0, cyclicLinkedList1);

        int[] expectedArray = {4, 5, 6, 1, 2, 3};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка добавления списка 2 в конец списка 1")
    public void testAddingInTheEndListTwoInTheListOne() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        CyclicLinkedList<Integer> cyclicLinkedList1 = new CyclicLinkedList<>();

        cyclicLinkedList1.add(4);
        cyclicLinkedList1.add(5);
        cyclicLinkedList1.add(6);

        cyclicLinkedList.addAll(3, cyclicLinkedList1);

        CyclicLinkedList<Integer> actual = new CyclicLinkedList<>();

        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.add(5);
        actual.add(6);

        int[] expectedArray = {1, 2, 3, 4, 5, 6};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != actual.get(i)) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Test
    @DisplayName("Проверка отсутствия ошибки при добавлении пустого списка 1 к пустому списку 1")
    public void testAddingInRandomPlaceListTwoInTheListOne() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.addAll(0, cyclicLinkedList);

        Assertions.assertEquals("List is empty", cyclicLinkedList.toString());
    }

    @Test
    @DisplayName("Проверка добавления списка 2 в рандомное место списка 1")
    public void testAddingInTheRandomPlaceListTwoToTheListOne() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        CyclicLinkedList<Integer> cyclicLinkedList1 = new CyclicLinkedList<>();

        cyclicLinkedList1.add(4);
        cyclicLinkedList1.add(5);
        cyclicLinkedList1.add(6);

        cyclicLinkedList.addAll(1, cyclicLinkedList1);

        int[] expectedArray = {1, 4, 5, 6, 2, 3};

        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != cyclicLinkedList.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

    }

    @Test
    @DisplayName("Проверка добавления списка 1, состоящего из одной ноды, в список 1")
    public void testAddListOneToListOne() {
        CyclicLinkedList<Integer> list = new CyclicLinkedList<>();

        list.add(1);
        list.addAll(0, list);

        int[] expectedArray = {1, 1};
        Assertions.assertAll(() -> {
            for (int i = 0; i < expectedArray.length; i++) {
                if (expectedArray[i] != list.get(i)) {
                    throw new RuntimeException();
                }
            }
        });

    }
}
