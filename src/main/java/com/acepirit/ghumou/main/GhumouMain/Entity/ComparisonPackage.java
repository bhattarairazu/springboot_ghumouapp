package com.acepirit.ghumou.main.GhumouMain.Entity;

import javax.persistence.*;

@Entity
@Table(name="compare_packages")
public class ComparisonPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name="package_id",referencedColumnName = "package_id")
    private Packagess packagess;

    public ComparisonPackage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Packagess getPackagess() {
        return packagess;
    }

    public void setPackagess(Packagess packagess) {
        this.packagess = packagess;
    }
}
