package com.polo.blog.model;
// Generated 14/04/2018 22:35:48 by Hibernate Tools 4.3.1

import com.polo.blog.model.data.Model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Post generated by hbm2java
 */
@Entity
@Table(name="post",catalog="blog")
public class Post  extends Model {

    private String titulo;
    private String conteudo;
    private LocalDate dataPub;
    private List<Arquivo> arquivos = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public Post() {
        super();
    }

    @Column(name="titulo", nullable=false)
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    @Column(name="conteudo", nullable=false, length=65535)
    public String getConteudo() {
        return this.conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    
    @Column(name="data_pub", nullable=false, length=19)
    public LocalDate getDataPub() {
        return this.dataPub;
    }
    
    public void setDataPub(LocalDate dataPub) {
        this.dataPub = dataPub;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="post")
    public List<Arquivo> getArquivos() {
        return this.arquivos;
    }
    
    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="post_tag", catalog="blog", joinColumns = { 
    @JoinColumn(name="post_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
    @JoinColumn(name="tag_id", nullable=false, updatable=false) })
    public List<Tag> getTags() {
        return this.tags;
    }
    
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
    public static List<Post> getAll(){
        return Model.getAll(Post.class);
    }
   
    public static List<Post> find(String texto){
        return Model.find(Post.class, 
                "titulo like :texto or conteudo like :texto",
                String.format("%c%s%c", '%',texto,'%'));
    }
    
}


