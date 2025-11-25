CREATE TABLE tb_agendamento(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    horario  TIMESTAMP NOT NULL,

    professor_id BIGINT NOT NULL,
    sala_id BIGINT NOT NULL,

    CONSTRAINT fk_agendamento_professor
        FOREIGN KEY (professor_id)
        REFERENCES tb_professores(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_agendamento_sala
        FOREIGN KEY (sala_id)
        REFERENCES tb_sala(id)
        ON DELETE CASCADE
);