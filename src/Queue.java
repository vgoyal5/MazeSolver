/**
 * Name: Viraj Goyal
 * Mrs. Kankelborg
 * Period: 1
 * Project 3 Maze Solver Part 2: Queue
 * Revision History:
 * 1/2/2020 - Reused Queue lab solution from classroom website
 * Class Description:
 * A first-in-first-out (FIFO) queue of generic items.
 * @param <T> the type of item to store in the queue
 */
public class Queue<T>
{
	private int size;
    private T[] queue;
    private int front;
    private int rear;
    /**
     * Initializes an empty queue.
     */
    public Queue()
    {
    	size = 0;
        queue = (T[]) new Object[1];
        front = 0;
        rear = 0;
    }

    /**
     * Adds an item to the queue.
     *
     * @param newItem the item to add
     */
    public void enqueue(T newItem)
    {
    	if(newItem == null)
        {
            throw new IllegalArgumentException();
        }
        if(queue.length == size)
        {
            T[] copyQueue = (T[]) new Object[queue.length * 2];
            for(int i = 0; i < size; i++)
            {
                copyQueue[i] = queue[(front + i) % queue.length];
            }
            rear = size;
            front = 0;
            queue = copyQueue;
        }
        queue[rear++] = newItem;
        if(rear >= queue.length)
        {
            rear = 0;
        }
        size++;
    }

    /**
     * Removes and returns the item on the queue that was least recently added.
     *
     * @return the item on the queue that was least recently added
     */
    public T dequeue()
    {
    	if(isEmpty())
        {
            throw new IllegalStateException();
        }
        T item = queue[front];
        queue[front] = null;
        front++;
        size--;
        if(front >= queue.length)
        {
            front = 0;
        }
        if(size > 0 && queue.length/4 >= size)
        {
            T[] copyQueue = (T[]) new Object[queue.length / 2];
            for(int i = 0; i < size; i++)
            {
                copyQueue[i] = queue[(front + i) % queue.length];
            }
            rear = size;
            front = 0;
            queue = copyQueue;
        }
        return item;
    }

    /**
     * Returns the item least recently added to the queue.
     *
     * @return the item least recently added to the queue
     */
    public T peek()
    {
    	if(isEmpty())
        {
            throw new IllegalStateException();
        }
        return queue[front];
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return whether the queue is empty
     */
    public boolean isEmpty()
    {
    	if(size == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    public int size()
    {
        return size;
    }
}
