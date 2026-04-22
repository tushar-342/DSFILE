class Solution {

    Stack<TreeNode> asc = new Stack<>();
    Stack<TreeNode> desc = new Stack<>();

    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;

        TreeNode t = root;

        // ascending stack
        while(t != null){
            asc.push(t);   // fixed (offer → push)
            t = t.left;
        }

        t = root; // RESET

        // descending stack
        while(t != null){
            desc.push(t);  // fixed
            t = t.right;
        }

        TreeNode i = getSmall();
        TreeNode j = getBig();

        while(i != null && j != null && i != j){   // fixed condition
            int sum = i.val + j.val;

            if(sum == k) return true;

            if(sum > k){
                j = getBig();
            } else {
                i = getSmall();
            }
        }

        return false;   // moved outside loop
    }

    TreeNode getSmall(){
        if(asc.isEmpty()) return null;

        TreeNode small = asc.pop();   // fixed
        TreeNode rightChild = small.right;

        while(rightChild != null){    // fixed condition
            asc.push(rightChild);
            rightChild = rightChild.left;   // fixed semicolon
        }

        return small;
    }

    TreeNode getBig(){
        if(desc.isEmpty()) return null;

        TreeNode big = desc.pop();   // fixed (was asc.pop ❌)
        TreeNode leftChild = big.left;

        while(leftChild != null){
            desc.push(leftChild);    // fixed typo
            leftChild = leftChild.right;   // fixed logic
        }

        return big;
    }
}