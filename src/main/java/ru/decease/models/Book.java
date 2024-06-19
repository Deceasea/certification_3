package ru.decease.models;

import ru.decease.utils.ConfigLoader;

import java.util.Arrays;
import java.util.Objects;

public class Book {
    private String isbn;

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static class BookCollection {
        private final Book[] collectionOfIsbns;
        private String userId;

        public BookCollection(Book[] collectionOfIsbns) {
            this.userId = ConfigLoader.getUserId();
            this.collectionOfIsbns = collectionOfIsbns;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Book[] getCollectionOfIsbns() {
            return collectionOfIsbns;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BookCollection book)) return false;
            return Objects.equals(userId, book.userId) && Arrays.equals(collectionOfIsbns, book.collectionOfIsbns);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(userId);
            result = 31 * result + Arrays.hashCode(collectionOfIsbns);
            return result;
        }

        @Override
        public String toString() {
            return "BookCollection{" +
                    "userId='" + userId + '\'' +
                    ", collectionOfIsbns=" + Arrays.toString(collectionOfIsbns) +
                    '}';
        }
    }
}
