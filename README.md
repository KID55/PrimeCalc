PrimeCalc
=========

Приложения для нахождения простых чисел в заданном диапазоне и последующей генерацией из них заданного числа массивов простых чисел, таких что, среднее-арифметичское модулей разностей всех элементов массива максимально близка к заданному числу (+-1 от заданного числа).

=========

С первой задачей приложение справляется хорошо, шустро находит простые числа даже на больших диапазонах (в диапазоне от 1 до 1000000 нашел все числа примерно за 1 секунду *Окружение: Sony Xperia SP, Android 4.3, Qualcomm MSM8960T 1700 МГц 2 ядра, 
1 Гб ОЗУ*), использовался алгоритм решета Эртосфена.

Со второй задачей приложение справляется не всегда из-за не самой крепкой дружбы разработчика с математикой, но все же 
переодически справляется, и в этом есть свой плюс: вы можете проверить свою удачу!

Как говорилось выше, из-за бесплодных попыток вывести точную формулу для расчета, использовался эмпирический метод анализа, в ходе которого было установлено, что в подавляющем большинстве случаев если к формируемому массиву простых чисел, в конец добавить число равное или меньшее, чем последний элемент этого массива, то среднее-арифметическое модулей разностей всех элементов уменьшится, а если добавить большее число, то, соответственно, увеличится. Исходя из полученых знаний был составлен алгоритм.

Сам алгоритм:
****************************
Создаем ArrayList простых чисел из заданного диапазона - __intArrayList__;

Создаем пустой ArrayList, который будет хранить наш результат - __rezArrayList__;

Добавляем два элемента случайно выбранных из intArrayList в __rezArrayList__;

Вычисляем среднее-арифметическое для rezArrayList - __result__;

Цикл:
{__while (!(p-1 <= result <= p+1))__

Обнуляем сумму модулей разностей __preresult__;

Обнуляем счетчик разностей __counter__;

__if(__последний элемент в __rezArrayList ==__ последний элемент в __intArrayList__) 

  добавляем случайный элемент из __intArrayList__, кроме последнего;      *//чтобы избежать бесконечного уменьшения result*
  
__else if(result >= p+1)__

  добавляем случайный элемент из __intArrayList__, <= последний элемент в __rezArrayList__;
  
__else if(result <= p-1)__

  добавляем случайный элемент из __intArrayList__, __>__ последний элемент в __rezArrayList__;
  
В цикле находим сумму модулей разностей всех элементов (сохраняем в __preresult__) и количество разностей (сохраняем в __counter__);

Вычисляем __result = preresult/counter__;

}конец цикла;

Преобразуем найденный массив в строку и передаем в переменую - __resRandom__;

Очищаем __resArrayList__ для последующего использования;

Возвращаем пользователю __resRandom__;
*****************************

Теоретически, алгоритм, разработанный автором приложения для решения второй задачи, будет находить решение всегда, но в большом ряде случаев на это потребуется весьма длительное время из-за использования случайных значений.

Если приложение не выдает сгенерированные массивы, то советую отключить его, запустить и попробовать снова, если у вас много терпения и оперативки в телефоне)
