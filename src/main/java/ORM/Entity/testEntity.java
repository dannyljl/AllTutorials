package ORM.Entity;


import javax.persistence.*;

@Entity
public class testEntity {


    private int id;
    public testEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
