# Параметризированные типы/generics

## Алгоритм выполнения

1. Добавить реализацию списка в класс __MyList__, переделать его интерфейс на использование дженериков. Для внутреннего хранения данных использовать массив. Поведение методов должно совпадать с поведением аналогичных методов List, но нельзя унаследоваться от List
2. Наложить ограничение чтоб список можно было параметризировать только численными типами.
3. Покрыть код [тестами](https://habr.com/ru/post/169381/).
4. Сделать класс MyList __Iterable__. Реализовать итератор через __InnerClass__. 
5. Покрыть новый код тестами.
6. В _javadoc_ описать разницу между __статическим InnerClass__ и __обычным__.
7. Прочитать __javadoc__ методов __equals__ и __hashCode__.
8. Реализовать для нашей коллекции __toString__, __hashCode__ и __equals__(необходимо сделать собственную реализацию, вызовы библиотек не принимаются).
9. Сделать по одному тесту на каждое правило __equals__ и __hashCode__.
10. Реализовать метод map как у стримов
