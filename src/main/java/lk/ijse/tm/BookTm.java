package lk.ijse.tm;

public class BookTm {
    private String BookId;
    private String Title;
    private String Author;
    private String Genre;
    private String AvailableStatus;
    private int Quantity;

    public BookTm() {
    }

    public BookTm(String bookId, String title, String author, String genre, String availableStatus, int quantity) {
        BookId = bookId;
        Title = title;
        Author = author;
        Genre = genre;
        AvailableStatus = availableStatus;
        Quantity = quantity;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getAvailableStatus() {
        return AvailableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        AvailableStatus = availableStatus;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
