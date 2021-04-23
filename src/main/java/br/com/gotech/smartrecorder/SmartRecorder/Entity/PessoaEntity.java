package br.com.gotech.smartrecorder.SmartRecorder.Entity;

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
    private String nomePessoa;

    @Column(name = "nr_cpf", length = 14)
    private Long cpf;

    @Column(name = "nr_cnpj", length = 18)
    private  Long cnpj;

    @OneToOne(mappedBy = "pessoa")
    private MetaConsumoEntity metaConsumo;

    @Column(name = "en_pessoa_f_j")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;


    public PessoaEntity() {
    }

    public Long getCdPessoa() {
        return cdPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public Long getCpf() {
        return cpf;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public MetaConsumoEntity getMetaConsumo() {
        return metaConsumo;
    }

    public void setCdPessoa(Long cdPessoa) {
        this.cdPessoa = cdPessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public void setMetaConsumo(MetaConsumoEntity metaConsumo) {
        this.metaConsumo = metaConsumo;
    }
}
