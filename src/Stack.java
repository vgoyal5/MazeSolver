/**
 * Name: Viraj Goyal
 * Mrs. Kankelborg
 * Period: 1
 * Project 3 Maze Solver Part 1: Stack
 * Revision History:
 * 12/17/2019 - Reused Stack lab solution from classroom website
 * Class Description:
 * A last-in-first-out (LIFO) stack of generic items.
 * @param <T> the type of item to store in the stack
 */
public class Stack<T>
{
	private int size;
	private T[] stack;
    /**
     * Initializes an empty stack.
     */
	public Stack()
    {
        size = 0;
        stack = (T[]) new Object[size + 1];
    }

    /**
     * Adds an item to the stack.
     *
     * @param newItem the item to add
     */
	public void push(T newItem)
    {
    	if(newItem == null)
        {
            throw new IllegalArgumentException();
        }
        if(stack.length == size)
        {
            T[] copyStack = (T[]) new Object[size * 2];
            for(int i = 0; i < stack.length; i++)
            {
                copyStack[i] = stack[i];
            }
            stack = copyStack;
        }
        stack[size] = newItem;
        size++;
    }

    /**
     * Removes and returns the item on the stack that was most recently added.
     *
     * @return the item on the stack that was most recently added
     */
	public T pop()
    {
    	if(isEmpty())
        {
            throw new IllegalStateException();
        }
        T returnType = stack[size - 1];
        stack[size - 1] = null;
        size--;
        if(size == stack.length/4)
        {
            T[] copyStack = (T[]) new Object[stack.length / 2];
            for(int i = 0; i < size; i++)
            {
                copyStack[i] = stack[i];
            }
            stack = copyStack;
        }
        return returnType;
    }

    /**
     * Returns the item most recently added to the stack.
     *
     * @return the item most recently added to the stack
     */
    public T peek()
    {
    	if(isEmpty())
        {
            throw new IllegalStateException();
        }
        return stack[size - 1];
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return whether the stack is empty
     */
    public boolean isEmpty()
    {
    	//check this line
    	if(size == 0 || stack[0] == null)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size()
    {
        return size;
    }
}
