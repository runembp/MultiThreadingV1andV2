public class MultiThread extends Thread
{
    private static int x = 0;

    public static void main(String[] args) throws InterruptedException
    {
        Thread t1 = new MultiThread(x);
        Thread t2 = new MultiThread(x);
        Thread t3 = new MultiThread(x);
        Thread t4 = new MultiThread(x);
        Thread t5 = new MultiThread(x);
        Thread t6 = new MultiThread(x);
        Thread t7 = new MultiThread(x);
        Thread t8 = new MultiThread(x);
        Thread t9 = new MultiThread(x);
        Thread t10 = new MultiThread(x);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();

        System.out.println(x);
    }

    public MultiThread(int i)
    {
        this.x = i;
    }

    public void run()
    {
        // Loop counter is only used as proof of concept, to prove that the current thread actually
        // goes through 100.000 iterations
        int loopCounter = 0;

        for(int i = 0; i < 100_000;i++)
        {
            x++;
            loopCounter = i;
        }
        System.out.println("Loop count: " + ++loopCounter);
    }
}


        // 1.
        // We have an assumption that X would be 1.000.000 after we would have run 10 threads,
        // which each incremented the X 100.000 times.
        // This does not happen, as all the threads happen concurrently and they all 'fight' for the X variable number.
        // If one thread is at 10.000 and the other one is at 700, and increments to 701, the first thread
        // might read X to be 701, discard its former value of 10.000, and continue on from 701.
        // So if we have 10 threads at the same time, it's easy to imagine how much fighting is going on. That is
        // why X never reaches 1.000.000

        // 2.
        // We do not control the Thread managers priority within Java, so the threads might recieve different
        // priorities every time we start the program.

        // PART 4 EXERCISE - Synchronization
        // We can avoid the Race Condition issue, if we make use of the 'synchronized' keyword.
        // Inside the run-method we will add:
        //
        // public void run()
        // {
        //      synchronized (MultiThread.class)
        //      {
        //          for(int i = 0; i < 100_000;i++)
        //          {
        //                x++;
        //          }
        // }
        //
        // We add the MultiThread.class, so we ensure that we synchronize the entire class, as
        // x is not within the specific run() method - that is why we do not use
        // public synchronized void run() as a solution


