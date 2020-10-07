package org.survey.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Questions.
 */
@Entity
@Table(name = "questions")
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "option_one")
    private String optionOne;

    @Column(name = "option_two")
    private String optionTwo;

    @Column(name = "option_three")
    private String optionThree;

    @Column(name = "option_four")
    private String optionFour;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "option_five")
    private String optionFive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public Questions question(String question) {
        this.question = question;
        return this;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public Questions optionOne(String optionOne) {
        this.optionOne = optionOne;
        return this;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public Questions optionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
        return this;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public Questions optionThree(String optionThree) {
        this.optionThree = optionThree;
        return this;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public Questions optionFour(String optionFour) {
        this.optionFour = optionFour;
        return this;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public Boolean isActive() {
        return active;
    }

    public Questions active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOptionFive() {
        return optionFive;
    }

    public Questions optionFive(String optionFive) {
        this.optionFive = optionFive;
        return this;
    }

    public void setOptionFive(String optionFive) {
        this.optionFive = optionFive;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Questions)) {
            return false;
        }
        return id != null && id.equals(((Questions) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Questions{" +
            "id=" + getId() +
            ", question='" + getQuestion() + "'" +
            ", optionOne='" + getOptionOne() + "'" +
            ", optionTwo='" + getOptionTwo() + "'" +
            ", optionThree='" + getOptionThree() + "'" +
            ", optionFour='" + getOptionFour() + "'" +
            ", active='" + isActive() + "'" +
            ", optionFive='" + getOptionFive() + "'" +
            "}";
    }
}
