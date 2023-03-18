import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CyclicLinkedListTest {

    @Test
    @DisplayName("Проверка добавления элемента в список при изначально пустом листе")
    public void addElementInCycleList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        Assertions.assertEquals(1, cyclicLinkedList.getLastElement());
    }

    @Test
    @DisplayName("Проверка добавления элемента в конец списка")
    public void addElementInTheEndOfCycleList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        Assertions.assertEquals(2, cyclicLinkedList.getLastElement());
    }

    @Test
    @DisplayName("Проверка добавление элемента в начало списка")
    public void addElementInTheBeginningInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(0, 0);
        Assertions.assertEquals(0, cyclicLinkedList.getFirstElement());
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
        Assertions.assertEquals(5, cyclicLinkedList.get(2));
        Assertions.assertEquals(1, cyclicLinkedList.get(0));
        Assertions.assertEquals(2, cyclicLinkedList.get(1));
        Assertions.assertEquals(3, cyclicLinkedList.get(3));
        Assertions.assertEquals(4, cyclicLinkedList.get(4));

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
    }

    @Test
    @DisplayName("Проверка срабатывания исключения при невалидном индексе")
    public void throwsException() throws IndexOutOfBoundsException {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        try {
            cyclicLinkedList.add(-1, 6);
        } catch (IndexOutOfBoundsException thrown) {
            assertEquals("index : -1 size : 2", thrown.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка срабатывания исключения при индексе больше чем размер списка")
    public void throwsExceptionWhenIndexIsGreaterThenSizeOfList() throws IndexOutOfBoundsException {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        try {
            cyclicLinkedList.add(3, 6);
        } catch (IndexOutOfBoundsException thrown) {
            assertEquals("index : 3 size : 2", thrown.getMessage());
        }
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

        Assertions.assertEquals(3, cyclicLinkedList.get(0));
        Assertions.assertEquals(4, cyclicLinkedList.get(1));
        Assertions.assertEquals(1, cyclicLinkedList.get(2));
        Assertions.assertEquals(2, cyclicLinkedList.get(3));
    }

    @Test
    @DisplayName("Проверка добавления списка 1 в начало списка 1")
    public void addFirstListToFirstList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);

        cyclicLinkedList.addAll(0, cyclicLinkedList);

        Assertions.assertEquals(1, cyclicLinkedList.get(0));
        Assertions.assertEquals(2, cyclicLinkedList.get(1));
        Assertions.assertEquals(1, cyclicLinkedList.get(2));
        Assertions.assertEquals(2, cyclicLinkedList.get(3));
    }

    @Test
    @DisplayName("Проверка получения последнего элемента из списка")
    public void checkGetLastElement() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(1);
        cyclicLinkedList.add(2);
        cyclicLinkedList.add(3);

        Assertions.assertEquals(3, cyclicLinkedList.get(2));

    }

    @Test
    @DisplayName("Проверка срабатвания исключения при попытке получить последний элемент из списка")
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
    public void checkGetElementByInvalidIndex() throws IndexOutOfBoundsException {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);

        try {
            cyclicLinkedList.get(3);
        } catch (IndexOutOfBoundsException thrown) {
            assertEquals("index: 3 size 3", thrown.getMessage());
        }
    }

    @Test
    @DisplayName("Проверка получение элемента по отрицательному индексу")
    public void checkGetElementByNegativeIndex() throws Exception {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(3);
        cyclicLinkedList.add(4);
        cyclicLinkedList.add(5);

        try {
            cyclicLinkedList.get(-1);
        } catch (IndexOutOfBoundsException thrown) {
            assertEquals("index: -1 size 3", thrown.getMessage());
        }
    }

    @Test
    @DisplayName("Получение элемента по 1 индексу")
    public void getFirstElementInTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(45355);
        cyclicLinkedList.add(44);

        Assertions.assertEquals(43, cyclicLinkedList.get(0));
    }

    @Test
    @DisplayName("Получение элемента по рандомному индексу")
    public void getElementByRandomIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(3);

        Assertions.assertEquals(1, cyclicLinkedList.get(1));
        Assertions.assertEquals(5, cyclicLinkedList.get(2));

    }

    @Test
    @DisplayName("Получение элемента по последнему индексу")
    public void getElementByLastIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(3);

        Assertions.assertEquals(3, cyclicLinkedList.get(5));
    }

    @Test
    @DisplayName("Проверка размера листа")
    public void checkSize() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(43);
        cyclicLinkedList.add(1);
        cyclicLinkedList.add(5);
        cyclicLinkedList.add(9);
        cyclicLinkedList.add(7);
        cyclicLinkedList.add(3);

        Assertions.assertEquals(6, cyclicLinkedList.size());

    }

    @Test
    @DisplayName("Проверка размера листа после добавления элемента в начало списка")
    public void checkTheSizeOfTheListWhenAddingElementInTheBeggingOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);
        cyclicLinkedList.add(0, 2323);

        Assertions.assertEquals(4, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после добавления элемента в конец списка")
    public void checkTheSizeOfTheListWhenAddingElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);
        cyclicLinkedList.add(3, 2323);

        Assertions.assertEquals(4, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после добавления элемента в рандомное место по индексу")
    public void checkTheSizeOfTheListWhenAddingElementIsRandomly() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);
        cyclicLinkedList.add(40);
        cyclicLinkedList.add(50);
        cyclicLinkedList.add(60);

        cyclicLinkedList.add(3, 2323);

        Assertions.assertEquals(7, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после добавления нового листа к существующему")
    public void checkTheSizeOfTheListWhenAddingNewListByIndex() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        CyclicLinkedList<Integer> cyclicLinkedList1 = new CyclicLinkedList<>();

        cyclicLinkedList1.add(10);
        cyclicLinkedList1.add(20);
        cyclicLinkedList1.add(30);

        cyclicLinkedList.addAll(0, cyclicLinkedList1);

        Assertions.assertEquals(6, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после удаления элемента в начале списка")
    public void checkTheSizeOfTheListWhenRemovingElementInTheBeggingOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.remove(0);

        Assertions.assertEquals(2, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после удаления элемента в конце списка")
    public void checkTheSizeOfTheListWhenRemovingElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.remove(2);

        Assertions.assertEquals(2, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после удаления элемента в рандомном месте списка")
    public void checkTheSizeOfTheListWhenRemovingElementInTheRandomPlaceOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.remove(1);

        Assertions.assertEquals(2, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после замены элемента в начале списка")
    public void checkTheSizeOfTheListWhenChangingElementInTheBeggingOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(0, 1);

        Assertions.assertEquals(3, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после замены элемента в конце списка")
    public void checkTheSizeOfTheListWhenChangingElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(2, 1);

        Assertions.assertEquals(3, cyclicLinkedList.size());
    }

    @Test
    @DisplayName("Проверка размера листа после замены элемента в рандомном месте списка")
    public void checkTheSizeOfTheListWhenChangingElementInTheRandomPlaceOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(1, 1);

        Assertions.assertEquals(3, cyclicLinkedList.size());
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
    }

    @Test
    @DisplayName("Проверка замены элемента в начале списка")
    public void checkChangingElementInTheBeggingOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(0, 1);

        Assertions.assertEquals(1, cyclicLinkedList.getFirstElement());
    }

    @Test
    @DisplayName("Проверка замены элемента в конце списка")
    public void checkChangingElementInTheEndOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(2, 1);

        Assertions.assertEquals(1, cyclicLinkedList.getLastElement());
    }

    @Test
    @DisplayName("Проверка замены элемента в рандом месте списка")
    public void checkChangingElementInTheRandomIndexOfTheList() {
        CyclicLinkedList<Integer> cyclicLinkedList = new CyclicLinkedList<>();

        cyclicLinkedList.add(10);
        cyclicLinkedList.add(20);
        cyclicLinkedList.add(30);

        cyclicLinkedList.set(1, 1);

        Assertions.assertEquals(1, cyclicLinkedList.get(1));
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

        Assertions.assertEquals(1, actual.get(0));
        Assertions.assertEquals(2, actual.get(1));
        Assertions.assertEquals(3, actual.get(2));

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

        Assertions.assertEquals(8, actual.get(0));
        Assertions.assertEquals(9, actual.get(1));


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

        Assertions.assertEquals(8, actual.get(0));
        Assertions.assertEquals(9, actual.get(1));
        Assertions.assertEquals(10, actual.get(2));


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

        CyclicLinkedList<Integer> actual = new CyclicLinkedList<>();

        actual.add(4);
        actual.add(5);
        actual.add(6);
        actual.add(1);
        actual.add(2);
        actual.add(3);

        Assertions.assertEquals(cyclicLinkedList.toString(), actual.toString());
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

        Assertions.assertEquals(cyclicLinkedList.toString(), actual.toString());
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

        cyclicLinkedList.addAll(3, cyclicLinkedList1);

        CyclicLinkedList<Integer> actual = new CyclicLinkedList<>();

        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.add(5);
        actual.add(6);

        Assertions.assertEquals(cyclicLinkedList.toString(), actual.toString());
    }

    @Test
    @DisplayName("Проверка добавления списка 1, состоящего из одной ноды, в список 1")
    public void testAddListOneToListOne() {
        CyclicLinkedList<Integer> list = new CyclicLinkedList<>();

        list.add(1);
        list.addAll(0, list);

        CyclicLinkedList<Integer> actual = new CyclicLinkedList<>();

        actual.add(1);
        actual.add(1);

        Assertions.assertEquals(list.toString(), actual.toString());

    }

    @Test
    @DisplayName("Проверка сортировки")
    public void testSort() {
        class Person {

            private int age;
            private String LastName;

            public Person(int age, String lastName) {
                this.age = age;
                LastName = lastName;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            @Override
            public String toString() {
                return "Person{" +
                        "age=" + age +
                        ", LastName=" + LastName +
                        '}';
            }
        }

        class ComparatorAge implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                } else if (o1.getAge() < o2.getAge()) {
                    return -1;
                }
                return 0;
            }
        }

        CyclicLinkedList<Person> personCyclicLinkedList = new CyclicLinkedList<>();

        personCyclicLinkedList.add(new Person(10, "Alex"));
        personCyclicLinkedList.add(new Person(12, "Andrey"));
        personCyclicLinkedList.add(new Person(9, "Valeriy"));

        Comparator<Person> comparator = new ComparatorAge();

        personCyclicLinkedList.sort(comparator);

        Assertions.assertEquals("Node{data=Person{age=9, LastName=Valeriy}}Node{data=Person{age=10, LastName=Alex}}Node{data=Person{age=12, LastName=Andrey}}", personCyclicLinkedList.toString());

    }

    @Test
    @DisplayName("Проверка сортировки")
    public void testSortEmptyList() {
        class Person {

            private int age;
            private String LastName;

            public Person(int age, String lastName) {
                this.age = age;
                LastName = lastName;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            @Override
            public String toString() {
                return "Person{" +
                        "age=" + age +
                        ", LastName=" + LastName +
                        '}';
            }
        }

        class ComparatorAge implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                } else if (o1.getAge() < o2.getAge()) {
                    return -1;
                }
                return 0;
            }
        }

        CyclicLinkedList<Person> personCyclicLinkedList = new CyclicLinkedList<>();


        Comparator<Person> comparator = new ComparatorAge();

        personCyclicLinkedList.sort(comparator);

        Assertions.assertEquals("List is empty", personCyclicLinkedList.toString());
    }

    @Test
    @DisplayName("Проверка сортировки")
    public void testSortListWithOneNode() {
        class Person {

            private int age;
            private String LastName;

            public Person(int age, String lastName) {
                this.age = age;
                LastName = lastName;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getLastName() {
                return LastName;
            }

            public void setLastName(String lastName) {
                LastName = lastName;
            }

            @Override
            public String toString() {
                return "Person{" +
                        "age=" + age +
                        ", LastName=" + LastName +
                        '}';
            }
        }

        class ComparatorAge implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getAge() > o2.getAge()) {
                    return 1;
                } else if (o1.getAge() < o2.getAge()) {
                    return -1;
                }
                return 0;
            }
        }

        CyclicLinkedList<Person> personCyclicLinkedList = new CyclicLinkedList<>();


        Comparator<Person> comparator = new ComparatorAge();

        personCyclicLinkedList.add(new Person(10, "Alex"));

        personCyclicLinkedList.sort(comparator);

        Assertions.assertEquals("Node{data=Person{age=10, LastName=Alex}}", personCyclicLinkedList.toString());
    }


}