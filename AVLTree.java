// THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
// A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Jeffrey Kok Li

// AVLTree.java: this should be an AVL search tree.
// For homework, you only edit this file.
// Remember to add your name to the SPCA statement above.

// An AVLTree is a BST (binary search tree) which also maintains the
// "height balance condition": for every node in the tree, its two
// child subtrees should have heights within one of each other.
//
// AVLTree inherits most of its methods from BST.  The only BST method
// you really need to change is fixup().  In BST, you can see fixup(t)
// is called on every node t along the path up from a modification
// point (insertion or removal) back up to the root.  In BST the
// fixup(t) method simply calls t.update(), which recomputes the size
// and height fields.
//
// But in AVLTree, fixup(t) may also do the "trinode restructuring"
// necessary to restore the balance condition.  It returns the root of
// the restructured subtree, which may be different from t.'
//
// Note it is up to the callers of fixup(t) to save the returned root,
// either as a child of some parent, or as the root of the whole tree.
// This is already done in the BST insert and remove methods, so you
// shouldn't need to modify those.
//
// In your fixup(t), you may want to use rotateRight (provided below).
// If you use that, you'll probably also need rotateLeft, which you
// can write yourself.

public class AVLTree<K extends Comparable<K>> extends BST<K> {
    // We inherit "root" field from BST.
    // All the public BST methods should work without modification.
    // We only need to revise this one method, to do the rebalancing.


        // TODO: restore the balance here using an appropriate trinode
        // restructuring (one or two rotations).
        // Try to break ties like in the book.
    protected Node fixup(Node t) {
        t.update();             // update t.size and t.height
        int bal = height(t.left) - height(t.right);
        if (-1 <= bal && bal <= +1) {
            // got lucky: balanced already, nothing to do!
            return t;
        } else if (bal <= -2 || bal >= 2) { //checks if the absolute difference is an integer greater than 1
            if (bal <= -2) { 
                if (height(t.right.right) >= height(t.right.left)) { //case 3
                    t = rotateLeft(t); 
                } else {
                    t = doubleRotate2(t); //case 4
                }
            } else {
                if (height(t.left.left) >= height(t.left.right)) { //case 1
                    t = rotateRight(t); 
                } else {
                   t = doubleRotate1(t); //case 2
                }    
            }
        }

        // return new root of this subtree (might not be t)
        return t;
        
    }
    // Feel free to ignore, delete, or modify the following method.

    // This does a rotation lifting the left child to become the new root
    // of this subtree. The old root becomes its right child.
        Node rotateRight(Node k2) { //rotate with left, Case 1: right-right
            Node k1 = k2.left;
            k2.left = k1.right;
            k2.update();
            k1.right = k2;
            k1.update();
            return k1;
        }

        //Based off of code given above
        Node rotateLeft(Node k1) { //rotate with right, Case 3: right-right
            Node k2 = k1.right;
            k1.right = k2.left;
            k1.update();
            k2.left = k1;
            k2.update();
            return k2;
        }

        Node doubleRotate1(Node k3){ //double rotate with left, Case 2: left-right 
            k3.left = rotateLeft(k3.left);
            return rotateRight(k3);        
        }

        Node doubleRotate2(Node k1) { //double rotate with right, Case 4: right-left 
            k1.right = rotateRight(k1.right);
            return rotateLeft(k1);

        }    
  
}