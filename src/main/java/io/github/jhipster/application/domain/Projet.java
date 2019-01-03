package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Projet.
 */
@Entity
@Table(name = "projet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Projet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "statut")
    private String statut;

    @Column(name = "jhi_next")
    private String next;

    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    @JsonIgnoreProperties("projets")
    private CategorieActivite categorieActivite;

    @ManyToOne
    @JsonIgnoreProperties("projets")
    private Client client;

    @OneToMany(mappedBy = "projet")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Historique> historiques = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Projet nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public Projet statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNext() {
        return next;
    }

    public Projet next(String next) {
        this.next = next;
        return this;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Projet commentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public CategorieActivite getCategorieActivite() {
        return categorieActivite;
    }

    public Projet categorieActivite(CategorieActivite categorieActivite) {
        this.categorieActivite = categorieActivite;
        return this;
    }

    public void setCategorieActivite(CategorieActivite categorieActivite) {
        this.categorieActivite = categorieActivite;
    }

    public Client getClient() {
        return client;
    }

    public Projet client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Historique> getHistoriques() {
        return historiques;
    }

    public Projet historiques(Set<Historique> historiques) {
        this.historiques = historiques;
        return this;
    }

    public Projet addHistorique(Historique historique) {
        this.historiques.add(historique);
        historique.setProjet(this);
        return this;
    }

    public Projet removeHistorique(Historique historique) {
        this.historiques.remove(historique);
        historique.setProjet(null);
        return this;
    }

    public void setHistoriques(Set<Historique> historiques) {
        this.historiques = historiques;
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
        Projet projet = (Projet) o;
        if (projet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), projet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Projet{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", statut='" + getStatut() + "'" +
            ", next='" + getNext() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }
}
