package org.bitnick.portfolio.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "welcome_texts")
public class WelcomeText {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name="welcome_text")
    private String welcomeText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }
}
