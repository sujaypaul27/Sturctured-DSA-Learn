import java.util.*;

/**
 * Problem Statement:
 * The Tower of Hanoi is a mathematical puzzle with three rods (source, auxiliary, destination)
 * and n disks. Solve the puzzle following these rules:
 * 1. Only one disk can be moved at a time.
 * 2. Each move consists of taking the upper disk from one of the stacks and placing it on top of another stack.
 * 3. No disk may be placed on top of a smaller disk.
 *
 * Example:
 * Input: n = 3, Source = 'A', Aux = 'B', Dest = 'C'
 * Output: Moves required and step-by-step instructions.
 *
 * Intuition & Approach:
 * To move n disks from Source (A) to Destination (C) using Auxiliary (B):
 * 1. Move top (n - 1) disks from A to B using C as auxiliary.
 * 2. Move the largest nth disk directly from A to C.
 * 3. Move the (n - 1) disks from B to C using A as auxiliary.
 * Base case: If n == 1, simply move disk 1 from source to destination.
 *
 * Time Complexity:
 * O(2^n) - The recurrence relation is T(n) = 2T(n - 1) + 1, resulting in (2^n - 1) moves.
 *
 * Space Complexity:
 * O(n) - Maximum depth of the recursion stack is n.
 */
public class TowerOfHanoi {

    private static int moveCount = 0;

    public static void solveHanoi(int n, char source, char destination, char auxiliary) {
        if (n == 0) {
            return;
        }
        // Move n - 1 disks from source to auxiliary rod
        solveHanoi(n - 1, source, auxiliary, destination);

        // Move nth disk from source to destination
        moveCount++;
        System.out.println("Move disk " + n + " from rod " + source + " -> rod " + destination);

        // Move n - 1 disks from auxiliary to destination rod
        solveHanoi(n - 1, auxiliary, destination, source);
    }

    public static void main(String[] args) {
        // Test Case 1: 3 Disks
        int n1 = 3;
        moveCount = 0;
        System.out.println("Test Case 1: n = " + n1);
        solveHanoi(n1, 'A', 'C', 'B');
        System.out.println("Total moves: " + moveCount);
        System.out.println();

        // Test Case 2: 2 Disks
        int n2 = 2;
        moveCount = 0;
        System.out.println("Test Case 2: n = " + n2);
        solveHanoi(n2, 'A', 'C', 'B');
        System.out.println("Total moves: " + moveCount);
    }
}
