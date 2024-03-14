package lk.ijse.dto;

public class BookDto {
    private String BookId;
    private String Title;
    private String Author;
    private String Country;
    private int Quantity;
    private String AvailableStatus;
    private String Genre;
    private String BranchId;

    public BookDto() {
    }

    public BookDto(String bookId, String title, String author, String country, int quantity, String availableStatus, String genre, String branchId) {
        BookId = bookId;
        Title = title;
        Author = author;
        Country = country;
        Quantity = quantity;
        AvailableStatus = availableStatus;
        Genre = genre;
        BranchId = branchId;
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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getAvailableStatus() {
        return AvailableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        AvailableStatus = availableStatus;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }
}
