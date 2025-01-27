public interface Progress {
    /**
     * Вычисляет элемент прогрессии по заданному индексу.
     * @param n Индекс элемента (n >= 1)
     * @return Значение n-го элемента.
     */
    double getElement(int n);

    /**
     * Вычисляет сумму первых n элементов прогрессии.
     * @param n Количество элементов (n >= 1)
     * @return Сумма первых n элементов.
     */
    double getSum(int n);
}