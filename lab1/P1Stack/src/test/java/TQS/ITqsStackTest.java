package TQS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

class ITqsStackTest {
    private ITqsStack<String> stackOne;
    private ITqsStack<String> emptyStack;

    @BeforeEach
    void setUp() {
        stackOne = new ITqsStack<>();

        stackOne.push("Aveiro");
        stackOne.push("Braga");
        stackOne.push("Coimbra");
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Pushing another element into the stack")
    @Test
    void push() {
        stackOne.push("Extra");
        assertEquals(4,stackOne.size());
    }

    @Test
    void pushOverBound(){
        stackOne.setBound(3);
        assertThrows(IllegalStateException.class, () -> stackOne.push("x"));
    }

    @Test
    void pop() {
        stackOne.push("x");
        assertEquals("x", stackOne.pop());
    }

    @DisplayName("Popping from an empty stack gives exception")
    @Test
    void popEmpty(){
        emptyStack = new ITqsStack<>();
        assertThrows(NoSuchElementException.class, () -> emptyStack.pop());
    }

    @Test
    void peek() {
        stackOne.push("x");
        assertEquals(4, stackOne.size());
        assertEquals("x",stackOne.peek());
        assertEquals(4, stackOne.size());
    }

    @DisplayName("Peeking into empty stack throws excetion")
    @Test
    void peekEmpty(){
        emptyStack = new ITqsStack<>();
        assertThrows(NoSuchElementException.class, () -> emptyStack.peek());
    }

    @Test
    void size() {
        emptyStack = new ITqsStack<>();
        assertEquals(0,emptyStack.size());
    }

    @DisplayName("Size equals 3 after 3 pushes")
    @Test
    void size3(){
        assertEquals(3, stackOne.size());
    }

    @DisplayName("After n pops size is 0")
    @Test
    void popAndSize(){
        //pop all elements
        stackOne.pop();
        stackOne.pop();
        stackOne.pop();
        assertEquals(0, stackOne.size());
    }

    @DisplayName("Stack should be empty")
    @Test
    void isEmpty() {
        emptyStack = new ITqsStack<>();
        assertTrue(emptyStack.isEmpty(),"Empty stack should return true");

        assertFalse(stackOne.isEmpty(),"A not empty stack should return false");
    }
}