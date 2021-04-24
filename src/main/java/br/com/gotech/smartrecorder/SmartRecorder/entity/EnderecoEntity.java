package br.com.gotech.smartrecorder.SmartRecorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tb_endereco")
public class EnderecoEntity implements Serializable {

    @Id
    @Column(name = "cd_endereco", nullable = false)
    @SequenceGenerator(name = "endereco", sequenceName = "sq_endereco", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
    private Long idEndereco;

    @Column(name = "nm_logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "vl_numero", nullable = false, length = 8)
    private Long numero;

    @Column(name = "ds_complemento", nullable = false, length = 100)
    private String complemento;

    @Column(name = "nm_bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "nm_cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "nm_uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "vl_cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "vl_latitude", nullable = false, length = 9)
    private Long latitude;

    @Column(name = "vl_longitude", nullable = false, length = 9)
    private Long longitude;

    @OneToOne(mappedBy = "idInstalacao")
    private InstalacaoEntity idInstalacao;


    public EnderecoEntity() {
    }

    public EnderecoEntity(Long idEndereco, String logradouro, Long numero, String complemento, String bairro, String cidade, String uf, String cep, Long latitude, Long longitude) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getIdEndereco() { return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) { this.idEndereco = idEndereco;
    }

    public String getLogradouro() { return logradouro;
    }

    public void setLogradouro(String logradouro) { this.logradouro = logradouro;
    }

    public Long getNumero() { return numero;
    }

    public void setNumero(Long numero) { this.numero = numero;
    }

    public String getComplemento() { return complemento;
    }

    public void setComplemento(String complemento) { this.complemento = complemento;
    }

    public String getBairro() { return bairro;
    }

    public void setBairro(String bairro) { this.bairro = bairro;
    }

    public String getCidade() { return cidade;
    }

    public void setCidade(String cidade) { this.cidade = cidade;
    }

    public String getUf() { return uf;
    }

    public void setUf(String uf) { this.uf = uf;
    }

    public String getCep() { return cep;
    }

    public void setCep(String cep) { this.cep = cep;
    }

    public Long getLatitude() { return latitude;
    }

    public void setLatitude(Long latitude) { this.latitude = latitude;
    }

    public Long getLongitude() { return longitude;
    }

    public void setLongitude(Long longitude) { this.longitude = longitude;
    }
}
