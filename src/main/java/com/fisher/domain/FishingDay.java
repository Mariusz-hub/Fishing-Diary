package com.fisher.domain;




import javax.persistence.*;
@Entity
@Table(name = "fishing_day")
public class FishingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fishName")
    private String fishName;

    @Column(name="fishSize")
    private int fishSize;

    @Lob
    @Column(name = "photo", length = 45, columnDefinition = "MEDIUMBLOB")
    private String photo;

    @Column(name="date")
    public String date;


    public FishingDay() {
    }

    public FishingDay(Long id, String fishName, int fishSize, String photo, String date) {
        this.id = id;
        this.fishName = fishName;
        this.fishSize = fishSize;
        this.photo = photo;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getFishSize() {
        return fishSize;
    }

    public void setFishSize(int fishSize) {
        this.fishSize = fishSize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FishingDay{" +
                "id=" + id +
                ", fishName='" + fishName + '\'' +
                ", fishSize=" + fishSize +
                ", photo='" + photo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
