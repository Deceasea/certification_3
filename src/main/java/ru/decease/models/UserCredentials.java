package ru.decease.models;

import java.util.List;
import java.util.Objects;

public class UserCredentials {
    private String loginName;
    private String secretCode;

    public UserCredentials(String loginName, String secretCode) {
        this.loginName = loginName;
        this.secretCode = secretCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public static class BookInfo {
        private String isbnCode;

        public BookInfo(String isbnCode) {
            this.isbnCode = isbnCode;
        }

        public String getIsbnCode() {
            return isbnCode;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof BookInfo bookInfo)) return false;
            return Objects.equals(getIsbnCode(), bookInfo.getIsbnCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getIsbnCode());
        }

        @Override
        public String toString() {
            return isbnCode;
        }
    }

    public static class BookCollectionRequest {
        private String userId;
        private List<BookInfo> bookCollection;

        public BookCollectionRequest(String userId, List<BookInfo> bookCollection) {
            this.userId = userId;
            this.bookCollection = bookCollection;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<BookInfo> getBookCollection() {
            return bookCollection;
        }

        public void setBookCollection(List<BookInfo> bookCollection) {
            this.bookCollection = bookCollection;
        }
    }
}
