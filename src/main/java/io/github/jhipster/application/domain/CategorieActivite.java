package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CategorieActivite.
 */
@Entity
@Table(name = "categorie_activite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategorieActivite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categorie")
    private String categorie;

    @OneToMany(mappedBy = "categorieActivite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Projet> projets = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public CategorieActivite categorie(String categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Set<Projet> getProjets() {
        return projets;
    }

    public CategorieActivite projets(Set<Projet> projets) {
        this.projets = projets;
        return this;
    }

    public CategorieActivite addProjet(Projet projet) {
        this.projets.add(projet);
        projet.setCategorieActivite(this);
        return this;
    }

    public CategorieActivite removeProjet(Projet projet) {
        this.projets.remove(projet);
        projet.setCategorieActivite(null);
        return this;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategorieActivite categorieActivite = (CategorieActivite) o;
        if (categorieActivite.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categorieActivite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategorieActivite{" +
            "id=" + getId() +
            ", categorie='" + getCategorie() + "'" +
            "}";
    }
}
