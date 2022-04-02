package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE videogamesberkland (
  id INT NOT NULL AUTO_INCREMENT,    
  gamename VARCHAR(30) NOT NULL,   
  year INT NOT NULL,
  price varchar(12) NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "videogamesberkland")
public class VideoGame {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "gamename")
   private String gamename;

   @Column(name = "year")
   private Integer year;
   
   @Column(name = "price")
   private String price;

   public VideoGame() {
   }

   public VideoGame(Integer id, String name, Integer year, String price) {
      this.id = id;
      this.gamename = name;
      this.year = year;
      this.price = price;
   }

   public VideoGame(String name, int year, String price) {
      this.gamename = name;
      this.year = year;
      this.price = price;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return gamename;
   }
   
   public String getPrice() {
	   return price;
   }

   public void setName(String name) {
      this.gamename = name;
   }
   
   public void setPrice(String price) {
	   this.price = price;
   }

   public Integer getYear() {
      return year;
   }

   public void setYear(Integer year) {
      this.year = year;
   }

   @Override
   public String toString() {
      return "Video Game " + this.id + ": " + this.gamename + ", Year: " + this.year + ", Price: " + this.price;
   }
}