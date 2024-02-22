public class ThreadNumberPrinterInOrder {

    static class myThread extends Thread {

        private final long number;

        public myThread(int number) {
            this.number = number;
        }

        public void run() {
            try {
                Thread.sleep(number * 100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {5, 3, 2, 4, 1};

        for (int number : numbers) {
            final var thread = new myThread(number);
            thread.start();
        }
        // find out how to print this as last without using sleep
//        System.out.println("Sorting finished");
    }
}
