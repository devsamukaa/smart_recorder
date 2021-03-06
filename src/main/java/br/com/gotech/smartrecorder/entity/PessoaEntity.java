package br.com.gotech.smartrecorder.entity;

import br.com.gotech.smartrecorder.entity.enum_classes.TipoPessoa;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_pessoa")
public class PessoaEntity implements Serializable{
    @Id
    @Column(name = "cd_pessoa", nullable = false)
    @SequenceGenerator(name = "cd_pessoa", sequenceName = "sq_cd_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cd_pessoa")
    private Long cdPessoa;

    @Column(name = "nm_pessoa", nullable = false, length = 100)
    private String nome;

    @Column(name = "vl_email")
    private String email;

    @Column(name = "vl_cpf", length = 15)
    private String cpf;

    @Column(name = "vl_cnpj", length = 18)
    private String cnpj;

    @OneToOne
    @JoinColumn(name="cd_meta_consumo", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private MetaConsumoEntity metaConsumo;

    @Column(name = "en_pessoa_f_j")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(name = "vl_password")
    private String password;

    @ManyToOne
    @JoinColumn(name="cd_plano")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private PlanoEntity plano;

    public PessoaEntity() {
    }

    public PessoaEntity(Long cdPessoa, String nome, String cpf, String cnpj, MetaConsumoEntity metaConsumo, TipoPessoa tipoPessoa, String email, String password, PlanoEntity plano) {
        this.cdPessoa = cdPessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.metaConsumo = metaConsumo;
        this.tipoPessoa = tipoPessoa;
        this.email = email;
        this.password = password;
        this.plano = plano;
    }

    public PessoaEntity(PessoaEntity pessoaEntity) {
        this.cdPessoa = pessoaEntity.cdPessoa;
        this.nome = pessoaEntity.nome;
        this.cpf = pessoaEntity.cpf;
        this.cnpj = pessoaEntity.cnpj;
        this.metaConsumo = pessoaEntity.getMetaConsumo() != null ? pessoaEntity.getMetaConsumo() : null;
        this.tipoPessoa = pessoaEntity.tipoPessoa;
        this.email = pessoaEntity.email;
        this.password = pessoaEntity.password;
        this.plano = pessoaEntity.plano;
    }

    public Long getCdPessoa() {
        return cdPessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public MetaConsumoEntity getMetaConsumo() {
        return metaConsumo;
    }

    public void setCdPessoa(Long cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public void setNome(String nomePessoa) {
        this.nome = nomePessoa;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setMetaConsumo(MetaConsumoEntity metaConsumo) {
        this.metaConsumo = metaConsumo;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa.equals("PJ") ? TipoPessoa.PJ : TipoPessoa.PF;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PlanoEntity getPlano() {
        return plano;
    }

    public void setPlano(PlanoEntity plano) {
        this.plano = plano;
    }
}
