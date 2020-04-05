DROP TABLE IF EXISTS vocabulary;
create table vocabulary
(
    id          bigint auto_increment,
    name        varchar(255)  not null comment '名称',
    explanation varchar(4096) not null comment '解释',
    primary key (id)
);

COMMENT ON TABLE vocabulary IS '词语';
COMMENT ON COLUMN vocabulary.id IS '词语';

