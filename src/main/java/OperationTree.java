class OperationTree {
    Tree root;

    public OperationTree() {
        this.root = null;
    }

    void insert(int value) {
        root = insertRec(root, value);
    }

    Tree insertRec(Tree root, int value) {
        if (root == null) {
            root = new Tree(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        }
        else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }

    void delete(int value) {
        root = deleteRec(root, value);
    }
    Tree deleteRec(Tree root, int value) {
        if (root == null) {
            return null;
        }
        if (value < root.value)
            root.left = deleteRec(root.left, value);
        else if (value > root.value)
            root.right = deleteRec(root.right, value);
        else{
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    int minValue(Tree root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    boolean search(int value) {
        return searchRec(root, value);
    }

    boolean searchRec(Tree root, int value) {
        if (root == null)
            return false;
        if (root.value == value)
            return true;
        if (root.value < value)
            return searchRec(root.right, value);
        return searchRec(root.left, value);
    }

    void inOrder() {
        inOrderRec(root);
    }

    void inOrderRec(Tree root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }
}