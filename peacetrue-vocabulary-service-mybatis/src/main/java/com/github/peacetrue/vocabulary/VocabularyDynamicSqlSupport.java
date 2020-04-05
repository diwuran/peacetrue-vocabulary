package com.github.peacetrue.vocabulary;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;


public final class VocabularyDynamicSqlSupport {

    public static final Vocabulary vocabulary = new Vocabulary();

    public static final SqlColumn<Long> id = vocabulary.id;
    public static final SqlColumn<String> name = vocabulary.name;
    public static final SqlColumn<String> explanation = vocabulary.explanation;

    public static final class Vocabulary extends

            SqlTable {
        public final SqlColumn<Long> id =
                column("id", JDBCType.BIGINT);
        public final SqlColumn<String> name =
                column("name", JDBCType.VARCHAR);
        public final SqlColumn<String> explanation =
                column("explanation", JDBCType.VARCHAR);

        public Vocabulary() {
            super("vocabulary");
        }
    }
}