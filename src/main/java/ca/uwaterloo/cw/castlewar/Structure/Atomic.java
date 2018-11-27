package ca.uwaterloo.cw.castlewar.Structure;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by harri on 2018/3/17.
 */

abstract public class Atomic {
    public static class Id<T> {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private T id;

        public Id(T id) {
            this.id = id;
        }

        public T get() {
            try {
                lock.readLock().lock();
                return id;
            } finally {
                lock.readLock().unlock();
            }
        }
        public void set(T id) {
            lock.writeLock().lock();
            this.id = id;
            lock.writeLock().unlock();
        }
    }

    public static class List<T> {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private ArrayList<T> content;

        public List(int size) {
            this.content = new ArrayList<>(size);
        }

        public List() {
            this.content = new ArrayList<>();
        }

        public ArrayList<T> getCopyOfContent() {
            lock.readLock().lock();
            try {
                return new ArrayList<>(content);
            } finally {
                lock.readLock().unlock();
            }
        }

        public boolean isEmpty() {
            lock.readLock().lock();
            try {
                return content.isEmpty();
            } finally {
                lock.readLock().unlock();
            }
        }

        public void add(T object) {
            lock.writeLock().lock();
            content.add(object);
            lock.writeLock().unlock();
        }

        public void remove(T object) {
            lock.writeLock().lock();
            content.remove(object);
            lock.writeLock().unlock();
        }
    }
}
