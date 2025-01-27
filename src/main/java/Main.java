// 5. Напишите программную реализацию бинарного дерева поиска
public class Main {
    public static void main(String[] args) {
        OperationTree tree = new OperationTree();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.println("Обход дерева: ");
        tree.inOrder();

        System.out.println("\nПоиск 5: " + tree.search(5));

        System.out.println("Удаление 4: ");
        tree.delete(4);
        tree.inOrder();
    }
}
