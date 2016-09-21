package br.com.estudo.cadastrolivros.modal.domain;

import br.com.estudo.cadastrolivros.enums.StatusBookEnum;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = -7227105185071353426L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "TITLE", length = 100, unique = true)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AUTHOR", length = 100)
    private String author;

    @Column(name = "TOTAL_PAGE")
    private Integer totalPage;

    @Column(name = "ISBN", length = 45)
    private String isbn;

    @Column(name = "STATUS", length = 45)
    @Enumerated(EnumType.STRING)
    private StatusBookEnum status;

    @Column(name = "YEAR_PUBLISHED", length = 45)
    private String yearPublished;

    @Column(name = "COVER_URL")
    private String coverUrl;

    public Book() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public StatusBookEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusBookEnum status) {
        this.status = status;
    }


    public String getYearPublished() {
        return this.yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }


    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result
                + ((coverUrl == null) ? 0 : coverUrl.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result
                + ((totalPage == null) ? 0 : totalPage.hashCode());
        result = prime * result
                + ((yearPublished == null) ? 0 : yearPublished.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Book))
            return false;
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (coverUrl == null) {
            if (other.coverUrl != null)
                return false;
        } else if (!coverUrl.equals(other.coverUrl))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (totalPage == null) {
            if (other.totalPage != null)
                return false;
        } else if (!totalPage.equals(other.totalPage))
            return false;
        if (yearPublished == null) {
            if (other.yearPublished != null)
                return false;
        } else if (!yearPublished.equals(other.yearPublished))
            return false;
        return true;
    }

}
