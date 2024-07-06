CREATE TABLE veiculo (
    id BIGSERIAL NOT NULL,
    proprietario_id BIGINT NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    placa VARCHAR(7) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_apreensao TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE veiculo
ADD CONSTRAINT fk_veiculo_proprietario FOREIGN KEY (proprietario_id) REFERENCES proprietario(id);
ALTER TABLE veiculo ADD CONSTRAINT uk_veiculo UNIQUE (placa);