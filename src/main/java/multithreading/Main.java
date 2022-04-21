package multithreading;

public class Main {
    private static final int SIZE = 10000000;
    private static final float[] ARRAY = new float[SIZE];

    public static void main(String[] args) {
        for (int i = 0; i < SIZE; i++) {
            ARRAY[i] = i;
        }
        int thread = 100;
        for (int i = 1; i <= thread; i++) {
            System.out.printf("%d потоков за %d%n", i, getTimeOfSomeFunction(ARRAY, i));
        }
    }

    public static long getTimeOfSomeFunction(float[] array, int threadCount) {
        float[][] a = new float[threadCount][];
        int segment = array.length / threadCount;
        long timeStart = System.currentTimeMillis();
        for (int i = 0, startIndex = 0; i < threadCount; startIndex += segment, i++) {
            int size = (i != threadCount - 1) ? segment : segment + array.length % segment;
            a[i] = new float[size];
            System.arraycopy(array, startIndex, a[i], 0, size);
        }
        Thread[] threads = new Thread[threadCount];
        try {
            for (int i = 0; i < threadCount; i++) {
                final int index = i;
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < a[index].length; j++) {
                        a[index][j] = someFunction(a[index][j], index);
                    }
                });
                threads[i].start();
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0, startIndex = 0; i < threadCount; startIndex += segment, i++) {
            int size = (i != threadCount - 1) ? segment : segment + array.length % segment;
            System.arraycopy(a[i], 0, array, startIndex, size);
        }
        long current = System.currentTimeMillis();
        return current - timeStart;
    }

    private static float someFunction(float value, int index) {
        return (float)(value * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));
    }
}
